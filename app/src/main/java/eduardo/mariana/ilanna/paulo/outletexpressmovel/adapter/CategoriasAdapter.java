package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.content.Intent;
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
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.LoginActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.CarrinhoFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.HomeFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.PesquisaFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;

public class CategoriasAdapter extends RecyclerView.Adapter {
    public HomeActivity homeActivity;
    public List<Categoria> categorias;

    public CategoriasAdapter(HomeActivity homeActivity, List<Categoria> categorias) {
        this.homeActivity = homeActivity;
        this.categorias = categorias;
    }

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

        //preenche a imagem da categoria
        ImageView imvCategoria = v.findViewById(R.id.imvCategoria);
        imvCategoria.setImageResource(categoria.imagem);

        //preenche o nome da categoria
        TextView tvDescricao = v.findViewById(R.id.tvDescricao);
        tvDescricao.setText(categoria.descricao);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PesquisaFragment pesquisaFragment = PesquisaFragment.newInstance(categoria.descricao,"");
                homeActivity.setFragment(pesquisaFragment, R.id.flOfertas);


            }
        });

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}
