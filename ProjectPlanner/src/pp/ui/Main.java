package pp.ui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pp.app.Activity;
import pp.app.PpApp;
import pp.app.Project;
import pp.app.User;
import pp.app.WorkSession;

public class Main extends Application {
	private PpApp ppApp;
	private StartUp startUp;
	private double dragAnchorX, dragAnchorY;

	@Override
	public void start(Stage window) throws Exception {
		ppApp = new PpApp();
		startUp = new StartUp(ppApp);
		setUp();
		
		Scene scene = new Scene(new View(ppApp), 900, 650, Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
		makeWindowDragable(scene, window);

		window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("Project Planner");
		window.setScene(scene);
        window.show();
	}

	private void makeWindowDragable(Scene scene, Stage window) {
		scene.setOnMousePressed(pe -> {
			dragAnchorX = pe.getScreenX() - window.getX();
            dragAnchorY = pe.getScreenY() - window.getY();
		});
		scene.setOnMouseDragged(de -> {
			window.setX(de.getScreenX() - dragAnchorX);
            window.setY(de.getScreenY() - dragAnchorY);
		});
	}

	private void setUp() {
		User user1 = startUp.makeAndRegisterUser("John", "Nielsen");
		Project project1 = startUp.makeAndAddProject("Rejsekortet", "Det skal fixes", startUp.date1);
		Activity activity1 = startUp.makeAndAddActivity("Design", "Design af brugergænseflade", startUp.date2, 100L, project1);
		Activity activity2 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity3 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity4 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity5 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity6 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity7 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity8 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity9 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
//		Activity activity10 = startUp.makeAndAddActivity("Implementering", "Implementering af NFC", startUp.date2, 100L, project1);
		activity1.assignUserToActivity(user1);
		activity2.assignUserToActivity(user1);

		user1.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity1, 60L));
}

	public static void main(String[] args) {
		launch(args);
	}

}