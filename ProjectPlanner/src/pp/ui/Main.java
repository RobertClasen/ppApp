package pp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage window) throws Exception {
		Scene scene = new Scene(new View(), 800, 550, Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
		
        window.setTitle("Project Planner");
		window.setScene(scene);
        window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
