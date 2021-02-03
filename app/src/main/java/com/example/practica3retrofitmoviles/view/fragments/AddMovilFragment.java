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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.practica3retrofitmoviles.MainActivity;
import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AddMovilFragment extends Fragment {

    private TextInputEditText tietMarca,tietModelo,tietDescripcion,tietPrecio,tietUrl;
    private TextInputLayout tilMarca,tilModelo,tilDescripcion,tilPrecio,tilUrl;
    private ImageButton btUploadFile;
    private Button btAtras,btAdd;
    private ImageView imgUploadMovil;
    private ViewModel viewModel;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_movil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        navController = Navigation.findNavController(getView());

        tilMarca = getView().findViewById(R.id.tilMarca);
        tilModelo = getView().findViewById(R.id.tilModelo);
        tilDescripcion = getView().findViewById(R.id.tilDescripcion);
        tilPrecio = getView().findViewById(R.id.tilPrecio);
        tilUrl = getView().findViewById(R.id.tilPrecio);

        tietMarca = getView().findViewById(R.id.tietMarca);
        tietModelo = getView().findViewById(R.id.tietModelo);
        tietDescripcion = getView().findViewById(R.id.tietDescripcion);
        tietPrecio = getView().findViewById(R.id.tietPrecio);
        tietUrl = getView().findViewById(R.id.tietUrl);

        //btUploadFile = getView().findViewById(R.id.btUploadImage);
        btAdd = getView().findViewById(R.id.btAddMovilAdd);
        btAtras = getView().findViewById(R.id.btAtrasAddMovil);
        //imgUploadMovil = getView().findViewById(R.id.imgMovilUpload);


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){

                    Movil movil = new Movil(0,tietUrl.getText().toString(),tietMarca.getText().toString(),tietModelo.getText().toString(),tietDescripcion.getText().toString(),Double.parseDouble(tietPrecio.getText().toString()),0);
                    viewModel.addMovil(movil);
                    Snackbar.make(getView(),"Movil creado con exito",Snackbar.LENGTH_LONG).show();
                    navController.navigate(R.id.listaMovilesFragment);


//                    viewModel.getLongMutableLiveDataInsertar().observe(getActivity(), new Observer<Long>() {
//                        @Override
//                        public void onChanged(Long aLong) {
//                            if(aLong==0){
//                                Snackbar.make(getView(),"No se ha podido crear el Movil.",Snackbar.LENGTH_LONG).show();
//                                navController.navigate(R.id.listaMovilesFragment);
//                            }else{
//                                Snackbar.make(getView(),"Movil creado con exito",Snackbar.LENGTH_LONG).show();
//                                navController.navigate(R.id.listaMovilesFragment);
//                            }
//                        }
//                    });
                }
            }
        });

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.listaMovilesFragment,true);
            }
        });


    }

    private boolean validaCampos() {
        boolean valida = false;
        if(tietMarca.getText().toString().length()!=0){
                tilMarca.setErrorEnabled(false);
                if(tietModelo.getText().toString().length()!=0){
                    tilModelo.setErrorEnabled(false);
                    if(tietDescripcion.getText().toString().length()!=0){
                        tilDescripcion.setErrorEnabled(false);
                        if(tietPrecio.getText().toString().length()!=0){
                            tilPrecio.setErrorEnabled(false);
                                if(tietUrl.getText().toString().length()!=0){
                                    tilUrl.setErrorEnabled(false);
                                        valida=true;
                                }else{
                                    tilUrl.setError("Introduzca un valor para el campo");
                                }
                        }else{
                            tietPrecio.setError("Introduzca un valor para el campo");
                        }

                    }else{
                        tilDescripcion.setError("Introduzca un valor para el campo");
                    }
                }else{
                    tilModelo.setError("Introduzca un valor para el campo");
                }
        }else{

            tilMarca.setError("Introduzca un valor para el campo");
        }
        return  valida;
    }
}