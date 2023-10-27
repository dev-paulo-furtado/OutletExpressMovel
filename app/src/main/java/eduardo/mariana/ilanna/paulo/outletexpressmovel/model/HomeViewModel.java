package eduardo.mariana.ilanna.paulo.outletexpressmovel.model;

import androidx.lifecycle.ViewModel;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;

public class HomeViewModel extends ViewModel {

    int navigationOpSelected = R.id.gridViewOp;

    //metodo para pegar a opcao de visualizacao selecionada
    public int getNavigationOpSelected() {
        return navigationOpSelected;
    }

    //metodo para estabelecer a opcao de navegacao selecionada
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }
}
