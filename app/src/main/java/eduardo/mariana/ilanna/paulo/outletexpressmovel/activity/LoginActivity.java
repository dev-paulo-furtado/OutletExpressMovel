package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.LoginViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.Config;

public class LoginActivity extends AppCompatActivity {

    static int RESULT_REQUEST_PERMISSION = 2;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // A tela de login na prática é a primeira que vai aparecer na app. Então é aqui que a gente
        // pede as permissões necessárias. A app precisa de duas permissões:
        // - Camera -> para tirar foto do produto
        // - Acesso à galeria publica - para escolher um foto da galeria do celular como foto do
        // produto.
        //List<String> permissions = new ArrayList<>();
        //permissions.add(Manifest.permission.CAMERA);
        //permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        //checkForPermissions(permissions);

        // A função que entra em contato com o servidor web está definida dentro da ViewModel
        // referente a essa Activity
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Quando o usuário clicar no botão de login
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Primeiro obtemos os dados de login e senha digitados pelo usuário
                EditText etLogin = findViewById(R.id.etEmail);
                final String login = etLogin.getText().toString();

                EditText etPassword = findViewById(R.id.etSenha);
                final String password = etPassword.getText().toString();

                // O ViewModel possui o método login, que envia as informações para o servidor web.
                // O servidor web recebe as infos e verifica se estão corretas. Se sim, siginifca
                // que o login foi realizado com sucesso e a app recebe o valor true. Se as infos
                // estão incorretas, o servidor retorna o valor false.
                //
                // O método de login retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = loginViewModel.login(login, password);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o login deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(LoginActivity.this, new Observer<Boolean>() {

                    // Ao ser chamado, o método onChanged informa também qual foi o resultado
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do login. Se aBoolean for true, significa
                        // que as infos de login e senha enviadas ao servidor estão certas. Neste
                        // caso, guardamos as infos de login e senha dentro da app através da classe
                        // Config. Essas infos de login e senha precisam ser guardadas dentro da app
                        // para que possam ser usadas quando a app pedir dados ao servidor web que só
                        // podem ser obtidos se o usuário enviar o login e senha.
                        if (aBoolean) {

                            // guarda os dados de login e senha dentro da app
                            Config.setLogin(LoginActivity.this, login);
                            Config.setPassword(LoginActivity.this, password);

                            // exibe uma mensagem indicando que o login deu certo
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            // Navega para tela principal
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {

                            // Se o login não deu certo, apenas continuamos na tela de login e
                            // indicamos com uma mensagem ao usuário que o login não deu certo.
                            Toast.makeText(LoginActivity.this, "Não foi possível realizar o login da aplicação", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}