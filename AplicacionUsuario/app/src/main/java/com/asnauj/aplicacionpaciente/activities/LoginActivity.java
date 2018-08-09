package com.asnauj.aplicacionpaciente.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asnauj.aplicacionpaciente.Contantes;
import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.activities.admin.Main3Activity;
import com.asnauj.aplicacionpaciente.activities.doctor.Main2Activity;
import com.asnauj.aplicacionpaciente.activities.person.MainActivity;
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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_person_button)
    Button loginP;
    @BindView(R.id.login_doctor_button)
    Button loginD;
    @BindView(R.id.login__admin_button)
    Button loginA;
    @BindView(R.id.documento)
    EditText document;
    @BindView(R.id.contrasena)
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    @OnClick(R.id.login_person_button)
    public void login(){
        if(document.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso documento", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso codigo pin", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().length() != 4){
            Toast.makeText(getApplicationContext(), "Su codigo debe ser de 4 numeros", Toast.LENGTH_LONG).show();
        }

        try{
            InetAddress add = InetAddress.getByName(Contantes.IP);
            Socket socket = new Socket(add,1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se abrio conexion
            out.println("HOLA");
            String line = in.readLine();
            if(line.equals("CONEXION")){
                out.println("PACIENTE");
                line = in.readLine();
                if(line.equals("LOGINNPIN")){
                    String usuario = document.getText().toString();
                    String pas = pass.getText().toString();
                    out.println("LOGINPIN:"+usuario+":"+pas);
                    line = in.readLine();
                    if(line.startsWith("OK")){
                        String[] info = line.split(":");
                        String[] docs = info[4].split("-");

                        Paciente paci = new Paciente(info[1], info[2],info[3], docs, info[5], info[6], info[7], info[8]);
                        out.println("CLOSE");
                        socket.close();
                        Intent intn = new Intent(this, MainActivity.class);
                        intn.putExtra("PACIENTE", paci);
                        startActivity(intn);
                        finish();
                    }
                    else if(line.equals("ERRORC")){
                        Toast.makeText(getApplicationContext(), "Tu documenoto de identidad o contraseña" +
                                " no son correctos", Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.login_doctor_button)
    public void loginDOC(){
        if(document.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso documento", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso codigo pin", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().length() != 4){
            Toast.makeText(getApplicationContext(), "Su codigo debe ser de 4 numeros", Toast.LENGTH_LONG).show();
        }
            try{
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add,1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if(line.equals("CONEXION")){
                    out.println("DOC");
                    line = in.readLine();
                    if(line.equals("LOGINNPIN")){
                        String usuario = document.getText().toString();
                        String pas = pass.getText().toString();
                        out.println("LOGINPIN:"+usuario+":"+pas);
                        line = in.readLine();
                        if(line.equals("OK")){
                            out.println("CLOSE");
                            socket.close();
                            Intent intn = new Intent(this, Main2Activity.class);
                            intn.putExtra("DOC", usuario);
                            startActivity(intn);
                            finish();
                        }
                        else if(line.equals("ERRORC")){
                            Toast.makeText(getApplicationContext(), "Tu documenoto de iidentidad o contraseña" +
                                    "no son correctos", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error terrible", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (UnknownHostException e) {
                Log.v("AAAA","SIIIIIIII");
            } catch  (IOException e) {
                Log.v("AAAA","NOOOOO");
            }
        }




    @OnClick(R.id.login__admin_button)
    public void loginAd(){
        if(document.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso documento", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No ingreso codigo pin", Toast.LENGTH_LONG).show();
        }
        else if(pass.getText().toString().length() != 4){
            Toast.makeText(getApplicationContext(), "Su codigo debe ser de 4 numeros", Toast.LENGTH_LONG).show();
        }
            try{
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add,1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if(line.equals("CONEXION")){
                    out.println("ADMIN");
                    line = in.readLine();
                    if(line.equals("LOGINNPIN")){
                        String usuario = document.getText().toString();
                        String pas = pass.getText().toString();
                        out.println("LOGINPIN:"+usuario+":"+pas);
                        line = in.readLine();
                        if(line.equals("OK")){
                            out.println("CLOSE");
                            socket.close();
                            Intent intn = new Intent(this, Main3Activity.class);
                            startActivity(intn);
                            finish();
                        }
                        else if(line.equals("ERRORC")){
                            Toast.makeText(getApplicationContext(), "Tu documenoto de iidentidad o contraseña" +
                                    "no son correctos", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error terrible", Toast.LENGTH_LONG).show();
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

