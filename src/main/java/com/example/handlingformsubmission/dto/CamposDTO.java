package com.example.handlingformsubmission.dto;

public class CamposDTO {
    private String txtIdAgente;
    private String slcPersona;
    private String vigenciaDesde;
    private String vigenciaHasta;

    public String getTxtIdAgente() {
        return txtIdAgente;
    }

    public void setTxtIdAgente(String txtIdAgente) {
        this.txtIdAgente = txtIdAgente;
    }

    public String getSlcPersona() {
        return slcPersona;
    }

    public void setSlcPersona(String slcPersona) {
        this.slcPersona = slcPersona;
    }

    public String getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(String vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(String vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }
}
