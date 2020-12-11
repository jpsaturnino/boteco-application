package boteco.db.entidades;

public class Garcom {
    private int id;
    private String cpf, cep, nome, endereco, cidade, telefone, uf;
    
    public Garcom() {
        this(0, "", "", "", "", "", "", "");
    }

    public Garcom(String cpf, String cep, String nome, String endereco, String cidade, String telefone, String uf) {
        this(0, cpf, cep, nome, endereco, cidade, telefone, uf);
    }

    public Garcom(int id, String cpf, String cep, String nome, String endereco, String cidade, String telefone, String uf) {
        this.id = id;
        this.cpf = cpf;
        this.cep = cep;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
