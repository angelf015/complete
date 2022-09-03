package com.example.handlingformsubmission.dto;

public class FieldsProperties {
     private String idCampo;
     private String type;
     private String ValorDefault;
     private boolean readOnly;
     private boolean Requerido;
     private String Convertir;
     private String ExpresionRegular;
     private String MinimoCaracteres;
     private String MaximoCaracteres;
     private String MensajeError;

     public String getIdCampo() {
          return idCampo;
     }

     public void setIdCampo(String idCampo) {
          this.idCampo = idCampo;
     }

     public String getType() {
          return type;
     }

     public void setType(String type) {
          this.type = type;
     }

     public String getValorDefault() {
          return ValorDefault;
     }

     public void setValorDefault(String valorDefault) {
          ValorDefault = valorDefault;
     }

     public boolean getReadOnly() {
          return readOnly;
     }

     public void setReadOnly(boolean readOnly) {
          this.readOnly = readOnly;
     }

     public boolean getRequerido() {
          return Requerido;
     }

     public void setRequerido(boolean requerido) {
          Requerido = requerido;
     }

     public String getConvertir() {
          return Convertir;
     }

     public void setConvertir(String convertir) {
          Convertir = convertir;
     }

     public String getExpresionRegular() {
          return ExpresionRegular;
     }

     public void setExpresionRegular(String expresionRegular) {
          ExpresionRegular = expresionRegular;
     }

     public String getMinimoCaracteres() {
          return MinimoCaracteres;
     }

     public void setMinimoCaracteres(String minimoCaracteres) {
          MinimoCaracteres = minimoCaracteres;
     }

     public String getMaximoCaracteres() {
          return MaximoCaracteres;
     }

     public void setMaximoCaracteres(String maximoCaracteres) {
          MaximoCaracteres = maximoCaracteres;
     }

     public String getMensajeError() {
          return MensajeError;
     }

     public void setMensajeError(String mensajeError) {
          MensajeError = mensajeError;
     }
}
