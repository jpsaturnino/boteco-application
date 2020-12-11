
package boteco;

import boteco.db.dal.CategoriaDAL;
import boteco.db.entidades.Categoria;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class TelaCadCategoriaController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXTextField txNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Categoria cat = TelaRelCategoriaController.cat;
        if(cat!=null){
            txId.setText(""+cat.getId());
            txNome.setText(cat.getNome());
            txId.setDisable(true);
        }else{
            txId.setVisible(false);
        }
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        if(!txNome.getText().isEmpty()){
            Categoria cat = new Categoria(txNome.getText());
             if(txId.getText().isEmpty())
                new CategoriaDAL().Salvar(cat);
            else{
                 System.out.println("to no else");
                cat.setId(Integer.parseInt(txId.getText()));
                new CategoriaDAL().Alterar(cat);
            }
            txId.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }
    
}
