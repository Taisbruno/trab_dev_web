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
import java.sql.Timestamp;
import models.Consulta;
import utils.Date;

@WebServlet(name = "ConsultaDAO", urlPatterns = {"/ConsultaDAO"})
public class ConsultaDAO extends HttpServlet {
    final private String tableName = "consulta";
    private Connection conn;

    public ConsultaDAO() {
        try {
            conn = Database.newConnection();
        } catch(SQLException e) {
            System.out.println("ConsultaDAO: connection failed");
        }
    }
    
    public ArrayList<Consulta> getAll() {
        ArrayList<Consulta> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);
            
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setData(Date.format(resultSet.getString("data"), "brazil"));
                consulta.setDescricao(resultSet.getString("descricao"));
                consulta.setRealizada(resultSet.getString("realizada"));
                consulta.setIdMedico(resultSet.getInt("idmedico"));
                consulta.setIdPaciente(resultSet.getInt("idpaciente"));
                arrayList.add(consulta);
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public ArrayList<Consulta> getByPaciente(int idPaciente) {
        ArrayList<Consulta> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                    " INNER JOIN paciente ON paciente.id = consulta.idpaciente" +
                    " WHERE idpaciente = " + String.valueOf(idPaciente));
            
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setData(Date.format(resultSet.getString("data"), "brazil"));
                consulta.setDescricao(resultSet.getString("descricao"));
                consulta.setRealizada(resultSet.getString("realizada"));
                consulta.setIdMedico(resultSet.getInt("idmedico"));
                consulta.setIdPaciente(resultSet.getInt("idpaciente"));
                arrayList.add(consulta);
            }
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public ArrayList<Consulta> getByMedico(int idMedico) {
        ArrayList<Consulta> arrayList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + 
                    " INNER JOIN medico ON medico.id = consulta.idmedico" +
                    " WHERE idmedico = " + String.valueOf(idMedico));
            
            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("id"));
                consulta.setData(Date.format(resultSet.getString("data"), "brazil"));
                consulta.setDescricao(resultSet.getString("descricao"));
                consulta.setRealizada(resultSet.getString("realizada"));
                consulta.setIdMedico(resultSet.getInt("idmedico"));
                consulta.setIdPaciente(resultSet.getInt("idpaciente"));
                arrayList.add(consulta);
            }
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return arrayList;
    }
    
    public Consulta get(int id) {
        Consulta consulta = new Consulta();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName + " WHERE id = " + String.valueOf(id));
            
            if (resultSet.next()) {
                consulta.setId(resultSet.getInt("id"));
                consulta.setData(Date.format(resultSet.getString("data"), "brazil"));
                consulta.setDescricao(resultSet.getString("descricao"));
                consulta.setRealizada(resultSet.getString("realizada"));
                consulta.setIdMedico(resultSet.getInt("idmedico"));
                consulta.setIdPaciente(resultSet.getInt("idpaciente"));
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return consulta;
    }
    
    public boolean insert(Consulta consulta) {
        try {
            String dataConsulta = consulta.getData() + ":00";
            Timestamp dataTimeStamp = Timestamp.valueOf(dataConsulta);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + 
                this.tableName + " (data, descricao, realizada, idmedico, idpaciente) VALUES (?,?,?,?,?)");
            
            preparedStatement.setTimestamp(1, dataTimeStamp);
            preparedStatement.setString(2, consulta.getDescricao());
            preparedStatement.setString(3, consulta.getRealizada());
            preparedStatement.setInt(4, consulta.getIdMedico());
            preparedStatement.setInt(5, consulta.getIdPaciente());
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean update(Consulta consulta) {
        try {
            String dataConsulta = consulta.getData();
            Timestamp dataTimeStamp = null;
            try{
                dataTimeStamp = Timestamp.valueOf(dataConsulta);
            }catch(Exception e){
                dataTimeStamp = Timestamp.valueOf(dataConsulta + ":00");
            }
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + 
                    this.tableName + " SET data=?, descricao=?, realizada=?, idmedico=?, idpaciente=? WHERE id=?");
            
            preparedStatement.setTimestamp(1, dataTimeStamp);
            preparedStatement.setString(2, consulta.getDescricao());
            preparedStatement.setString(3, consulta.getRealizada());
            preparedStatement.setInt(4, consulta.getIdMedico());
            preparedStatement.setInt(5, consulta.getIdPaciente());
            preparedStatement.setInt(6, consulta.getId());
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
