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

/**
 * @author Rasmus
 */

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
		User user2 = startUp.makeAndRegisterUser("Andreas", "Hansen");
		User user3 = startUp.makeAndRegisterUser("Ulla", "Brit");
		Project project1 = startUp.makeAndAddProject("Rejsekortet", "Det skal fixes", startUp.date1);
		Project project2 = startUp.makeAndAddProject("Elektronisk patientjournal", "Det skal sammenkobles", startUp.date1);
		Activity activity1 = startUp.makeAndAddActivity("Design", "Design af brugergænseflade", startUp.date2, 120L, project1);
		Activity activity2 = startUp.makeAndAddActivity("Check-in", "Implementering af Check-in på stationerne", startUp.date2, 100L, project1);
		Activity activity3 = startUp.makeAndAddActivity("Check-ud", "Implementering af Check-ud på stationerne", startUp.date2, 100L, project1);
		Activity activity4 = startUp.makeAndAddActivity("Rejsehistorik", "Implementering af brugerens rejseoversigt", startUp.date2, 75L, project1);
		Activity activity5 = startUp.makeAndAddActivity("Brugergrænseflade", "Design af brugergænseflade", startUp.date2, 130L, project2);
		Activity activity6 = startUp.makeAndAddActivity("Patientjournaler", "Deling af patientjournaler", startUp.date2, 200L, project2);
		Activity activity7 = startUp.makeAndAddActivity("Patientflytning", "Implementering af struktur af flytning af patienter", startUp.date2, 60L, project2);
		Activity activity8 = startUp.makeAndAddActivity("Kravsspecifikation", "Kravsspecifikation for softwaren", startUp.date2, 75L, project2);
		activity1.assignUserToActivity(user1);
		activity2.assignUserToActivity(user1);
		activity3.assignUserToActivity(user2);
		activity4.assignUserToActivity(user3);
		activity5.assignUserToActivity(user2);
		activity6.assignUserToActivity(user3);
		activity7.assignUserToActivity(user3);
		activity8.assignUserToActivity(user1);
		

		user1.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity1, 320L));
		user1.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity6, 440L));
		user1.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity4, 90L));
		user2.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity3, 145L));
		user2.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity2, 520L));
		user2.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity5, 500L));
		user3.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity8, 230L));
		user3.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity1, 100L));
		user3.addWorkSession(new WorkSession(LocalDate.now().minusDays(1L), activity7, 300L));
}

	public static void main(String[] args) {
		launch(args);
	}

}