package util;

import com.sun.net.httpserver.HttpServer;
import handler.ApiChangeUserLastnameHandler;
import handler.ApiFindUserHandler;
import handler.ApiPhonebookHandler;
import handler.ApiSortedByAgeHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerController {
    private static HttpServer server;

    public static void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/users/finduser", new ApiFindUserHandler());
            server.createContext("/users/changeuserlastname", new ApiChangeUserLastnameHandler());
            server.createContext("/users/getallsortedbyage", new ApiSortedByAgeHandler());
            server.createContext("/users/phonebook", new ApiPhonebookHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        server.stop(0);
    }
}
