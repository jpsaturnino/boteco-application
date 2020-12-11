package boteco.db.entidades;

public class RelacaoPagamentoComanda {
    private String tipo;
    private int id;
    private Double valor;

    public RelacaoPagamentoComanda(String tipo, int id, Double valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
