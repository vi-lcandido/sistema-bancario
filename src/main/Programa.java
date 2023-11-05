package main;

import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import banco.IOperacoesBancarias;
import banco.Itau;
import banco.NuBank;
import conta.ContaPF;
import conta.ContaPJ;
import pessoa.Pessoa;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		Pessoa alice = new Pessoa("Alice", "1234", 1);
		assertNull("Conta da Alice deve ser nula", alice.getConta());
		
		Pessoa lenio = new Pessoa("Lenio", "5678", 2);
		assertNull("Conta do Lenio deve ser nula", lenio.getConta());
		
		Pessoa mara = new Pessoa("Mara", "9999", 2);
		assertNull("Conta da Mara deve ser nula", mara.getConta());
		
		try {
			assertThrows(Exception.class, () -> new Pessoa("Erro", "1111", 3));
			new Pessoa("Erro", "1111", 3);
		} catch (Exception e) {
			String msgErroEsperada = "Tipo Pessoa deve ser 1 ou 2";
			String msgErroObtida = e.getMessage();
			assertEquals("Mensagem de validacao do tipo de pessoa incorreta",msgErroEsperada, msgErroObtida);
			System.out.println(String.format("Erro: %s\n", e.getMessage()));
		}
		
		Itau itau = new Itau();
		assertFalse("O saque nao deve ser concluido pois a Alice nao tem conta", itau.sacar(alice.getConta(), 100));
		
		itau.abrirConta(alice);
		alice.listarInformacoes();
		assertEquals("Tipo conta deve ser PF", alice.getConta().obterTipoConta(), "PF");		
		assertInstanceOf(ContaPF.class, alice.getConta());
		
		itau.depositar(alice.getConta(), 1000);	
		
		assertEquals("Erro Saldo Alice apos deposito de 1000", alice.getConta().getSaldo(), 1000, 0.0);
		
		assertFalse("O envio nao deve ser concluido pois o Lenio nao tem conta", itau.enviarPix(alice, lenio, 100));		
		assertNull("Lenio nao tem conta", lenio.getConta());		
		
		NuBank nubank = new NuBank();
		nubank.abrirConta(lenio);
		lenio.listarInformacoes();
		assertEquals("Tipo conta deve ser PJ", lenio.getConta().obterTipoConta(), "PJ");
		assertInstanceOf(ContaPJ.class, lenio.getConta());
		
		nubank.abrirConta(mara);
		
		assertEquals("Tipo conta deve ser PJ", mara.getConta().obterTipoConta(), "PJ");
		assertInstanceOf(ContaPJ.class, mara.getConta());
		
		if(itau.enviarPix(lenio, alice, 100)) {
			
			String msg = String.format("Pix enviado com sucesso de %s para %s", 
					lenio.getNome(), alice.getNome());
			System.out.println(msg);
			
		} else {
			String msg = String.format("Nao foi possivel enviar o Pix de %s para %s",
					lenio.getNome(), alice.getNome());
			System.out.println(msg);
		}		
		
		itau.sacar(alice.getConta(), 100);
		
		assertEquals("Erro Saldo Alice apos saque de 100", alice.getConta().getSaldo(), 900, 0.0);
		
		assertTrue("O envio do Pix deveria ter sido feito", itau.enviarPix(alice, lenio, 50));
		assertEquals("Erro Saldo Alice apos envio pix de 50", alice.getConta().getSaldo(), 850, 0.0);
		assertEquals("Erro Saldo Lenio apos receber pix de 50", lenio.getConta().getSaldo(), 50, 0.0);
		
		assertFalse("Nao e possivel sacar pois o saldo nao e suficiente", nubank.sacar(lenio.getConta(), 100));
		assertTrue("O saque deveria ter sido efetuado pois ha saldo suficiente", nubank.sacar(lenio.getConta(), 20));
		assertEquals("Erro Saldo Lenio apos saque de 20", lenio.getConta().getSaldo(), 30, 0.0);
		
		lenio.listarInformacoes();
		alice.listarInformacoes();
		
		assertEquals("Numero clientes incorreto para o Itau", itau.obterClientes().size(), 1);
		assertEquals("Numero clientes incorreto para o Nubank", nubank.obterClientes().size(), 2);
		assertFalse("Conta nao pode ser encerrada pois Mara nao e cliente deste banco", itau.encerrarConta(mara));
		assertTrue("Conta deveria ter sido encerrada", nubank.encerrarConta(mara));
		assertEquals("Numero clientes incorreto para o Nubank apos encerramento de conta", nubank.obterClientes().size(), 1);
		
		Pessoa roger = new Pessoa("Roger", "7777", 1);
		itau.abrirConta(roger);
		assertEquals("Novo Numero clientes incorreto para o Itau apos abertura de conta", itau.obterClientes().size(), 2);
		
		assertEquals("Erro Saldo Lenio", lenio.getConta().getSaldo(), 30, 0.0);
		assertEquals("Erro Saldo Alice", alice.getConta().getSaldo(), 850, 0.0);
		
		Programa programa = new Programa();
		
		programa.exibirSaldo(itau);
		programa.exibirSaldo(nubank);
		
	}

	public void exibirSaldo(IOperacoesBancarias banco) {
		List<Pessoa> clientes = banco.obterClientes();
		for (Pessoa cliente : clientes) {
			float saldo = banco.obterSaldo(cliente);
			String msg = String.format("Saldo da pessoa %s: %.2f", 
					cliente.getNome(), saldo);
			System.out.println(msg);
		}
	}
}
