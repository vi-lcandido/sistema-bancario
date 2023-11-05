package banco;

import java.util.Random;

import conta.*;
import pessoa.Pessoa;

public class Itau extends Banco {

    @Override
    public void abrirConta(Pessoa pessoa) {
        if (pessoa != null) {
            Conta novaConta = null;

            if (pessoa.getTipoPessoa() == 1) {
                novaConta = new ContaPF();
            } else if (pessoa.getTipoPessoa() == 2) {
                novaConta = new ContaPJ();
            } else {
                System.out.println("Apenas tipo 1 ou 2 são permitidos para abrir uma conta no Itaú");
                return;
            }

            if (novaConta != null) {

                // gera numero aleatorio da conta
                String numeroConta = "Itau-" + criarNumeroAleatorio();
                novaConta.setNumeroConta(numeroConta);

                // adiciona a pessoa a lista de clientes
                clientes.add(pessoa);

                pessoa.setConta(novaConta);

                System.out.println("Conta criada, segue o número dela: " + numeroConta);

            }

        } else {
            System.out.println("Tipo de pessoa inválido");
        }

    }

    private int criarNumeroAleatorio() {
        // cria um número aleatório
        Random random = new Random();
        return 100 + random.nextInt(900);

    }

    @Override
    public boolean encerrarConta(Pessoa pessoa) {
        Conta conta = pessoa.getConta();
        String[] tipoConta = conta.getNumeroConta().split("-");

        if (pessoa != null && conta != null && tipoConta[0].equals("Itau")) {
            // remove a pessoa da lista de clientes
            clientes.remove(pessoa);

            // define como nula a conta da pessoa
            pessoa.setConta(null);
            System.out.println("Conta encerrada com sucesso no Itau");
            return true;
        } else {
            System.out.println("Não existe essa conta no Itau para encerrar");
            return false;
        }
    }

}
