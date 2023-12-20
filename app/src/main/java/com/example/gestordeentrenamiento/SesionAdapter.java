package com.example.gestordeentrenamiento;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class SesionAdapter extends RecyclerView.Adapter<SesionAdapter.ViewHolder> {
    private List<Sesion> sesiones;
    private LayoutInflater inflater;
    private ItemClickerListener mItemListener;

    public SesionAdapter(Context context, List<Sesion> sesiones,ItemClickerListener itemClickerListener) {
        this.inflater = LayoutInflater.from(context);
        this.sesiones = sesiones;
        this.mItemListener =  itemClickerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sesion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sesion sesion = sesiones.get(position);
        holder.nombreTextView.setText(sesion.getNombre() + "\n");

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(sesiones.get(position)); //toma la posicion del item que seleccionemos en el RecyclerView
        });
    }

    @Override
    public int getItemCount() {
        return sesiones.size();
    }


    public interface ItemClickerListener{
        void onItemClick(Sesion sesiones);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }
}
