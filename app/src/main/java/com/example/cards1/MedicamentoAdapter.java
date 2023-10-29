package com.example.cards1;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder> {
    private List<Medicamento> medicamentos;
    private OnItemClickListener listener;

    // Constructor y métodos de inicialización
    public interface OnItemClickListener{
        void onItemClick(Medicamento medicamento);


    }
    public MedicamentoAdapter() {
        this.medicamentos = new ArrayList<>();
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicamento, parent, false);
        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewHolder holder, int position) {
        if (medicamentos != null && position < medicamentos.size()) {
            Medicamento medicamento = medicamentos.get(position);
            if (medicamento != null) {
                holder.textNombre.setText(medicamento.getNombre());
                holder.textDosis.setText("Dosis: " + medicamento.getDosis());
                holder.textHora.setText("Hora: " + medicamento.getHora());
                holder.textFrecuencia.setText("Cada " + medicamento.getFreq() + " horas");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onItemClick(medicamento);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public void setMedicamentos(List<Medicamento> obtenerMedicamentos) {
        this.medicamentos = obtenerMedicamentos;
        notifyDataSetChanged();
    }

    public static class MedicamentoViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre, textDosis, textHora, textFrecuencia;

        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombre);
            textDosis = itemView.findViewById(R.id.textDosis);
            textHora = itemView.findViewById(R.id.textHora);
            textFrecuencia = itemView.findViewById(R.id.textFrecuencia);
        }
    }
}