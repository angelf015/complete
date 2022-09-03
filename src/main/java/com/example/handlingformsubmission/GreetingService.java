package com.example.handlingformsubmission;

import javax.servlet.http.HttpServletResponse;

public interface GreetingService {
    //	Servicio que recibe el usuario y contraseña, lo valida en BD y regresa el token
    String getToken(long id, String pass);

    //	Valida que el token siga activo
    String validToken(String token, String viewRedir);

    String login (HttpServletResponse response,
                  Greeting greeting);
}
