package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import static java.lang.Integer.parseInt;

import android.util.SparseIntArray;
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
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.PesquisaFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCarrinho;

public class CarrinhoAdapter extends RecyclerView.Adapter {

    public HomeActivity homeActivity;
    public List<ItemCarrinho> itensCarrinho;

    public CarrinhoAdapter(HomeActivity homeActivity, List<ItemCarrinho> itensCarrinho) {
        this.homeActivity = homeActivity;
        this.itensCarrinho = itensCarrinho;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_horizontal, parent, false);
        return new CarrinhoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemCarrinho itemCarrinho = itensCarrinho.get(position);
        View v = holder.itemView;

        ImageView imvCarrinho = v.findViewById(R.id.imvCarrinho);

        //corrigir imagem no onBindView
        //imvCarrinho.setImageURI(itemCarrinho.produto.imagem);

        TextView tvItemCarNome = v.findViewById(R.id.tvItemCarNome);
        tvItemCarNome.setText(itemCarrinho.produto.nome_produto);

        TextView tvItemCarPreco = v.findViewById(R.id.tvItemCarPreco);
        tvItemCarPreco.setText(itemCarrinho.produto.valor_atual);

        TextView tvItemCarQtd = v.findViewById(R.id.tvItemCarQtd);
        tvItemCarQtd.setText(itemCarrinho.quantidade);

       /*
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PesquisaFragment pesquisaFragment = PesquisaFragment.newInstance(categoria.descricao, "");
                homeActivity.setFragment(pesquisaFragment, R.id.flOfertas);
            }

        });
        */
    }

    public int getItemCount() {
        return itensCarrinho.size();
    }
}
