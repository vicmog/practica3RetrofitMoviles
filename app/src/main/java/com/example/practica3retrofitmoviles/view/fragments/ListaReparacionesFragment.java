package com.example.practica3retrofitmoviles.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerMovil;
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerReparacion;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class ListaReparacionesFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ViewModel viewModel;
    private List<Reparacion> reparaciones = new ArrayList<>();
    private Button btVerLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista_reparaciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        bottomNavigationView = getView().findViewById(R.id.btNavViewReparaciones);
        navController = Navigation.findNavController(getView());

        btVerLista = getView().findViewById(R.id.btVerListaReparaciones);
        btVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.addReparacionesFragment);
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.listaReparaciones);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.listaMoviles: navController.navigate(R.id.listaMovilesFragment);
                        break;

                }
                return true;
            }
        });

        viewModel.getAllReparaciones();
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerReparaciones);
        AdapterRecyclerReparacion adapter = new AdapterRecyclerReparacion(reparaciones,getActivity(),getView());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.getListMutableLiveDataReparacion().observe(getActivity(), new Observer<List<Reparacion>>() {
            @Override
            public void onChanged(List<Reparacion> reparacions) {
                if(reparacions.size()!=0){
                    reparaciones.clear();
                    reparaciones.addAll(reparacions);
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }

}