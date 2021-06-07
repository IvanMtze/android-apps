package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.pojo.Empleado;
import com.app.myapplication.remote.APIUtils;
import com.app.myapplication.remote.DepartamentoService;
import com.app.myapplication.remote.PersonaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoActivity extends AppCompatActivity {
    PersonaService personaService;
    EditText edtUId,edtEmpleadoname,edtEmpleadoDireccion,edtEmpleadoTelefono;
    Button btnSave,btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);
        setTitle("Inicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtEmpleadoname = (EditText) findViewById(R.id.edtEmpleadoName);
        edtEmpleadoDireccion = (EditText) findViewById(R.id.edtEmpleadoDireccion);
        edtEmpleadoTelefono = (EditText) findViewById(R.id.edtEmpleadoTelefono);



        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        personaService = APIUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        final String EmpleadoId = extras.getString("Empleado_id");
        String EmpleadoName = extras.getString("Empleado_name");
        String EmpleadoDireccion = extras.getString("Empleado_direccion");
        String EmpleadoTelefono = extras.getString("Empleado_telefono");

        edtUId.setText(EmpleadoId);
        edtEmpleadoname.setText(EmpleadoName);
        edtEmpleadoDireccion.setText(EmpleadoDireccion);
        edtEmpleadoTelefono.setText(EmpleadoTelefono);
        if(EmpleadoId != null && EmpleadoId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empleado u = new Empleado();
                u.setNameEmployee(edtEmpleadoname.getText().toString());
                u.setAddressEmployee(edtEmpleadoDireccion.getText().toString());
                u.setPhoneNumberEmployee(edtEmpleadoTelefono.getText().toString());
                if(EmpleadoId != null && EmpleadoId.trim().length() > 0){
                    //update Empleado
                    updateEmpleado( u);
                } else {
                    //add Empleado
                    addEmpleado(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmpleado(Long.parseLong(EmpleadoId));

                Intent intent = new Intent(EmpleadoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addEmpleado(Empleado u){
        Call<Empleado> call = personaService.addEmpleado(u);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this, "Empleado created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateEmpleado(Empleado u){
        Call<Empleado> call = personaService.updateEmpleado( u);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this, "Empleado updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteEmpleado(Long id){
        Call<Empleado> call = personaService.deleteEmpleado(id);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this, "Empleado deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
