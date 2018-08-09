package com.asnauj.aplicacionpaciente.activities.doctor;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.buscar_doc)
    Button buscar;
    @BindView(R.id.aBuscar_doc)
    TextView user;

    String doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        doctor = getIntent().getStringExtra("DOC");
    }


    @OnClick(R.id.buscar_doc)
    public void buscar(){
        if(user.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Ingrese un usuario a buscar", Toast.LENGTH_LONG);
        }

        else{
            try{
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add,1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if(line.equals("CONEXION")){
                    out.println("ABUSCAR");
                    line = in.readLine();
                    if(line.equals("USUARIO")){
                        String usuario = user.getText().toString();
                        out.println("USUARIO:" + doctor+":"+usuario);
                        line = in.readLine();
                        if(line.startsWith("OK")){
                            String[] infoP = line.split(":");
                            String name1 = infoP[1];
                            String apellido = infoP[2];
                            String bebedor = infoP[3];
                            String fumador = infoP[4];
                            String NR = infoP[5];
                            String donante = infoP[6];
                            String sangre = infoP[7];
                            String[] medicamentos = infoP[8].split("-");
                            String[] diagnosticos = infoP[9].split("-");
                            String[] procedimientos = infoP[10].split("-");
                            out.println("CLOSE");
                            socket.close();
                            String[] med = new String[] {};
                            Paciente pas = new Paciente(name1, apellido, usuario, med, bebedor, fumador, NR, donante);
                            pas.setSangre(sangre);
                            Intent intn = new Intent(this, InfoPacienteDocActivity.class);
                            intn.putExtra("MEDICAMENTOOS", medicamentos);
                            intn.putExtra("DIAGNOSTICOS", diagnosticos);
                            intn.putExtra("PROCEDIMIENTOS", procedimientos);;
                            intn.putExtra("USUARIO", pas);
                            intn.putExtra("DOC", doctor);
                            startActivity(intn);
                            finish();

                        }
                        else if(line.equals("NOACCES")){
                            Toast.makeText(getApplicationContext(), "No tiene acceso a la información del paciente", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (UnknownHostException e) {
                Log.v("AAAA","SIIIIIIII");
            } catch  (IOException e) {
                Log.v("AAAA","NOOOOO");
            }
        }
    }
}
