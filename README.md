# Company-Management-System
This is a desktop application for managing a company's departments, roles, and employees. The application allows users to add new departments, roles, and employees, assign employees to departments, and track their efficiency. The project is built using the Model-View-Controller design pattern and leverages JavaFX for the graphical user interface.

Features
Department Management: Create and manage departments with specific roles.

Role Management: Define different roles within the company.

Employee Management: Add employees, assign them roles, and set their work preferences (e.g., salary method, hours).

Efficiency Tracking: The system calculates and displays the efficiency of employees, roles, and departments.

Data Persistence: The application can save and load data to and from a file (1.txt).

Modular Design: The project is structured with separate classes for the Model, View, and Controller to ensure a clean separation of concerns.

How to Run
Prerequisites: Ensure you have the Java Development Kit (JDK) and a compatible IDE (like Eclipse or IntelliJ IDEA) with JavaFX support installed.

Clone or Download: Get the project files.

Configure IDE:

Open the project in your IDE.

Add the JavaFX SDK to your project's build path. You can find instructions for your specific IDE online.

Run the Main Class:

Locate the CompanyMain.java file.

Run this file as a JavaFX Application. The application will launch two separate windows, each representing a different view (ViewCompanyJavaFX and ViewCompanyJavaFX2) of the same data model.

Code Structure
CompanyMain.java: The main entry point of the application, responsible for initializing the MVC components and launching the JavaFX stage.

CompanyModel.java: Manages the application's data, including lists of departments, roles, and employees. It contains the core logic for adding, retrieving, and manipulating company data, as well as handling file I/O for data persistence.

CompanyController.java: Acts as the intermediary between the Model and View. It handles user input from the view and updates the model accordingly. It also implements the CompanyEventsListener interface to listen for events from the views.

AbstractCompanyView.java: An interface that defines the contract for all views.

ViewCompanyJavaFX.java & ViewCompanyJavaFX2.java: Implementations of the AbstractCompanyView interface. These classes create the graphical user interfaces for interacting with the system.

Department.java, Employee.java, Role.java: Core data model classes representing the main entities of the company.

Synchronizable.java & WorkMethodChangeable.java: Interfaces defining properties for employees, roles, and departments related to work synchronization and changability.

CompanyEventsListener.java: An interface for event handling, allowing the controller to communicate with the views.
