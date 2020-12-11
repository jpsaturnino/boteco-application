
package boteco.db.dal;

import boteco.db.entidades.Item;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ItemDAL {
    public boolean Salvar(Item i) {
        String sql = "INSERT into item values (#1,#2,#3)";

        sql = sql.replace("#1", ""+i.getCom_id());  
        sql = sql.replace("#2", ""+i.getProd_id());  
        sql = sql.replace("#3", ""+i.getQuantidade());  
        
        return Banco.getCon().manipular(sql);
    }

    public boolean Alterar(Item i) {
    String sql = "UPDATE item set it_quantidade = #1 WHERE com_id="+i.getCom_id()+" AND prod_id="+i.getProd_id();   
    sql = sql.replace("#1", ""+i.getQuantidade());
        
        return Banco.getCon().manipular(sql);
    }
    public boolean Adicionar(Item i) {
    String sql = "UPDATE item set it_quantidade = it_quantidade + #1 WHERE com_id="+i.getCom_id()+" AND prod_id="+i.getProd_id();   
    sql = sql.replace("#1", ""+i.getQuantidade());
        
        return Banco.getCon().manipular(sql);
    }
    public boolean Diminuir(int comID, int prodID) {
        Item i = get(comID, prodID);
        if(i.getQuantidade()>1){
            String sql = "UPDATE item set it_quantidade = it_quantidade - 1 WHERE com_id="+i.getCom_id()+" AND prod_id="+i.getProd_id();   
            return Banco.getCon().manipular(sql);
        }  
        else
            return Apagar(prodID,comID);
    }
    
    
    public boolean Apagar(int prodID, int  comID) {
        String sql = "DELETE FROM item WHERE com_id="+comID+" AND prod_id="+prodID;
        return Banco.getCon().manipular(sql); 
    }
 
    public List<Item> get(String filtro) {
        List<Item> itens = new ArrayList();
        String sql = "SELECT * from item";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                itens.add(new Item(rs.getInt("com_id"), rs.getInt("prod_id"), rs.getInt("it_quantidade")));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return itens; 
    }
    public Item get(int com_id, int prod_id) {
        Item item = null;
        String sql = "SELECT * from item WHERE com_id="+com_id+" AND prod_id="+prod_id;

        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            if(rs.next())
                item = new Item(rs.getInt("com_id"), rs.getInt("prod_id"),rs.getInt("it_quantidade"));
        } catch(Exception e) 
        { System.out.println(e); }
        
        return item; 
    }

}
