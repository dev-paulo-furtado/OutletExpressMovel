package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCompra;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class ComprasAdapter extends RecyclerView.Adapter{
    HomeActivity homeActivity;
    List<ItemCompra> compras;

    public ComprasAdapter(HomeActivity homeActivity, List<ItemCompra> compras) {
        this.homeActivity = homeActivity;
        this.compras = compras;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_compra,parent,false);
        System.out.println("ComprasAdapter onCreateViewHolder !!!");
        return new PesquisaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //ItemCompra itemCompra = compras.get(position);
        View v = holder.itemView;

        RecyclerView rvCompras = v.findViewById(R.id.rvCompras);
        rvCompras.setLayoutManager(new LinearLayoutManager(v.getContext()));

        ItemCompraAdapter itemCompraAdapter = new ItemCompraAdapter(this.homeActivity, this.compras);
        rvCompras.setAdapter(itemCompraAdapter);

        float total = 0;
        for(int i = 0; i < compras.size(); i++){
            ItemCompra ic = compras.get(i);;
            total += ic.valor_item * ic.quantidade;
        }

        TextView tvComprasTotal = v.findViewById(R.id.tvComprasTotal);
        String valor = String.format("%.2f", total);
        tvComprasTotal.setText("R$ " + valor);

    }

    @Override
    public int getItemCount() {
        return compras.size();
    }
}
