package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

public class HomeViewModel extends AndroidViewModel {

    //acho que a variavel armazena o fragmento padrao a ser carregado
    int navigationOpSelected = R.id.opHome;

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

        categorias.add(new Categoria("Roupa", R.drawable.cateletronicos));
        categorias.add(new Categoria("Calçada", R.drawable.cateletronicos));
        categorias.add(new Categoria("Eletrodoméstico", R.drawable.cateletronicos));
        categorias.add(new Categoria("Eletrônico", R.drawable.cateletronicos));
        categorias.add(new Categoria("Móvel", R.drawable.cateletronicos));

        return  categorias;
    }

    public List<Produto> getProdutos(String categoria){
        List<Produto> produtos = new ArrayList<>();

        ProductsRepository productsRepository = new ProductsRepository(getApplication());

        MutableLiveData<Produto> produtosLD = new MutableLiveData<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                ProductsRepository productsRepository = new ProductsRepository(getApplication());

                Produto p = productsRepository.categorizeProducts(categoria);

                produtosLD.postValue(p);
            }
        });

        return produtos;
    }

}
