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

public class ItemCompraAdapter extends RecyclerView.Adapter{
    HomeActivity homeActivity;
    public List<ItemCompra> itemCompraList;

    public ItemCompraAdapter(HomeActivity homeActivity, List<ItemCompra> itemCompraList) {
        this.homeActivity = homeActivity;
        this.itemCompraList = itemCompraList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_item_compra,parent,false);
        System.out.println("ItemCompraAdapter onCreateViewHolder !!!");
        return new PesquisaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("ItemCompraAdapter onBindViewHolder !!!");
        ItemCompra itemCompra = itemCompraList.get(position);
        View v = holder.itemView;

        //RecyclerView rvCompras = v.findViewById(R.id.rvCompras);
        //rvCompras.setLayoutManager(new LinearLayoutManager(v.getContext()));

        //ItemCompraAdapter itemCompraAdapter = new ItemCompraAdapter(this.compras);
        //rvCompras.setAdapter(itemCompraAdapter);

        TextView tvItemCompraNomeProduto = v.findViewById(R.id.tvItemCompraNomeProduto);
        tvItemCompraNomeProduto.setText(itemCompra.nome_produto);

        TextView tvItemCompraQuantidade = v.findViewById(R.id.tvItemCompraQuantidade);
        tvItemCompraQuantidade.setText("x " + itemCompra.quantidade);

    }

    @Override
    public int getItemCount() {
        return itemCompraList.size();
    }
}
