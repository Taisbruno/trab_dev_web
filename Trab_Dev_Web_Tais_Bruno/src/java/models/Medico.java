package models;

import dao.EspecialidadeDAO;
import java.util.ArrayList;

public class Medico {
    private int id;
    private String nome;
    private int crm;
    private String estadocrm;
    private String cpf;
    private String senha;
    private String autorizado;
    private int idespecialidade;

    public Medico(String nome, int crm, String estadocrm, String cpf, String senha, String autorizado, int idespecialidade){
        this.nome = nome;
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.cpf = cpf;
        this.senha = senha;
        this.autorizado = autorizado;
        this.idespecialidade = idespecialidade;
    }
    
    public Medico() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }
    
    public String getEstadoCrm() {
        return estadocrm;
    }

    public void setEstadoCrm(String estadocrm) {
        this.estadocrm = estadocrm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }
    
    public int getIdEspecialidade() {
        return idespecialidade;
    }

    public void setIdEspecialidade(int idespecialidade) {
        this.idespecialidade = idespecialidade;
    }
    
    public String getNomeEspecialidade(int idespecialidade){
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO(); 
        ArrayList<Especialidade> especialidades = especialidadeDAO.getAll();
        for(Especialidade especialidade : especialidades){
            if(especialidade.getId() == idespecialidade) {
                return especialidade.getDescricao();
            }
        }
        return "";
    }
    
}

