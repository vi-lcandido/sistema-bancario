package conta;

public class ContaPJ extends Conta {
    public ContaPJ() {
        super(2);
    }

    // implementa o método obterTipoConta da classe mãe

    @Override
    public String obterTipoConta() {
        return "PJ";
    }

    @Override
    public void depositar(float valor) {
        // chama o método depositar da classe base
        if (valor > 0) {
            super.depositar(valor);
        }
    }

    @Override
    public void sacar(float valor) {
        // chama o método sacar da classe base
        if (valor > 0 && getSaldo() >= valor) {
            super.sacar(valor);
        }
    }
}
