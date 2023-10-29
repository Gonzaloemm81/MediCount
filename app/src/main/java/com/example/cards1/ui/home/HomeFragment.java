package com.example.cards1.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cards1.DatabaseHelper;
import com.example.cards1.Medicamento;
import com.example.cards1.MedicamentoAdapter;
import com.example.cards1.R;

import java.util.List;

public class HomeFragment extends Fragment {
    private Button btnAgregar, btnActualizar, btnBorrar;
    private RecyclerView recyclerView;
    private MedicamentoAdapter medicamentoAdapter;
    private DatabaseHelper databaseHelper;
    private Medicamento medicamentoSeleccionado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        medicamentoAdapter = new MedicamentoAdapter();
        recyclerView.setAdapter(medicamentoAdapter);

        databaseHelper = new DatabaseHelper(requireContext());

        // Cargar medicamentos desde la base de datos y establecer en el adaptador
        List<Medicamento> medicamentos = databaseHelper.obtenerMedicamentos();
        medicamentoAdapter.setMedicamentos(medicamentos);
        medicamentoAdapter.notifyDataSetChanged();

        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnActualizar = view.findViewById(R.id.btnActualizar);
        btnBorrar = view.findViewById(R.id.btnBorrar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoAgregarEditarMedicamento(false, null);
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicamentoSeleccionado != null) {
                    mostrarDialogoAgregarEditarMedicamento(true, medicamentoSeleccionado);
                } else {
                    Toast.makeText(requireContext(), "Selecciona un medicamento para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicamentoSeleccionado != null) {
                    databaseHelper.eliminarMedicamento(medicamentoSeleccionado.getId());
                } else {
                    Toast.makeText(requireContext(), "Selecciona un medicamento para eliminar", Toast.LENGTH_SHORT).show();
                }
                actualizarListaMedicamentos();
            }
        });

        medicamentoAdapter.setOnItemClickListener(new MedicamentoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Medicamento medicamento) {
                medicamentoSeleccionado = medicamento;
                Toast.makeText(requireContext(), "Medicamento seleccionado: " + medicamento.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void mostrarDialogoAgregarEditarMedicamento(final boolean esEditar, final Medicamento medicamentoEditar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_agregar_editar_medicamento, null);
        builder.setView(dialogView);

        final EditText etNombre = dialogView.findViewById(R.id.etNombre);
        final EditText etDosis = dialogView.findViewById(R.id.etDosis);
        final EditText etHora = dialogView.findViewById(R.id.etHora);
        final EditText etFrecuencia = dialogView.findViewById(R.id.etFreq);
        // Si es una edición, llenar los campos con los datos del medicamento existente
        if (esEditar && medicamentoEditar != null) {
            etNombre.setText(medicamentoEditar.getNombre());
            etDosis.setText(medicamentoEditar.getDosis());
            etHora.setText(medicamentoEditar.getHora());
        }

        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombre = etNombre.getText().toString();
                String dosis = etDosis.getText().toString();
                String hora = etHora.getText().toString();
                String frecuenciaStr = etFrecuencia.getText().toString();
                // Mostrar un mensaje si la frecuencia no es un número válido
                if (TextUtils.isDigitsOnly(frecuenciaStr)) {
                    int frecuencia = Integer.parseInt(etFrecuencia.getText().toString());
                    if (esEditar && medicamentoEditar != null) {
                        // Lógica para actualizar el medicamento en la base de datos
                        int idMedicamento = medicamentoEditar.getId();
                        databaseHelper.actualizarMedicamento(idMedicamento, nombre, dosis, hora, frecuencia);
                    } else {
                        // Lógica para agregar el nuevo medicamento a la base de datos
                        databaseHelper.agregarMedicamento(nombre, dosis, hora, frecuencia);
                    }

                    // Actualizar la lista de medicamentos en el RecyclerView
                    actualizarListaMedicamentos();
                } else {
                    Toast.makeText(requireContext(), "Ingresa una frecuencia válida", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    // Método para actualizar la lista de medicamentos en el RecyclerView
    private void actualizarListaMedicamentos() {
        List<Medicamento> medicamentos = databaseHelper.obtenerMedicamentos();
        medicamentoAdapter.setMedicamentos(medicamentos);
        medicamentoAdapter.notifyDataSetChanged();
    }
}
