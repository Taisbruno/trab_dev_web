package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import connection.Database;
import models.Exames;

@WebServlet(name = "ExameDAO", urlPatterns = {"/ExameDAO"})
public class ExameDAO extends HttpServlet {
    final private String tableName = "exames";
    private Connection conn;

    public ExameDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("ExameDAO: connection failed");
        }
    }
    
    public ArrayList<Exames> getAll() {
        ArrayList<Exames> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Exames exame = new Exames();
                exame.setId(resultSet.getInt("id"));
                exame.setIdConsulta(resultSet.getInt("idconsulta"));
                exame.setIdTipoExame(resultSet.getInt("idtipoexame"));
                arrayList.add(exame);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public ArrayList<Exames> getByConsulta(int idConsulta) {
        ArrayList<Exames> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                    " INNER JOIN consulta ON consulta.id = exames.idconsulta" +
                    " WHERE idconsulta = " + String.valueOf(idConsulta));
            
            while (resultSet.next()) {
                Exames exame = new Exames();
                exame.setId(resultSet.getInt("id"));
                exame.setIdConsulta(resultSet.getInt("idconsulta"));
                exame.setIdTipoExame(resultSet.getInt("idtipoexame"));
                arrayList.add(exame);
            }
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public Exames get(int id) {
        Exames exame = new Exames();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                exame.setId(resultSet.getInt("id"));
                exame.setIdConsulta(resultSet.getInt("idconsulta"));
                exame.setIdTipoExame(resultSet.getInt("idtipoexame"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return exame;
    }
    
    public boolean insert(Exames exame) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (idconsulta, idtipoexame) VALUES (?,?)");
            
            preparedStatement.setInt(1, exame.getIdConsulta());
            preparedStatement.setInt(2, exame.getIdTipoExame());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(Exames exame) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + " SET idconsulta=?, idtipoexame=? WHERE idconsulta=?");
            
            preparedStatement.setInt(1, exame.getIdConsulta());
            preparedStatement.setInt(2, exame.getIdTipoExame());
            preparedStatement.setInt(3, exame.getIdConsulta());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Exames> ListaExames() {
        ArrayList<Exames> exames = new ArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            if (resultSet != null) {
                while (resultSet.next()) {
                    Exames exame = new Exames(resultSet.getInt("id"), resultSet.getInt("idconsulta"), resultSet.getInt("idtipoexame"));
                    exames.add(exame);
            }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Erro (ListaExames)");
        }
        return exames;
    }
    
}
