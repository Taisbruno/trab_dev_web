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
import models.Administrador;

@WebServlet(name = "AdministradorDAO", urlPatterns = {"/AdministradorDAO"})
public class AdministradorDAO extends HttpServlet {
    final private String tableName = "administrador";
    private Connection conn;

    public AdministradorDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("AdministradorDAO: connection failed");
        }
    }
    
    public ArrayList<Administrador> getAll() {
        ArrayList<Administrador> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getString("cpf"));
                administrador.setSenha(resultSet.getString("senha"));
                arrayList.add(administrador);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public Administrador get(int id) {
        Administrador administrador = new Administrador();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getString("cpf"));
                administrador.setSenha(resultSet.getString("senha"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return administrador;
    }
    
    public Administrador get(String cpf) {
        Administrador administrador = new Administrador();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE cpf = '" + cpf + "'");
            
            if (resultSet.next()) {
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getString("cpf"));
                administrador.setSenha(resultSet.getString("senha"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return administrador;
    }
    
    public boolean insert(Administrador administrador) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (nome, cpf, senha) VALUES (?,?,?)");
            
            preparedStatement.setString(1, administrador.getNome());
            preparedStatement.setString(2, administrador.getCpf());
            preparedStatement.setString(3, administrador.getSenha());
            preparedStatement.executeUpdate();
            
            return true;
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(Administrador administrador) {
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + " SET nome=?, cpf=?, senha=? WHERE id=?");
            
            preparedStatement.setString(1, administrador.getNome());
            preparedStatement.setString(2, administrador.getCpf());
            preparedStatement.setString(3, administrador.getSenha());
            preparedStatement.setInt(4, administrador.getId());
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
    
    public Administrador login(String cpf, String senha) {
        Administrador administrador = new Administrador();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                " WHERE cpf = '" + String.valueOf(cpf) + "' AND senha = '" + String.valueOf(senha) + "'");
            
            if (resultSet.next()) {
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getString("cpf"));
                administrador.setSenha(resultSet.getString("senha"));
            } else {
                
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return administrador;
    }
}
