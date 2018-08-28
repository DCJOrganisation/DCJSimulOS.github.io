package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertAbout {
	
	public static void display(String title, String message) {
		Stage window = new Stage();
		// TODO Auto-generated method stub
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("");
		window.setMinWidth(250);
		window.setMinHeight(250);
		Label label = new Label();
		label.setText(message);
		VBox layout = new VBox(10);
		Button exitbutton = new Button("EXIT");
		exitbutton.setOnAction(e->{
			window.close();
		});
		layout.getChildren().addAll(label, exitbutton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
