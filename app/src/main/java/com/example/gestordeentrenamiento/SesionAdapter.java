package com.example.gestordeentrenamiento;

import android.content.Context;
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

    public SesionAdapter(Context context, List<Sesion> sesiones) {
        this.inflater = LayoutInflater.from(context);
        this.sesiones = sesiones;
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
        holder.nombreTextView.setText(sesion.getNombre());
    }

    @Override
    public int getItemCount() {
        return sesiones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }
}
