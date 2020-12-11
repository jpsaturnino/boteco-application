package boteco.db.entidades;

public class Pagamento {
    private int id;
    private double valor;
    private TipoPagamento tpPag;
    private Comanda com;
    
    public Pagamento() {
        this(0,0,new TipoPagamento(), new Comanda());
    }

    public Pagamento(double valor, TipoPagamento tpPag, Comanda com) {
        this(0, valor, tpPag, com);
    }

    public Pagamento(int id, double valor, TipoPagamento tpPag, Comanda com) {
        this.id = id;
        this.valor = valor;
        this.tpPag = tpPag;
        this.com = com;
    }

    public Comanda getCom() {
        return com;
    }

    public void setCom(Comanda com) {
        this.com = com;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoPagamento getTpPag() {
        return tpPag;
    }

    public void setTpPag(TipoPagamento tpPag) {
        this.tpPag = tpPag;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "id=" + id + ", valor=" + valor + ", tpPag=" + tpPag + ", com=" + com + '}';
    }
    
    
}
