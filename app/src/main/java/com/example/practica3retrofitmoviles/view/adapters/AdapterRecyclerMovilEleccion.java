package com.example.practica3retrofitmoviles.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;

import java.util.List;

public class AdapterRecyclerMovilEleccion extends RecyclerView.Adapter<AdapterRecyclerMovilEleccion.ViewHolder>{

    private List<Movil> moviles;
    private Activity activity;
    private View view;
    private ViewModel viewModel;
    private NavController navController;
    private Movil movil;


    public AdapterRecyclerMovilEleccion(List<Movil> moviles, Activity activity, View view) {
        this.moviles = moviles;
        this.activity = activity;
        this.view = view;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        navController = Navigation.findNavController(view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movil,parent,false);
        AdapterRecyclerMovilEleccion.ViewHolder holder = new AdapterRecyclerMovilEleccion.ViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNumeroReparaciones.setText(moviles.get(position).getNumeroReparaciones()+"");
        holder.tvMarca.setText(moviles.get(position).getMarca());
        holder.tvModelo.setText(moviles.get(position).getModelo());
        Glide.with(activity).load(moviles.get(position).getUrl()).into(holder.imgMovil);
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movil = moviles.get(position);
                viewModel.setMovilRepair(movil);
                navController.navigate(R.id.addReparacion2Fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNumeroReparaciones,tvMarca,tvModelo;
        ImageView imgMovil;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroReparaciones = itemView.findViewById(R.id.tvNumeroReparaciones);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvModelo = itemView.findViewById(R.id.tvModelo);
            imgMovil = itemView.findViewById(R.id.imgMovil);
            parent_layout = itemView.findViewById(R.id.clItemMovil);
        }
    }
}
