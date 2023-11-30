package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.PesquisaAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.ProdutoViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.ImageCache;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


        // Para obter os detalhes do produto, a app envia o id do produto ao servidor web. Este
        // último responde com os detalhes do produto referente ao pid.

        // O pid do produto é enviado para esta tela quando o produto é clicado na tela de Home.
        // Aqui nós obtemos o pid.
        Intent i = getIntent();
        String codigo_produto = i.getStringExtra("codigo_produto");

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ProdutoViewModel produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.
        LiveData<Produto> produto = produtoViewModel.getProductDetailsLD(codigo_produto);

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma produto
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o produto chegou chamando o método onChanged abaixo.
        produto.observe(this, new Observer<Produto>() {
            @Override
            public void onChanged(Produto produto) {

                // product contém os detalhes do produto que foram entregues pelo servidor web
                if(produto != null) {

                    // aqui nós obtemos a imagem do produto. A imagem não vem logo de cara. Primeiro
                    // obtemos os detalhes do produto. Uma vez recebidos os campos de id, nome, preço,
                    // descrição, criado por, usamos o id para obter a imagem do produto em separado.
                    // A classe ImageCache obtém a imagem de um produto específico, guarda em um cache
                    // o seta em um ImageView fornecido.
                    ImageView imvProduto = findViewById(R.id.imvDetalheProduto);
                    int w = (int) ProdutoActivity.this.getResources().getDimension(R.dimen.img_width);
                    int h = (int) ProdutoActivity.this.getResources().getDimension(R.dimen.img_height);
                    ImageCache.loadImageUrlToImageView(ProdutoActivity.this, produto.imagem, imvProduto, w, h);

                    // Abaixo nós obtemos os dados do produto e setamos na interface de usuário
                    TextView tvNomeProduto = findViewById(R.id.tvDetalheNomeProduto);
                    tvNomeProduto.setText(produto.nome_produto);

                    TextView tvValorAtual = findViewById(R.id.tvDetalheValorAtual);
                    tvValorAtual.setText("R$ " + produto.valor_atual);

                    TextView tvDetalheDesconto = findViewById(R.id.tvDetalheDesconto);
                    tvDetalheDesconto.setText(produto.desconto + "%");

                    RatingBar ratingBar = findViewById(R.id.rbDetalheAvaliacao);
                    ratingBar.setRating(produto.avaliacao);
                    /*
                    TextView tvNomeEmpresa = findViewById(R.id.tvNomeEmpresa);
                    tvNomeEmpresa.setText(produto.nome_empresa);
                    tvNomeEmpresa.setLinksClickable(true);
                    tvNomeEmpresa.set
                    */

                    /*
                    LiveData<Produto> p = produtoViewModel.getProductDetailsLD(Integer.toString(codigo_produto));
                    Produto produto = p.getValue();

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
                    */

                }
                else {
                    Toast.makeText(ProdutoActivity.this, "Não foi possível obter os detalhes do produto", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //quando clicar em "comprar agora" fazer uma funcao que envia o id do produto e a quantidade
}