package com.app.myapplication.remote;

import com.app.myapplication.pojo.Departamento;
import com.app.myapplication.pojo.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoService {
    @GET("/departments")
    Call<List<Departamento>> getDepartment();
    @GET("/departments/{id}")
    Call<List<Departamento>> getDepartmentsById(@Path("id") Long id);
    @POST("/departments")
    Call<Departamento> addDepartment(@Body Departamento empleado);
    @PUT("/departments")
    Call<Departamento> updateDepartment(@Body Departamento empleado);
    @DELETE("/departments/{id}")
    Call<Departamento> deleteDepartment(@Path("id") Long id);

}
