package boteco;

import boteco.db.util.Banco;
import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane painel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            evtModuloComandas(null);
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evtModuloComandas(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("TelaPComandas.fxml"));
        
        Parent root = (Parent) loader.load();
        ControllerSingleton.controllerpainelcomandas = loader.getController();
        painel.setCenter(root);
    }

    @FXML
    private void evtModuloAdm(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
        
        Parent root = (Parent) loader.load();
        painel.setCenter(root);
    }

    public void gerarRelatorio(String sql, String relat, String titulo)
    {
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            String jasperPrint = JasperFillManager.fillReportToFile(relat,null, jrRS);
            JasperViewer viewer = new JasperViewer(jasperPrint, false, false);

            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            viewer.setTitle(titulo);
            viewer.setVisible(true);
        } catch (JRException erro)
        {  erro.printStackTrace(); }
   }
    
    @FXML
    private void evtRelProdutos(ActionEvent event) {
        String sql = "select * from produto as p, categoria as c, unidade as u " +
                     "where p.cat_id=c.cat_id and p.uni_id=u.uni_id " +
                     "order by p.prod_nome desc";
        gerarRelatorio(sql, "Relatorios/RelatorioProdutos.jasper", "RELATÓRIO");
    }

    @FXML
    private void evtRelGarcons(ActionEvent event) {
        String cidade = JOptionPane.showInputDialog("Cidade do Garçon");
        
        String sql = "SELECT gar_cidade AS Cidade, gar_nome AS Nome"
                    + " FROM garcon WHERE gar_cidade LIKE '"+cidade+"'";
        gerarRelatorio(sql, "Relatorios/RelatorioGarcons.jasper", "RELATÓRIO");
    }

    private String escolherData(String estado) {
        String message;
        JDateChooser jd = new JDateChooser();
        if(estado.matches("inicial"))
            message ="Data Inicial\n";
        else
            message ="Data Final\n";
        Object[] params = {message,jd};
        JOptionPane.showConfirmDialog(null,params,"Gerar Comanda por Período", JOptionPane.PLAIN_MESSAGE);

        String data="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        data=sdf.format(((JDateChooser)params[1]).getDate());
        return data;
    }
    
    @FXML
    private void evtRelComandaData(ActionEvent event) {
        String dataInicial = escolherData("inicial");
        String dataFinal = escolherData("final");
        String sql;
        
        sql = "SELECT * FROM comanda c "
                + "WHERE c.com_data between '"+ dataInicial +"' "
                + "and '"+ dataFinal +"' AND c.com_status = 'F'";
        gerarRelatorio(sql, "Relatorios/RelatorioComandaData.jasper", "RELATÓRIO");
    }

    @FXML
    private void evtRelCategoria(ActionEvent event) {
        String sql = "select c.cat_nome,p.prod_preco, p.prod_nome" +
                " from produto p, categoria c, unidade u" +
                " where p.cat_id=c.cat_id and p.uni_id = u.uni_id" +
                " order by c.cat_nome";
        gerarRelatorio(sql, "Relatorios/RelatorioCategoria.jasper", "RELATÓRIO");
    }   

    @FXML
    private void evtMinizar(ActionEvent event) {
        Stage stage = (Stage)painel.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void evtMaximizar(ActionEvent event) {
        Stage stage = (Stage)painel.getScene().getWindow();
        if(stage.isMaximized())
            stage.setMaximized(false);
        else
            stage.setMaximized(true);
    }

    @FXML
    private void evtFechar(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void evtRestaurar(ActionEvent event) throws Exception {
        Banco.restaurar("bkp_boteco.backup");
    }

    @FXML
    private void evtBackup(ActionEvent event) throws Exception {
        Banco.backup("bkp_boteco.backup");
    }

    @FXML
    private void evtAjuda(ActionEvent event) {
        File file = new File("Ajuda/BotecoAjuda.chm");
        try {
            Runtime.getRuntime().exec("HH.EXE ms-its:" + file.getAbsolutePath() + "::/*.htm");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}