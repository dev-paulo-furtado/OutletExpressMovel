package eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.CategoriasAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.PesquisaAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PesquisaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PesquisaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORIA = "categoria";
    private static final String ARG_PESQUISA = "pesquisa";

    // TODO: Rename and change types of parameters
    private String categoria;
    private String pesquisa;

    public PesquisaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PesquisaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PesquisaFragment newInstance(String categoria, String pesquisa) {
        PesquisaFragment fragment = new PesquisaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORIA, categoria);
        args.putString(ARG_PESQUISA, pesquisa);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoria = getArguments().getString(ARG_CATEGORIA);
            pesquisa = getArguments().getString(ARG_PESQUISA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesquisa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //chamar metodo que ira preencher a lista de produtos
        //quando o PesquisaFragment for criado, essa funcao vai ser chamada e setar o adapter da rvPesquisa
        HomeActivity homeActivity = (HomeActivity) getActivity();

        HomeViewModel mViewModel = new ViewModelProvider(homeActivity).get(HomeViewModel.class);
        PesquisaAdapter pesquisaAdapter = new PesquisaAdapter(homeActivity, mViewModel.getProdutos());

        RecyclerView rvPesquisa = (RecyclerView) view.findViewById(R.id.rvPesquisa);
        rvPesquisa.setAdapter(pesquisaAdapter);
        rvPesquisa.setLayoutManager(new LinearLayoutManager(getContext()));

        homeActivity.setFragment(OfertasFragment.newInstance(),R.id.flOfertas);

    }
}