package pessoa;

import conta.Conta;

public class Pessoa {
    private String nome;
    private String cpf;
    private int tipoPessoa;
    private Conta conta;

    public Pessoa(String nome, String cpf, int tipoPessoa) throws Exception {
        if (tipoPessoa != 1 && tipoPessoa != 2) {
            throw new Exception("Tipo Pessoa deve ser 1 ou 2");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.tipoPessoa = tipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public String obterTipoPessoa() {
        if (tipoPessoa == 1) {
            return "PF";
        } else if (tipoPessoa == 2) {
            return "PJ";
        } else {
            return "Apenas 1 ou 2 são tipo de pessoa válidos";
        }
    }

    public void listarInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Tipo de Pessoa: " + obterTipoPessoa());
        if (conta != null) {
            System.out.println("Número da conta: " + conta.getNumeroConta());
            System.out.println("Saldo da conta: " + conta.getSaldo());
            System.out.println("Tipo da conta: " + conta.obterTipoConta());
        } else {
            System.out.println("Não há uma conta");
        }

    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public int getTipoPessoa() {
        return tipoPessoa;
    }

}
