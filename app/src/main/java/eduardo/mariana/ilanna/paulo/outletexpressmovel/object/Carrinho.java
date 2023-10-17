package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

import java.util.ArrayList;

public class Carrinho {
    ArrayList<ItemCarrinho> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    //criar o metodo addCarrinho()
    public void addCarrinho(ItemCarrinho item){
        this.itens.add(item);
    }

    //criar metodo removeCarrinho()
    public void removeCarrinho(/*passar o index do item ou o Objeto do item?*/){
        //implementar metodo quando tiver decidido o parametro
    }

    //criar metodo calcularTotal() para o preco dos produtos do carrinho


    //criar metodo comprarTudo()
}
