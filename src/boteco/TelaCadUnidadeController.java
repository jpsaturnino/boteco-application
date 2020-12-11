package boteco;

import boteco.db.dal.UnidadeDAL;
import boteco.db.entidades.Unidade;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaCadUnidadeController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXTextField txNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{txNome.requestFocus();});
        Unidade item = TelaRelUnidadeController.item;
        if(item!=null){
            txId.setText(""+item.getId());
            txNome.setText(item.getNome());
            txId.setDisable(true);
        }else{
            txId.setVisible(false);
        }
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        if(!txNome.getText().isEmpty()){
            Unidade uni = new Unidade(txNome.getText());
            if(txId.getText().isEmpty())
                new UnidadeDAL().Salvar(uni);
            else{
                uni.setId(Integer.parseInt(txId.getText()));
                new UnidadeDAL().Alterar(uni);
            }
            txId.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }
    
}
