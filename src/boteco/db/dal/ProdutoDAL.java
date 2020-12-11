package boteco.db.dal;

import boteco.db.entidades.Produto;
import boteco.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAL {
    
    public boolean Salvar(Produto p) {
        String sql = "INSERT into produto values (default,#1,#2,'#3',#4,'#5')";

        sql = sql.replace("#1", "" + p.getCategoria().getId());
        sql = sql.replace("#2", "" + p.getUnidade().getId());  
        sql = sql.replace("#3", p.getNome());                                        
        sql = sql.replace("#4", "" + p.getPreco());
        sql = sql.replace("#5", p.getDescricao());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean Alterar(Produto p) {
        String sql = "UPDATE produto set cat_id = #1, "
                                      + "uni_id = #2, "
                                      + "prod_nome='#3', "
                                      + "prod_preco = #4, "
                                      + "prod_descr = '#5' WHERE prod_id="+p.getId();

        sql = sql.replace("#1", "" + p.getCategoria().getId());
        sql = sql.replace("#2", "" + p.getUnidade().getId());                     
        sql = sql.replace("#3", p.getNome());                             
        sql = sql.replace("#4", "" + p.getPreco());
        sql = sql.replace("#5", p.getDescricao());
        System.out.println(sql);
        return Banco.getCon().manipular(sql);   
    }

    public boolean Apagar(int id) {
        return Banco.getCon().manipular("DELETE from produto WHERE prod_id= "+id);
    }
    
    public Produto get(int id) {
        Produto prod = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * from produto WHERE prod_id= " + id);
        
        try {
            if(rs.next())
                prod = new Produto(rs.getInt("prod_id"), 
                                   rs.getString("prod_nome"),
                                   rs.getString("prod_descr"),
                                   rs.getDouble("prod_preco"),
                                   new CategoriaDAL().get(rs.getInt("cat_id")),
                                   new UnidadeDAL().get(rs.getInt("uni_id"))
                                   );
        } catch(Exception e) 
        { System.out.println(e); }
        
        return prod;
    }
     
    public List<Produto> get(String filtro) {
        List <Produto> produtos = new ArrayList();
        String sql = "SELECT * from produto";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;

        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                produtos.add(new Produto(rs.getInt("prod_id"), 
                                         rs.getString("prod_nome"),
                                         rs.getString("prod_descr"),
                                         rs.getDouble("prod_preco"),
                                         new CategoriaDAL().get(rs.getInt("cat_id")),
                                         new UnidadeDAL().get(rs.getInt("uni_id"))
                                        ));
        } catch(Exception e) 
        { System.out.println(e); }
            
        return produtos;
    }

    public List<Produto> getNome(String filtro) {
        List <Produto> produtos = new ArrayList();
        String sql = "SELECT * from produto";
        
        if(!filtro.isEmpty())
            sql += " WHERE lower(prod_nome) like " + "'%" +filtro+ "%'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
                produtos.add(new Produto(rs.getInt("prod_id"), 
                                         rs.getString("prod_nome"),
                                         rs.getString("prod_descr"),
                                         rs.getDouble("prod_preco"),
                                         new CategoriaDAL().get(rs.getInt("cat_id")),
                                         new UnidadeDAL().get(rs.getInt("uni_id"))
                                        ));
        } catch(Exception e) 
        { System.out.println(e); }
            
        return produtos;
    }    
}
