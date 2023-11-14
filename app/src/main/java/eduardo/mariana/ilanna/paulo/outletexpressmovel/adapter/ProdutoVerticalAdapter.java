package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
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


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
