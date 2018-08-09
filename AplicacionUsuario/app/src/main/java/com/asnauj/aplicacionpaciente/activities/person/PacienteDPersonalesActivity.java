package com.asnauj.aplicacionpaciente.activities.person;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
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

public class PacienteDPersonalesActivity extends AppCompatActivity {

    @BindView(R.id.switch_bebedor)
    Switch bebedor;
    @BindView(R.id.switch_donador)
    Switch donador;
    @BindView(R.id.switch_fumador)
    Switch fumador;
    @BindView(R.id.switch_no_resucitar)
    Switch noResucitar;
    @BindView(R.id.infoPersonal_guardar)
    Button save;

    Paciente pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_dpersonales);
        ButterKnife.bind(this);
        pas = (Paciente)getIntent().getSerializableExtra("PACIENTE");
        if(pas.getBebedor().equals("si")){
            bebedor.setChecked(true);
        }
        else bebedor.setChecked(false);

        if(pas.getDonante().equals("si")){
            donador.setChecked(true);
        }
        else donador.setChecked(false);

        if(pas.getFumador().equals("si")){
            fumador.setChecked(true);
        }
        else fumador.setChecked(false);

        if(pas.getNr().equals("si")){
            noResucitar.setChecked(true);
        }
        else noResucitar.setChecked(false);
    }

    @OnClick(R.id.infoPersonal_guardar)
    public void save(){
        String sbebedor = pas.getBebedor();
        String sdonador = pas.getDonante();
        String sfumador = pas.getFumador();
        String snr = pas.getNr();

        if(bebedor.isChecked()){
            sbebedor = "si";
        }
        else sbebedor = "no";
        if(donador.isChecked()){
            sdonador = "si";
        }
        else sdonador = "no";
        if(noResucitar.isChecked()){
            snr = "si";
        }
        else snr = "no";
        if(fumador.isChecked()){
            sfumador = "si";
        }
        else sfumador = "no";

        pas.setBebedor(sbebedor);
        pas.setDonante(sdonador);
        pas.setFumador(sfumador);
        pas.setNr(snr);

        try{
            InetAddress add = InetAddress.getByName(Contantes.IP);
            Socket socket = new Socket(add,1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se abrio conexion
            out.println("HOLA");
            String line = in.readLine();
            if(line.equals("CONEXION")){
                out.println("EDITAR");
                line = in.readLine();
                if(line.equals("INFO")){
                    String info = "INFO" +":" + pas.getId() + ":" + sfumador + ":" + sbebedor + ":" + snr + ":" + sdonador ;
                    out.println(info);
                    line =in.readLine();
                    if(line.equals("OK")) {
                        out.println("CLOSE");
                        socket.close();
                        Intent intn = new Intent(this, MainActivity.class);
                        intn.putExtra("PACIENTE", pas);
                        intn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(intn);
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
