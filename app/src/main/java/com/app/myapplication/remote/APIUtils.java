package com.app.myapplication.remote;

import com.app.myapplication.pojo.Departamento;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://api";

    public static PersonaService getUserService(){
        return RetrofitClient.getClient(API_URL).create(PersonaService.class);
    }
    public static DepartamentoService getDepartmentService(){
        return RetrofitClient.getClient(API_URL).create(DepartamentoService.class);
    }
}
