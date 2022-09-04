package com.example.handlingformsubmission;

import com.example.handlingformsubmission.dto.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static com.example.handlingformsubmission.configuration.Global.*;

@Controller
public class GreetingController {

    private final GreetingService greetingService;


    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * Este es el metodo del login
     *
     * @param token Se le agrega el required = false para que no mande error en caso de que el token aún no exista en las cookies
     * @return Retorna al login o al home según la validación
     */
    @GetMapping("/login")
    ModelAndView t1(@CookieValue(name = "token", required = false) String token) {

        // Válida el token, en caso de que el token NO SEA VÁLIDO redirecciona al login. Si el token es VÁLIDO redirecciona al home (vista result)
        ModelAndView model = new ModelAndView(greetingService.validToken(token, "home")).addObject("greeting", new Greeting());
        model.addObject("greeting", new Greeting());

        return model;
    }

    /**
     * En este metodo se valida el usuario y contraseña, ya que la entrada es por tipo post
     * Y en este metodo se agrega el token a las cookies
     *
     * @param response HttpServletResponse
     * @param greeting Object
     * @return Retorna al login o al home según la validación
     */
    @PostMapping("/home")
    public ModelAndView greetingSubmitPost(HttpServletResponse response,
                                           @ModelAttribute Greeting greeting) {

//        String token = greetingService.getToken(greeting.getId(), greeting.getContent());
//
//        // Si el usuario y contraseña son correctos se ingresa al home
//        if (token != null) {
//            ModelAndView model = new ModelAndView("result");
//
//            // Aquí se pueden agregar todos los headers, pero cada que se cambia la vista se eliminan los headers agregados.
//            // Asi que cada que se cambie a una nueva vista se tienen que agregar los headers
//            String headerValue = CacheControl.maxAge(10, TimeUnit.SECONDS)
//                    .getHeaderValue();
//
//            response.addHeader("Cache-Control", headerValue);
//            response.addHeader("token", "123");
//
//            // fin headers
//
//            // Aquí se agregan las cookies, estas son permanentes, no importa si se cambia de vista
//            Cookie cookie = new Cookie("token", token);
//            response.addCookie(cookie);
//            return model;
//        }

        return new ModelAndView(greetingService.login(response, greeting));
    }

    /**
     * En este metodo se valida el usuario y contraseña, ya que es de tipo GET, en su lugar se valida el token
     *
     * @param request  HttpServletRequest
     * @param greeting Object
     * @param token    Se le agrega el required = false para que no mande error en caso de que se quiera entrar al home sin loguearse
     * @return Retorna al login o al home según la validación
     */
    @GetMapping("/home")
    public ModelAndView greetingSubmitGet(HttpServletRequest request,
                                          @ModelAttribute Greeting greeting,
                                          @CookieValue(name = "token", required = false) String token) {

        // Se valida si el token es diferente de NULL y que sea igual al token devuelto por el servicio de token
        ModelAndView model = new ModelAndView(greetingService.validToken(token, "home"));

        // Retorna null porque al entrar a esta nueva vista se borran los headers
        System.out.println("token del header: " + request.getHeader("token"));

        // Este es el token que se guardó en las cookies
        System.out.println("Token de las cookies: " + token);

        model.addObject("greeting", greeting);

        return model;
    }

    @GetMapping("index")
    ModelAndView index(@CookieValue(name = "token", required = false) String token) {

        // Válida el token, en caso de que el token NO SEA VÁLIDO redirecciona al login. Si el token es VÁLIDO redirecciona al home (vista result)
        ModelAndView model = new ModelAndView(greetingService.validToken(token, "index")).addObject("propertiesFields", readJson());
        model.addObject("greeting", new Greeting())
                .addObject("global", variableglobal1);

        return model;
    }

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

}
