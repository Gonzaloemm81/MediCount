package com.example.cards1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    private Button btnAgregar, btnActualizar, btnBorrar;
    private RecyclerView recyclerView;
    private MedicamentoAdapter medicamentoAdapter;
    private DatabaseHelper databaseHelper;
    private Medicamento medicamentoSeleccionado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

///esto es lo ultimo que agregue para la navegacion
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }}

        /*recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicamentoAdapter = new MedicamentoAdapter();
        recyclerView.setAdapter(medicamentoAdapter);

        databaseHelper = new DatabaseHelper(this);

        // Cargar medicamentos desde la base de datos y establecer en el adaptador
        List<Medicamento> medicamentos = databaseHelper.obtenerMedicamentos();
        medicamentoAdapter.setMedicamentos(medicamentos);
        medicamentoAdapter.notifyDataSetChanged();

        btnAgregar = findViewById(R.id.btnAgregar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnBorrar = findViewById(R.id.btnBorrar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoAgregarEditarMedicamento(false, null);
            }
        });
        // Configurar OnClickListener para el botón Actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicamentoSeleccionado != null) {
                    // Si hay un medicamento seleccionado, mostrar el cuadro de diálogo para editarlo
                    mostrarDialogoAgregarEditarMedicamento(true, medicamentoSeleccionado);
                } else {

                    Toast.makeText(MainActivity.this, "Selecciona un medicamento para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicamentoSeleccionado != null) {
                    // Si hay un medicamento seleccionado, eliminarlo
                    eliminarMedicamento(medicamentoSeleccionado.getId());
                } else {

                    Toast.makeText(MainActivity.this, "Selecciona un medicamento para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        medicamentoAdapter.setOnItemClickListener(new MedicamentoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Medicamento medicamento) {
                medicamentoSeleccionado =  medicamento;
                Toast.makeText(MainActivity.this, "Medicamento seleccionado: " + medicamento.getNombre(), Toast.LENGTH_SHORT).show();
            }



        });


    }

    // Método para agregar un nuevo medicamento
    private void agregarMedicamento(String nombre, String dosis, String hora, int frecuencia) {
        if (nombre != null && dosis != null && hora != null) {
            databaseHelper.agregarMedicamento(nombre, dosis, hora, frecuencia);
            // Actualizar la lista de medicamentos en el adaptador
            medicamentoAdapter.setMedicamentos(databaseHelper.obtenerMedicamentos());
            medicamentoAdapter.notifyDataSetChanged();
        }}

    // Método para actualizar un medicamento existente
    private void actualizarMedicamento(String nombre, String dosis, String hora , int frecuencia) {
        if (medicamentoSeleccionado != null) {
            int id = medicamentoSeleccionado.getId();
            databaseHelper.actualizarMedicamento(id, nombre, dosis, hora, frecuencia);
            // Actualizar la lista de medicamentos en el adaptador
            medicamentoAdapter.setMedicamentos(databaseHelper.obtenerMedicamentos());
            medicamentoAdapter.notifyDataSetChanged();
            // Limpiar la selección después de actualizar
            medicamentoSeleccionado = null;
        }
    }

    // Método para eliminar un medicamento
    private void eliminarMedicamento(int id) {
        if (medicamentoSeleccionado != null) {
            id = medicamentoSeleccionado.getId();
            databaseHelper.eliminarMedicamento(id);
            // Actualizar la lista de medicamentos en el adaptador
            medicamentoAdapter.setMedicamentos(databaseHelper.obtenerMedicamentos());
            medicamentoAdapter.notifyDataSetChanged();
            // Limpiar la selección después de eliminar
            medicamentoSeleccionado = null;
        } else {
            // Manejar el caso cuando no hay medicamento seleccionado
            Toast.makeText(this, "Selecciona un medicamento para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDialogoAgregarEditarMedicamento(final boolean esEditar, final Medicamento medicamentoEditar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                if(TextUtils.isDigitsOnly(frecuenciaStr)) {
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
                }else {
                    // Mostrar un mensaje si la frecuencia no es un número válido
                    Toast.makeText(MainActivity.this, "Ingresa una frecuencia válida", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            private void actualizarListaMedicamentos() {
                List<Medicamento> medicamentos = databaseHelper.obtenerMedicamentos();
                medicamentoAdapter.setMedicamentos(medicamentos);
                medicamentoAdapter.notifyDataSetChanged();
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
}*/