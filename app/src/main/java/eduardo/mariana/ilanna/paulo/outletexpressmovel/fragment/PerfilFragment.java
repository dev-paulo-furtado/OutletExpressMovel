package eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.LoginActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.ComprasAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.PesquisaAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.ItemCompra;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Perfil;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.util.Config;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.i("criou perfil ",);
        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeActivity homeActivity = (HomeActivity) getActivity();

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        HomeViewModel homeViewModel = new ViewModelProvider(homeActivity).get(HomeViewModel.class);

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.
        //System.out.println("SOCORRO ");
        LiveData<Perfil> perfil = homeViewModel.getDetalhesPerfil();

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
                    Toast.makeText(getActivity(), "Não foi possível obter os detalhes do usuario", Toast.LENGTH_LONG).show();
                }
            }
        });

        //carregar Minhas compras

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.

        //TextView tvEmailPerfil = view.findViewById(R.id.tvEmailPerfil);
        //String email = (String) tvEmailPerfil.getText();
        System.out.println("EMAIL: " + Config.getLogin(getContext()));

        RecyclerView rvProdutosComprados = (RecyclerView) view.findViewById(R.id.rvProdutosComprados);
        rvProdutosComprados.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<ItemCompra>> produtosComprados = homeViewModel.getProdutosComprados(Config.getLogin(getContext()));
        System.out.println("dps de produtosComprados");
        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma produto
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o produto chegou chamando o método onChanged abaixo.
        produtosComprados.observe(getViewLifecycleOwner(), new Observer<List<ItemCompra>>() {
            @Override
            public void onChanged(List<ItemCompra> produtos) {
                //Log.i("produtinhos", produtos);
                ComprasAdapter comprasAdapter = new ComprasAdapter(homeActivity, produtos);
                rvProdutosComprados.setAdapter(comprasAdapter);
            }
        });

        //botao editar senha
        Button btnAlterarSenha = view.findViewById(R.id.btnAlterarSenha);
        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.alterar_senha_dlg, null))
                        // Add action buttons
                        .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                                //getView().findViewById(R.id.sbFiltroPreco);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //botao editar senha
        ImageButton btnAlterarNome = view.findViewById(R.id.btnAlterarNome);
        btnAlterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.alterar_nome_dlg, null))
                        // Add action buttons
                        .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                                //getView().findViewById(R.id.sbFiltroPreco);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //botao sair da conta
        Button btnLogin = view.findViewById(R.id.btnSair);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = null;
                Config.setLogin(getActivity(), login);
                //ir para home

                HomeFragment homeFragment = HomeFragment.newInstance();
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.setFragment(homeFragment, R.id.fragContainer);
            }
        });



    }
}