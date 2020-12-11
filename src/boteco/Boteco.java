package boteco;

import boteco.db.util.Banco;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class Boteco extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
   
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));

        Parent root = (Parent) loader.load();
        ControllerSingleton.controllerpainelprincipal = loader.getController();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent event){
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if(!Banco.conectar()) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar:" 
                    + Banco.getCon().getMensagemErro());
            if(JOptionPane.showConfirmDialog(null, "Confirma a tentativa de criação de uma nova base de dados")==JOptionPane.YES_OPTION)
            {
               if(Banco.criarBD("BotecoDB"))
                   try
                   {   Banco.restaurar("bdutil/bkp_inicial.backup");  }
                   catch(Exception e){ }
            }
            Platform.exit();
        }
        launch(args);
    }
}