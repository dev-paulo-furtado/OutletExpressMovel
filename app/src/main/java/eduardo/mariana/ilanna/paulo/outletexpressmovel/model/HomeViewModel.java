package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;

public class HomeViewModel extends ViewModel {

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

        Categoria catEletronico = new Categoria("Eletrônicos", R.drawable.cateletronicos);
        categorias.add(catEletronico);

        return  categorias;

    }
}
