package models;

import dao.ExameDAO;
import java.util.List;

public class Consulta {
    private int id;
    private String data;
    private String descricao;
    private String realizada;
    private int idmedico;
    private int idpaciente;
    
    public Consulta(String data, String descricao, String realizada, int idmedico, int idpaciente) {
    this.data = data;
    this.descricao = descricao;
    this.realizada = realizada;
    this.idmedico = idmedico;
    this.idpaciente = idpaciente;
    }

    public Consulta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }
    
    public int getIdMedico() {
        return idmedico;
    }

    public void setIdMedico(int idmedico) {
        this.idmedico = idmedico;
    }
    
    public int getIdPaciente() {
        return idpaciente;
    }

    public void setIdPaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }
    
    public String getExames(){
        ExameDAO exameDAO = new ExameDAO();
        List<Exames> exames = exameDAO.ListaExames();
        for(Exames exame : exames){
            if(exame.getIdConsulta() == this.id){
                return exame.getDescricaoTipoExame();
            }
        }
        return "";
    }
    
}
