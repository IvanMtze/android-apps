package com.app.myapplication.remote;

import android.app.Person;

import com.app.myapplication.pojo.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonaService {
    @GET("/employees")
    Call<List<Empleado>> getEmpleados();
    @GET("/employees/{id}")
    Call<List<Empleado>> getEmpleadosById(@Path("id") Long id);
    @POST("/employees")
    Call<Empleado> addEmpleado (@Body Empleado empleado);
    @PUT("/employees")
    Call<Empleado> updateEmpleado(@Body Empleado empleado);
    @DELETE("/employees/{id}")
    Call<Empleado> deleteEmpleado(@Path("id") Long id);
}
