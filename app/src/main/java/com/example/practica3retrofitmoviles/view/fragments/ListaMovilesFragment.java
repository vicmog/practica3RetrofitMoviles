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
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerMovil;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class ListaMovilesFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ViewModel viewModel;
    private List<Movil>moviles = new ArrayList<>();
    private Button btAddMovil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_moviles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        btAddMovil = getView().findViewById(R.id.btAddMovil);
        btAddMovil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.addMovilFragment);
            }
        });

        bottomNavigationView = getView().findViewById(R.id.btNavViewMoviles);
        navController = Navigation.findNavController(getView());

        bottomNavigationView.setSelectedItemId(R.id.listaMoviles);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.listaReparaciones: navController.navigate(R.id.listaReparacionesFragment);
                        break;
                }
                return true;
            }
        });
        cargaRecycler();
    }

    private void cargaRecycler() {
        viewModel.getAllMoviles();

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerMoviles);
        AdapterRecyclerMovil adapter = new AdapterRecyclerMovil(moviles,getActivity(),getView());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.getListMutableLiveDataMoviles().observe(getActivity(), new Observer<List<Movil>>() {
            @Override
            public void onChanged(List<Movil> m) {
                moviles.clear();
                moviles.addAll(m);
                adapter.notifyDataSetChanged();
            }
        });
    }
}