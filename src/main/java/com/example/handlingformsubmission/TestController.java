package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.CamposDTO;
import com.example.handlingformsubmission.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.handlingformsubmission.configuration.Global.*;

@RestController
@RequestMapping("app")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("add/{value}")
    String var1(@PathVariable("value") String value) {

        variableglobal1 = value;
        return variableglobal1;
    }


    @GetMapping("select")
    String var1Select() {
        return variableglobal1;
    }

    @GetMapping("pruebaJson")
    GenericResponse validacionesJson() {

        CamposDTO campos = new CamposDTO();
        campos.setSlcPersona("1");
        campos.setTxtIdAgente("dfsg");
        campos.setVigenciaDesde("25/12/2022");
        campos.setVigenciaHasta("25/11/2022");
//        Se realizan las validaciones
        return testService.valida(campos);
    }

}
