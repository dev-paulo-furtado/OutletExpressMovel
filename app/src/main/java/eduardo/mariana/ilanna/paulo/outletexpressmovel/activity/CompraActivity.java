package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.CompraViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.ProdutoViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.ImageCache;

public class CompraActivity extends AppCompatActivity {
    CompraViewModel compraViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        // Aqui nós obtemos o codigo do produto passado pela ProdutoActivity.
        Intent i = getIntent();
        String codigo_produto = String.valueOf(i.getIntExtra("codigo_produto", 0));

        //buscar valor do produto selecionado
        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ProdutoViewModel produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);
        LiveData<Produto> produto = produtoViewModel.getProductDetailsLD(codigo_produto);
        produto.observe(this, new Observer<Produto>() {
            @Override
            public void onChanged(Produto produto) {

                if(produto != null) {
                    TextView total = findViewById(R.id.tvTotal);
                    String valor = String.format("%.2f", Float.parseFloat(produto.valor_atual));
                    total.setText("R$ " + valor);

                }
                else {
                    Toast.makeText(CompraActivity.this, "Não foi possível obter os detalhes do produto", Toast.LENGTH_LONG).show();
                }
            }
        });

        //pegando todos os campos declarados pelo usuario na activity_compra quando clicar em finalizar compra
        Button btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);
        btnFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //selecionando forma de pagamento
                RadioGroup rgFormaPagamento = findViewById(R.id.rgFormaPagamento);
                int pagamentoId = rgFormaPagamento.getCheckedRadioButtonId();
                System.out.println("id pagamento: " + pagamentoId);
                RadioButton rb = (RadioButton) rgFormaPagamento.getChildAt(rgFormaPagamento.indexOfChild(findViewById(pagamentoId)));
                String pagamentoSelecionado = rb.getText().toString();
                //System.out.println("pagamentoSelecionado: " + pagamentoSelecionado);

                //selecionando dados do endereco
                EditText cep = findViewById(R.id.etCep);
                String cepSelecionado = cep.getText().toString();
                EditText numero = findViewById(R.id.etNumero);
                Integer numeroSelecionado = Integer.parseInt(numero.getText().toString());
                EditText rua = findViewById(R.id.etRua);
                String ruaSelecionada = rua.getText().toString();

                //declarando cpf
                EditText cpf = findViewById(R.id.etCpf);
                String cpfSelecionado = cpf.getText().toString();

                LiveData<Boolean> resultLD = compraViewModel.compra(pagamentoSelecionado, cpfSelecionado, cepSelecionado, ruaSelecionada, numeroSelecionado);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(CompraActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(CompraActivity.this, "Nova compra registrada com sucesso", Toast.LENGTH_LONG).show();
                            // Navega para tela principal
                            Intent i = new Intent(CompraActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(CompraActivity.this, "Erro ao registrar nova compra", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}