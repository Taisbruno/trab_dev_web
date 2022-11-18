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
import models.Medico;

@WebServlet(name = "MedicoDAO", urlPatterns = {"/MedicoDAO"})
public class MedicoDAO extends HttpServlet {
    final private String tableName = "medico";
    private Connection conn;

    public MedicoDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("MedicoDAO: connection failed");
        }
    } 
    
    public ArrayList<Medico> getAll() {
        ArrayList<Medico> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Medico medico = new Medico();
                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getInt("crm"));
                medico.setEstadoCrm(resultSet.getString("estadocrm"));
                medico.setCpf(resultSet.getString("cpf"));
                medico.setSenha(resultSet.getString("senha"));
                medico.setAutorizado(resultSet.getString("autorizado"));
                medico.setIdEspecialidade(resultSet.getInt("idespecialidade"));
                arrayList.add(medico);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public Medico get(int id) {
        Medico medico = new Medico();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getInt("crm"));
                medico.setEstadoCrm(resultSet.getString("estadocrm"));
                medico.setCpf(resultSet.getString("cpf"));
                medico.setSenha(resultSet.getString("senha"));
                medico.setAutorizado(resultSet.getString("autorizado"));
                medico.setIdEspecialidade(resultSet.getInt("idespecialidade"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return medico;
    }
    
    public Medico get(String cpf) {
        Medico medico = new Medico();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE cpf = '" + cpf + "'");
            
            if (resultSet.next()) {
                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getInt("crm"));
                medico.setEstadoCrm(resultSet.getString("estadocrm"));
                medico.setCpf(resultSet.getString("cpf"));
                medico.setSenha(resultSet.getString("senha"));
                medico.setAutorizado(resultSet.getString("autorizado"));
                medico.setIdEspecialidade(resultSet.getInt("idespecialidade"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return medico;
    }
    
    public boolean insert(Medico medico) throws SQLException {
            try {
                if (get(medico.getCpf()).getCpf() != null) {
                    System.out.println("SQL Error: Invalid CPF");
                    return false;
                }
            
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade) VALUES (?,?,?,?,?,?,?)");
            
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setInt(2, Integer.valueOf(medico.getCrm()));
            preparedStatement.setString(3, medico.getEstadoCrm());
            preparedStatement.setString(4, medico.getCpf());
            preparedStatement.setString(5, medico.getSenha());
            preparedStatement.setString(6, medico.getAutorizado());
            preparedStatement.setInt(7, Integer.valueOf(medico.getIdEspecialidade()));
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(Medico medico) {
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + this.tableName + 
                    " SET nome=?, crm=?, estadocrm=?, cpf=?, senha=?, autorizado=?, idespecialidade=? WHERE id=?");
            
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setInt(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEstadoCrm());
            preparedStatement.setString(4, medico.getCpf());
            preparedStatement.setString(5, medico.getSenha());
            preparedStatement.setString(6, medico.getAutorizado());
            preparedStatement.setInt(7, medico.getIdEspecialidade());
            preparedStatement.executeUpdate();
            
            return true;
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public Medico login(String cpf, String senha) {
        Medico medico = new Medico();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                " WHERE cpf = '" + String.valueOf(cpf) + "' AND senha = '" + String.valueOf(senha) + "'");
            
            if (resultSet.next()) {
                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getInt("crm"));
                medico.setEstadoCrm(resultSet.getString("estadocrm"));
                medico.setCpf(resultSet.getString("cpf"));
                medico.setSenha(resultSet.getString("senha"));
                medico.setAutorizado(resultSet.getString("autorizado"));
                medico.setIdEspecialidade(resultSet.getInt("idespecialidade"));
            } 
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return medico;
    }
}
