package com.example.mibdsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOContactos {
    SQLiteDatabase _sqLiteDatabase;
    Context ctx;

    public DAOContactos(Context ctx) {
        this.ctx = ctx;
        _sqLiteDatabase =
                new MiDB(ctx).getWritableDatabase();
    }

    public long insert(Contacto contacto){
        ContentValues contentValues
                = new ContentValues();

        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[1],
                contacto.getUsuario());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[2],
                contacto.getEmail());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[3],
                contacto.getTel());

        return  _sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,
                null, contentValues);
    }
    public int update(Contacto contacto){
        ContentValues contentValues=
                new ContentValues();
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[1],
                contacto.getUsuario());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[2],
                contacto.getEmail());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[3],
                contacto.getTel());

        return _sqLiteDatabase.update(MiDB.TABLE_NAME_CONTACTOS,
                contentValues,
                "_id = "+
               String.valueOf(contacto.getId()),
                null);
}

    public int eliminar(String id){

        return _sqLiteDatabase.delete(MiDB.TABLE_NAME_CONTACTOS,
                                      "_id="+id,null);
    }




    public List<Contacto> getAll (){
        List<Contacto> lst=null;

        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);

        if (c.moveToFirst() ){
            lst = new ArrayList<Contacto>();
            do {
                Contacto contacto =
                        new Contacto(c.getInt(0), c.getString(1),
                                c.getString(2), c.getString(3));
                lst.add(contacto);

            }while(c.moveToNext());
        }
        return  lst;

    }

    public Cursor getAllCursor (){


        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);


        return  c;

    }
    public Cursor getAllByUsuario(String criterio){

        Cursor c = _sqLiteDatabase.query(
                MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                "usuario like %?%",
                new String[]{criterio},
                null,
                null,null

        );
        return c;
    }

    public Contacto obtenerContacto(String campo){
        try {
            Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                    MiDB.COLUMNS_NAME_CONTACTO,
                    "_id="+campo,
                    null,
                    null,
                    null,
                    null,
                    null);

            c.moveToFirst();
            Contacto contacto =
                    new Contacto(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3));
            return contacto;
        }catch (Exception e){
            return null;
        }

    }

}
