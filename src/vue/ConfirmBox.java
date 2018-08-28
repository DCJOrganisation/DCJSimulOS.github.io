package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static Boolean answer;

	public static Boolean display(String title, String message) {
		Stage window = new Stage();
		// TODO Auto-generated method stub
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("");
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		Button yesbutton = new Button("Yes");
		Button nobutton = new Button("No");
		yesbutton.setOnAction(e->{
			answer = true ;
			window.close();
		});
		nobutton.setOnAction(e->{
			answer = false ;
			window.close();
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesbutton, nobutton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return answer;
	}

}
