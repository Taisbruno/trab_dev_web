package dao;

import models.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import connection.Database;

@WebServlet(name = "EspecialidadeDAO", urlPatterns = {"/EspecialidadeDAO"})
public class EspecialidadeDAO extends HttpServlet {
    final private String tableName = "especialidade";
    private Connection conn;

    public EspecialidadeDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("EspecialidadeDAO: connection failed");
        }
    }
    
    public ArrayList<Especialidade> getAll() {
        ArrayList<Especialidade> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(resultSet.getInt("id"));
                especialidade.setDescricao(resultSet.getString("descricao"));
                arrayList.add(especialidade);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public Especialidade get(int id) {
        Especialidade especialidade = new Especialidade();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                especialidade.setId(resultSet.getInt("id"));
                especialidade.setDescricao(resultSet.getString("descricao"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return especialidade;
    }
    
    public boolean insert(Especialidade especialidade) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (descricao) VALUES (?)");
            
            preparedStatement.setString(1, especialidade.getDescricao());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(Especialidade especialidade) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + " SET descricao=? WHERE id=?");
            
            preparedStatement.setString(1, especialidade.getDescricao());
            preparedStatement.setInt(2, especialidade.getId());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            preparedStatement.executeUpdate();

            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
}
