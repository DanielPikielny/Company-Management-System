package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
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

public class ViewCompanyJavaFX implements AbstractCompanyView{
	private ArrayList<CompanyEventsListener> allListeners = new ArrayList<CompanyEventsListener>();
	private Text welcomeTxt = new Text("Welcome to company");
	private Text addObj = new Text("Add an object:");
	private Text printObj = new Text("Print objects:");
	private Text printEff = new Text("Print efficiencies:");
	private Text moreActs = new Text ("More actions");
	private Button defaultClose = new Button("Close");
	//add employee
	private Button btnAddEmployee = new Button("Add an employee");
	private Button finishEmployee = new Button("Create employee");
	private ComboBox<String> comboEmployeeRole = new ComboBox<>(); 
	private RadioButton rbEarlyPref = new RadioButton("Start work early");
	private RadioButton rbLatePref = new RadioButton("Start work late");
	private RadioButton rbNoPref = new RadioButton("Keep current hours");
	private RadioButton rbHomePref = new RadioButton("Work from home");
	private RadioButton rbBaseSalary = new RadioButton("Base (160 hrs)");
	private RadioButton rbByHourSalary = new RadioButton("Payment per hour");
	private RadioButton rbBaseNPercentageSalary = new RadioButton("Base salary + percentages");
	private CheckBox cbEmpSynchronizable = new CheckBox("Is synchronizable");
	private CheckBox cbEmpChangeable = new CheckBox("Is changeable");
	private String empName;
	private int salary;
	private String pref = "same";
	private String role;
	private String salaryMethod;
	private boolean empChangeable;
	private boolean empSynchronizable;
	//print employees
	private Button btnPrintEmployees = new Button("Print employees");
	//add role
	private Button btnAddRole = new Button("Add a role");
	private Button finishRole = new Button("Create role");
	private CheckBox cbRoleSynchronizable = new CheckBox("Is synchronizable");
	private CheckBox cbRoleChangeable = new CheckBox("Is changeable");
	private boolean roleChangeable;
	private boolean roleSynchronizable;
	//print roles
	private Button btnPrintRoles = new Button("Print roles");
	//add department
	private Button btnAddDepartment = new Button("Add a department");
	private Button finishDepartment = new Button("Create department");
	private CheckBox cbDepSynchronizable = new CheckBox("Is synchronizable");
	private CheckBox cbDepChangeable = new CheckBox("Is changeable");
	private boolean depChangeable;
	private boolean depSynchronizable;
	//print departments
	private Button btnPrintDeps = new Button("Print departments");
	//add employee to department
	private Button btnAddEmpToDep = new Button("Fill department");
	private ComboBox<String> comboDepNames = new ComboBox<>();
	private ComboBox<String> comboEmpNames = new ComboBox<>();
	private Button finishAdd = new Button("Add employee");
	private String addedEmpName;
	private String depName;
	//private String addedRole;
	//save and close
	private Button btnSave = new Button("Close and save");
	//run experiment
	private Button btnRunSim = new Button("Run Simulation");
	private ComboBox<String> comboHourChange = new ComboBox<>(); 
	private RadioButton rbChangeDep = new RadioButton("Department");
	private RadioButton rbChangeRole = new RadioButton("Role");
	private char slctPart = 'D';
	private int hrChange = 0;
	private Button btnSlctRD = new Button("Next");
	private ComboBox<String> comboDepChange = new ComboBox<>();
	private Button btnRunExp = new Button("Finish");
	private CheckBox homeWork = new CheckBox("");
	boolean homeWorkBool = false;
	private ComboBox<String> comboRoleChange = new ComboBox<>();
	//print efficiencies
	private Button btnPrintEmployeeEfficiency = new Button("Employees efficiency");
	private Button btnPrintRoleEfficiency = new Button("Roles efficiency");
	private Button btnPrintDepartmentEfficiency = new Button("Department efficiency");


