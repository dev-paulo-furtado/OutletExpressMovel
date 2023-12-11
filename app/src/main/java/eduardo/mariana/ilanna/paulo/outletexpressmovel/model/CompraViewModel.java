package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.CompraActivity;

public class CompraViewModel extends AndroidViewModel {
    public CompraViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> compra(String forma_pagamento, String cpf, String cep, String rua, Integer numero, String codigo_produto, String qtd) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de ProductsRepository. É dentro dessa classe que estão os
                // métodos que se comunicam com o servidor web.
                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                // O método login envia os dados de autenticação ao servidor. Ele retorna
                // um booleano indicando true caso o login tenha sido feito com sucesso e false
                // em caso contrário
                boolean b = productsRepository.compra(forma_pagamento, cpf, cep, rua, numero, codigo_produto, qtd);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

}
