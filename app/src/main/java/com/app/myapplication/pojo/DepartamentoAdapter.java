package com.app.myapplication.pojo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.app.myapplication.EmpleadoActivity;
import com.app.myapplication.R;

import java.util.List;

public class DepartamentoAdapter extends ArrayAdapter<Departamento>{
    private Context context;
    private List<Departamento> emp;

    public DepartamentoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Departamento> objects) {
        super(context, resource, objects);
        this.context = context;
        this.emp = objects;
    }
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_department, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtDeparmentId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtDepartmentDescription);

        txtUserId.setText(String.format("#ID: %s", emp.get(pos).getId()));
        txtUsername.setText(String.format("Nombre: %s", emp.get(pos).getNameDepartment()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, EmpleadoActivity.class);
                intent.putExtra("Departamento id:", String.valueOf(emp.get(pos).getId()));
                intent.putExtra("Departamento nombre:", emp.get(pos).getNameDepartment());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}

