package eduardo.mariana.ilanna.paulo.outletexpressmovel.compra;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Compra {
    ArrayList<String> produtos = new ArrayList<>();
    int formaPagamento;
    String cep;
    String complemento;
    Float frete;
    Float total;

    public Compra(ArrayList<String> produtos, int formaPagamento, String cep, String complemento, Float frete, Float total) {
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.cep = cep;
        this.complemento = complemento;
        this.frete = frete;
        this.total = total;
    }

}
