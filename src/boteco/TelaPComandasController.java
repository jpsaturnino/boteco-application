package boteco;

import boteco.db.dal.ComandaDAL;
import boteco.db.entidades.Comanda;
import boteco.db.util.Banco;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;

public class TelaPComandasController implements Initializable {

    @FXML
    private TilePane painel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComandas();
    }    

    @FXML
    private void evtNovaComanda(ActionEvent event) throws IOException {
        telaModal tl = new telaModal("TelaGeraComanda.fxml");
        tl.getStage().showAndWait();    
        carregarComandas();    
    }
    
    private void carregarComandas() {
        painel.getChildren().clear();
        
        for(Comanda com: new ComandaDAL().get(" com_status = 'A'"))
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaComanda.fxml"));
                Parent root = (Parent) loader.load();
                TelaComandaController ctr = loader.getController();
                ctr.setId(com.getId());
                painel.getChildren().add(root);
                
            }
            catch(Exception e) {}
    }
    
    public void atualizar() {
        carregarComandas();
    }

    @FXML
    private void evtAtualizar(ActionEvent event) {
        carregarComandas();
    }
}
