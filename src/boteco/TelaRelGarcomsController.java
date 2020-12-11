package boteco;

import boteco.db.dal.GarcomDAL;
import boteco.db.entidades.Garcom;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class TelaRelGarcomsController implements Initializable {

    @FXML
    private TableView<Garcom> tabela;
    @FXML
    private TableColumn<Garcom, Integer> colId;
    @FXML
    private TableColumn<Garcom, String> colNome;
    @FXML
    private TableColumn<Garcom, String> colCidade;
    @FXML
    private TableColumn<Garcom, String> colTelefone;
    @FXML
    private TableColumn<Garcom, String> colUf;
    
    public static Garcom g = null;
    @FXML
    private TextField tfBuscar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colUf.setCellValueFactory(new PropertyValueFactory("uf"));
        carregarTabela("");
    }    

    @FXML
    private void evtNovo(ActionEvent event) throws IOException {
        telaCadastro();
    }

    private void carregarTabela(String filtro) {
        List<Garcom> garcons = new GarcomDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(garcons));
        tabela.getSortOrder().add(colNome);
    }
    
    private void telaCadastro() throws IOException{
        telaModal tela = new telaModal("TelaCadGarcom.fxml");
        tela.getStage().showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        g = tabela.getSelectionModel().getSelectedItem();
        telaCadastro();
        g = null;
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        int id = tabela.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar o garcom?");
        if(alert.showAndWait().get()==ButtonType.OK){
            System.out.println(id);
            carregarTabela("");
        }
    }

    @FXML
    private void evtPesquisar(KeyEvent event) {
        List<Garcom> produtos = new GarcomDAL().getNome(tfBuscar.getText());
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }
}
