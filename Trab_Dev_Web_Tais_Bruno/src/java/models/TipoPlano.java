package models;

public class TipoPlano {
    private int id;
    private String descricao;
    
    public TipoPlano(String descricao){
        this.descricao = descricao;
    }
    
    public TipoPlano() {
        
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
