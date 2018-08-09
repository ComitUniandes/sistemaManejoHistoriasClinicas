package com.asnauj.aplicacionpaciente.activities.person;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.entities.Paciente;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerInfoMedicaActivity extends AppCompatActivity {

    @BindView(R.id.titulo_infomedicapaciente)
    TextView titulo;
    @BindView(R.id.layout_infomedicapaciente)
    LinearLayout infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_info_medica);
        ButterKnife.bind(this);

        titulo.setText((String)getIntent().getStringExtra("TITULO"));
        String[] info = getIntent().getStringArrayExtra("INFO");
        for(int i = 0; i < info.length; i++){
            TextView tv = new TextView(this);
            tv.setText(info[i]);
            tv.setPadding(7,7,7,7);
            tv.setTextSize(15);
            infor.addView(tv);
        }
    }
}
