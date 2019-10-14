package com.example.mibdsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements View.OnLongClickListener */{

    Button btnAgregar,btnEditar,btnDelete;
    RecyclerView recycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Contacto> listContactos = new
                ArrayList<>();
        MyAdapter adaptador = null;

        recycler = findViewById(R.id.my_recycler_view);
        recycler.setLayoutManager(new GridLayoutManager(this,1));

        DAOContactos dao=new DAOContactos(this);
        try {
            for (Contacto c : dao.getAll()) {
                listContactos.add(c);
            }
            adaptador = new MyAdapter(listContactos);
            recycler.setAdapter(adaptador);
        }catch (Exception e){}

        btnAgregar=findViewById(R.id.btnAgregarContac);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,
                        AgregarActivity.class);
                startActivity(i);

            }
        });


        btnEditar=findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,
                                UpdateActivity.class);
                startActivity(i);
            }
        });

        btnDelete=findViewById(R.id.btnEliminar);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,
                        DeleteActivity.class);
                startActivity(i);
            }
        });

        /*
        DAOContactos dao = new DAOContactos(this);
        dao.insert(new Contacto(0, "perronegro",
                "perronegro@","445"));
        dao.insert(new Contacto(0, "perroblanco",
                "perroblanco@","544"));
         for (Contacto c : dao.getAll()){
             Toast.makeText(this,
                     c.usuario,
                     Toast.LENGTH_SHORT).show();
         }

         lv = findViewById(R.id.lv);

        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        dao.getAllCursor(),
                        new String[]{"usuario","email"},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                );
        lv.setAdapter(adp);
*/

    }

}
