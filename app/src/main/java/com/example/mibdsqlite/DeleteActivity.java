package com.example.mibdsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    TextView txtID,txtEmail, txtUsuario, txtTel, txtFechaNac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

    }
    public void btnPruevaDelete_click(View v){
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

    public void btnDelete_click(View v){
        try {

            inicializar_variables();
            DAOContactos dao = new DAOContactos(this);
            dao.eliminar(txtID.getText().toString());

            Toast.makeText(this,
                    "Eliminado",
                    Toast.LENGTH_LONG).show();

            Intent i=new Intent(DeleteActivity.this,
                    MainActivity.class);
            startActivity(i);

        }catch (Exception e){
            Toast.makeText(this,
                    "Error: "+e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
    public void inicializar_variables(){
        txtID=findViewById(R.id.etxID_delete);
        txtEmail=findViewById(R.id.txtEmail_delete);
        txtUsuario=findViewById(R.id.txtUsuario_delete);
        txtTel=findViewById(R.id.txtTelefono_delete);
        txtFechaNac=findViewById(R.id.txtFechaNac_delete);
    }

}
