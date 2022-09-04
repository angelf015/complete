package com.example.handlingformsubmission;

import org.springframework.web.bind.annotation.*;

import static com.example.handlingformsubmission.configuration.Global.*;

@RestController
@RequestMapping("app")
public class TestController {

    @GetMapping("add/{value}")
    String var1(@PathVariable("value") String value) {

        variableglobal1 = value;
        return variableglobal1;
    }


    @GetMapping("select")
    String var1Select() {
        return variableglobal1;
    }
}
