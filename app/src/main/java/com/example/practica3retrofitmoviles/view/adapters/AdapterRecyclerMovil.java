package com.example.practica3retrofitmoviles.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

public class AdapterRecyclerMovil extends RecyclerView.Adapter<AdapterRecyclerMovil.ViewHolder> implements PopupMenu.OnMenuItemClickListener{

    private List<Movil>moviles;
    private Activity activity;
    private View view;
    private ViewModel viewModel;
    private NavController navController;
    private Movil movil;

    public AdapterRecyclerMovil(List<Movil> moviles, Activity activity, View view) {
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
        ViewHolder holder = new ViewHolder(vista);

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
                menuPopup(v);
            }
        });

    }

    private void menuPopup(View anchor) {
        PopupMenu popup = new PopupMenu(activity, anchor);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return moviles.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

            case R.id.borraMovil:
                viewModel.deleteMovil(movil.getId());
                navController.navigate(R.id.listaReparacionesFragment);
                navController.navigate(R.id.listaMovilesFragment);


                break;
            case R.id.editarMovil:
                viewModel.setMovilEditar(movil);
                navController.navigate(R.id.editMovilFragment);

                break;

            case R.id.verMovil:
                    viewModel.setMovilVer(movil);
                    navController.navigate(R.id.vistaMovilFragment);

                break;

        }
        return true;
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
