package boteco.db.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    
    private int id, numero;
    private String nome, descricao;
    private LocalDate data;
    private double valor;
    private char status;
    private Garcom garcom;  
    private List<Item> itens;
    private List<Pagamento> pagamentos;
    
    public Comanda() {
        this(0, 0, "", "", LocalDate.now(), 0, 'A', new Garcom());
    }
    
    public Comanda(int numero, String nome, String descricao, LocalDate data, double valor, char status, Garcom garcom) {
        this(0, numero, nome, descricao, data, valor, status, garcom);
    }

    public Comanda(int id, int numero, String nome, String descricao, LocalDate data, double valor, char status, Garcom garcom) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.garcom = garcom;
        itens = new ArrayList();
        pagamentos = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    
    
    public boolean addItem(Produto produto, int quantidade) {
        return itens.add(new Item(id,produto.getId(), quantidade));
    }
    
    public boolean addPagamento(TipoPagamento pagamento, double valor) {
        return pagamentos.add(new Pagamento(valor, pagamento,this));
    }

    public double getValorPagamentos() {
        double total = 0;
        
        for(Pagamento p:pagamentos)
            total += p.getValor();
        
        return total;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    
}
