package boteco;

import boteco.db.dal.GarcomDAL;
import boteco.db.entidades.Garcom;
import boteco.db.util.Servicos;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.JSONObject;

public class TelaCadGarcomController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXTextField txNome;
    @FXML
    private JFXTextField txCpf;
    @FXML
    private JFXTextField txCep;
    @FXML
    private JFXTextField txEndereco;
    @FXML
    private JFXTextField txCidade;
    @FXML
    private JFXTextField txUf;
    @FXML
    private JFXTextField txTelefone;
    @FXML
    private ImageView ivFoto;

    private File file=null;
    private Image imagem;
    private BufferedImage bimag;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{txNome.requestFocus();});
        Garcom g = TelaRelGarcomsController.g;
        if(g!=null)
            txId.setText(""+g.getId());
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        if(!txNome.getText().isEmpty() && !txCpf.getText().isEmpty()){
            Garcom garcom = new Garcom(txCpf.getText(),txCep.getText(),
                        txNome.getText(),txEndereco.getText(),txCidade.getText(),
                        txTelefone.getText(), txUf.getText());
            if(txId.getText().isEmpty())
                new GarcomDAL().Salvar(garcom);
            else{
                garcom.setId(Integer.parseInt(txId.getText()));
                new GarcomDAL().Alterar(garcom);
            }
            txId.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }
    
    protected String json;

    @FXML
    private void evtBuscarCep(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override
            protected Void call() {
                json = Servicos.consultaCep(txCep.getText(), "json");
                if(!json.isEmpty()) {
                    JSONObject obj = new JSONObject(json);

                    txEndereco.setText(obj.getString("logradouro"));
                    txCidade.setText(obj.getString("localidade"));
                    txUf.setText(obj.getString("uf"));
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void evtSelecionarFoto(ActionEvent event) {
        FileChooser fchooser=new FileChooser();
        file =fchooser.showOpenDialog(null);
        if(file!=null)
        {
            imagem=new Image(file.toURI().toString());
            ivFoto.setFitWidth(93);
            ivFoto.setFitHeight(131);
            ivFoto.setImage(imagem);
            bimag=SwingFXUtils.fromFXImage(imagem, null);
        }
    }
}
