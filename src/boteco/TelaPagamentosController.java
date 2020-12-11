package boteco;

import boteco.db.dal.ComandaDAL;
import boteco.db.dal.PagamentoDAL;
import boteco.db.dal.TipoPagamentoDAL;
import boteco.db.entidades.Comanda;
import boteco.db.entidades.Pagamento;
import boteco.db.entidades.RelacaoPagamentoComanda;
import boteco.db.entidades.TipoPagamento;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class TelaPagamentosController implements Initializable {

    @FXML
    private TableView<RelacaoPagamentoComanda> tabela;
    @FXML
    private TableColumn<RelacaoPagamentoComanda, Integer> colId;
    @FXML
    private TableColumn<RelacaoPagamentoComanda, String> colTp;
    @FXML
    private TableColumn<RelacaoPagamentoComanda, Double> colValor;

    static Comanda com;
    @FXML
    private Text lbComanda;
    @FXML
    private JFXComboBox<TipoPagamento> cbTpPgto;
    @FXML
    private JFXTextField tfValor;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbValorPago;
    @FXML
    private Label lbPagar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbComanda.setText("Pagamento da Comanda #"+com.getId());
        cbTpPgto.setItems(FXCollections.observableArrayList(new TipoPagamentoDAL().get("")));
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colTp.setCellValueFactory(new PropertyValueFactory("tipo"));
     
        cbTpPgto.setConverter(new StringConverter<TipoPagamento>() {
            @Override
            public String toString(TipoPagamento object) {
                return object.getNome();
            }

            @Override
            public TipoPagamento fromString(String string) {
                return null;
            }
            });
        carregarTabela();
    }

    private void carregarTabela(){
        List<RelacaoPagamentoComanda> itens = new PagamentoDAL().getRelacaoComanda(com.getId());
        tabela.setItems(FXCollections.observableArrayList(itens));
        try {
            com.setValor(new ComandaDAL().atualizaValor(com.getId()));
            lbPagar.setText("A PAGAR: R$ "+ String.format("%.2f", com.getValor()));
            lbTotal.setText("TOTAL: R$ "+ new ComandaDAL().getTotal(com.getId()));
            lbValorPago.setText("VALOR PAGO: R$ "+ new PagamentoDAL().getTotalPago(com.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(TelaPagamentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void evtAdicionar(ActionEvent event) throws SQLException {
        double valPago =  Double.parseDouble(tfValor.getText());
        if(!cbTpPgto.getSelectionModel().isEmpty() && valPago>0){
            double valComanda = new ComandaDAL().atualizaValor(TelaEditaComandaController.com.getId());
            if(valPago<=valComanda){
                new PagamentoDAL().Salvar(new Pagamento(valPago,cbTpPgto.getSelectionModel().getSelectedItem(), com));
                new ComandaDAL().atualizaValor(TelaEditaComandaController.com.getId());
            }
        }
        carregarTabela();
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        lbComanda.getScene().getWindow().hide();
    }

    @FXML
    private void evtFecharComanda(ActionEvent event) {
        Alert alert;
        if(com.getValor()==0){
            new ComandaDAL().fechar(com.getId());
            com.setStatus('F');
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Comanda Fechada");
            alert.showAndWait();
            cbTpPgto.getScene().getWindow().hide();          
            
            String sql = "SELECT c.com_numero, c.com_nome, c.com_data, c.com_valor, " + 
            "c.com_status, p.prod_nome, p.prod_preco, garcon.gar_nome" +
            " FROM comanda c, produto p, garcon, item" +
            " WHERE c.com_id = "+ com.getId() +" and c.com_id = item.com_id AND" +
            " item.prod_id = p.prod_id AND c.gar_id = garcon.gar_id" +
            " ORDER BY c.com_nome";
            ControllerSingleton.controllerpainelprincipal.gerarRelatorio(sql, "Relatorios/RelatorioComanda.jasper", "RELATÓRIO");
        }
        else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ainda há valor a ser pago!");
            alert.showAndWait();
        }
    }
    
}
