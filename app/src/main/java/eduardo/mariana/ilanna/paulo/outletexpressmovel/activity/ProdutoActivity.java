package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.ProdutoViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class ProdutoActivity extends AppCompatActivity {

    int codigo_produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }

    public ProdutoActivity(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //HomeActivity homeActivity = (HomeActivity) getActivity();

        ProdutoViewModel mViewModel = new ViewModelProvider().get(ProdutoViewModel.class);

        //codigo pegando detalhes_produto do bd
        Produto produto = ;

        ImageView imvProduto = findViewById(R.id.imvDetalheProduto);
        imvProduto.setImageURI(produto.imagem);

        TextView tvNomeProduto = findViewById(R.id.tvDetalheNomeProduto);
        tvNomeProduto.setText(produto.nome_produto);

        TextView tvValorAtual = findViewById(R.id.tvDetalheValorAtual);
        tvValorAtual.setText("R$ " + produto.valor_atual);

        TextView tvDetalheDesconto = findViewById(produto.desconto);
        tvDetalheDesconto.setText(produto.desconto + "%");

        RatingBar ratingBar = findViewById(R.id.rbDetalheAvaliacao);
        ratingBar.setRating(produto.avaliacao);

        //carregar comentarios do BD

    }

    //quando clicar em "comprar agora" fazer uma funcao que envia o id do produto e a quantidade
}