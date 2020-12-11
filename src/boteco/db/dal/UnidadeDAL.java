package boteco.db.dal;

import boteco.db.entidades.Unidade;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UnidadeDAL {
    
    public Unidade get(int id) {
        Unidade uni = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM unidade WHERE uni_id=" + id);
        
        try {
            if(rs.next())
                uni = new Unidade (rs.getInt("uni_id"), rs.getString("uni_nome"));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return uni;
    }
    
    public List<Unidade> get(String filtro) {
        List<Unidade> cat = new ArrayList();
        String sql = "SELECT * FROM unidade";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new Unidade (rs.getInt("uni_id"), rs.getString("uni_nome")));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return cat;     
    }

    public List<Unidade> getNome(String filtro) {
        List<Unidade> cat = new ArrayList();
        String sql = "SELECT * FROM unidade";
        
        if(!filtro.isEmpty())
            sql += " WHERE lower(uni_nome) LIKE " + "'%" +filtro+ "%'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new Unidade (rs.getInt("uni_id"), rs.getString("uni_nome")));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return cat;     
    }
    
    public boolean Salvar(Unidade uni) {
        String sql = "INSERT INTO unidade VALUES (default,'#1')";
        sql = sql.replace("#1", "" + uni.getNome());
    
        return Banco.getCon().manipular(sql);
    }
    public boolean Alterar(Unidade uni) {
        String sql = "UPDATE unidade SET uni_nome = '#1'  "
                                      + "WHERE uni_id="+uni.getId();

        sql = sql.replace("#1", "" + uni.getNome());    
        
        return Banco.getCon().manipular(sql);   
    }
    public boolean Apagar(int id) {
        return Banco.getCon().manipular("DELETE FROM unidade WHERE uni_id= "+id);
    }
}
