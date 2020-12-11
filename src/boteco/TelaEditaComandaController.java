package boteco;

import boteco.db.dal.CategoriaDAL;
import boteco.db.dal.ComandaDAL;
import boteco.db.dal.ItemDAL;
import boteco.db.dal.PagamentoDAL;
import boteco.db.dal.ProdutoDAL;
import boteco.db.entidades.Categoria;
import boteco.db.entidades.Comanda;
import boteco.db.entidades.Item;
import boteco.db.entidades.Produto;
import boteco.db.entidades.RelacaoComandaItem;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

public class TelaEditaComandaController implements Initializable {

    @FXML
    private JFXTextField txId;
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private JFXComboBox<Produto> cbProduto;
    @FXML
    private JFXTextField tfQuantidade;

    static Comanda com;
    @FXML
    private TableView<RelacaoComandaItem> tabela;
    @FXML
    private TableColumn<RelacaoComandaItem, Integer> colQtde;
    @FXML
    private TableColumn<RelacaoComandaItem, String> colProd;
    @FXML
    private TableColumn<RelacaoComandaItem, Double> colPreco;
    @FXML
    private TableColumn<RelacaoComandaItem, Double> colSubTotal;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbValorPago;
    @FXML
    private Label lbPagar;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{cbCategoria.requestFocus();
        });
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colProd.setCellValueFactory(new PropertyValueFactory("prod_nome"));
        colQtde.setCellValueFactory(new PropertyValueFactory("qtde"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory("subTotal"));
        carregarTabela();
    }    

    public void carregarTabela(){
        try {
            List<RelacaoComandaItem> itens = new ComandaDAL().getItens(com.getId());
            tabela.setItems(FXCollections.observableArrayList(itens));
            tabela.getSortOrder().add(colQtde);
            com.setValor(new ComandaDAL().atualizaValor(com.getId()));
            lbPagar.setText("A PAGAR: R$ "+ String.format("%.2f", com.getValor()));
            lbTotal.setText("TOTAL: R$ "+ new ComandaDAL().getTotal(com.getId()));
            lbValorPago.setText("VALOR PAGO: R$ "+ new PagamentoDAL().getTotalPago(com.getId()));
            tfQuantidade.setText("");
            cbCategoria.setItems(FXCollections.observableArrayList(new CategoriaDAL().get("")));
            cbCategoria.getSelectionModel().clearSelection();
            cbProduto.setItems(FXCollections.observableArrayList(new ProdutoDAL().get("")));
            txId.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditaComandaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void evtAdicionar(ActionEvent event) throws SQLException {
        if(!cbCategoria.getSelectionModel().isEmpty() && !cbProduto.getSelectionModel().isEmpty() && Integer.parseInt(tfQuantidade.getText()) > 0){
            Item item = new Item(com.getId(),cbProduto.getSelectionModel().getSelectedItem().getId(),Integer.parseInt(tfQuantidade.getText()));
            if(new ItemDAL().get(com.getId(),item.getProd_id())==null)
                new ItemDAL().Salvar(item);
            else{
                new ItemDAL().Adicionar(item);
            }
            carregarTabela();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        txId.getScene().getWindow().hide();
    }

    @FXML
    private void evtCarregaProduto(ActionEvent event) {
        if(!cbCategoria.getSelectionModel().isEmpty()){
            int i = cbCategoria.getSelectionModel().getSelectedItem().getId();
            cbProduto.setItems(FXCollections.observableArrayList(new ProdutoDAL().get("cat_id = "+i)));

            cbProduto.setConverter(new StringConverter<Produto>() {
                @Override
                public String toString(Produto object) {
                    return object.getNome();
                }

                @Override
                public Produto fromString(String string) {
                    return null;
                }
            });
        }
    }

    @FXML
    private void evtPagar(ActionEvent event) throws IOException {
        TelaPagamentosController.com = com;
        telaModal tm = new telaModal("TelaPagamentos.fxml");
        tm.getStage().showAndWait();
        if(TelaPagamentosController.com.getStatus()=='F')
                    cbCategoria.getScene().getWindow().hide();
        carregarTabela();
        
    }

    @FXML
    private void evtExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar os itens?");
        if(alert.showAndWait().get()==ButtonType.OK){
            if(tabela.getSelectionModel().getSelectedItem().getSubTotal()<=com.getValor())
                new ItemDAL().Apagar(tabela.getSelectionModel().getSelectedItem().getProd_cod(), com.getId());
            else{
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Os itens já foram pagos");
                alert.showAndWait();
            }
                   
            carregarTabela();
        }
    }

    
    @FXML
    private void evtBuscaProd(KeyEvent event) {
        if(!txId.getText().isEmpty()){
            Produto p = new ProdutoDAL().get(Integer.parseInt(txId.getText()));
            if(p!=null){
               cbCategoria.setItems(FXCollections.observableArrayList(new CategoriaDAL().get(" cat_id ="+p.getCategoria().getId())));
                cbCategoria.getSelectionModel().select(0);
                cbProduto.setItems(FXCollections.observableArrayList(( p )));
                cbProduto.getSelectionModel().select(0);
                tfQuantidade.setText("1");
            }
        }
        else{
            cbCategoria.setItems(FXCollections.observableArrayList(new CategoriaDAL().get("")));
            cbProduto.setItems(FXCollections.observableArrayList(new ProdutoDAL().get("")));
        }
            
    }

    @FXML
    private void evtExcluirUm(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar uma unidade deste item?");
        if(alert.showAndWait().get()==ButtonType.OK){
            if(tabela.getSelectionModel().getSelectedItem().getPreco()<=com.getValor())
                new ItemDAL().Diminuir(com.getId(),tabela.getSelectionModel().getSelectedItem().getProd_cod());
            else{
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("O item já foi pago");
                alert.showAndWait();
            }
                   
            carregarTabela();
        }
    }

    

}
