package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.CamposDTO;
import com.example.handlingformsubmission.dto.GenericResponse;
import com.example.handlingformsubmission.dto.GenericResponseGlobal;
import com.example.handlingformsubmission.dto.View;

public interface ValidationPropTestService<T> {
    GenericResponseGlobal<T> validaDatos(View view, T t);

    GenericResponse valida(CamposDTO campos);

    View readJson();
}
