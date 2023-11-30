package eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Perfil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_perfil);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Para obter os detalhes do produto, a app envia o id do produto ao servidor web. Este
        // último responde com os detalhes do produto referente ao pid.

        // O pid do produto é enviado para esta tela quando o produto é clicado na tela de Home.
        // Aqui nós obtemos o pid.
        HomeActivity homeActivity = (HomeActivity) getActivity();
        Intent i = homeActivity.getIntent();
        String pEmail = i.getStringExtra("pEmail");

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        HomeViewModel homeViewModel = new ViewModelProvider(homeActivity).get(HomeViewModel.class);

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.
        LiveData<Perfil> perfil = homeViewModel.getDetalhesPerfil(pEmail);

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma produto
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o produto chegou chamando o método onChanged abaixo.
        perfil.observe(getViewLifecycleOwner(), new Observer<Perfil>() {
            @Override
            public void onChanged(Perfil perfil) {

                // product contém os detalhes do produto que foram entregues pelo servidor web
                if(perfil != null) {

                    // Abaixo nós obtemos os dados do produto e setamos na interfa de usuário
                    TextView tvNomePerfil = view.findViewById(R.id.tvNomePerfil);
                    tvNomePerfil.setText(perfil.nome);

                    TextView tvEmailPerfil = view.findViewById(R.id.tvEmailPerfil);
                    tvEmailPerfil.setText(perfil.email);
                }
                else {
                    Toast.makeText(homeActivity, "Não foi possível obter os detalhes do produto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}