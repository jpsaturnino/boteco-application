package boteco.db.dal;

import boteco.db.entidades.Comanda;
import boteco.db.entidades.RelacaoComandaItem;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAL {
        
    public boolean Salvar(Comanda c) {
        String sql = "insert into comanda values (default,#1,#2,'#3','#4','#5',#6,'#7')";
        System.out.println(c.getNumero());
        sql = sql.replace("#1", "" + c.getGarcom().getId());
        sql = sql.replace("#2", "" + c.getNumero());  
        sql = sql.replace("#3", c.getNome());                                        
        sql = sql.replace("#4", c.getData().toString());
        sql = sql.replace("#5", c.getDescricao());
        sql = sql.replace("#6", "" + c.getValor());
        sql = sql.replace("#7", "" + c.getStatus());
        Banco.getCon().manipular(sql);        
        return true;
    }

    
    public boolean Alterar(Comanda c) {
        return true; 
    }

    
    public boolean Apagar(int c) {
        return Banco.getCon().manipular("delete from comanda where com_id="+ c);
    }
    
    public boolean fechar(int id){
        return Banco.getCon().manipular("UPDATE comanda SET com_status = 'F' WHERE com_id = "+id);
    }
    
    public Comanda get(int id) {
        Comanda comanda = null;
        ResultSet rs = Banco.getCon().consultar("select * from comanda where com_id= " + id);
    
        try {
            if(rs.next())
                comanda = new Comanda(rs.getInt("com_id"), 
                                         rs.getInt("com_numero"),
                                         rs.getString("com_nome"),
                                         rs.getString("com_desc"),
                                         rs.getDate("com_data").toLocalDate(),
                                         rs.getDouble("com_valor"),
                                         rs.getString("com_status").charAt(0),
                                         new GarcomDAL().get(rs.getInt("gar_id"))
                                        );
        } catch(SQLException e) 
        { System.out.println(e); }
        
        return comanda;
    }
    
    public List<Comanda> get(String filtro) {
        List <Comanda> comandas = new ArrayList();
        String sql = "select * from comanda";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql+= " ORDER BY com_id";
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                comandas.add(new Comanda(rs.getInt("com_id"), 
                                         rs.getInt("com_numero"),
                                         rs.getString("com_nome"),
                                         rs.getString("com_desc"),
                                         rs.getDate("com_data").toLocalDate(),
                                         rs.getDouble("com_valor"),
                                         rs.getString("com_status").charAt(0),
                                         new GarcomDAL().get(rs.getInt("gar_id"))
                                        ));
        } catch(Exception e) 
        { System.out.println(e); }
        return comandas;
    }
     public List <RelacaoComandaItem> getItens(int id) {
        List <RelacaoComandaItem> rl = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT i.it_quantidade AS qtde, p.prod_id, p.prod_nome AS prod, p.prod_preco AS preco, SUM( i.it_quantidade * p.prod_preco) AS sub "
                                                + "FROM item i INNER JOIN produto p ON i.com_id = "+id
                                                + " AND i.prod_id = p.prod_id GROUP BY qtde, prod, preco, p.prod_id");
        if(rs!=null)
        try {
            while(rs.next())
                rl.add(new RelacaoComandaItem(rs.getInt("qtde"), 
                                         rs.getString("prod"),
                                         rs.getInt("prod_id"),
                                         rs.getDouble("preco"),
                                         rs.getDouble("sub")
                                        ));
        } catch(SQLException e) 
        { System.out.println(e); }
        else  System.out.println(Banco.getCon().getMensagemErro());
        return rl;
    }
     public Double getTotal(int id) throws SQLException{
         ResultSet rs = Banco.getCon().consultar("SELECT SUM(sub) AS total FROM ( SELECT SUM( i.it_quantidade * p.prod_preco) AS sub "
                                                + " FROM item i INNER JOIN produto p ON i.com_id = "+id
                                      + " AND i.prod_id = p.prod_id ) AS subtotal");
         if(rs!=null && rs.next()){
             return rs.getDouble("total");
         }
         return 0.0;
     }
     public Double atualizaValor(int id) throws SQLException{
         
         double valTotal = getTotal(id), v=0;
         double valPago = 0; 
         if (valTotal>0){
             valPago = new PagamentoDAL().getTotalPago(id);  
             v = valTotal - valPago;
             if(v<0)
                v=0;
             Banco.getCon().manipular("UPDATE comanda SET com_valor = "+ v+" WHERE com_id = "+id);
         }
         return v;
     }
}