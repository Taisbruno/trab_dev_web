package models;

public class TipoExame {
    private int id;
    private String descricao;

    public TipoExame(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public TipoExame(){
        
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
