package boteco;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class telaModal {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private double xOffset, yOffset;
    private FXMLLoader loader;
    

    public telaModal(String fxml) throws IOException {
        loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = (Parent) loader.load();
        root = FXMLLoader.load(getClass().getResource(fxml));
        scene = new Scene(root);
        stage = new Stage();
        xOffset = yOffset = 0;
        
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
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
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    
}
