package boteco;

import boteco.db.dal.TipoPagamentoDAL;
import boteco.db.entidades.TipoPagamento;
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
import javafx.scene.input.KeyEvent;

public class TelaRelTipoPagamentoController implements Initializable {

    @FXML
    private TableColumn<TipoPagamento, Integer> colId;
    @FXML
    private TableColumn<TipoPagamento, String> colNome;
    @FXML
    private TableView<TipoPagamento> tabela;
    public static TipoPagamento item = null;
    @FXML
    private TextField tfBuscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        carregarTabela("");
    }    
    
    private void carregarTabela(String filtro) {
        List<TipoPagamento> itens = new TipoPagamentoDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(itens));
        tabela.getSortOrder().add(colNome);
    }

    @FXML
    private void evtNovo(ActionEvent event) throws IOException {
        telaCadastro();
    }

    
    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        item = tabela.getSelectionModel().getSelectedItem();
        telaCadastro();
        item = null;
    }
    private void telaCadastro() throws IOException{
        telaModal tela = new telaModal("TelaCadTipoPagamento.fxml");
        tela.getStage().showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        int id = tabela.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir este tipo de pagamento?");
        if(alert.showAndWait().get()==ButtonType.OK){
            System.out.println(id);
            if(new TipoPagamentoDAL().Apagar(id))
                System.out.println("excluido(tp_pgto)");
            else
                System.out.println("erro ao excluir(tp_pgto)");
            carregarTabela("");
        }
    }

    @FXML
    private void evtPesquisar(KeyEvent event) {
        List<TipoPagamento> produtos = new TipoPagamentoDAL().getNome(tfBuscar.getText());
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }
}
