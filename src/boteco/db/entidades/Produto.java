package boteco.db.entidades;

public class Produto {
    private int id;
    private String nome, descricao;
    private double preco;
    private Categoria categoria;
    private Unidade unidade;

    
    public Produto() {
        this(0,"","",0,new Categoria(), new Unidade());
    }

    public Produto(int id, String nome, String descricao, double preco, Categoria categoria, Unidade unidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.unidade = unidade;
    }

    public Produto(String nome, String descricao, double preco, Categoria categoria, Unidade unidade) {
        this(0,nome,descricao,preco,categoria,unidade);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", categoria=" + categoria + ", unidade=" + unidade + '}';
    }
    
}
