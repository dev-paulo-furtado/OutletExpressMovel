package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

public class Comentario {
    String nome_cliente;
    float avaliacao;
    String comentario;
    String data;

    public Comentario(String nome_cliente, float avaliacao, String comentario, String data) {
        this.nome_cliente = nome_cliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.data = data;
    }
}
