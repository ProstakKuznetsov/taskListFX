package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// не сделал удаление еще и не все ограничения по вводимым данным

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("myForm.fxml"));
			primaryStage.setTitle("Добро пожаловать!");
			primaryStage.setScene(new Scene(root, 650, 400));
			primaryStage.setMinWidth(650);
			primaryStage.setMinHeight(450);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
