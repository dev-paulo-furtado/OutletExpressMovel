package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Comentario;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class ProdutoViewModel extends AndroidViewModel {

    public ProdutoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Produto> getProductDetailsLD(String pid) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Produto> productDetailLD = new MutableLiveData<>();

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

                // O método loadProductDetail obtem os dados detalhados de um produto junto ao servidor.
                // Ele retorna um objeto do tipo Product, que contém os dados detalhados do produto.
                Produto p = productsRepository.loadProductDetail(pid);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                productDetailLD.postValue(p);
            }
        });

        return productDetailLD;
    }

    public LiveData<List<Comentario>> getComentarioLD(String codigo_produto){

        MutableLiveData<List<Comentario>> comentariosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Comentario>  c = productsRepository.getComentarios(codigo_produto);

                comentariosLD.postValue(c);
            }
        });

        return comentariosLD;
    }

    public LiveData<Boolean> addProdutoNoCarrinho(String codigo_produto, String quantidade){

        MutableLiveData<Boolean> resultLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                Boolean  c = productsRepository.addProdutoNoCarrinho(codigo_produto, quantidade);

                resultLD.postValue(c);
            }
        });

        return resultLD;
    }
}
