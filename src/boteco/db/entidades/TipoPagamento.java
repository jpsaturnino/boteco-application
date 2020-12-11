package boteco.db.entidades;

public class TipoPagamento {
    private int id;
    private String nome;

    public TipoPagamento() {
        this(0,"");
    }
    
    public TipoPagamento(String nome) {
        this(0,nome);
    }

    public TipoPagamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
