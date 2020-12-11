package boteco;

import boteco.db.dal.CategoriaDAL;
import boteco.db.dal.ProdutoDAL;
import boteco.db.dal.UnidadeDAL;
import boteco.db.entidades.Categoria;
import boteco.db.entidades.Produto;
import boteco.db.entidades.Unidade;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaCadProdutoController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXTextField txNome;
    @FXML
    private JFXTextField txPreco;
    @FXML
    private JFXTextField txDescricao;
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private JFXComboBox<Unidade> cbUnidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{txNome.requestFocus();});
        cbCategoria.setItems(FXCollections.observableArrayList(new CategoriaDAL().get("")));
        cbUnidade.setItems(FXCollections.observableArrayList(new UnidadeDAL().get("")));
        Produto prod = TelaRelProdutosController.prod;
        
        if(prod!=null){
            txId.setDisable(true);
            txId.setText(""+prod.getId());
            txNome.setText(prod.getNome());
            txPreco.setText(""+prod.getPreco());
            txDescricao.setText(prod.getDescricao());
            int i=0;
            List<Categoria>  cat = new CategoriaDAL().get("");
            while(!(cat.get(i).getNome()).equals(prod.getCategoria().getNome()))
                 i++;
            cbCategoria.getSelectionModel().select(i);
            i=0;
            List<Unidade>  unidade = new UnidadeDAL().get("");
            while(!(unidade.get(i).getNome()).equals(prod.getUnidade().getNome()))
                 i++;
            cbUnidade.getSelectionModel().select(i);
        }else{
            txId.setVisible(false);
        }
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        if(!txNome.getText().isEmpty() && !txPreco.getText().isEmpty() && !cbCategoria.getSelectionModel().isEmpty() && !cbUnidade.getSelectionModel().isEmpty()){
            Produto prod = new Produto(txNome.getText(),txDescricao.getText(),Double.parseDouble(txPreco.getText()),cbCategoria.getSelectionModel().getSelectedItem(),cbUnidade.getSelectionModel().getSelectedItem());
             if(txId.getText().isEmpty())
                new ProdutoDAL().Salvar(prod);
            else{
                prod.setId(Integer.parseInt(txId.getText()));
                new ProdutoDAL().Alterar(prod);
            }
            txId.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }
    
}
