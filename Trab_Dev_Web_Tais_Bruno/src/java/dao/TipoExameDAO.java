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
import models.TipoExame;

@WebServlet(name = "TipoExameDAO", urlPatterns = {"/TipoExameDAO"})
public class TipoExameDAO extends HttpServlet {
    final private String tableName = "tipoexame";
    private Connection conn;

    public TipoExameDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("TipoExameDAO: connection failed");
        }
    }
    
    public ArrayList<TipoExame> getAll() {
        ArrayList<TipoExame> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                TipoExame tipoexame = new TipoExame();
                tipoexame.setId(resultSet.getInt("id"));
                tipoexame.setDescricao(resultSet.getString("descricao"));
                arrayList.add(tipoexame);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    } 
    
    public TipoExame get(int id) {
        TipoExame tipoexame = new TipoExame();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                tipoexame.setId(resultSet.getInt("id"));
                tipoexame.setDescricao(resultSet.getString("descricao"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return tipoexame;
    }
    
    public boolean insert(TipoExame tipoexame) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (descricao) VALUES (?)");
            
            preparedStatement.setString(1, tipoexame.getDescricao());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(TipoExame tipoexame) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + " SET descricao=? WHERE id=?");
            
            preparedStatement.setString(1, tipoexame.getDescricao());
            preparedStatement.setInt(2, tipoexame.getId());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<TipoExame> ListaTipoExames() {
        ArrayList<TipoExame> tipoexames = new ArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            if (resultSet != null) {
                while (resultSet.next()) {
                TipoExame tipoexame = new TipoExame(resultSet.getInt("id"), resultSet.getString("descricao"));
                tipoexames.add(tipoexame);
            }
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return tipoexames;
    }
    
}
