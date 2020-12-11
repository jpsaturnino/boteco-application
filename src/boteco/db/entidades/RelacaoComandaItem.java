package boteco.db.entidades;

public class RelacaoComandaItem {
    private int qtde, prod_id;
    private String prod_nome;
    private Double preco, subTotal;

    
    public RelacaoComandaItem(int qtde, String prod_nome, int prod_cod, Double preco, Double subTotal) {
        this.qtde = qtde;
        this.prod_nome = prod_nome;
        this.prod_id = prod_cod;
        this.preco = preco;
        this.subTotal = subTotal;
    }
    public int getProd_cod() {
        return prod_id;
    }

    public void setProd_cod(int prod_cod) {
        this.prod_id = prod_cod;
    }
    
    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getProd_nome() {
        return prod_nome;
    }

    public void setProd_nome(String prod_nome) {
        this.prod_nome = prod_nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}
