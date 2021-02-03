package com.example.practica3retrofitmoviles.view.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practica3retrofitmoviles.R;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;
import com.example.practica3retrofitmoviles.viewmodel.ViewModel;

import java.util.List;

public class AdapterRecyclerReparacion extends RecyclerView.Adapter<AdapterRecyclerReparacion.ViewHolder> implements PopupMenu.OnMenuItemClickListener{

    private List<Reparacion>reparacions;
    private View view;
    private Activity activity;
    private ViewModel viewModel;
    private NavController navController;
    private Reparacion reparacion;

    public AdapterRecyclerReparacion(List<Reparacion> reparacions,Activity activity,View view) {
        this.reparacions = reparacions;
        this.view = view;
        this.activity = activity;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        navController = Navigation.findNavController(view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reparacion,parent,false);
        AdapterRecyclerReparacion.ViewHolder holder = new AdapterRecyclerReparacion.ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDescripcion.setText(reparacions.get(position).getDescripcionReparacion());
        holder.tvPrecio.setText(reparacions.get(position).getPrecioReparacion()+"");
        holder.idMovil.setText(reparacions.get(position).getIdMovil()+"");
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reparacion = reparacions.get(position);
                menuPopup(v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reparacions.size();
    }

    private void menuPopup(View anchor) {
        PopupMenu popup = new PopupMenu(activity, anchor);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.menu.menu_popup_reparacion, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

            case R.id.borraReparacion:
                Log.v("ZZZ",reparacion.toString());
                viewModel.deleteReparacion(reparacion.getId());

                viewModel.getMovil(reparacion.getIdMovil());
                viewModel.getMovilMutableLiveData().observe((LifecycleOwner) activity, new Observer<Movil>() {
                    @Override
                    public void onChanged(Movil movil) {

                        if(movil.getNumeroReparaciones()!=0){
                            movil.setNumeroReparaciones(movil.getNumeroReparaciones()-1);
                        }


                        viewModel.updateMovil(movil.getId(),movil);
                    }
                });

                navController.navigate(R.id.listaMovilesFragment);
                navController.navigate(R.id.listaReparacionesFragment);


                break;

        }
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDescripcion,tvPrecio,idMovil;

        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idMovil = itemView.findViewById(R.id.tvIdMovil);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionReparacion);
            tvPrecio = itemView.findViewById(R.id.tvPrecioReparacion);

            parent_layout  = itemView.findViewById(R.id.clReparacion);
        }
    }
}
