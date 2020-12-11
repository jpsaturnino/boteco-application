package boteco.db.dal;

import boteco.db.entidades.Pagamento;
import boteco.db.entidades.RelacaoPagamentoComanda;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAL {
        
    public Pagamento get(int id) {
        Pagamento pgto = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM pagamento WHERE pag_id=" + id);
        
        try {
            if(rs.next())
                pgto = new Pagamento(rs.getInt("pag_id"),rs.getDouble("pag_valor"), new TipoPagamentoDAL().get(rs.getInt("tpg_id")),
                        new ComandaDAL().get(rs.getInt("com_id")));
        } catch(SQLException e) 
        { System.out.println(e); }
        
        return pgto;
    }
    
    public List<Pagamento> get(String filtro) {
        List<Pagamento> pag = new ArrayList();
        String sql = "SELECT * FROM pagamento";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                pag.add(new Pagamento(rs.getInt("pag_id"),rs.getDouble("pag_valor"), new TipoPagamentoDAL().get(rs.getInt("tpg_id")),
                        new ComandaDAL().get(rs.getInt("com_id"))));
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return pag; 
    }
    public List<RelacaoPagamentoComanda> getRelacaoComanda(int id) {
        List<RelacaoPagamentoComanda> list = new ArrayList();
        String sql = "SELECT p.pag_id AS id, t.tpg_nome AS tipo ,p.pag_valor AS valor FROM pagamento p INNER JOIN tipopgto t"
                + " ON  p.com_id =  "+id+" AND p.tpg_id = t.tpg_id";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        if(rs!=null){
            try {
                while(rs.next())
                    list.add(new RelacaoPagamentoComanda( rs.getString("tipo") , rs.getInt("id") , rs.getDouble("valor")));
            } catch(SQLException e){
                System.out.println(e);
            }
        }else
            System.out.println(Banco.getCon().getMensagemErro());
        
        
        return list; 
    }
    public double getTotalPago(int id) throws SQLException{
        ResultSet rs = Banco.getCon().consultar("SELECT SUM(pag_valor) AS valorPago FROM pagamento WHERE com_id = "+id);
        if(rs!=null && rs.next()){
            return rs.getDouble("valorPago");
        }else
                System.out.println(Banco.getCon().getMensagemErro());
        return 0;
    }
    public boolean Salvar(Pagamento p) {
        String sql = "INSERT INTO pagamento VALUES (default,#1,#2,#3)";
        sql = sql.replace("#1", "" + p.getCom().getId());
        sql = sql.replace("#2", "" + p.getValor());
        sql = sql.replace("#3", "" + p.getTpPag().getId());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(int id) {
        return Banco.getCon().manipular("DELETE FROM pagamento WHERE pag_id= "+id);
    }
}