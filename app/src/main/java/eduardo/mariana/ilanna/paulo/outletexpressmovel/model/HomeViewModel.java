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

        categorias.add(new Categoria("Roupa", R.drawable.cateletronicos));
        categorias.add(new Categoria("Calçado", R.drawable.cateletronicos));
        categorias.add(new Categoria("Eletrodoméstico", R.drawable.cateletronicos));
        categorias.add(new Categoria("Eletrônico", R.drawable.cateletronicos));
        categorias.add(new Categoria("Móvel", R.drawable.cateletronicos));

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

}
