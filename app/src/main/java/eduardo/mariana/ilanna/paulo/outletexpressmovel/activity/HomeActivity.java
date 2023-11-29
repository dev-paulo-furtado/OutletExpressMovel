package eduardo.mariana.ilanna.paulo.outletexpressmovel.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.CarrinhoFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.HomeFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.OfertasFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.PerfilFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.Config;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);

        bottomNavigationView = findViewById(R.id.btmNavView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()) {
                    //dar id aos itens do menu
                    //conferir se a pessoa esta logada, antes de ir pro carrinho ou tela de perfil
                    case R.id.opCarrinho:
                        if(Config.getLogin(HomeActivity.this).isEmpty()) {
                            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            CarrinhoFragment carrinhoFragment = CarrinhoFragment.newInstance();
                            setFragment(carrinhoFragment, R.id.fragContainer);
                        }
                        break;
                    //configurando a navegacao para a visualizacao em list
                    case R.id.opHome:
                        HomeFragment homeFragment = HomeFragment.newInstance();
                        setFragment(homeFragment, R.id.fragContainer);
                        break;
                    case R.id.opPerfil:
                        if(Config.getLogin(HomeActivity.this).isEmpty()) {
                            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            PerfilFragment perfilFragment = PerfilFragment.newInstance();
                            setFragment(perfilFragment, R.id.fragContainer);
                        }
                        break;
                }
                return true;

            }
        });

        bottomNavigationView.setSelectedItemId(R.id.opHome);
    }


    public void setFragment(Fragment fragment, int idFrameLayout) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(idFrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}