package conta;

public class ContaPF extends Conta {
    public ContaPF() {
        super(1);
    }

    // implementa o método obterTipoConta da classe mãe
    @Override
    public String obterTipoConta() {
        return "PF";
    };

    @Override
    public void depositar(float valor) {
        // Chama o método depositar da classe base
        if (valor > 0) {
            super.depositar(valor);
        }
    }

    @Override
    public void sacar(float valor) {
        // Chama o método sacar da classe base
        if (valor > 0 && getSaldo() >= valor) {
            super.sacar(valor);
        }
    }

}
