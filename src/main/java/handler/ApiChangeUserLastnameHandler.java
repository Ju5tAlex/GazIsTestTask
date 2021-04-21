package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.User;
import service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ApiChangeUserLastnameHandler implements HttpHandler {
    private final UserService userService = new UserService();

    public void handle(HttpExchange t) throws IOException {
        String response;
        if (!t.getRequestMethod().equals("PATCH")) {
            t.sendResponseHeaders(405, -1);
            t.close();
            return;
        }
        Map<String, String> params = HandlerUtils.queryToMap(t.getRequestURI().getQuery());
        if (params.containsKey("username") & params.containsKey("lastname")) {
            if (HandlerUtils.isLastnameValid(params.get("lastname"))) {
                User user = userService.changeUserLastName(params.get("username"), params.get("lastname"));
                if (user != null) {
                    response = new ObjectMapper().writeValueAsString(user);
                    t.sendResponseHeaders(200, response.length());
                }
                else {
                    response = "User with username '" + params.get("username") + "' wasn't found";
                }
            } else {
                response = "Wrong 'lastname' format. Must contain only alphabetic characters and contain from 3 to 255 characters";
            }
        } else {
            response = "Parameter 'username' or 'lastname' wasn't found in request url";
        }
        if (t.getResponseHeaders().size() == 0) t.sendResponseHeaders(400, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
