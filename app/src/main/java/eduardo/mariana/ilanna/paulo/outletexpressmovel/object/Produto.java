package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Produto {
    public int codigo;
    public String imagem;
    public String nome_produto;
    public String valor_atual;
    public float avaliacao;
    public float desconto;
    public String descricao;
    public String link_empresa;
    public String nome_empresa;

    public Produto(int codigo, String imagem, String nome_produto, String valor_atual, float avaliacao, float desconto, String link_empresa, String nome_empresa) {
        this.codigo = codigo;
        this.imagem = imagem;
        this.nome_produto = nome_produto;
        this.valor_atual = valor_atual;
        this.avaliacao = avaliacao;
        this.desconto = desconto;
        this.link_empresa = link_empresa;
        this.nome_empresa = nome_empresa;
    }

    public Produto() {
    }

}
