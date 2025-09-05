package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Role implements Serializable, WorkMethodChangeable, Synchronizable {

	private static final long serialVersionUID = 1L;
	public static ArrayList<Role> allRoles = new ArrayList<Role>();
	private String name;
	public ArrayList<Employee> allEmployees;//needed for toString
	private boolean changeable;
	private boolean synchronizable;

	public Role(String name, boolean changeable, boolean synchronizable) {
		this.name = name;
		this.changeable = changeable;
		this.synchronizable = synchronizable;
		this.allEmployees = new ArrayList<Employee>();
		allRoles.add(this);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isSynchronizable() {
		return synchronizable;
	}

	@Override
	public boolean isChangeable() {
		return changeable;
	}

	public void addEmployee(Employee employee) {
		allEmployees.add(employee);
		return;
	}

	public String toString() {
		String notC = "";
		String notS = "";
		if (!changeable) {
			notC += "not";
		}
		if (!synchronizable) {
			notS += "not";
		}
		String s = name + " is " + notC + " changeable and " + notS + " synchronizable.\n" + "The " + name + "s of the company are:\n";
		for(int i = 0; i < allEmployees.size(); i++) {
			s += allEmployees.get(i).toString();
		}
		s += "\n";
		return s;
	}
}
