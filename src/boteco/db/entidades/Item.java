
package boteco.db.entidades;

public class Item {
    private int com_id, prod_id;
    private int quantidade;

    public Item(int com_id, int prod_id, int quantidade) {
        this.com_id = com_id;
        this.prod_id = prod_id;
        this.quantidade = quantidade;
    }
    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


       
}
