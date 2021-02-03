package com.example.practica3retrofitmoviles.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AddReparacion2Fragment extends Fragment {

   private TextInputLayout tilDescripcion,tilPrecio;
   private TextInputEditText tietDescripcion,tietPrecio;
   private Button btCancelar,btCrear;
   private ViewModel viewModel;
   private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_reparacion2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(getView());

        tilDescripcion = getView().findViewById(R.id.tilDescripcionReparacion);
        tilPrecio = getView().findViewById(R.id.tilPrecioReparacion);
        tietDescripcion = getView().findViewById(R.id.tietDescripcionReparacion);
        tietPrecio = getView().findViewById(R.id.tietPrecioReparacion);
        btCancelar = getView().findViewById(R.id.btCancelarReparacion);
        btCrear = getView().findViewById(R.id.btCrearReparacion);



        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.listaReparacionesFragment,false);
            }
        });

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    Movil m = viewModel.getMovilRepair();
                    Reparacion reparacion = new Reparacion(0,m.getId(),tietDescripcion.getText().toString(),Double.valueOf(tietPrecio.getText().toString()));
                    viewModel.insertReparacion(reparacion);
                    m.setNumeroReparaciones(m.getNumeroReparaciones()+1);
                    viewModel.updateMovil(m.getId(),m);
                    Snackbar.make(getView(),"Se ha creado la reparacion correctamente",Snackbar.LENGTH_LONG).show();
                    navController.popBackStack(R.id.listaMovilesFragment,false);
                    navController.popBackStack(R.id.listaReparacionesFragment,false);

                    viewModel.getLongMutableLiveDataInsertarReparacion().observe(getActivity(), new Observer<Long>() {
                        @Override
                        public void onChanged(Long aLong) {
                            if(aLong!=0){
                                Snackbar.make(getView(),"Se ha creado la reparacion correctamente",Snackbar.LENGTH_LONG).show();
                                navController.popBackStack(R.id.listaReparacionesFragment,false);
                            }else{
                                Snackbar.make(getView(),"No se ha podido crear la Reparacion.",Snackbar.LENGTH_LONG).show();
                                navController.popBackStack(R.id.listaReparacionesFragment,false);;
                            }
                        }
                    });
                }

            }
        });
    }

    private boolean validaCampos() {
        boolean valida = false;

                if(tietDescripcion.getText().toString().length()!=0){
                    tilDescripcion.setErrorEnabled(false);
                    valida=true;
                }else{
                    tilDescripcion.setError("Introduzca un valor para el campo");
                }

        return  valida;
    }


}