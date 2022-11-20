package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class Database extends HttpServlet {
    private static Connection conn = null;
    
    public static Connection newConnection() throws SQLException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica", "root", "");    
            } catch(ClassNotFoundException e) {
                System.out.println("Driver not found");
            }
        }
        return conn;
    }

}