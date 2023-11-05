package banco;

import java.util.List;
import conta.Conta;
import pessoa.Pessoa;

public interface IOperacoesBancarias {
    // método para obter lista de clientes
    public List<Pessoa> obterClientes();

    // método para enviar PIX entre duas pessoas com um valor determinado
    public boolean enviarPix(Pessoa origem, Pessoa destino, float valor);

    // méto que obtém saldo de uma pessoa
    public float obterSaldo(Pessoa pessoa);

    // método para sacar de uma conta
    public boolean sacar(Conta conta, int valor);

}
