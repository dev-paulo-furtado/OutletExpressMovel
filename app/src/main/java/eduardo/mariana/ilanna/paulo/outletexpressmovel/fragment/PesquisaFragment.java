package eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment;

import android.content.DialogInterface;
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
import android.widget.SeekBar;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.PesquisaAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Produto;

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

        Button btnFiltro = view.findViewById(R.id.btnFiltro);
        btnFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.filtros_dlg, null))
                        // Add action buttons
                        .setPositiveButton("Aplicar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                                SeekBar sbFiltroPrecoMin = getView().findViewById(R.id.sbFiltroPrecoMin);
                                int precoMin = sbFiltroPrecoMin.getProgress();

                                SeekBar sbFiltroPrecoMax = getView().findViewById(R.id.sbFiltroPrecoMax);
                                int precoMax = sbFiltroPrecoMax.getProgress();

                                Log.e("precoMin: ", String.valueOf(precoMin));
                                Log.e("precoMax: ", String.valueOf(precoMax));

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

        RecyclerView rvPesquisa = (RecyclerView) view.findViewById(R.id.rvPesquisa);
        rvPesquisa.setLayoutManager(new LinearLayoutManager(getContext()));

        //chamar metodo que ira preencher a lista de produtos
        //quando o PesquisaFragment for criado, essa funcao vai ser chamada e setar o adapter da rvPesquisa
        HomeActivity homeActivity = (HomeActivity) getActivity();

        HomeViewModel mViewModel = new ViewModelProvider(homeActivity).get(HomeViewModel.class);

        if(categoria != "") {
            LiveData<List<Produto>> prodLiveData = mViewModel.getProdutosLD(this.categoria);
            prodLiveData.observe(getViewLifecycleOwner(), new Observer<List<Produto>>() {
                @Override
                public void onChanged(List<Produto> produtos) {
                    PesquisaAdapter pesquisaAdapter = new PesquisaAdapter(homeActivity, produtos);
                    rvPesquisa.setAdapter(pesquisaAdapter);
                }
            });
        }
        else if (pesquisa != "") {
            LiveData<List<Produto>> prodLiveData = mViewModel.getProdutosPesquisaLD(this.pesquisa);
            prodLiveData.observe(getViewLifecycleOwner(), new Observer<List<Produto>>() {
                @Override
                public void onChanged(List<Produto> produtos) {
                    PesquisaAdapter pesquisaAdapter = new PesquisaAdapter(homeActivity, produtos);
                    rvPesquisa.setAdapter(pesquisaAdapter);
                }
            });
        }

        //homeActivity.setFragment(OfertasFragment.newInstance(),R.id.flOfertas);

    }
}