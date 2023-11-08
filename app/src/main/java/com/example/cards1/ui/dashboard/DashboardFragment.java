package com.example.cards1.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cards1.Login;
import com.example.cards1.MainActivity2user;
import com.example.cards1.R;
import com.example.cards1.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    EditText edtemailusuario;
    EditText edtnombreusuario;
    ImageButton imgbtnsetting,imgbtnsalir;

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ImageButton imgBtnSalir = view.findViewById(R.id.imgbtnsalir);
        imgBtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // después de cerrar la sesión, lleva al usuario a la pantalla de inicio de sesión
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                requireActivity().finish(); // cierra la actividad actual para evitar que el usuario regrese a ella al presionar el botón de retroceso
            }
        });
        return view;
    }
}