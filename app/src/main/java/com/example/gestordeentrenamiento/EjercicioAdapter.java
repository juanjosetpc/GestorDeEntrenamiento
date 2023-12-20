package com.example.gestordeentrenamiento;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.ViewHolder>{

    private List<Ejercicio> ejercicios;
    private LayoutInflater inflater;
    private EjercicioAdapter.ItemClickerListener mItemListener;

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        this.inflater = LayoutInflater.from(context);
        this.ejercicios = ejercicios;
      //  this.mItemListener =  itemClickerListener;
    }
    @NonNull
    @Override
    public EjercicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_ejercicio, parent, false);
        return new EjercicioAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EjercicioAdapter.ViewHolder holder, int position) {
        Ejercicio ejercicio = ejercicios.get(position);
        holder.nombreTextView.setText("Ejericicio: "+ ejercicio.getNombre());
        holder.repeticionesTextView.setText("Repeticiones: " + ejercicio.getRepeticiones());
        holder.seriesTextView.setText("Series: " + ejercicio.getSeries());

    }



    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public interface ItemClickerListener{
        void onItemClick(Ejercicio ejercicio);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView repeticionesTextView;
        TextView seriesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            repeticionesTextView = itemView.findViewById(R.id.repeticionesTextView);
            seriesTextView = itemView.findViewById(R.id.seriesTextView);


        }
    }
}
