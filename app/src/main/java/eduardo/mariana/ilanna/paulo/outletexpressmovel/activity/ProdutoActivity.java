package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }

    //quando clicar em "comprar agora" fazer uma funcao que envia o id do produto e a quantidade
}