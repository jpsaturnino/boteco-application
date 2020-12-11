package boteco;

import boteco.db.dal.ComandaDAL;
import boteco.db.entidades.Comanda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TelaComandaController implements Initializable {

    @FXML
    private Label lbId;
    @FXML
    private Label lbValor;
    public static Comanda comanda = null;
    @FXML
    private Label lbNome;
    @FXML
    private VBox body;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void evtClickComanda(MouseEvent event) throws IOException {
        
    }
    
    public void setId(int id) {
        Comanda c = new ComandaDAL().get(id);
        lbId.setText("" + c.getId());
        lbValor.setText("" + c.getValor());
        lbNome.setText(c.getNome());
    }

    @FXML
    private void evtAdicionar(ActionEvent event) throws IOException {
        telaAdicionar();
    }

    @FXML
    private void evtRemover(ActionEvent event) {
        int id = Integer.parseInt(lbId.getText());
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja apagar a comanda?");
        if(alert.showAndWait().get()==ButtonType.OK){
            new ComandaDAL().Apagar(id);
            ControllerSingleton.controllerpainelcomandas.atualizar();
        }
    }
    
    private void telaAdicionar() throws IOException{
        TelaEditaComandaController.com = new ComandaDAL().get(Integer.parseInt(lbId.getText()));
        telaModal modal = new telaModal("TelaEditaComanda.fxml");
        modal.getStage().showAndWait();
        ControllerSingleton.controllerpainelcomandas.atualizar();
    }
}
