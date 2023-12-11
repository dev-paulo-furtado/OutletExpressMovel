package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCarrinho;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCompra;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Perfil;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import kotlinx.coroutines.CoroutineScope;

public class HomeViewModel extends AndroidViewModel {

    //acho que a variavel armazena o fragmento padrao a ser carregado
    int navigationOpSelected = R.id.opHome;
    LiveData<PagingData<Produto>> pageLv;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        /*
        ProductsRepository productsRepository = new ProductsRepository(application);
        ProductsPagingSource productsPagingSource = new ProductsPagingSource(productsRepository);
        Pager<Integer, Produto> pager = new Pager(new PagingConfig(10),() -> productsPagingSource);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        pageLv = PagingLiveData.cacheIn(PagingLiveData.getLiveData(pager),viewModelScope);
        */
    }

    public LiveData<PagingData<Produto>> getPageLv() {
        return pageLv;
    }

    //metodo para pegar a opcao de visualizacao selecionada
    public int getNavigationOpSelected() {
        return navigationOpSelected;
    }

    //metodo para estabelecer a opcao de navegacao selecionada
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }

    public List<Categoria> getCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        categorias.add(new Categoria("Roupa", R.drawable.roupa));
        categorias.add(new Categoria("Calçado", R.drawable.calcado));
        categorias.add(new Categoria("Eletrodoméstico", R.drawable.eletrodomestico));
        categorias.add(new Categoria("Eletroportátil", R.drawable.eletroportatil));
        categorias.add(new Categoria("Eletrônico", R.drawable.eletronico));
        categorias.add(new Categoria("Móvel", R.drawable.movel));

        return  categorias;
    }

    public LiveData<List<Produto>> getProdutosLD(String categoria){

        MutableLiveData<List<Produto>> produtosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Produto> p = productsRepository.categorizeProducts(categoria);

                produtosLD.postValue(p);
            }
        });

        return produtosLD;
    }


    public LiveData<List<ItemCarrinho>> getCarrinhoLD(){

        MutableLiveData<List<ItemCarrinho>> CarrinhoLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */

            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<ItemCarrinho> ic = productsRepository.getItensCarrinho();

                CarrinhoLD.postValue(ic);
            }
        });

        return CarrinhoLD;
    }

    public LiveData<Boolean> getResultDeleteLD(int idProduto){

        MutableLiveData<Boolean> getResultDeleteLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */

            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                Boolean deleted = productsRepository.deleteItemCarrinho(idProduto);

                getResultDeleteLD.postValue(deleted);
            }
        });

        return getResultDeleteLD;
    }

    public LiveData<Boolean> getUpdateQtd(int idProduto, int quantidade){

        MutableLiveData<Boolean> getUpdateQtd = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */

            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                Boolean deleted = productsRepository.updateItemCarrinho(idProduto, quantidade);

                getUpdateQtd.postValue(deleted);
            }
        });

        return getUpdateQtd;
    }

    public LiveData<Perfil> getDetalhesPerfil() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Perfil> perfildetalhes = new MutableLiveData<>();

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

                // O método login envia os dados de novo usuário ao servidor. Ele retorna
                // um booleano indicando true caso o cadastro de novo usuário tenha sido feito com sucesso e false
                // em caso contrário
                Perfil p = productsRepository.dadosUsuario();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                perfildetalhes.postValue(p);
            }
        });

        return perfildetalhes;
    }

    public LiveData<List<Produto>> getProdutosPesquisaLD(String pesquisa) {
        MutableLiveData<List<Produto>> produtosPesquisaLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Produto> p = productsRepository.searchProducts(pesquisa);

                produtosPesquisaLD.postValue(p);
            }
        });

        return produtosPesquisaLD;
    }
    public LiveData<List<ItemCompra>> getProdutosComprados(String email) {

        MutableLiveData<List<ItemCompra>> produtosComprados = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<ItemCompra> p = productsRepository.produtosComprados();

                produtosComprados.postValue(p);
            }
        });
        return produtosComprados;
    }

    public LiveData<List<Produto>> getProdutosFiltradosLD(float precoMin, float precoMax, float filtroAvaliacao, int descontoSelecionado, String avariaSelecionada, String categoria, String pesquisa) {
        MutableLiveData<List<Produto>> produtosFiltradosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Produto> p = productsRepository.getProdutosFiltrados(precoMin,precoMax,filtroAvaliacao,descontoSelecionado,avariaSelecionada,categoria,pesquisa);

                produtosFiltradosLD.postValue(p);
            }
        });

        return produtosFiltradosLD;
    }

    public LiveData<List<Produto>> getMaisCompradosLD() {
        MutableLiveData<List<Produto>> maisCompradosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Produto> p = productsRepository.getMaisComprados();

                maisCompradosLD.postValue(p);
            }
        });

        return maisCompradosLD;
    }

    public LiveData<List<Produto>> getMelhoresAvaliadosLD() {
        MutableLiveData<List<Produto>> melhoresAvaliadosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                List<Produto> p = productsRepository.getMelhoresAvaliados();

                melhoresAvaliadosLD.postValue(p);
            }
        });

        return melhoresAvaliadosLD;
    }
}
