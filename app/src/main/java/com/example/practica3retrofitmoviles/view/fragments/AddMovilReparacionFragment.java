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
import android.view.View;
import android.view.ViewGroup;

import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerMovil;
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerMovilEleccion;
import com.example.practica3retrofitmoviles.view.adapters.AdapterRecyclerReparacion;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class AddMovilReparacionFragment extends Fragment {

    private ViewModel viewModel;
    private NavController navController;
    private List<Movil>moviles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_reparaciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        navController = Navigation.findNavController(getView());
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        cargaRecycler();


    }


    private void cargaRecycler() {
        viewModel.getAllMoviles();

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerRepararMoviles);
        AdapterRecyclerMovilEleccion adapter = new AdapterRecyclerMovilEleccion(moviles,getActivity(),getView());
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
