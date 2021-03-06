package sample.frontend;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomStage extends Stage {
    public CustomStage() {
        this.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
    }

    public void setScene(Parent content, double width, double height) {
            Scene scene = new Scene(content, width, height);
            scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toString());
            this.setResizable(false);
            this.setScene(scene);
    }

    protected void enableBlocking() {
        initModality(Modality.APPLICATION_MODAL);
    }
}
