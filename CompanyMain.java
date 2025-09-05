package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CompanyMain extends Application{

	@Override
	public void start(Stage primaryStage) {
		CompanyModel theModel = new CompanyModel();
		AbstractCompanyView theView1 = new ViewCompanyJavaFX(primaryStage);
		CompanyController controller1 = new CompanyController(theModel, theView1);
		AbstractCompanyView theView2 = new ViewCompanyJavaFX2((new Stage()));
		CompanyController controller2 = new CompanyController(theModel, theView2);
	}
	public static void main(String[] args) {
		launch(args);
	}
}