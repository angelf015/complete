package com.example.handlingformsubmission.dto;

import java.util.List;

public class View {
    private String Nombre;
    private List<FieldsProperties> listaCampos;

    public List<FieldsProperties> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<FieldsProperties> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
