package boteco.db.dal;

import boteco.db.entidades.Categoria;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class CategoriaDAL {
    
    
    public boolean Salvar(Categoria c) {
        String sql = "INSERT into categoria values (default,'#1')";

        sql = sql.replace("#1", c.getNome());  
        
        return Banco.getCon().manipular(sql);
    }

    public boolean Alterar(Categoria c) {
    String sql = "UPDATE categoria set cat_nome = '#1' WHERE cat_id="+c.getId();   
    sql = sql.replace("#1", c.getNome());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(int id) {
        String sql = "DELETE from categoria WHERE cat_id = "+id;
        return Banco.getCon().manipular(sql); 
    }
    
    public Categoria get(int id) {
        Categoria cat = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * from categoria WHERE cat_id=" + id);
        
        try {
            if(rs.next())
                cat = new Categoria (rs.getInt("cat_id"), rs.getString("cat_nome"));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return cat;
    }
    
    public List<Categoria> get(String filtro) {
        List<Categoria> cat = new ArrayList();
        String sql = "SELECT * from categoria";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new Categoria (rs.getInt("cat_id"), rs.getString("cat_nome")));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return cat; 
    }
    
    public List<Categoria> getNome(String filtro) {
        List<Categoria> cat = new ArrayList();
        String sql = "SELECT * from categoria";
        
        if(!filtro.isEmpty())
            sql += " WHERE lower(cat_nome) like " + "'%" +filtro+ "%'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new Categoria (rs.getInt("cat_id"), rs.getString("cat_nome")));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return cat; 
    }
}