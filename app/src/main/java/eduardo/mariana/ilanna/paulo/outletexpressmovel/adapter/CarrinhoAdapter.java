package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import static java.lang.Integer.parseInt;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.PesquisaFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCarrinho;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.ImageCache;

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
        View v = inflater.inflate(R.layout.itemlist_carrinho, parent, false);
        return new CarrinhoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemCarrinho itemCarrinho = itensCarrinho.get(position);
        View v = holder.itemView;


        //preenche a imagem do produto
        // preenche o campo de foto
        int w = (int) homeActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) homeActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imvCarrinho = holder.itemView.findViewById(R.id.imvCarrinho);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(homeActivity, itemCarrinho.produto.imagem, imvCarrinho, w, h);

        TextView tvItemCarNome = v.findViewById(R.id.tvItemCarNome);
        tvItemCarNome.setText(itemCarrinho.produto.nome_produto);

        TextView tvItemCarPreco = v.findViewById(R.id.tvItemCarPreco);
        tvItemCarPreco.setText("R$ " + itemCarrinho.produto.valor_atual);

        TextView tvItemCarQtd = v.findViewById(R.id.tvItemCarQtd);
        tvItemCarQtd.setText(Integer.toString(itemCarrinho.quantidade));

        Button btnAddQtdCarrinho = v.findViewById(R.id.btnAddQtdCarrinho);
        btnAddQtdCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println(qtd.getText().toString());
                //System.out.println(Integer.parseInt(qtd.getText().toString()));
                //System.out.println(Integer.parseInt(qtd.getText().toString()) + 1);
                int nova_qtd = Integer.parseInt(tvItemCarQtd.getText().toString()) + 1;
                if(nova_qtd > 20){
                    nova_qtd = 20;
                }
                //System.out.println(nova_qtd);
                tvItemCarQtd.setText(String.valueOf(nova_qtd));
            }
        });

        Button btnLowQtdCarrinho = v.findViewById(R.id.btnLowQtdCarrinho);
        btnLowQtdCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println(qtd.getText().toString());
                //System.out.println(Integer.parseInt(qtd.getText().toString()));
                //System.out.println(Integer.parseInt(qtd.getText().toString()) - 1);
                int nova_qtd = Integer.parseInt(tvItemCarQtd.getText().toString()) - 1;
                if(nova_qtd < 1){
                    nova_qtd = 1;
                }
                tvItemCarQtd.setText(String.valueOf(nova_qtd));
            }
        });
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
