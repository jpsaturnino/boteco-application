package boteco;

import boteco.db.dal.ProdutoDAL;
import boteco.db.entidades.Categoria;
import boteco.db.entidades.Produto;
import boteco.db.entidades.Unidade;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

public class TelaRelProdutosController implements Initializable {

    @FXML
    private TableColumn<Produto, Integer> colId;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, Categoria> colCategoria;
    @FXML
    private TableColumn<Produto, Unidade> colUnidade;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    @FXML
    private TableView<Produto> tabela;
    public static Produto prod = null;
    @FXML
    private TextField tfBuscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        colUnidade.setCellValueFactory(new PropertyValueFactory("unidade"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        carregarTabela("");
    }    
    
    private void carregarTabela(String filtro) {
        List<Produto> produtos = new ProdutoDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }

    @FXML
    private void evtNovo(ActionEvent event) throws IOException {
        telaCadastro();
    }

    @FXML
    private void evtFechar(ActionEvent event){
    }
    
    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        prod = tabela.getSelectionModel().getSelectedItem();
        telaCadastro();
        prod = null;
    }
    
    private void telaCadastro() throws IOException{
        telaModal tela = new telaModal("TelaCadProduto.fxml");
        tela.getStage().showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        int id = tabela.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar o produto?");
        if(alert.showAndWait().get()==ButtonType.OK){
            new ProdutoDAL().Apagar(id);
            carregarTabela("");
        }
            
    }

    @FXML
    private void evtPesquisar(KeyEvent event) {
        List<Produto> produtos = new ProdutoDAL().getNome(tfBuscar.getText());
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }

}
