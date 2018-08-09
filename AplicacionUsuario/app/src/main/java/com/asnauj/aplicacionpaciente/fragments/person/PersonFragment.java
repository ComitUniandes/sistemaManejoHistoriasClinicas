package com.asnauj.aplicacionpaciente.fragments.person;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.activities.person.MainActivity;
import com.asnauj.aplicacionpaciente.activities.person.PacienteDPersonalesActivity;
import com.asnauj.aplicacionpaciente.entities.Paciente;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    @BindView(R.id.apellido_paciente)
    TextView apellido;
    @BindView(R.id.nombre_paciente)
    TextView nombre;
    @BindView(R.id.cedula_paciente)
    TextView ced;
    @BindView(R.id.paciente_des_personales)
    Button desPersonales;
    MainActivity main;
    Paciente pas;

    public PersonFragment() {
        // Required empty public constructor
    }
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        main = (MainActivity)getActivity();
         pas = main.getPaciente();
        apellido.setText(pas.getLastName());
        nombre.setText(pas.getName());
        ced.setText(pas.getId());

        return view;

    }

    @OnClick(R.id.paciente_des_personales)
    public void aDesiciones(){
        Intent intn = new Intent(main.getApplicationContext(), PacienteDPersonalesActivity.class);
        intn.putExtra("PACIENTE", pas);
        startActivity(intn);
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
