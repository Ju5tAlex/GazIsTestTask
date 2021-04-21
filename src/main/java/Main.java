import org.h2.tools.RunScript;
import util.ConnectionFromBd;
import util.HttpServerController;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Enter \"exit\" to shutdown server");
            Connection connection = ConnectionFromBd.getConnection();
            RunScript.execute(connection, new InputStreamReader(Main.class.getResourceAsStream("database/initDB.sql")));
            RunScript.execute(connection, new InputStreamReader(Main.class.getResourceAsStream("database/populateDB.sql")));
            HttpServerController.startServer();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if ((scanner.nextLine()).equalsIgnoreCase("exit")) {
                    HttpServerController.stopServer();
                    break;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
