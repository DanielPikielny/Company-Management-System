package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable, WorkMethodChangeable, Synchronizable {
	
	private static final long serialVersionUID = 1L;
	//public static ArrayList<Department> allDepartments = new ArrayList<Department>();
	private String name;
	private int numOfEmployees;
	private boolean changeable;
	private boolean synchronizable;
	private Role[] roles;
	public Employee[] employees;
	private int employeeCounter = 0;
	//private ArrayList<Role> roles;
	
	public Department(String name, int[] roleCounter, boolean changeable, boolean synchronizable, ArrayList<Role> roles) {
		this.name = name;
		this.changeable = changeable;
		this.synchronizable = synchronizable;
//		this.numOfEmployees = numOfEmployees;
		numOfEmployees = 0;
		for (int i = 0; i < roleCounter.length; i++) {
			numOfEmployees += roleCounter[i];
		}
		this.employees = new Employee[numOfEmployees];
		this.roles = new Role[numOfEmployees];
		int currRoleIdx = 0;
		for (int i = 0; i < roleCounter.length; i++) {
			for (int j = 0; j < roleCounter[i]; j++) {
				this.roles[currRoleIdx++] = roles.get(i);
			}
		}
		//allDepartments.add(this);
//		this.roles = new ArrayList<Role>();
	}
	
	public void addRolesToDepartment(Role role, int num) {
		for (int i = employeeCounter; i < roles.length; i++) {
			roles[i] = role;
		}
		employeeCounter = num;
	}
	
	public boolean addEmployeeToDepartment(Employee employee) {
		String r = employee.getRole().getName();
		for (int i = 0; i < roles.length; i++) {
			if(roles[i].getName().equals(r) && employees[i] == null) {
				employees[i] = employee;
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String s = new String();
		s += "Department " + name + " has room for " + numOfEmployees + " employees.\nCurrent employees are:\n";
		for(int i = 0; i < employees.length; i++) {
			s += roles[i].getName() + ": ";
			if(employees[i] == null) {
				s += "N/A\n";
				continue;
			}
			s += employees[i].getName() + "\n";
		}
		s+= "\n";
		return s;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumOfEmps() {
		return numOfEmployees;
	}
	
	@Override
	public boolean isSynchronizable() {
		return synchronizable;
	}
	
	@Override
	public boolean isChangeable() {
		return changeable;
	}
	
	public boolean equals(Object obj) {
    	if (obj.getClass().equals(this.getClass())) {
    		Department tmp = (Department) obj;
    		return tmp.name == name;
    	}
    	return false;
    }
}
