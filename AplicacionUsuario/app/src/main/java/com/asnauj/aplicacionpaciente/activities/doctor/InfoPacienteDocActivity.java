package com.asnauj.aplicacionpaciente.activities.doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asnauj.aplicacionpaciente.Contantes;
import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.entities.Paciente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoPacienteDocActivity extends AppCompatActivity {

    Paciente usuario;
    String doctor;

    @BindView(R.id.infopacienteNombre)
    TextView nombrePaciente;
    @BindView(R.id.infopacienteApellido)
    TextView apellidoPaciente;
    @BindView(R.id.infopacienteBebedor)
    TextView bebedor;
    @BindView(R.id.infopacienteFumador)
    TextView fumador;
    @BindView(R.id.infopacienteNR)
    TextView nr;
    @BindView(R.id.infopacienteDonante)
    TextView donante;
    @BindView(R.id.infopacienteSangre)
    TextView sangre;
    @BindView(R.id.medicamentosDoc)
    LinearLayout medicamentosLO;
    @BindView(R.id.diagnosticosDoc)
    LinearLayout diagnosticosLO;
    @BindView(R.id.procedimientosDoc)
    LinearLayout procedimientosLO;
    @BindView(R.id.medicamentosNuevosDoc)
    EditText medicamentosNuevoss;
    @BindView(R.id.agregarMed)
    Button agregarMed;
    @BindView(R.id.diagnosticoNuevoDoc)
    EditText diagnosticosNuevos;
    @BindView(R.id.agregarDiag)
    Button agregarBiagnostico;
    @BindView(R.id.prcedimientoNuevoDoc)
    EditText procedimientoNuevo;
    @BindView(R.id.agregarProcedimiento)
    Button agregarProcedimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paciente_doc);
        ButterKnife.bind(this);
        usuario = (Paciente) getIntent().getSerializableExtra("USUARIO");
        doctor = getIntent().getStringExtra("DOC");

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
    }

    @OnClick(R.id.agregarProcedimiento)
    public void agregarProcedimiiento() {
        String pronu = procedimientoNuevo.getText().toString();
        if (pronu.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Escriba un procedimiento para agregar", Toast.LENGTH_LONG).show();
        } else {
            try {
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add, 1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if (line.equals("CONEXION")) {
                    out.println("AGREGARPROCEDIMIENTO");
                    line = in.readLine();
                    if (line.equals("CUAL")) {
                        out.println("ESTE:" + usuario.getId() + ":" + pronu);
                        line = in.readLine();
                        if (line.equals("OK")) {
                            out.println("CLOSE");
                            socket.close();
                            TextView temp = new TextView(this);
                            temp.setText(pronu);
                            temp.setGravity(16);
                            procedimientosLO.addView(temp);
                        }
                    }
                }
            } catch (UnknownHostException e) {
                Log.v("AAAA", "SIIIIIIII");
            } catch (IOException e) {
                Log.v("AAAA", "NOOOOO");
            }
        }
    }
    @OnClick(R.id.agregarDiag)
    public void agregariagnostico() {
        String pronu = diagnosticosNuevos.getText().toString();
        if (pronu.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Escriba un diagnostico para agregar", Toast.LENGTH_LONG).show();
        } else {
            try {
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add, 1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if (line.equals("CONEXION")) {
                    out.println("AGREGARDIAGNOSTICO");
                    line = in.readLine();
                    if (line.equals("CUAL")) {
                        out.println("ESTE:" + usuario.getId() + ":" + pronu);
                        line = in.readLine();
                        if (line.equals("OK")) {
                            out.println("CLOSE");
                            socket.close();
                            TextView temp = new TextView(this);
                            temp.setText(pronu);
                            temp.setGravity(16);
                            diagnosticosLO.addView(temp);
                        }
                    }
                }
            } catch (UnknownHostException e) {
                Log.v("AAAA", "SIIIIIIII");
            } catch (IOException e) {
                Log.v("AAAA", "NOOOOO");
            }
        }
    }
    @OnClick(R.id.agregarMed)
    public void agregarMed() {
        String pronu = medicamentosNuevoss.getText().toString();
        if (pronu.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Escriba un medicamento para agregar", Toast.LENGTH_LONG).show();
        } else {
            try {
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add, 1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if (line.equals("CONEXION")) {
                    out.println("AGREGARMEDICAMENTO");
                    line = in.readLine();
                    if (line.equals("CUAL")) {
                        out.println("ESTE:" + usuario.getId() + ":" + pronu);
                        line = in.readLine();
                        if (line.equals("OK")) {
                            out.println("CLOSE");
                            socket.close();
                            TextView temp = new TextView(this);
                            temp.setText(pronu);
                            temp.setGravity(16);
                            medicamentosLO.addView(temp);
                        }
                    }
                }
            } catch (UnknownHostException e) {
                Log.v("AAAA", "SIIIIIIII");
            } catch (IOException e) {
                Log.v("AAAA", "NOOOOO");
            }
        }
    }
}

