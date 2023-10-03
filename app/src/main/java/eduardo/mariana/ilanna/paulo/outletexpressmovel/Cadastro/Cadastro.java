package eduardo.mariana.ilanna.paulo.outletexpressmovel.Cadastro;

public class Cadastro {
    String email;
    String nome;
    String senha;
    String senha_confirm;

    public Cadastro(String email, String nome, String senha, String senha_confirm) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.senha_confirm = senha_confirm;
    }
}
