package com.example.mibdsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText txtID,txtEmail, txtUsuario, txtTel, txtFechaNac;
    Button btnUpdate, btnSearch,prueva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnUpdate=findViewById(R.id.btn_update);
    }

    public void btnUpdate_click(View v){
        try {

            inicializar_variables();

            Contacto con = new Contacto(Integer.parseInt(txtID.getText().toString()),
                                                         txtUsuario.getText().toString(),
                                                         txtEmail.getText().toString(),
                                                          txtTel.getText().toString());

            DAOContactos dao = new DAOContactos(this);
            dao.update(con);

            Toast.makeText(this,
                    "Contacto actualizado satisfactoriamente",
                    Toast.LENGTH_LONG).show();

            Intent i=new Intent(UpdateActivity.this,
                                               MainActivity.class);
            startActivity(i);

        }catch (Exception e){
            Toast.makeText(this,
                    "Error: "+e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
    public void inicializar_variables(){

        txtID=findViewById(R.id.etxID_update);
        txtUsuario = findViewById(R.id.etxtUsuario_update);
        txtEmail = findViewById(R.id.etxtEmail_update);
        txtTel = findViewById(R.id.etxtTelefono_update);
        txtFechaNac = findViewById(R.id.etxtFechaNac_update);
    }

    public void btnPrueva_click(View v){

        inicializar_variables();

            DAOContactos dao = new DAOContactos(this);

            Contacto con = dao.obtenerContacto(txtID.getText().toString());

            if(con!=null) {
                txtUsuario.setText(con.getUsuario());
                txtEmail.setText(con.getEmail());
                txtTel.setText(con.getTel());
                txtFechaNac.setText(String.valueOf(con.getFecNac()));
            }else{
                Toast.makeText(this,
                        "No se encontro el contacto "+txtID.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }

    }

}
