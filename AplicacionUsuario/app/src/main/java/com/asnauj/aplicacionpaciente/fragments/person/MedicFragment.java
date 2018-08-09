package com.asnauj.aplicacionpaciente.fragments.person;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.asnauj.aplicacionpaciente.Contantes;
import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.activities.person.MainActivity;
import com.asnauj.aplicacionpaciente.activities.person.VerInfoMedicaActivity;
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


public class MedicFragment extends Fragment {


    @BindView(R.id.buttonMeidcamentosPaciente)
    Button medicamentos;
    @BindView(R.id.buttonDiagnosticoPaciente)
    Button diagnosticos;
    @BindView(R.id.buttonConsultaPaciente)
    Button consulta;

    private OnFragmentInteractionListener mListener;
    MainActivity main;
    Paciente pas;

    public MedicFragment() {
        // Required empty public constructor
    }

    public static MedicFragment newInstance(String param1, String param2) {
        MedicFragment fragment = new MedicFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medic, container, false);
        ButterKnife.bind(this, view);
        main = (MainActivity)getActivity();
        pas = main.getPaciente();
        return view;
    }

    @OnClick(R.id.buttonMeidcamentosPaciente)
    public void medicamentos(){
        try{
            InetAddress add = InetAddress.getByName(Contantes.IP);
            Socket socket = new Socket(add,1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se abrio conexion
            out.println("HOLA");
            String line = in.readLine();
            if(line.equals("CONEXION")){
                out.println("INFORMACION:MEDICAMENTOS:" + pas.getId());
                line = in.readLine();
                String res[] = line.split(":")[1].split("-");
                out.println("CLOSE");
                        Intent intn = new Intent(main.getApplicationContext(), VerInfoMedicaActivity.class);
                        intn.putExtra("TITULO", "Medicamentos");
                intn.putExtra("INFO", res);
                        startActivity(intn);

            }
        } catch (UnknownHostException e) {
            Log.v("AAAA","SIIIIIIII");
        } catch  (IOException e) {
            Log.v("AAAA","NOOOOO");
        }
    }
    @OnClick(R.id.buttonDiagnosticoPaciente)
    public void diagnosticos(){
        try{
            InetAddress add = InetAddress.getByName(Contantes.IP);
            Socket socket = new Socket(add,1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se abrio conexion
            out.println("HOLA");
            String line = in.readLine();
            if(line.equals("CONEXION")){
                out.println("INFORMACION:PROCEDIMIENTOS:" + pas.getId());
                line = in.readLine();
                String res[] = line.split(":")[1].split("-");
                out.println("CLOSE");
                Intent intn = new Intent(main.getApplicationContext(), VerInfoMedicaActivity.class);
                intn.putExtra("TITULO", "Procedimientos");
                intn.putExtra("INFO", res);
                startActivity(intn);

            }
        } catch (UnknownHostException e) {
            Log.v("AAAA","SIIIIIIII");
        } catch  (IOException e) {
            Log.v("AAAA","NOOOOO");
        }
    }
    @OnClick(R.id.buttonConsultaPaciente)
    public void conulta(){
        try{
            InetAddress add = InetAddress.getByName(Contantes.IP);
            Socket socket = new Socket(add,1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se abrio conexion
            out.println("HOLA");
            String line = in.readLine();
            if(line.equals("CONEXION")){
                out.println("INFORMACION:CONSULTAS:" + pas.getId());
                line = in.readLine();
                String res[] = line.split(":")[1].split("-");
                out.println("CLOSE");
                Intent intn = new Intent(main.getApplicationContext(), VerInfoMedicaActivity.class);
                intn.putExtra("TITULO", "Consultas");
                intn.putExtra("INFO", res);
                startActivity(intn);

            }
        } catch (UnknownHostException e) {
            Log.v("AAAA","SIIIIIIII");
        } catch  (IOException e) {
            Log.v("AAAA","NOOOOO");
        }
    }























    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
