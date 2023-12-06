package eduardo.mariana.ilanna.paulo.outletexpressmovel.object;

public class ItemCompra {
    public int quantidade;
    public String nome_produto;
    public float valor_item;

    public ItemCompra(int quantidade, String nome_produto, float valor_item) {
        this.quantidade = quantidade;
        this.nome_produto = nome_produto;
        this.valor_item = valor_item;
    }

    public ItemCompra() {
    }

}
