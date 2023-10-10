package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Produto {
    String imagem;
    String nome_produto;
    float preco;
    float avaliacao;
    String link_empresa;
    //criar item_list dos comentarios

    public Produto(String imagem, String nome_produto, float preco, float avaliacao, String link_empresa, int quantidade) {
        this.imagem = imagem;
        this.nome_produto = nome_produto;
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.link_empresa = link_empresa;
    }


}
