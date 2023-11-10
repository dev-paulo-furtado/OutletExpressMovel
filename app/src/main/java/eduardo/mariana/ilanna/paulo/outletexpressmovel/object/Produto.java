package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Produto {
    public int id;
    public String imagem;
    public String nome_produto;
    public float preco;
    public float avaliacao;
    public String link_empresa;
    public ArrayList<Comentario> comentarios;


    public Produto(int id, String imagem, String nome_produto, float preco, float avaliacao, String link_empresa, ArrayList<Comentario> comentarios) {
        this.id = id;
        this.imagem = imagem;
        this.nome_produto = nome_produto;
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.link_empresa = link_empresa;
        this.comentarios = comentarios;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }
}
