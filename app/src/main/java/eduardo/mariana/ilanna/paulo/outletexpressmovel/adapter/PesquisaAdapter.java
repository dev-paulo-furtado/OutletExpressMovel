package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class PesquisaAdapter extends RecyclerView.Adapter {
    public HomeActivity homeActivity;
    public List<Produto> produtos;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_horizontal,parent,false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        adsasd
        //parei aqui na ultima aula de mobile
        //devo copiar o que fiz na CategoriasAdapter
        //pra poder preencher a lista de produtos, quando alguem pesquisar uma categoria ou quando pesquisarem um produto
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
