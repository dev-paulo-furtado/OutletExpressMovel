package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.HomeFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;

public class CategoriasAdapter extends RecyclerView.Adapter {
    HomeActivity homeActivity;
    List<Categoria> categorias;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_categoria,parent,false);
        return new CategoriasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        View v = holder.itemView;
        ImageView imvCategoria = v.findViewById(R.id.imvCategoria);
        imvCategoria.setImageDrawable();

        TextView tvDescricao = v.findViewById(R.id.tvDescricao);
        tvDescricao.setText(categoria.descricao);


    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}
