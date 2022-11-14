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
import models.Paciente;

@WebServlet(name = "PacienteDAO", urlPatterns = {"/PacienteDAO"})
public class PacienteDAO extends HttpServlet {
    final private String tableName = "paciente";
    private Connection conn;

    public PacienteDAO() throws SQLException {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("PacienteDAO: connection failed");
        }
    } 
    public ArrayList<Paciente> getAll() {
        ArrayList<Paciente> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setAutorizado(resultSet.getString("autorizado"));
                paciente.setIdTipoPlano(resultSet.getInt("idtipoplano"));
                arrayList.add(paciente);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
        
    } 
    
    public Paciente get(int id) {
        Paciente paciente = new Paciente();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setAutorizado(resultSet.getString("autorizado"));
                paciente.setIdTipoPlano(resultSet.getInt("idtipoplano"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return paciente;
    }
    
    public Paciente get(String cpf) {
        Paciente paciente = new Paciente();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE cpf = '" + cpf + "'");
            
            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setAutorizado(resultSet.getString("autorizado"));
                paciente.setIdTipoPlano(resultSet.getInt("idtipoplano"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return paciente;
    }
    
    public boolean insert(Paciente paciente) {
        try {
            if (get(paciente.getCpf()).getCpf() != null) {
                System.out.println("SQL Error: Invalid CPF");
                return false;
            }
            
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (cpf, senha, nome, autorizado, idtipoplano) VALUES (?,?,?,?,?)");
            
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getSenha());
            preparedStatement.setString(3, paciente.getNome());
            preparedStatement.setString(4, paciente.getAutorizado());
            preparedStatement.setInt(5, Integer.valueOf(paciente.getIdTipoPlano()));
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Paciente paciente) {
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + " SET cpf=?, senha=?, nome=?, autorizado=?, idtipoplano=? WHERE id=?");
            
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getSenha());
            preparedStatement.setString(3, paciente.getNome());
            preparedStatement.setString(4, paciente.getAutorizado());
            preparedStatement.setInt(5, paciente.getIdTipoPlano());
            preparedStatement.setInt(6, paciente.getId());
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
    
    public Paciente login(String cpf, String senha) {
        Paciente paciente = new Paciente();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                " WHERE cpf = '" + String.valueOf(cpf) + "' AND senha = '" + String.valueOf(senha) + "'");
            
            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setAutorizado(resultSet.getString("autorizado"));
                paciente.setIdTipoPlano(resultSet.getInt("idtipoplano"));
            } 
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }   
        return paciente;
    }
}
   
