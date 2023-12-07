package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.ProdutoActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.ImageCache;

public class OfertasAdapter extends RecyclerView.Adapter{
    HomeActivity homeActivity;
    public List<Produto> produtos;

    public OfertasAdapter(HomeActivity homeActivity, List<Produto> produtos) {
        this.homeActivity = homeActivity;
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(homeActivity);
        View v = inflater.inflate(R.layout.itemlist_vertical,parent,false);
        return new PesquisaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //parei aqui na ultima aula de mobile
        //devo copiar o que fiz na CategoriasAdapter
        //pra poder preencher a lista de produtos, quando alguem pesquisar uma categoria ou quando pesquisarem um produto
        Produto produto = produtos.get(position);
        View v = holder.itemView;


        //preenche a imagem do produto
        // preenche o campo de foto
        int w = (int) homeActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) homeActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imvProductThumb = holder.itemView.findViewById(R.id.imvProduto);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(homeActivity, produto.imagem, imvProductThumb, w, h);

        //preenche o nome do produto
        TextView tvNomeProduto = v.findViewById(R.id.tvNomeProduto);
        String nome = "";
        /*if(produto.nome_produto.length() >= 25){
            nome = produto.nome_produto.substring(22) + "...";
        }
        else{
            nome = produto.nome_produto;
        }*/
        tvNomeProduto.setText(produto.nome_produto);

        TextView tvValorProduto = v.findViewById(R.id.tvValorProduto);
        String valor = String.format("%.2f", Float.parseFloat(produto.valor_atual));
        tvValorProduto.setText("R$ " + valor);

        /*
        RatingBar rbAvaliacaoProduto = v.findViewById(R.id.rbAvaliacaoProduto);
        rbAvaliacaoProduto.setRating(produto.avaliacao);

        TextView tvDesconto = v.findViewById(R.id.tvDesconto);
        float porcentagem = Float.parseFloat(produto.valor_atual) + produto.desconto;
        porcentagem = produto.desconto / porcentagem * 100;
        porcentagem = Math.round(porcentagem);
        tvDesconto.setText(String.valueOf(porcentagem) + "%");
        */

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeActivity, ProdutoActivity.class);
                i.putExtra("codigo_produto",produto.codigo);
                homeActivity.startActivity(i);
                homeActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
