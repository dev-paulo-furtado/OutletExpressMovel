package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Produto {
    public int id;
    public String imagem;
    public String nome_produto;
    public float valor_atual;
    public float avaliacao;
    public float desconto;
    public String link_empresa;
    public ArrayList<Comentario> comentarios;

    public Produto(int id, String imagem, String nome_produto, float valor_atual, float avaliacao, float desconto, String link_empresa, ArrayList<Comentario> comentarios) {
        this.id = id;
        this.imagem = imagem;
        this.nome_produto = nome_produto;
        this.valor_atual = valor_atual;
        this.avaliacao = avaliacao;
        this.desconto = desconto;
        this.link_empresa = link_empresa;
        this.comentarios = comentarios;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }
}
