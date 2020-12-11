package boteco.db.dal;

import boteco.db.entidades.Garcom;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GarcomDAL {
    public boolean Salvar(Garcom g) {
        String sql = "INSERT into garcon values (default,'#1','#2','#3','#4','#5','#6','#7')";

        sql = sql.replace("#1", g.getNome());  
        sql = sql.replace("#2", g.getCpf());                                        
        sql = sql.replace("#3", g.getCep());
        sql = sql.replace("#4", g.getEndereco());
        sql = sql.replace("#5", g.getCidade());
        sql = sql.replace("#6", g.getUf());
        sql = sql.replace("#7", g.getTelefone());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Garcom g) {
        String sql = "UPDATE garcon set gar_nome = #1, "
                                      + "gar_cpf = #2, "
                                      + "gar_cep = #3, "
                                      + "gar_endereco = '#4', "
                                      + "gar_cidade = #5, "
                                      + "gar_uf = #6, "
                                      + "gar_fone = #7, WHERE prod_id="+g.getId();

        sql = sql.replace("#1", g.getNome());  
        sql = sql.replace("#2", g.getCpf());                                        
        sql = sql.replace("#3", g.getCep());
        sql = sql.replace("#4", g.getEndereco());
        sql = sql.replace("#5", g.getCidade());
        sql = sql.replace("#6", g.getUf());
        sql = sql.replace("#7", g.getTelefone());
        
        return Banco.getCon().manipular(sql);   
    }
    
    public boolean Apagar(int id) {
        return Banco.getCon().manipular("DELETE from garcon WHERE gar_id="+id);
    }
    
    public Garcom get(int id) {
        Garcom garcom = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * from garcon WHERE gar_id= " + id);
        
        try {
            if(rs.next())
                garcom = new Garcom(rs.getInt("gar_id"),
                                    rs.getString("gar_cpf"),
                                    rs.getString("gar_cep"),
                                    rs.getString("gar_nome"),
                                    rs.getString("gar_endereco"),
                                    rs.getString("gar_cidade"),
                                    rs.getString("gar_fone"),
                                    rs.getString("gar_uf")
                                   );
        } catch(Exception e) 
        { System.out.println(e); }
        
        return garcom;
    }
    
    public List<Garcom> get(String filtro) {
        List <Garcom> garcons = new ArrayList();
        String sql = "SELECT * from garcon";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                garcons.add(new Garcom(rs.getInt("gar_id"),
                                    rs.getString("gar_cpf"),
                                    rs.getString("gar_cep"),
                                    rs.getString("gar_nome"),
                                    rs.getString("gar_endereco"),
                                    rs.getString("gar_cidade"),
                                    rs.getString("gar_fone"),
                                    rs.getString("gar_uf")
                                    ));
        } catch(Exception e) 
        { System.out.println(e); }
            
        return garcons;
    }
    
    public List<String> getCidades() {
        List<String> cidades = new ArrayList();
        String sql = "SELECT DISTINCT garcon.gar_cidade from garcon";
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while(rs.next())
                cidades.add(rs.getString("gar_cidade"));
        } catch(Exception e) 
        { System.out.println(e); }
            
        return cidades;
    }
    
    public List<Garcom> getNome(String filtro) {
        List <Garcom> garcons = new ArrayList();
        String sql = "SELECT * from garcon";
        
        if(!filtro.isEmpty())
            sql += " WHERE lower(gar_nome) like " + "'%" +filtro+ "%'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                garcons.add(new Garcom(rs.getInt("gar_id"),
                                    rs.getString("gar_cpf"),
                                    rs.getString("gar_cep"),
                                    rs.getString("gar_nome"),
                                    rs.getString("gar_endereco"),
                                    rs.getString("gar_cidade"),
                                    rs.getString("gar_fone"),
                                    rs.getString("gar_uf")
                                    ));
        } catch(Exception e) 
        { System.out.println(e); }
            
        return garcons;
    }
}
