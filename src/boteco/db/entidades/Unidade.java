package boteco.db.entidades;

public class Unidade {
    private int id;
    private String nome;

    public Unidade() {
        this(0,"");
    }
    
    public Unidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Unidade(String nome) {
        this(0,nome);
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
   
    @Override
    public String toString() {
        return nome;
    }
}
