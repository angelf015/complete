package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.CamposDTO;
import com.example.handlingformsubmission.dto.GenericResponse;

public interface TestService {
    GenericResponse valida(CamposDTO campos);
}
