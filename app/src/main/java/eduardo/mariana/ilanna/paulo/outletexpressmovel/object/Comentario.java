package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

public class Comentario {
    public String nome_cliente;
    public float avaliacao;
    public String conteudo;
    public String data;

    public Comentario(String nome_cliente, float avaliacao, String conteudo, String data) {
        this.nome_cliente = nome_cliente;
        this.avaliacao = avaliacao;
        this.conteudo = conteudo;
        this.data = data;
    }

    public Comentario() {
    }
}
