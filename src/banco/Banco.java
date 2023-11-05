package banco;

import java.util.ArrayList;
import java.util.List;
import conta.Conta;
import pessoa.Pessoa;

public abstract class Banco implements IOperacoesBancarias {
    // atributo para guardar a lista de clientes
    protected List<Pessoa> clientes;

    // construtor que inicializa a lista de clientes
    public Banco() {
        this.clientes = new ArrayList<>();
    }

    // método abstrato para abrir uma conta
    // public abstract void abrirConta(Pessoa pessoa);
    public abstract void abrirConta(Pessoa pessoa);

    // método abstrato para encerrar uma conta
    public abstract boolean encerrarConta(Pessoa pessoa);

    public boolean enviarPix(Pessoa origem, Pessoa destino, float valor)
            throws IllegalArgumentException {

        Conta contaOrigem = origem.getConta();
        Conta contaDestino = destino.getConta();

        // verifica se as contas existem
        if (contaOrigem != null && contaDestino != null) {
            // verifica a existencia de saldo na conta
            if (contaOrigem.getSaldo() >= valor) {
                // deduz o valor da conta de origem
                contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);

                // soma o valor na conta de origem
                contaDestino.setSaldo(contaDestino.getSaldo() + valor);

                return true;
            } else {
            	return false;
            }
        } else {
            return false;
        }
    }

    public List<Pessoa> obterClientes() {
        return clientes;
    }

    public float obterSaldo(Pessoa pessoa) {
        Conta conta = pessoa.getConta();

        // lógica para checar a existencia da conta
        if (conta != null) {
            return conta.getSaldo();
        } else {
            throw new IllegalArgumentException("A pessoa não possui conta no banco");
        }
    }

    public void depositar(Conta conta, float valor) {

        // verifica se a conta existe, e se o valor de depósito é maior que zero
        if (conta != null && valor > 0) {

            // incrementa no saldo o valor do depósito
            float novoSaldo = conta.getSaldo() + valor;

            // atribui ao saldo o novo saldo
            conta.setSaldo(novoSaldo);
        } else {
            throw new IllegalArgumentException("Valor do déposito incorreto ou essa conta não existe.");
        }
    }

    public boolean sacar(Conta conta, int valor) {

        // verifica se a conta existe, se o saldo condiz com o valor e se o valor de
        // saque é maior que zero
        if (conta != null && valor > 0 && conta.getSaldo() >= valor) {

            // decrementa do saldo o valor de saque
            float novoSaldo = conta.getSaldo() - valor;

            // atribui ao saldo o novo saldo
            conta.setSaldo(novoSaldo);
            return true;
        } else {
            return false;
        }
    }
}