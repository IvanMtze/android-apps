package com.app.myapplication.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Empleado {
    @SerializedName("idEmployee")
    @Expose
    private Long idEmployee;

    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;

    @SerializedName("addressEmployee")
    @Expose
    private String addressEmployee;

    @SerializedName("phoneNumberEmployee")
    @Expose
    private String phoneNumberEmployee;


    public Empleado(Long idEmployee, String nameEmployee, String addressEmployee, String phoneNumberEmployee) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.addressEmployee = addressEmployee;
        this.phoneNumberEmployee = phoneNumberEmployee;
    }

    public Empleado() {
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public String getPhoneNumberEmployee() {
        return phoneNumberEmployee;
    }

    public void setPhoneNumberEmployee(String phoneNumberEmployee) {
        this.phoneNumberEmployee = phoneNumberEmployee;
    }
}
