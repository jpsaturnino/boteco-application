package boteco;

import boteco.db.dal.ComandaDAL;
import boteco.db.dal.GarcomDAL;
import boteco.db.entidades.Comanda;
import boteco.db.entidades.Garcom;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

public class TelaGeraComandaController implements Initializable {

    @FXML
    private JFXTextField txNome;
    @FXML
    private JFXTextField txDescricao;
    @FXML
    private JFXComboBox<Garcom> cbGarcom;
    @FXML
    private JFXTextField txNumero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbGarcom.setItems(FXCollections.observableArrayList(new GarcomDAL().get("")));
        cbGarcom.setConverter(new StringConverter<Garcom>() {
           @Override
           public String toString(Garcom object) {
               return object.getNome();
           }

           @Override
           public Garcom fromString(String string) {
               return null;
           }
        });
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        if(cbGarcom.getValue()!=null && !txNumero.getText().isEmpty() && !txNome.getText().isEmpty() && !txDescricao.getText().isEmpty()){
            Comanda c = new Comanda(Integer.parseInt(txNumero.getText()), txNome.getText(), txDescricao.getText(), LocalDate.now(), 0, 'A', cbGarcom.getValue());
            new ComandaDAL().Salvar(c);
            cbGarcom.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        cbGarcom.getScene().getWindow().hide();
    }
    
}