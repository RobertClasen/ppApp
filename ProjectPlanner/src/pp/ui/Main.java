package pp.ui;

import java.time.LocalDate;
import java.time.Month;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pp.app.Activity;
import pp.app.PpApp;
import pp.app.Project;
import pp.app.User;

public class Main extends Application {
	private PpApp ppApp;
	private double dragAnchorX, dragAnchorY;

	@Override
	public void start(Stage window) throws Exception {
		ppApp = new PpApp();
		startUp();
		
		Scene scene = new Scene(new View(ppApp), 800, 550, Color.TRANSPARENT);
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


	private void startUp() {
		User user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
		ppApp.registerUser(user1);

		Project project1 = new Project(ppApp);
		project1.setTitle("Rejsekortet");
		project1.setDescription("Det ska fornys");
		project1.setStartDate(LocalDate.of(2017, Month.JANUARY, 1));
		ppApp.addProject(project1);

		Activity activity1 = new Activity(ppApp, project1);
		activity1.setTitle("Design");
		activity1.setDescription("Design af brugergænseflade");
		activity1.setStartDate(LocalDate.of(2017, Month.FEBRUARY, 1));
		activity1.setEstimatedTime(100L);
		project1.addActivity(activity1);
		activity1.assignUserToActivity(user1);
		
		Activity activity2 = new Activity(ppApp, project1);
		activity2.setTitle("Implementering");
		activity2.setDescription("Implementering af brugergænseflade");
		activity2.setStartDate(LocalDate.of(2017, Month.MARCH, 1));
		activity2.setEstimatedTime(80L);
		project1.addActivity(activity2);
		activity2.assignUserToActivity(user1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}