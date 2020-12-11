package boteco;

import boteco.db.dal.TipoPagamentoDAL;
import boteco.db.entidades.TipoPagamento;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaCadTipoPagamentoController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXTextField txNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{txNome.requestFocus();});
        TipoPagamento item = TelaRelTipoPagamentoController.item;
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
            TipoPagamento tpgto = new TipoPagamento(txNome.getText());
            if(txId.getText().isEmpty())
                new TipoPagamentoDAL().Salvar(tpgto);
            else{
                tpgto.setId(Integer.parseInt(txId.getText()));
                new TipoPagamentoDAL().Alterar(tpgto);
            }
            txId.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }
    
}
