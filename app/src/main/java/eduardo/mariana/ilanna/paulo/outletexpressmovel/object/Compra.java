package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Compra {
    ArrayList<Produto> produtos;
    //int formaPagamento;
    //String cep;
    //String complemento;
    //float frete;
    float total;

    public Compra(ArrayList<Produto> produtos, float total) {
        this.produtos = produtos;
        this.total = total;
    }

    //metodo comprar() com parametro de ArrayList, serve para "comprar agora" ou "comprar tudo" do carrinhp

    //metodo calcularFrete()

    //metodo calcularTotal(), igual ao metodo do Carrinho

    //metodo finalizarCompra()

}
