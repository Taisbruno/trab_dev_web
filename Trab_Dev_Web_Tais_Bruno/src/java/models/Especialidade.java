package models;

public class Especialidade {
    private int id;
    private String descricao;
    
    public Especialidade(String descricao){
        this.descricao = descricao;
    }
    
    public Especialidade() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
