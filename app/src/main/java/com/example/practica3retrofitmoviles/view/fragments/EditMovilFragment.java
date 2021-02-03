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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditMovilFragment extends Fragment {

    private TextInputEditText tietMarca,tietModelo,tietDescripcion,tietPrecio,tietUrl;
    private TextInputLayout tilMarca,tilModelo,tilDescripcion,tilPrecio,tilUrl;
    private ImageButton btUploadFile;
    private Button btAtras, btUpdate;
    private ImageView imgEdit;
    private ViewModel viewModel;
    private NavController navController;
    private Movil movilEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_movil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        navController = Navigation.findNavController(getView());

        movilEdit = viewModel.getMovilEditar();

        imgEdit = getView().findViewById(R.id.imgMovilUpdate);
        tilMarca = getView().findViewById(R.id.tilMarcaUpdate);
        tilModelo = getView().findViewById(R.id.tilModeloUpdate);
        tilDescripcion = getView().findViewById(R.id.tilDescripcionUpdate);
        tilPrecio = getView().findViewById(R.id.tilPrecioUpdate);
        tilUrl = getView().findViewById(R.id.tilPrecioUpdate);

        tietMarca = getView().findViewById(R.id.tietMarcaUpdate);
        tietModelo = getView().findViewById(R.id.tietModeloUpdate);
        tietDescripcion = getView().findViewById(R.id.tietDescripcionUpdate);
        tietPrecio = getView().findViewById(R.id.tietPrecioUpdate);
        tietUrl = getView().findViewById(R.id.tietUrlUpdate);

        tietMarca.setText(movilEdit.getMarca());
        tietModelo.setText(movilEdit.getModelo());
        tietDescripcion.setText(movilEdit.getDescripcionMovil());
        tietPrecio.setText(movilEdit.getPrecioMovil()+"");
        tietUrl.setText(movilEdit.getUrl());
        Glide.with(getActivity()).load(movilEdit.getUrl()).into(imgEdit);
        btUpdate = getView().findViewById(R.id.btUpdate);
        btAtras = getView().findViewById(R.id.btAtrasUpdate);



        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(validaCampos()){
                        movilEdit.setMarca(tietMarca.getText().toString());
                        movilEdit.setModelo(tietModelo.getText().toString());
                        movilEdit.setDescripcionMovil(tietDescripcion.getText().toString());
                        movilEdit.setPrecioMovil(Double.valueOf(tietPrecio.getText().toString()));
                        movilEdit.setUrl(tietUrl.getText().toString());
                        Glide.with(getActivity()).load(movilEdit.getUrl()).into(imgEdit);
                        viewModel.updateMovil(movilEdit.getId(),movilEdit);
                        Snackbar.make(getView(),movilEdit.getMarca()+" "+ movilEdit.getModelo()+"  Se ha actualizado correctamente",Snackbar.LENGTH_LONG).show();

                    }
            }
        });

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.listaMovilesFragment,false);
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