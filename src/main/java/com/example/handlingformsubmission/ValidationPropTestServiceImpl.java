package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationPropTestServiceImpl<T> implements ValidationPropTestService<T> {

    @Override
    public GenericResponse valida(CamposDTO campos) {
        GenericResponse response = new GenericResponse();
        View view = readJson();

//        Se realizan las validaciones
        GenericResponseGlobal<T> generic = validaDatos(view, (T) campos);

        ObjectMapper mapper = new ObjectMapper();
        //Se obtiene el DTO original, pero con cambios de mayúsculas o minúsculas según se haya especificado en las reglas
        campos = mapper.convertValue(generic.getT(), CamposDTO.class);

        response.setError(generic.getError());
        response.setIdError(generic.getIdError());
        return response;
    }

    @Override
    public View readJson() {
        View view = new View();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("propuesta.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            Gson gson = new GsonBuilder().create();
            view = gson.fromJson(obj.toString(), View.class);
            System.out.println(view.getNombre());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public GenericResponseGlobal<T> validaDatos(View view, T t) {
        ObjectMapper oMapper = new ObjectMapper();

        // object -> Map
        Map<String, Object> map = oMapper.convertValue(t, Map.class);
        System.out.println(map);

        try {
            for (FieldsProperties obj : view.getListaCampos()) {
                if (map.containsKey(obj.getIdCampo())) {
                    // Se obtiene el valor del campo a evaluar
                    String value = map.get(obj.getIdCampo()).toString();
                    // Si en la validación se pide que el campo sea requerido, entonces,
                    // se evalua que el valor obtenido sea diferente de null y diferente a vacio

                    if (obj.getRequerido() && (value == null || value.isEmpty()))
                        throw new Exception(obj.getMensajeError());
                    // Si en la validación se pide que se convierta a mayúsculas o minúsculas, entonces,
                    // se convierte a mayúsculas o minúsculas, pero si la validación llega como null, entonces, no se le hace nada al valor
                    if (obj.getConvertir() != null && obj.getConvertir().equals("Mayusculas"))
                        map.replace(obj.getIdCampo(), value.toUpperCase());
                    if (obj.getConvertir() != null && obj.getConvertir().equals("Minusculas"))
                        map.replace(obj.getIdCampo(), value.toLowerCase());

                    // Válida las expresiones regulares
                    if (obj.getExpresionRegular() != null && (value == null || value.isEmpty()))
                        throw new Exception(obj.getMensajeError());
                    else if (obj.getExpresionRegular() != null && (value != null && !value.isEmpty()))
                        if (!pattern(value, obj.getExpresionRegular()))
                            throw new Exception(obj.getMensajeError());

                    // Válida la longitud minima
                    if (obj.getMinimoCaracteres() != null && (value == null || value.isEmpty()))
                        throw new Exception(obj.getMensajeError());
                    else if (obj.getMinimoCaracteres() != null && (value != null && !value.isEmpty()))
                        if (value.length() < Integer.parseInt(obj.getMinimoCaracteres()))
                            throw new Exception(obj.getMensajeError());

                    // Válida la longitud maxima
                    if (obj.getMaximoCaracteres() != null && (value == null || value.isEmpty()))
                        throw new Exception(obj.getMensajeError());
                    else if (obj.getMaximoCaracteres() != null && (value != null && !value.isEmpty()))
                        if (value.length() > Integer.parseInt(obj.getMaximoCaracteres()))
                            throw new Exception(obj.getMensajeError());
                }
            }
        } catch (Exception exception) {
            return new GenericResponseGlobal<>(exception.getMessage(), 1, t);
        }


        return new GenericResponseGlobal<>();
    }

    private static boolean pattern(String value, String regex) {
        // compila una cadena en un objeto Pattern
        Pattern p = Pattern.compile(regex);

        // Usa el objeto Pattern para crear un objeto Matcher
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
