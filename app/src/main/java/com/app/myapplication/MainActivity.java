package com.app.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.app.myapplication.pojo.Departamento;
import com.app.myapplication.pojo.DepartamentoAdapter;
import com.app.myapplication.pojo.Empleado;
import com.app.myapplication.pojo.EmpleadoAdapter;
import com.app.myapplication.remote.APIUtils;
import com.app.myapplication.remote.DepartamentoService;
import com.app.myapplication.remote.PersonaService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnAddEmpleado;
    Button btnGetEmpleadoList;
    Button btnAddDepto;
    Button btnGetDeptoList;
    ListView listView;
    ListView listDeptoView;


    PersonaService personaService;
    DepartamentoService deptoService;
    List<Empleado> list = new ArrayList<Empleado>();
    List<Departamento> listDepto = new ArrayList<Departamento>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Inicio");

        btnAddEmpleado = (Button)findViewById(R.id.btnAddUser);
        btnGetEmpleadoList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listGetUserList);
        personaService = APIUtils.getUserService();

        btnGetEmpleadoList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                getEmpleadoList();
            }
        });

        btnAddEmpleado.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, EmpleadoActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });

        btnAddDepto = (Button)findViewById(R.id.btnAddDept);
        btnGetDeptoList = (Button) findViewById(R.id.btnGetDept);
        listDeptoView = (ListView) findViewById(R.id.listGetDeptList);
        deptoService = APIUtils.getDepartmentService();

        btnGetDeptoList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                getDeptList();
            }
        });

        btnAddDepto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, DepartamentoActivity.class);
                intent.putExtra("depto_name", "");
                startActivity(intent);
            }
        });

    }

    public void getEmpleadoList(){
        Call<List<Empleado>> call = personaService.getEmpleados();
        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new EmpleadoAdapter(MainActivity.this, R.layout.list_user,list));
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.e("Error: ",t.getMessage());
            }
        });
    }

    public void getDeptList(){
        Call<List<Departamento>> call = deptoService.getDepartment();
        call.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                if(response.isSuccessful()){
                    listDepto = response.body();
                    listDeptoView.setAdapter(new DepartamentoAdapter(MainActivity.this, R.layout.list_department,listDepto));
                }
            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                Log.e("Error: ",t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
