package boteco;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TelaCadastroController implements Initializable {
    
    private Label label;
    @FXML
    private BorderPane painel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void evtCadProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelProdutos.fxml"));
        painel.setCenter(root);
    }

    @FXML
    private void evtCadGarcom(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelGarcoms.fxml"));
        painel.setCenter(root);  
    }

    @FXML
    private void evtCadCategoria(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelCategoria.fxml"));
        painel.setCenter(root);
    }

    @FXML
    private void evtCadUnidade(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelUnidade.fxml"));
        painel.setCenter(root);
    }

    @FXML
    private void evtCadTpagamento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelTipoPagamento.fxml"));
        painel.setCenter(root);
    }
    
}
