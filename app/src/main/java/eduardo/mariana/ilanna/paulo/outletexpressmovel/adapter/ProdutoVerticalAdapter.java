package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.OfertasFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class ProdutoVerticalAdapter extends RecyclerView.Adapter {

    HomeActivity homeActivity;
    OfertasFragment ofertasFragment;
    List<Produto> produtos;

    public ProdutoVerticalAdapter(HomeActivity homeActivity, OfertasFragment ofertasFragment, List<Produto> produtos) {
        this.homeActivity = homeActivity;
        this.ofertasFragment = ofertasFragment;
        this.produtos = produtos;
    }


}
