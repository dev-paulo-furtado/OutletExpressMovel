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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter.CategoriasAdapter;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.model.HomeViewModel;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CategoriasAdapter categoriasAdapter;
    ArrayList<Categoria> categorias;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeActivity homeActivity = (HomeActivity) getActivity();

        HomeViewModel mViewModel = new ViewModelProvider(homeActivity).get(HomeViewModel.class);
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(homeActivity, mViewModel.getCategorias());
        RecyclerView rvCatergorias = (RecyclerView) view.findViewById(R.id.rvCategorias);
        rvCatergorias.setAdapter(categoriasAdapter);
        rvCatergorias.setHasFixedSize(true);
        rvCatergorias.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        EditText etPesquisa = (EditText) view.findViewById(R.id.etPesquisa);

        ImageButton imbPesquisa = view.findViewById(R.id.imbPesquisa);
        imbPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pesquisa = etPesquisa.getText().toString();

                PesquisaFragment pesquisaFragment = PesquisaFragment.newInstance("",pesquisa);
                homeActivity.setFragment(pesquisaFragment, R.id.flOfertas);
            }
        });



        homeActivity.setFragment(OfertasFragment.newInstance(), R.id.flOfertas);
    }
}