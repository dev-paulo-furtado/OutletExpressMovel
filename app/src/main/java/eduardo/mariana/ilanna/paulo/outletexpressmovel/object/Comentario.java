package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

public class Comentario {
    public String nome_cliente;
    public float avaliacao;
    public String comentario;
    public String data;

    public Comentario(String nome_cliente, float avaliacao, String comentario, String data) {
        this.nome_cliente = nome_cliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.data = data;
    }

    public Comentario() {
    }
}