	public ViewCompanyJavaFX(Stage primaryStage) {
		try {
			primaryStage.setTitle("Main menu");
			GridPane gridPaneRoot = new GridPane();
			gridPaneRoot.setPadding(new Insets(10));
			gridPaneRoot.setHgap(10);
			gridPaneRoot.setVgap(10);
			Scene scene = new Scene(gridPaneRoot, 440, 340);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			gridPaneRoot.add(welcomeTxt, 0, 0);//
			gridPaneRoot.add(addObj, 0, 1);//
			gridPaneRoot.add(printObj, 0, 3);//
			gridPaneRoot.add(btnAddEmployee, 0, 2);//
			gridPaneRoot.add(btnPrintEmployees, 0, 4);//
			gridPaneRoot.add(btnAddRole, 1, 2);//
			gridPaneRoot.add(btnPrintRoles, 1, 4);//
			gridPaneRoot.add(btnAddDepartment, 2 , 2);//
			gridPaneRoot.add(btnPrintDeps, 2, 4);//
			gridPaneRoot.add(moreActs, 0, 5);
			gridPaneRoot.add(btnAddEmpToDep, 0, 6);
			gridPaneRoot.add(btnRunSim, 1, 6);
			gridPaneRoot.add(btnPrintEmployeeEfficiency, 0, 8);
			gridPaneRoot.add(printEff, 0, 7);
			gridPaneRoot.add(btnPrintRoleEfficiency, 1, 8);
			gridPaneRoot.add(btnPrintDepartmentEfficiency, 2, 8);
			gridPaneRoot.add(btnSave, 0, 9);
			primaryStage.show();
			double rand = (Math.random() * 100000) + 100000;
			int tempBonus = (int) rand;
			int bonus = tempBonus / 100;
			btnSave.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					for (CompanyEventsListener l : allListeners) {
						l.saveResults();
					}
					primaryStage.close();
				}
			});

			btnRunSim.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						handleComboHourChange();
						handleRadioPartChange();
						handleCBHomeWork();
						GridPane gpRoot = new GridPane();
						gpRoot.setPadding(new Insets(10));
						gpRoot.setHgap(10);
						gpRoot.setVgap(10);
						Stage stage = new Stage();
						stage.setTitle("Simulation");
						stage.centerOnScreen();
						stage.setScene(new Scene(gpRoot, 230, 280));
						stage.setResizable(false);
						Label hrChangeLbl = new Label("New starting hour:");
						VBox vboxPart = new VBox(15, rbChangeDep, rbChangeRole);
						TitledPane tpanePref = new TitledPane("Part to change:", vboxPart);
						tpanePref.setCollapsible(false);
						Label homeWorkDepLbl = new Label("Work from home:");
						gpRoot.add(tpanePref, 0, 0);
						gpRoot.add(hrChangeLbl, 0, 1);
						gpRoot.add(comboHourChange, 1, 1);
						gpRoot.add(homeWorkDepLbl, 0, 2);
						gpRoot.add(homeWork, 1, 2);
						gpRoot.add(btnSlctRD, 0, 3);
						if(homeWork.isSelected()) {
							comboHourChange.setDisable(true);
						}
						stage.show();
						btnSlctRD.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent arg0) {
								stage.close();
								for (CompanyEventsListener l : allListeners) {
									if (slctPart == 'D') {
										handleComboDepChange();
										GridPane gpRoot = new GridPane();
										gpRoot.setPadding(new Insets(10));
										gpRoot.setHgap(10);
										gpRoot.setVgap(10);
										Stage stage = new Stage();
										stage.setTitle("Department time change");
										stage.centerOnScreen();
										stage.setScene(new Scene(gpRoot, 300, 150));
										stage.setResizable(false);
										Label slctDep = new Label("Select department:");
										gpRoot.add(slctDep, 0, 0);
										gpRoot.add(comboDepChange, 1, 0);
										gpRoot.add(btnRunExp, 0, 1);
										if(comboDepChange.getValue() == null) {
											finishAdd.setDisable(true);
											Label noDeps = new Label("There are no changeable departments");
											noDeps.setTextFill(Color.RED);
											gpRoot.add(noDeps, 3, 0);
										}
										stage.show();
										btnRunExp.setOnAction(new EventHandler<ActionEvent>() {
											public void handle(ActionEvent arg0) {
												if(homeWork.isSelected()) {
													l.changeDepHours(comboDepChange.getValue(), 0);
													stage.close();
												}
												else {
													String theHourS = comboHourChange.getValue();
													int theHourI = Integer.parseInt((theHourS.substring(0, theHourS.lastIndexOf(':'))));
													int finalHour = theHourI - 8;
													l.changeDepHours(comboDepChange.getValue(), finalHour);
													stage.close();
												}
											}
										});
									}
									else {
										handleComboRoleChange();
										GridPane gpRoot = new GridPane();
										gpRoot.setPadding(new Insets(10));
										gpRoot.setHgap(10);
										gpRoot.setVgap(10);
										Stage stage = new Stage();
										stage.setTitle("Role time change");
										stage.centerOnScreen();
										stage.setScene(new Scene(gpRoot, 300, 150));
										stage.setResizable(false);
										Label slctRole = new Label("Select role:");
										gpRoot.add(slctRole, 0, 0);
										gpRoot.add(comboRoleChange, 1, 0);
										gpRoot.add(btnRunExp, 0, 1);
										if(comboRoleChange.getValue() == null) {
											btnRunExp.setDisable(true);
											Label noDeps = new Label("There are no changeable roles");
											noDeps.setTextFill(Color.RED);
											gpRoot.add(noDeps, 1, 1);
										}
										stage.show();
										btnRunExp.setOnAction(new EventHandler<ActionEvent>() {
											public void handle(ActionEvent arg0) {
												if(homeWorkBool) {
													l.changeRoleHours(comboRoleChange.getValue(), 0);
													stage.close();
												}
												else {
													String theHourS = comboHourChange.getValue();
													int theHourI = Integer.parseInt((theHourS.substring(0, theHourS.lastIndexOf(':'))));
													l.changeRoleHours(comboRoleChange.getValue(), theHourI);
													stage.close();
												}
											}
										});
									}
								}
							}
						});
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnAddEmpToDep.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					//					if(Employee.allEmployees.size() == 0 || Department.allDepartments.size() == 0) {
					//						Stage stage = new Stage();
					//						Label errorLbl = new Label("Can't access without a department and an employee");
					//						stage.setTitle("Insufficient data");
					//						BorderPane pane = new BorderPane(errorLbl);
					//						pane.setBottom(defaultClose);
					//						pane.setPadding(new Insets(20));
					//						stage.setAlwaysOnTop(true);
					//						stage.setResizable(false);
					//						stage.centerOnScreen();
					//						Scene scene = new Scene(pane, 350, 100);
					//						stage.setScene(scene);
					//						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
					//							@Override
					//							public void handle(ActionEvent arg0) {
					//								stage.close();
					//							}
					//						});
					//						stage.show();
					//					}
					//					else {
					try {
						handleComboDepNames();
						handleComboEmpNames();
						GridPane gpRoot = new GridPane();
						gpRoot.setPadding(new Insets(10));
						gpRoot.setHgap(10);
						gpRoot.setVgap(10);
						Stage stage = new Stage();
						stage.setTitle("Add employee to department");
						stage.centerOnScreen();
						stage.setScene(new Scene(gpRoot, 300, 200));
						stage.setResizable(false);
						Label depSelected = new Label("Select department:");
						Label empSelected = new Label("Select employee:");
						//						Label roleOfEmp = new Label("Employee's role:");
						//						TextField showRole = new TextField();
						//						showRole.textProperty().bind(tf1.textProperty());
						//addedEmpName
						//						showRole.setEditable(false);
						int tempD = 0, tempE = 0;
						gpRoot.add(depSelected, 0, 0);
						gpRoot.add(comboDepNames, 1, 0);
						gpRoot.add(empSelected, 0, 1);
						gpRoot.add(comboEmpNames, 1, 1);
						gpRoot.add(finishAdd, 0, 2);
						for (CompanyEventsListener l : allListeners) {
							tempD += l.getDepNum();
							tempE += l.getEmpNum();
						}
						finishAdd.setDisable(false);
						if(comboDepNames.getSelectionModel().isEmpty()) {
							finishAdd.setDisable(true);
							Label noDeps = new Label("Can't add with no departments");
							noDeps.setTextFill(Color.RED);
							gpRoot.add(noDeps, 0, 3);
						}
						if (comboEmpNames.getSelectionModel().isEmpty()) {
							finishAdd.setDisable(true);
							Label noEmps1 = new Label("Can't add with no");
							Label noEmps2 = new Label("free employees");
							noEmps1.setTextFill(Color.RED);
							noEmps2.setTextFill(Color.RED);
							gpRoot.add(noEmps1, 0, 4);
							gpRoot.add(noEmps2, 0, 5);
						}
						//						gpRoot.add(roleOfEmp, 2, 0);
						//						gpRoot.add(showRole, 2, 1);
						stage.show();
						finishAdd.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								for (CompanyEventsListener l : allListeners) {
									if(l.addEmpToDep(comboDepNames.getValue(), comboEmpNames.getValue())) { //depName, addedEmpName
										stage.close();
									}
									else {
										Stage stage = new Stage();
										Label noSpaceLbl = new Label("There is no room for employees of that role in this department");
										stage.setTitle("Insufficient room");
										BorderPane pane = new BorderPane(noSpaceLbl);
										pane.setBottom(defaultClose);
										pane.setPadding(new Insets(20));
										stage.setAlwaysOnTop(true);
										stage.setResizable(false);
										stage.centerOnScreen();
										Scene scene = new Scene(pane, 350, 100);
										stage.setScene(scene);
										defaultClose.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												stage.close();
											}
										});
										stage.show();
									}
								}//comboBox.getValue()

							}
						});
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					//					}
				}
			});

			btnAddDepartment.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					int tempR = 0;
					for (CompanyEventsListener l : allListeners) {
						tempR += l.getRoleNum();
					}
					if(tempR == 0) {
						Stage stage = new Stage();
						Label noRolesLbl = new Label("Can't generate a department without any roles");
						stage.setTitle("No roles");
						BorderPane pane = new BorderPane(noRolesLbl);
						pane.setBottom(defaultClose);
						pane.setPadding(new Insets(20));
						stage.setAlwaysOnTop(true);
						stage.setResizable(false);
						stage.centerOnScreen();
						Scene scene = new Scene(pane, 350, 100);
						stage.setScene(scene);
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					else {
						try {
							handleDepChangableCB();
							handleDepSynchronizableCB();
							GridPane gpRoot = new GridPane();
							gpRoot.setPadding(new Insets(10));
							gpRoot.setHgap(10);
							gpRoot.setVgap(10);
							Stage stage = new Stage();
							stage.setTitle("Department generation");
							stage.centerOnScreen();
							stage.setScene(new Scene(gpRoot, 400, 700));
							stage.setResizable(false);
							Label depNameLbl = new Label("Name:");
							TextField depNameTxt = new TextField();
							gpRoot.add(depNameLbl, 0, 0);
							gpRoot.add(depNameTxt, 1, 0);
							gpRoot.add(cbDepSynchronizable, 0, 2);
							gpRoot.add(cbDepChangeable, 0, 3);
							Label roleNameTxtArr[] = new Label[tempR];
							TextField roleNameNumArr[] = new TextField[tempR];
							Label guideLbl = new Label("Select how many workers \nof each role are in the \ndepartment:");
							gpRoot.add(guideLbl, 0, 4);


							//							ArrayList<Role> tempRoleList;
							//							for (CompanyEventsListener l : allListeners) {
							//								for (int i = 0; i < tempR; i++) {
							//									tempRoleList.add(l.getRoleInIdx(i));
							//								}
							//							}
							ArrayList<Role> tempRoleList = null;
							for(int i = 0; i < tempR; i++) {
								for (CompanyEventsListener l : allListeners) {
									tempRoleList = l.getArrListRoles();
								}
								roleNameTxtArr[i] = new Label(tempRoleList.get(i).getName() + ":");
								roleNameNumArr[i] = new TextField(); // Accept only int
								roleNameNumArr[i].setText("0");
								gpRoot.add(roleNameTxtArr[i], 0, i + 5);
								gpRoot.add(roleNameNumArr[i], 1, i + 5);
							}
							gpRoot.add(finishDepartment, 0, tempR + 6);
							stage.show();
							@SuppressWarnings("deprecation")
							final Integer innerTempR = new Integer(tempR);
							finishDepartment.setOnAction(new EventHandler<ActionEvent>() {
								@Override 
								public void handle(ActionEvent arg0) {
									int[] arr = new int[innerTempR];
									for(int i = 0; i < arr.length; i++) {
										if(roleNameNumArr[i].getText() == null) {
											arr[i] = 0;
										}
										else {
											arr[i] = Integer.parseInt(roleNameNumArr[i].getText());
										}
									}
									for (CompanyEventsListener l : allListeners) {
										if(!l.usedDepName(depNameTxt.getText())) {
											l.addDepToModelEvent(depNameTxt.getText(), arr, cbDepChangeable.isSelected(), cbDepSynchronizable.isSelected());
											stage.close();
										}
										else {
											Stage stage = new Stage();
											Label nameTakenlbl = new Label("Please select a name that isn't already in use");
											stage.setTitle("Used name");
											BorderPane pane = new BorderPane(nameTakenlbl);
											pane.setBottom(defaultClose);
											pane.setPadding(new Insets(20));
											stage.setAlwaysOnTop(true);
											stage.setResizable(false);
											stage.centerOnScreen();
											Scene scene = new Scene(pane, 333, 100);
											stage.setScene(scene);
											defaultClose.setOnAction(new EventHandler<ActionEvent>() {
												@Override
												public void handle(ActionEvent arg0) {
													stage.close();
												}
											});
											stage.show();
										}
									}	
								}
							});
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			btnPrintDeps.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						Text depText = new Text();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getDepartments();
						}
						depText.setText(str);
						BorderPane root = new BorderPane(depText);
						root.setBottom(defaultClose);
						root.setPadding(new Insets(25));
						ScrollPane sp = new ScrollPane();
						sp.setContent(root);
						Scene scene = new Scene(sp, 900, 600); 
						stage.setTitle("All roles");
						stage.setScene(scene);
						stage.setResizable(false);
						stage.centerOnScreen();
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}

			});

			btnAddRole.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						handleRoleChangableCB();
						handleRoleSynchronizableCB();
						GridPane gpRoot = new GridPane();
						gpRoot.setPadding(new Insets(10));
						gpRoot.setHgap(10);
						gpRoot.setVgap(10);
						Stage stage = new Stage();
						stage.setTitle("Role generation");
						stage.centerOnScreen();
						stage.setScene(new Scene(gpRoot, 350, 150));
						stage.setResizable(false);
						Label roleNameLbl = new Label("Name:");
						TextField roleNameTxt = new TextField();
						cbRoleSynchronizable.setSelected(false);
						cbRoleChangeable.setSelected(false);
						gpRoot.add(roleNameLbl, 0, 0);
						gpRoot.add(roleNameTxt, 1, 0);
						gpRoot.add(cbRoleSynchronizable, 0, 1);
						gpRoot.add(cbRoleChangeable, 0, 2);
						gpRoot.add(finishRole, 0, 3);
						stage.show();
						finishRole.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								for (CompanyEventsListener l : allListeners) {
									if(!l.usedRoleName(roleNameTxt.getText())) {
										l.addRoleToModelEvent(roleNameTxt.getText(), cbRoleChangeable.isSelected(), cbRoleSynchronizable.isSelected());
										stage.close();
									}
									else {
										Stage stage = new Stage();
										Label nameTakenlbl = new Label("Please select a name that isn't already in use");
										stage.setTitle("Used name");
										BorderPane pane = new BorderPane(nameTakenlbl);
										pane.setBottom(defaultClose);
										pane.setPadding(new Insets(20));
										stage.setAlwaysOnTop(true);
										stage.setResizable(false);
										stage.centerOnScreen();
										Scene scene = new Scene(pane, 333, 100);
										stage.setScene(scene);
										defaultClose.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												stage.close();
											}
										});
										stage.show();
									}
								}
							}
						});
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}

			});

			btnPrintRoles.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						Text roleText = new Text();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getRoles();
						}
						roleText.setText(str);
						BorderPane root = new BorderPane(roleText);
						root.setBottom(defaultClose);
						root.setPadding(new Insets(25));
						ScrollPane sp = new ScrollPane();
						sp.setContent(root);
						Scene scene = new Scene(sp, 900, 600); 
						stage.setTitle("All roles");
						stage.setScene(scene);
						stage.setResizable(false);
						stage.centerOnScreen();
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});

			btnAddEmployee.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					int tempR = 0;
					for (CompanyEventsListener l : allListeners) {
						tempR += l.getRoleNum();
					}
					if (tempR == 0) {
						Stage stage = new Stage();
						Label noRolesLbl = new Label("Can't generate an employee without any roles");
						stage.setTitle("No roles");
						BorderPane pane = new BorderPane(noRolesLbl);
						pane.setBottom(defaultClose);
						pane.setPadding(new Insets(20));
						stage.setAlwaysOnTop(true);
						stage.setResizable(false);
						stage.centerOnScreen();
						Scene scene = new Scene(pane, 350, 100);
						stage.setScene(scene);
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					else {
						try {
							handleRadioEmployeePref();
							handleComboEmployeeRole();
							handleRadioEmployeeSalaryMethod();
							handleEmployeeChangableCB();
							handleEmployeeSynchronizableCB();
							GridPane gpRoot = new GridPane();
							gpRoot.setPadding(new Insets(10));
							gpRoot.setHgap(10);
							gpRoot.setVgap(10);
							Stage stage = new Stage();
							stage.setTitle("Employee generation");
							stage.centerOnScreen();
							stage.setScene(new Scene(gpRoot, 400, 530));
							Label empNameLbl = new Label("Name:");
							TextField empNameTxt = new TextField();
							Label empSalLbl = new Label("Salary:");
							TextField empSalTxt = new TextField();
							empSalTxt.setText("0");
							Label empRoleLbl = new Label("Role:");
							VBox vboxPref = new VBox(15, rbNoPref, rbEarlyPref, rbLatePref, rbHomePref);
							TitledPane tpanePref = new TitledPane("Work preference:", vboxPref);
							VBox vboxSal = new VBox(15, rbBaseSalary, rbByHourSalary, rbBaseNPercentageSalary);
							TitledPane tpaneSal = new TitledPane("Salary method:", vboxSal);
							tpanePref.setCollapsible(false);
							tpaneSal.setCollapsible(false);
							stage.setResizable(false);
							cbEmpSynchronizable.setSelected(false);
							cbEmpChangeable.setSelected(false);
							gpRoot.add(empNameLbl, 0, 0);
							gpRoot.add(empNameTxt, 1, 0);
							gpRoot.add(empRoleLbl, 0, 1);
							gpRoot.add(comboEmployeeRole, 1, 1);
							gpRoot.add(empSalLbl, 0, 2);
							gpRoot.add(empSalTxt, 1, 2);
							gpRoot.add(tpanePref, 0, 3);
							gpRoot.add(tpaneSal, 0, 4);
							gpRoot.add(cbEmpSynchronizable, 0, 5);
							gpRoot.add(cbEmpChangeable, 0, 6);
							gpRoot.add(finishEmployee, 0, 7);
							stage.show();
							finishEmployee.setOnAction(new EventHandler<ActionEvent>() {
								@Override 
								public void handle(ActionEvent arg0) {
									int salaryInCreation = Integer.parseInt(empSalTxt.getText());
									for (CompanyEventsListener l : allListeners) {
										if ((empSalTxt.getText().matches("[0-9]+"))) {//{9}$
											if (salaryMethod.equals("percentage")) {
												l.addEmpToModelEvent(empNameTxt.getText(), pref, comboEmployeeRole.getValue(), salaryMethod, salaryInCreation, cbEmpChangeable.isSelected(), cbEmpSynchronizable.isSelected(), bonus);
											}
											else {
												l.addEmpToModelEvent(empNameTxt.getText(), pref, comboEmployeeRole.getValue(), salaryMethod, salaryInCreation, cbEmpChangeable.isSelected(), cbEmpSynchronizable.isSelected(), 0);
											}
											stage.close();
										}
										else {
											Stage stage = new Stage();
											Label salaryErr = new Label("Invalid salary input.");
											stage.setTitle("Input error");
											BorderPane pane = new BorderPane(salaryErr);
											pane.setBottom(defaultClose);
											pane.setPadding(new Insets(20));
											stage.setAlwaysOnTop(true);
											stage.setResizable(false);
											stage.centerOnScreen();
											Scene scene = new Scene(pane, 250, 100);
											stage.setScene(scene);
											defaultClose.setOnAction(new EventHandler<ActionEvent>() {
												@Override
												public void handle(ActionEvent arg0) {
													stage.close();
												}
											});
											stage.show();
										}
									}

								}
							});
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}

				}
			});

			btnPrintEmployees.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						Text employeeText = new Text();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getEmployees();
						}
						employeeText.setText(str);
						BorderPane root = new BorderPane(employeeText);
						root.setBottom(defaultClose);
						root.setPadding(new Insets(25));
						ScrollPane sp = new ScrollPane();
						sp.setContent(root);
						Scene scene = new Scene(sp, 900, 600); 
						stage.setTitle("All employees");
						stage.setScene(scene);
						stage.setResizable(false);
						stage.centerOnScreen();
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			btnPrintDepartmentEfficiency.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						Text depEffTxt = new Text();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getDepartmentsEfficiency();
						}
						depEffTxt.setText(str);
						BorderPane root = new BorderPane(depEffTxt);
						root.setBottom(defaultClose);
						root.setPadding(new Insets(25));
						ScrollPane sp = new ScrollPane();
						sp.setContent(root);
						Scene scene = new Scene(sp, 900, 600); 
						stage.setTitle("Departments efficiency");
						stage.setScene(scene);
						stage.setResizable(false);
						stage.centerOnScreen();
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			btnPrintRoleEfficiency.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						Text roleEffTxt = new Text();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getRolesEfficiency();
						}
						roleEffTxt.setText(str);
						BorderPane root = new BorderPane(roleEffTxt);
						root.setBottom(defaultClose);
						root.setPadding(new Insets(25));
						ScrollPane sp = new ScrollPane();
						sp.setContent(root);
						Scene scene = new Scene(sp, 900, 600); 
						stage.setTitle("Roles efficiency");
						stage.setScene(scene);
						stage.setResizable(false);
						stage.centerOnScreen();
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			btnPrintEmployeeEfficiency.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						Stage stage = new Stage();
						String str = "";
						for (CompanyEventsListener l : allListeners) {
							str += l.getEmployeesEfficiency();
						}
						Label empEffPrint = new Label(str);
						stage.setTitle("Employees efficiency");
						BorderPane pane = new BorderPane(empEffPrint);
						pane.setBottom(defaultClose);
						pane.setPadding(new Insets(20));
						stage.setAlwaysOnTop(true);
						stage.setResizable(false);
						stage.centerOnScreen();
						Scene scene = new Scene(pane, 400, 100);
						stage.setScene(scene);
						defaultClose.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								stage.close();
							}
						});
						stage.show();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//@@@@@@@@@ START department creation details @@@@@@@@@@@\
	private void handleDepChangableCB() {
		cbEmpChangeable.setOnAction(e -> updateDepartment());
	}

	private void handleDepSynchronizableCB() {
		cbEmpSynchronizable.setOnAction(e -> updateDepartment());
	}

	private void updateDepartment() {
		depChangeable = cbEmpChangeable.isSelected() ? true : false;
		depSynchronizable = cbEmpSynchronizable.isSelected() ? true : false; 
	}
	//@@@@@@@@@ END department creation details @@@@@@@@@@@

	//@@@@@@@@@ START add employee to department buttons @@@@@@@@@@@
	private void handleComboDepNames() {
		comboDepNames.getItems().clear();
		for (CompanyEventsListener l : allListeners) {
			comboDepNames.getItems().addAll(l.getDepartmentNames());
		}
		comboDepNames.getSelectionModel().select(0);
		comboDepNames.setOnAction(e -> {
			updateEmployeeAddition();
		});
	}

	private void handleComboEmpNames() {
		comboEmpNames.getItems().clear();
		for (CompanyEventsListener l : allListeners) {
			comboEmpNames.getItems().addAll(l.getEmpNRole());
		}
		comboEmpNames.getSelectionModel().select(0);
		comboEmpNames.setOnAction(e -> {
			updateEmployeeAddition();
		});
	}

	private void updateEmployeeAddition() {
		depName = comboDepNames.getValue();
		addedEmpName = comboEmpNames.getValue();
		//addedRole = comboEmpNames.getValue()
	}

	//@@@@@@@@@ END add employee to department buttons @@@@@@@@@@@

	//@@@@@@@@@ START employee creation details @@@@@@@@@@@
	private void handleRadioEmployeePref() {
		ToggleGroup t = new ToggleGroup();
		rbNoPref.setToggleGroup(t);
		rbEarlyPref.setToggleGroup(t);
		rbLatePref.setToggleGroup(t);
		rbHomePref.setToggleGroup(t);
		rbNoPref.setSelected(true);
		EventHandler<ActionEvent> handler = e -> updateEmployee();
		rbNoPref.setOnAction(handler);
		rbEarlyPref.setOnAction(handler);
		rbLatePref.setOnAction(handler);
		rbHomePref.setOnAction(handler);
	}

	private void handleComboEmployeeRole() {
		comboEmployeeRole.getItems().clear();
		for (CompanyEventsListener l : allListeners) {
			comboEmployeeRole.getItems().addAll(l.getRoleNames());
		}
		comboEmployeeRole.getSelectionModel().select(0);
		comboEmployeeRole.setOnAction(e -> {
			updateEmployee();
		});
	}

	private void handleRadioEmployeeSalaryMethod() {
		ToggleGroup t = new ToggleGroup();
		rbBaseSalary.setToggleGroup(t); 
		rbByHourSalary.setToggleGroup(t);
		rbBaseNPercentageSalary.setToggleGroup(t);
		rbBaseSalary.setSelected(true);
		EventHandler<ActionEvent> handler = e -> updateEmployee();
		rbBaseSalary.setOnAction(handler);
		rbByHourSalary.setOnAction(handler);
		rbBaseNPercentageSalary.setOnAction(handler);
	}

	private void handleEmployeeChangableCB() {
		cbEmpChangeable.setOnAction(e -> updateEmployee());
	}

	private void handleEmployeeSynchronizableCB() {
		cbEmpSynchronizable.setOnAction(e -> updateEmployee());
	}

	private void updateEmployee() {
		pref = "same";
		if (rbEarlyPref.isSelected())
			pref = "early";
		else if (rbLatePref.isSelected())
			pref = "late";
		else if (rbNoPref.isSelected())
			pref = "same";
		else if (rbHomePref.isSelected())
			pref = "home";
		salaryMethod = "base";
		if (rbBaseSalary.isSelected())
			salaryMethod = "base";
		else if (rbByHourSalary.isSelected())
			salaryMethod = "hours";
		else if (rbBaseNPercentageSalary.isSelected())
			salaryMethod = "percentage";
		role = comboEmployeeRole.getValue();
		empChangeable = cbEmpChangeable.isSelected() ? true : false;
		empSynchronizable = cbEmpSynchronizable.isSelected() ? true : false; 
	}

	//@@@@@@@@@ END employee creation details @@@@@@@@@@@

	//@@@@@@@@@ START role creation details @@@@@@@@

	private void handleRoleChangableCB() {
		cbEmpChangeable.setOnAction(e -> updateRole());
	}

	private void handleRoleSynchronizableCB() {
		cbEmpSynchronizable.setOnAction(e -> updateRole());
	}

	private void updateRole() {
		roleChangeable = cbEmpChangeable.isSelected() ? true : false;
		roleSynchronizable = cbEmpSynchronizable.isSelected() ? true : false; 
	}

	//@@@@@@@@@ END employee creation details @@@@@@@@@@@

	//@@@@@@@@@ START experiment @@@@@@@@@
	private void handleComboHourChange() {
		comboHourChange.getItems().clear();
//		comboHourChange.getItems().add(0);
//		for(int i = 8; i > 0; i--) {
//			comboHourChange.getItems().add(i);
//		}
//		for(int i = -1; i > -9; i--) {
//			comboHourChange.getItems().add(i);
//		}
		for(int i = 0; i < 8; i++) {
			comboHourChange.getItems().add(i + ":00");
		}
		for(int i = 9; i < 24; i++) {
			comboHourChange.getItems().add(i + ":00");
		}
		comboHourChange.getSelectionModel().select(0);
		comboHourChange.setOnAction(e -> {
			updateExpRun();
		});
	}

	private void handleRadioPartChange() {
		ToggleGroup t = new ToggleGroup();
		rbChangeDep.setToggleGroup(t);
		rbChangeRole.setToggleGroup(t);
		rbChangeDep.setSelected(true);
		EventHandler<ActionEvent> handler = e -> updateExpRun();
		rbChangeDep.setOnAction(handler);
		rbChangeRole.setOnAction(handler);
	}
	
	private void handleCBHomeWork() {
		homeWork.setOnAction(e -> updateExpRun());
	}
	
