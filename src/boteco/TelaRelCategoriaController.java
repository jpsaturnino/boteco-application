
package boteco;

import boteco.db.dal.CategoriaDAL;
import boteco.db.entidades.Categoria;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class TelaRelCategoriaController implements Initializable {

    @FXML
    private TableView<Categoria> tabela;
    @FXML
    private TableColumn<Categoria, String> colNome;
    @FXML
    private TableColumn<Categoria, Integer> colId;
    public static Categoria cat = null;
    @FXML
    private TextField tfBuscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        carregarTabela("");
    }    

    @FXML
    private void evtNovo(ActionEvent event) throws IOException {
        telaCadastro();
    }


    private void carregarTabela(String filtro) {
        List<Categoria> categorias = new CategoriaDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(categorias));
        tabela.getSortOrder().add(colNome);
    }
    private void telaCadastro() throws IOException{
        telaModal tela = new telaModal("TelaCadCategoria.fxml");
        tela.getStage().showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        System.out.println("alterar");
        cat = tabela.getSelectionModel().getSelectedItem();
        telaCadastro();
        cat = null;
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        int id = tabela.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar a categoria?");
        if(alert.showAndWait().get()==ButtonType.OK){
            new CategoriaDAL().Apagar(id);
            carregarTabela("");
        }
    }

    @FXML
    private void evtPesquisar(KeyEvent event) {
        List<Categoria> produtos = new CategoriaDAL().getNome(tfBuscar.getText());
        tabela.setItems(FXCollections.observableArrayList(produtos));
        tabela.getSortOrder().add(colNome);
    }
}
