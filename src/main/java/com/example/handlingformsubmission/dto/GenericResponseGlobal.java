package com.example.handlingformsubmission.dto;

public class GenericResponseGlobal<T> {
    private String error = "Exito";
    private int idError = 0;

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public GenericResponseGlobal(String error, int idError, T t) {
        this.error = error;
        this.idError = idError;
        this.t = t;
    }

    public GenericResponseGlobal() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getIdError() {
        return idError;
    }

    public void setIdError(int idError) {
        this.idError = idError;
    }
}
