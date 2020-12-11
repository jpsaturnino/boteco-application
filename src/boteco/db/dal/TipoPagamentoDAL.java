package boteco.db.dal;

import boteco.db.entidades.TipoPagamento;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDAL {
        
    public TipoPagamento get(int id) {
        TipoPagamento tp_pgto = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM tipopgto WHERE tpg_id=" + id);
        
        try {
            if(rs.next())
                tp_pgto = new TipoPagamento(rs.getInt("tpg_id"), rs.getString("tpg_nome"));
        } catch(SQLException e) 
        { System.out.println(e); }
        
        return tp_pgto;
    }
    
    public List<TipoPagamento> get(String filtro) {
        List<TipoPagamento> cat = new ArrayList();
        String sql = "SELECT * FROM tipopgto";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new TipoPagamento(rs.getInt("tpg_id"), rs.getString("tpg_nome")));
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return cat; 
    }
    public List<TipoPagamento> getNome(String filtro) {
        List<TipoPagamento> cat = new ArrayList();
        String sql = "SELECT * FROM tipopgto";
        
        if(!filtro.isEmpty())
            sql += " WHERE lower(tpg_nome) LIKE " + "'%" +filtro+ "%'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                cat.add(new TipoPagamento(rs.getInt("tpg_id"), rs.getString("tpg_nome")));
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return cat; 
    }
    
    public boolean Salvar(TipoPagamento tp) {
        String sql = "INSERT INTO tipopgto VALUES (default,'#1')";
        sql = sql.replace("#1", "" + tp.getNome());
    
        return Banco.getCon().manipular(sql);
    }
    public boolean Alterar(TipoPagamento tp) {
        String sql = "UPDATE tipopgto SET tpg_nome = '#1'  "
                                      + "WHERE tpg_id="+tp.getId();

        sql = sql.replace("#1", "" + tp.getNome());    
        
        return Banco.getCon().manipular(sql);   
    }
    public boolean Apagar(int id) {
        return Banco.getCon().manipular("DELETE FROM tipopgto WHERE tpg_id= "+id);
    }
}