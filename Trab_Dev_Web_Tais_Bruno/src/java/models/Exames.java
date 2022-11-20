package models;

import dao.TipoExameDAO;
import java.util.List;

public class Exames {
    private int id;
    private int idconsulta;
    private int idtipoexame;

    public Exames(int idconsulta, int idtipoexame) {
        this.idconsulta = idconsulta;
        this.idtipoexame = idtipoexame;
    }
    
    public Exames(int id, int idconsulta, int idtipoexame) {
        this.id = id;
        this.idconsulta = idconsulta;
        this.idtipoexame = idtipoexame;
    }
    
    public Exames() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConsulta() {
        return idconsulta;
    }

    public void setIdConsulta(int idconsulta) {
        this.idconsulta = idconsulta;
    }

    public int getIdTipoExame() {
        return idtipoexame;
    }

    public void setIdTipoExame(int idtipoexame) {
        this.idtipoexame = idtipoexame;
    }
    
    public String getDescricaoTipoExame(){
        TipoExameDAO tipoexameDAO = new TipoExameDAO();
        List<TipoExame> tiposexames = tipoexameDAO.ListaTipoExames();
        for(TipoExame tipoexame : tiposexames){
            if (tipoexame.getId() == this.idtipoexame){
                return tipoexame.getDescricao();
            }
        }
        return "";
    }
    
}
