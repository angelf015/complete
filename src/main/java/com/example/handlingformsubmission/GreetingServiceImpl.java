package com.example.handlingformsubmission;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class GreetingServiceImpl implements GreetingService {

//    Este es el token que te regresa tu servicio
    private final String initialToken = "jNK_DGszYOMEpPOFoRGjuyJ5KX9kaJQKCl3cujlHoXklCS8Ij6b-QBmhv0jVwVC54KcNXkzyM62xpswqcjo9Ajf-n-rWzSaIoiYNglaXhtPspziZ0PcTKzTMAvw8si3A7BlcD98M-IjIxYjxieVYAPWVtcvomWi";

    //	Servicio que recibe el usuario y contraseña, lo valida en BD y regresa el token
    @Override
    public String getToken(long id, String pass) {
        return id == 123 && pass.equals("user") ? initialToken : null;
    }

    //	Valida que el token siga activo
    @Override
    public String validToken(String token, String viewRedir) {
        // Si el token es válido regresa un string con el nombre de la vista a redirigir, Este nombre llega en la variable viewRedir
        // En caso de que no sea correcto el token, regresa un String con el nombre de la vista Login para volver a iniciar sesión
        return token != null && token.equals(initialToken) ? viewRedir : "login";

    }

    @Override
    public String login(HttpServletResponse response,
                        Greeting greeting) {
        String token = getToken(greeting.getId(), greeting.getContent());

        // Si el usuario y contraseña son correctos se ingresa al home
        if (token != null) {

            // Aquí se pueden agregar todos los headers, pero cada que se cambia la vista se eliminan los headers agregados.
            // Asi que cada que se cambie a una nueva vista se tienen que agregar los headers
            String headerValue = CacheControl.maxAge(10, TimeUnit.SECONDS)
                    .getHeaderValue();

            response.addHeader("Cache-Control", headerValue);
            response.addHeader("token", "123");

            // fin headers

            // Aquí se agregan las cookies, estas son permanentes, no importa si se cambia de vista
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            return "home";
        }
        return "login";
    }
}
