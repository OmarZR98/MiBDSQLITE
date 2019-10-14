package com.example.mibdsqlite;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Contacto> listContacto;
    private View.OnClickListener onClickListener;

    public void setOnItemClickListener(View.OnClickListener clickListener){
        this.onClickListener=clickListener;
    }


    public MyAdapter(ArrayList<Contacto> listContactos) {
        this.listContacto = listContactos;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =
                LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.item_list,null,false);
        v.setOnClickListener(onClickListener);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listContacto.get(position).getId()));
        holder.txtUser.setText(listContacto.get(position).getUsuario());
        holder.txtEmail.setText(listContacto.get(position).getEmail());
        holder.txtTelefono.setText(listContacto.get(position).getTel());
        holder.txtFecha.setText(String.valueOf(listContacto.get(position).getFecNac()));
    }

    @Override
    public int getItemCount() { return listContacto.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtid,txtUser,txtEmail,txtTelefono,txtFecha;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid=itemView.findViewById(R.id.txtId);
            txtUser=itemView.findViewById(R.id.txtUser);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtTelefono=itemView.findViewById(R.id.txtTel);
            txtFecha=itemView.findViewById(R.id.txtFecha);


        }
    }
}
