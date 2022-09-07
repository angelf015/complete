package com.example.handlingformsubmission.dto;

public class GenericResponse {
    private String error = "Exito";
    private int idError = 0;

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
