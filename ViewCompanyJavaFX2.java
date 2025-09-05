package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class ViewCompanyJavaFX2 implements AbstractCompanyView  {
	private ArrayList<CompanyEventsListener> allListeners = new ArrayList<CompanyEventsListener>();
	private Label label = new Label("Do you want to load data from file?");
	private Button btnYes = new Button("Yes");
	private Button btnNo = new Button("No");
	private Button defaultClose = new Button("Close");
	public ViewCompanyJavaFX2(Stage theStage) {
		try {
			theStage.setTitle("File Read");
			HBox hbox = new HBox(20, btnYes, btnNo);
			VBox vbox = new VBox(25, label, hbox);
			vbox.setPadding(new Insets(20));
			Scene scene = new Scene(vbox, 250, 100);
			theStage.setScene(scene);
			theStage.show();
			theStage.setAlwaysOnTop(true);
			btnYes.setOnAction(new EventHandler<ActionEvent>() {
                @Override 
                public void handle(ActionEvent arg0) {
                	for (CompanyEventsListener l : allListeners) {
                		if(!l.readData(true)) {
                			Stage stage = new Stage();
							Label idErrorlbl = new Label("File not found. Starting the program with no data.");
							stage.setTitle("File not found");
							BorderPane pane = new BorderPane(idErrorlbl);
							pane.setBottom(defaultClose);
							pane.setPadding(new Insets(20));
							stage.setAlwaysOnTop(true);
							stage.setResizable(false);
							stage.centerOnScreen();
							Scene scene = new Scene(pane, 340, 100);
							stage.setScene(scene);
							defaultClose.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									stage.close();
								}
							});
							stage.show();
							l.readData(false);
                		}
                	}
                	theStage.close();//l.readData(true);
                }
            });
			btnNo.setOnAction(new EventHandler<ActionEvent>() {
                @Override 
                public void handle(ActionEvent arg0) {
                	for (CompanyEventsListener l : allListeners) {
                		l.readData(false);
                	}
                	theStage.close();
                }
            });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerListener(CompanyEventsListener newListener) {
		allListeners.add(newListener);
	}
}
