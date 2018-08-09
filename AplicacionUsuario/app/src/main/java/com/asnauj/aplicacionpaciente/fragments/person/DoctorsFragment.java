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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asnauj.aplicacionpaciente.Contantes;
import com.asnauj.aplicacionpaciente.R;
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


public class DoctorsFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    @BindView(R.id.lay_docsenPac)
    LinearLayout layout;
    @BindView(R.id.et_newdoc)
    EditText newDoc;
    @BindView(R.id.add_permited_doc)
    Button add;
    Paciente pas;
    MainActivity main;

    public DoctorsFragment() {
        // Required empty public constructor
    }

    public static DoctorsFragment newInstance(String param1, String param2) {
        DoctorsFragment fragment = new DoctorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_doctors, container, false);
        ButterKnife.bind(this, view);
         main = (MainActivity)getActivity();
        pas = main.getPaciente();
        int tam = pas.getMedicosAceptados().length;
        for(int i = 0; i< tam;i++)
        {
            TextView tv = new TextView(main);
            tv.setText(pas.getMedicosAceptados()[i]);
            tv.setPadding(7,7,7,7);
            tv.setTextSize(15);
            layout.addView(tv);

        }
        return view;
    }
    @OnClick(R.id.add_permited_doc)
    public void add(){
        if(newDoc.getText().toString().isEmpty()){
            Toast.makeText(main.getApplicationContext(), "Escriba el doumento de un doctor para agregarlo", Toast.LENGTH_LONG).show();
        }
        else{
            String doctor = newDoc.getText().toString();
            try{
                InetAddress add = InetAddress.getByName(Contantes.IP);
                Socket socket = new Socket(add,1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Se abrio conexion
                out.println("HOLA");
                String line = in.readLine();
                if(line.equals("CONEXION")){
                    out.println("AGREGARDOCTOR");
                    line = in.readLine();
                    if(line.equals("DOCPACIENTEINFO")){
                        String user = pas.getId();
                        out.println("INFODOCPAC:"+doctor+":"+user);
                        line = in.readLine();
                        if(line.startsWith("OK")){
                            int i = pas.getMedicosAceptados().length;
                            int n = ++i;
                            String[] newArray = new String[n];
                            for(int cnt=0;cnt<pas.getMedicosAceptados().length;cnt++)
                            {
                                newArray[cnt] = pas.getMedicosAceptados()[cnt];
                            }
                            newArray[n-1] = doctor;
                            pas.setMedicosAceptados(newArray);
                            out.println("CLOSE");
                            Intent intn = new Intent(main.getApplicationContext(), MainActivity.class);
                            intn.putExtra("PACIENTE", pas);
                            intn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intn);
                        }
                        else if(line.equals("ERRORC")){
                            Toast.makeText(main.getApplicationContext(), "No encontramos a ese doctor", Toast.LENGTH_LONG).show();
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
        void onFragmentInteraction(Uri uri);
    }
}
