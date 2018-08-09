package com.asnauj.aplicacionpaciente.activities.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.entities.Paciente;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoPacienteAdminActivity extends AppCompatActivity {

    @BindView(R.id.infoAdminpacienteNombre)
    TextView nombrePaciente;
    @BindView(R.id.infoAdminPacienteApellido)
    TextView apellidoPaciente;
    @BindView(R.id.infoAdminPacienteBebedor)
    TextView bebedor;
    @BindView(R.id.infoAdminPacienteFumador)
    TextView fumador;
    @BindView(R.id.infoAdminPacienteNR)
    TextView nr;
    @BindView(R.id.infoAdminPacienteDonante)
    TextView donante;
    @BindView(R.id.infoAdminPacienteSangre)
    TextView sangre;
    @BindView(R.id.medicamentosAdmin)
    LinearLayout medicamentosLO;
    @BindView(R.id.diagnosticosAdmin)
    LinearLayout diagnosticosLO;
    @BindView(R.id.procedimientosAdmin)
    LinearLayout procedimientosLO;
    @BindView(R.id.facturassAdmin)
    LinearLayout facturasLO;

    Paciente usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paciente_admin);
        ButterKnife.bind(this);

        usuario = (Paciente) getIntent().getSerializableExtra("USUARIO");


        nombrePaciente.setText(usuario.getName());
        apellidoPaciente.setText(usuario.getLastName());
        bebedor.setText(usuario.getBebedor());
        fumador.setText(usuario.getFumador());
        nr.setText(usuario.getNr());
        donante.setText(usuario.getDonante());
        sangre.setText(usuario.getSangre());

        String[] medicamentos = getIntent().getStringArrayExtra("MEDICAMENTOOS");
        String[] diagnosticos = getIntent().getStringArrayExtra("DIAGNOSTICOS");
        String[] procedimientos = getIntent().getStringArrayExtra("PROCEDIMIENTOS");
        String[] facturas = getIntent().getStringArrayExtra("FACTURAS");

        for (int i = 0; i < medicamentos.length; i++) {
            TextView temp = new TextView(this);
            temp.setText(medicamentos[i]);
            temp.setGravity(16);
            medicamentosLO.addView(temp);
        }
        for (int i = 0; i < diagnosticos.length; i++) {
            TextView temp = new TextView(this);
            temp.setText(diagnosticos[i]);
            temp.setGravity(16);
            diagnosticosLO.addView(temp);
        }
        for (int i = 0; i < procedimientos.length; i++) {
            TextView temp = new TextView(this);
            temp.setText(procedimientos[i]);
            temp.setGravity(16);
            procedimientosLO.addView(temp);
        }
        for (int i = 0; i < facturas.length; i++) {
            TextView temp = new TextView(this);
            temp.setText(facturas[i]);
            temp.setGravity(16);
            facturasLO.addView(temp);
        }
    }
}