//	handleCBHomeWork();homeWork.setSelected(false);
	private void updateExpRun() {
		if(rbChangeDep.isSelected()) {
			slctPart = 'D';
		}
		if(rbChangeRole.isSelected()) {
			slctPart = 'R';
		}
		homeWorkBool = homeWork.isSelected() ? true : false;
		//hrChange = comboHourChange.getValue();
	}

	private void handleComboDepChange() {
		comboDepChange.getItems().clear();
		for (CompanyEventsListener l : allListeners) {
			comboDepChange.getItems().addAll(l.getChangeableDepartmentNames());
		}
		comboDepChange.getSelectionModel().select(0);
		comboDepChange.setOnAction(e -> {
			updateDepHrChange();
		});
	}
	
	private void handleComboRoleChange() {
		comboRoleChange.getItems().clear();
		for (CompanyEventsListener l : allListeners) {
			comboRoleChange.getItems().addAll(l.getChangeableRoleNames());
		}
		comboRoleChange.getSelectionModel().select(0);
		comboRoleChange.setOnAction(e -> {
			updateDepHrChange();
		});
	}

	private void updateDepHrChange() {
		//changedDepName = comboDepChange.getValue();
		return;
	}
	//@@@@@@@@@ END experiment @@@@@@@@@

	@Override
	public void registerListener(CompanyEventsListener newListener) {
		allListeners.add(newListener);
	}

}
