package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Compra {
    ArrayList<String> produtos;
    int formaPagamento;
    String cep;
    String complemento;
    float frete;
    float total;

    public Compra(ArrayList<String> produtos, int formaPagamento, String cep, String complemento, Float frete, Float total) {
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.cep = cep;
        this.complemento = complemento;
        this.frete = frete;
        this.total = total;
    }

    //metodo comprar() com parametro de ArrayList, serve para "comprar agora" ou "comprar tudo" do carrinhp

    //metodo calcularFrete()

    //metodo calcularTotal(), igual ao metodo do Carrinho

    //metodo finalizarCompra()

}
