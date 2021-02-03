package com.example.practica3retrofitmoviles.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;


public class VistaMovilFragment extends Fragment {

    private ImageView imgVistaMovil;
    private TextView tvMarca,tvModelo,tvDescripcion,tvNumeroReparaciones,tvPrecio;
    private ViewModel viewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vista_movil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(getView());
        imgVistaMovil = getView().findViewById(R.id.imgMovilVista);
        tvMarca = getView().findViewById(R.id.tvMarcaMovil);
        tvModelo = getView().findViewById(R.id.tvModeloMovil);
        tvDescripcion = getView().findViewById(R.id.tvDescripcionMovil);
        tvNumeroReparaciones = getView().findViewById(R.id.tvNumeroReparacionesMovil);
        tvPrecio = getView().findViewById(R.id.tvPrecioMovil);

        Movil movilVista = viewModel.getMovilVer();

        tvMarca.setText(movilVista.getMarca());
        tvModelo.setText(movilVista.getModelo());
        tvDescripcion.setText(movilVista.getDescripcionMovil());
        tvNumeroReparaciones.setText(movilVista.getNumeroReparaciones()+"");
        tvPrecio.setText(movilVista.getPrecioMovil()+"");
        Glide.with(getActivity()).load(movilVista.getUrl()).into(imgVistaMovil);

        Button btAtras = getView().findViewById(R.id.btAtrasVista);
        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.listaMovilesFragment,false);
            }
        });

    }
}