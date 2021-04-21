package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.User;
import service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ApiPhonebookHandler implements HttpHandler {
    private final UserService userService = new UserService();

    public void handle(HttpExchange t) throws IOException {
        String response;
        if (!t.getRequestMethod().equals("GET")) {
            t.sendResponseHeaders(405, -1);
            t.close();
            return;
        }
        Map<String, String> phonebook = userService.getPhonebook();
        response = new ObjectMapper().writeValueAsString(phonebook);
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
