package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.CamposDTO;
import com.example.handlingformsubmission.dto.GenericResponse;
import com.example.handlingformsubmission.dto.GenericResponseGlobal;
import com.example.handlingformsubmission.dto.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private final ValidationPropTestService<Object> validationPropTestService;

    @Autowired
    public TestServiceImpl(ValidationPropTestService<Object> validationPropTestService) {
        this.validationPropTestService = validationPropTestService;
    }

    @Override
    public GenericResponse valida(CamposDTO campos) {
        GenericResponse response = new GenericResponse();
        View view = validationPropTestService.readJson();

//        Se realizan las validaciones
        GenericResponseGlobal<Object> generic = validationPropTestService.validaDatos(view, campos);

        ObjectMapper mapper = new ObjectMapper();
        //Se obtiene el DTO original, pero con cambios de mayúsculas o minúsculas según se haya especificado en las reglas
        campos = mapper.convertValue(generic.getT(), CamposDTO.class);

        response.setError(generic.getError());
        response.setIdError(generic.getIdError());
        return response;
    }
}
