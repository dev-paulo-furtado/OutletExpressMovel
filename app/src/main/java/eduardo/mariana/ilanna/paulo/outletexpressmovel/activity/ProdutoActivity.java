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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.ComentarioAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.PesquisaAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.ProdutoViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Comentario;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.ImageCache;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ProdutoViewModel produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);

        // Para obter os detalhes do produto, a app envia o id do produto ao servidor web. Este
        // último responde com os detalhes do produto referente ao pid.

        // O pid do produto é enviado para esta tela quando o produto é clicado na tela de Home.
        // Aqui nós obtemos o pid.
        Intent i = getIntent();
        String codigo_produto = String.valueOf(i.getIntExtra("codigo_produto", 0));

        TextView qtd = findViewById(R.id.tvDetalheQuantidade);

        Button btnDetalheDiminuiQuantidade = findViewById(R.id.btnDetalheDiminuiQuantidade);
        btnDetalheDiminuiQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println(qtd.getText().toString());
                //System.out.println(Integer.parseInt(qtd.getText().toString()));
                //System.out.println(Integer.parseInt(qtd.getText().toString()) - 1);
                int nova_qtd = Integer.parseInt(qtd.getText().toString()) - 1;
                if(nova_qtd < 1){
                    nova_qtd = 1;
                }
                qtd.setText(String.valueOf(nova_qtd));
            }
        });

        Button btnDetalheAddQuantidade = findViewById(R.id.btnDetalheAddQuantidade);
        btnDetalheAddQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println(qtd.getText().toString());
                //System.out.println(Integer.parseInt(qtd.getText().toString()));
                //System.out.println(Integer.parseInt(qtd.getText().toString()) + 1);
                int nova_qtd = Integer.parseInt(qtd.getText().toString()) + 1;
                if(nova_qtd > 20){
                    nova_qtd = 20;
                }
                //System.out.println(nova_qtd);
                qtd.setText(String.valueOf(nova_qtd));
            }
        });

        Button btnAddCarrinho = findViewById(R.id.btnAddCarrinho);
        btnAddCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantidade = qtd.getText().toString();
                LiveData<Boolean> resultado = produtoViewModel.addProdutoNoCarrinho(codigo_produto, quantidade);
                resultado.observe(ProdutoActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean resultado) {
                        if(resultado){
                            Toast.makeText(ProdutoActivity.this, "Produto adicionado ao Carrinho com Sucesso", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(ProdutoActivity.this, "Produto não adicionado ao Carrinho", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        Button btnComprarAgora = findViewById(R.id.btnComprarAgora);
        btnComprarAgora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navega para tela de compra
                Intent i = new Intent(ProdutoActivity.this, CompraActivity.class);
                i.putExtra("codigo_produto",codigo_produto);
                String quantidade = qtd.toString();
                i.putExtra("quantidade",quantidade);
                startActivity(i);
            }
        });





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
                    String valor = String.format("%.2f", Float.parseFloat(produto.valor_atual));
                    tvValorAtual.setText("R$ " + valor);

                    TextView tvDetalheDesconto = findViewById(R.id.tvDetalheDesconto);
                    float porcentagem = Float.parseFloat(produto.valor_atual) + produto.desconto;
                    porcentagem = produto.desconto / porcentagem * 100;
                    porcentagem = Math.round(porcentagem);
                    tvDetalheDesconto.setText(String.valueOf(porcentagem) + "%");

                    RatingBar ratingBar = findViewById(R.id.rbDetalheAvaliacao);
                    ratingBar.setRating(produto.avaliacao);

                    TextView tvNomeEmpresa = findViewById(R.id.tvNomeEmpresa);
                    tvNomeEmpresa.setText(produto.nome_empresa);

                }
                else {
                    Toast.makeText(ProdutoActivity.this, "Não foi possível obter os detalhes do produto", Toast.LENGTH_LONG).show();
                }
            }
        });

        RecyclerView rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setLayoutManager(new LinearLayoutManager(ProdutoActivity.this));

        LiveData<List<Comentario>> prodLiveData = produtoViewModel.getComentarioLD(codigo_produto);
        prodLiveData.observe(ProdutoActivity.this, new Observer<List<Comentario>>() {
            @Override
            public void onChanged(List<Comentario> comentarios) {
                ComentarioAdapter comentarioAdapter = new ComentarioAdapter(ProdutoActivity.this, comentarios);
                rvComentarios.setAdapter(comentarioAdapter);
            }
        });
    }


    //quando clicar em "comprar agora" fazer uma funcao que envia o id do produto e a quantidade
}