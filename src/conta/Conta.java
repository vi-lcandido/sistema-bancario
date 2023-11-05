package conta;

public abstract class Conta {
    // atributos
    protected String numeroConta;
    protected float saldo;
    protected int tipoConta;

    public Conta(int tipoConta) {
        this.saldo = 0; // inicializa o saldo em zero
        this.tipoConta = tipoConta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float novoSaldo) {
        saldo = novoSaldo;
    }

    public abstract String obterTipoConta();

    public void depositar(float valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
        }
    }

    public void sacar(float valor) {
        if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
        }
    }

    // designa o número da conta na novaConta
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

}
