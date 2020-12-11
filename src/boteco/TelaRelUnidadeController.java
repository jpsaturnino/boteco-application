package boteco;

import boteco.db.dal.UnidadeDAL;
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
import javafx.scene.input.KeyEvent;

public class TelaRelUnidadeController implements Initializable {

    @FXML
    private TableColumn<Unidade, Integer> colId;
    @FXML
    private TableColumn<Unidade, String> colNome;
    @FXML
    private TableView<Unidade> tabela;
    public static Unidade item = null;
    @FXML
    private TextField tfBuscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        carregarTabela("");
    }    
    
    private void carregarTabela(String filtro) {
        List<Unidade> itens = new UnidadeDAL().get(filtro);
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
        telaModal tela = new telaModal("TelaCadUnidade.fxml");
        tela.getStage().showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        int id = tabela.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir este tipo de unidade?");
        if(alert.showAndWait().get()==ButtonType.OK){
            if(new UnidadeDAL().Apagar(id))
                System.out.println("excluido(unidade)");
            else
                System.out.println("erro ao excluir(unidade)");
            carregarTabela("");
        }
            
    }

    @FXML
    private void evtPesquisar(KeyEvent event) {
        List<Unidade> produtos = new UnidadeDAL().getNome(tfBuscar.getText());
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }
}
