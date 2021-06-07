package com.app.myapplication.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departamento {
    @SerializedName("idDepartment")
    @Expose
    private Long id;

    @SerializedName("nameDepartment")
    @Expose
    private String nameDepartment;

    public Departamento(Long id, String nameDepartment) {
        this.id = id;
        this.nameDepartment = nameDepartment;
    }

    public Departamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
