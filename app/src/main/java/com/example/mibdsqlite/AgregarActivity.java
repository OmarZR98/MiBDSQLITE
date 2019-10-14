package com.example.mibdsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarActivity extends AppCompatActivity {

    EditText txtEmail, txtUsuario, txtTel, txtFechaNac;
    Button btnSave ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_activity);

    }


    public void btnSave_click(View v){
        try {
            txtUsuario = findViewById(R.id.etxtUsuario);
            txtEmail = findViewById(R.id.etxtEmail);
            txtTel = findViewById(R.id.etxtTelefono);
            txtFechaNac = findViewById(R.id.etxtFechaNac);

            Contacto con = new Contacto(0, txtUsuario.getText().toString(),
                    txtEmail.getText().toString(),
                    txtTel.getText().toString());

            DAOContactos dao = new DAOContactos(this);
            dao.insert(con);

            Toast.makeText(this,
                            "Contacto agregado satisfactoriamente",
                            Toast.LENGTH_LONG).show();

            Intent i = new Intent(AgregarActivity.this,
                    MainActivity.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this,
                    "Error: "+e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
