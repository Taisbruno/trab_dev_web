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
import models.TipoPlano;

@WebServlet(name = "TipoPlanoDAO", urlPatterns = {"/TipoPlanoDAO"})
public class TipoPlanoDAO extends HttpServlet {
    final private String tableName = "tipoplano";
    private Connection conn;

    public TipoPlanoDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("tipoplanoDAO: connection failed");
        }
    }
    
    public ArrayList<TipoPlano> getAll() {
        ArrayList<TipoPlano> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                TipoPlano tipoplano = new TipoPlano();
                tipoplano.setId(resultSet.getInt("id"));
                tipoplano.setDescricao(resultSet.getString("descricao"));
                arrayList.add(tipoplano);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public TipoPlano get(int id) {
        TipoPlano tipoplano = new TipoPlano();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                tipoplano.setId(resultSet.getInt("id"));
                tipoplano.setDescricao(resultSet.getString("descricao"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return tipoplano;
    }
    
    public TipoPlano get(String descricao) {
        TipoPlano tipoplano = new TipoPlano();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE descricao = '" + descricao + "'");
            
            if (resultSet.next()) {
                tipoplano.setId(resultSet.getInt("id"));
                tipoplano.setDescricao(resultSet.getString("descricao"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return tipoplano;
    }
    
    public boolean insert(TipoPlano tipoplano) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (descricao) VALUES (?)");
            
            preparedStatement.setString(1, tipoplano.getDescricao());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(TipoPlano tipoplano) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + 
                    " SET descricao=? WHERE id=?");
            
            preparedStatement.setString(1, tipoplano.getDescricao());
            preparedStatement.setInt(2, tipoplano.getId());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM " + this.tableName + 
                    " WHERE id = " + String.valueOf(id));
            preparedStatement.executeUpdate();

            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}
