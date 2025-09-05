package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable, WorkMethodChangeable, Synchronizable {
	private static final long serialVersionUID = 1L;
	private static final int WORKING_HOURS = 8;
	//public static ArrayList<Employee> allEmployees = new ArrayList<Employee>();
	private String name;
	private String preference;
	private String salaryMethod;
	private int salary;
	private Role role;
	private int hoursPerMonth;
	private boolean synchronizable;
	private int bonus;
	private boolean changeable;
	private int startHour;
	private int endHour;
	private boolean hasDep;
	private int[] efficiency;

	//public static List<Employee> allEmployees = new ArrayList<Employee>();

	public Employee (String name, String preference, Role role, String salaryMethod, int salary, boolean changeable, boolean synchronizable, int bonus) {
		this.name = name;
		this.preference = preference;
		this.role = role;
		this.salaryMethod = salaryMethod;
		this.bonus = bonus;
		this.salary = salary + bonus;
		if(salaryMethod.equals("base") || salaryMethod.equals("percentage")) {
			this.salary = (salary + bonus) / 160;
		}
		//allEmployees.add(this);
		this.synchronizable = synchronizable;
		this.changeable = changeable;
		this.hasDep = false;
		this.efficiency = new int[WORKING_HOURS];
		for (int i = 0; i < WORKING_HOURS; i++) {
			efficiency[i] = 100;
		}
	}

	//	public int monthlySalary() {
	//		return role.getSalary();
	//	}

	//	public boolean addEmployeeToDepartment(Employee employee) {
	//		for (int i = 0; i < employeesWithoutDepartment.size(); i++) {
	//			if employeesWithoutDepartment.get(i) == employee
	//		}
	//	}

	public Role getRole() {
		return role;
	}
	
	public void setEfficiency(int hrChange) {
		for(int i = 0; i < efficiency.length; i++) {
			efficiency[i] = 100;
		}
		if(hrChange == 0 && preference.equals("home")) {
			for(int i = 0; i < efficiency.length; i++) {
				efficiency[i] = 110;
			}
		}
		else {
			for (int i = 0; i < Math.abs(hrChange); i++) {
				if(hrChange < 0) {
					if (preference.equals("early")) {
						efficiency[i] = 120;
					}
					if (preference.equals("late") || preference.equals("same")) {
						efficiency[i] = 80;
					}
				}
				if(hrChange > 0) {
					if (preference.equals("late")) {
						efficiency[i] = 120;//efficiency.length - i - 1
					}
					if (preference.equals("early") || preference.equals("same")) {
						efficiency[i] = 80;//efficiency.length - i - 1
					}
				}
			}
		}
	}
	
	public double getEfficiency() {
		double num = 0;
		for(int i = 0; i < efficiency.length; i++) {
			num += efficiency[i];
		}
		return num / WORKING_HOURS;
	}

	public void updateWorkingHours(int num) {
		startHour += num;
		endHour += num;
		if (startHour >= 24) {
			startHour = startHour % 24;
		}
		if (endHour >= 24) {
			endHour = endHour % 24;
		}
	}

	@Override
	public boolean isSynchronizable() {
		return synchronizable;
	}

	@Override
	public boolean isChangeable() {
		return changeable;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String s = "";
		if(preference.equals("early")) {
			s += "start working early.";
		}
		if(preference.equals("late")) {
			s += "start working late.";
		}
		if(preference.equals("same")) {
			s += "keep the same working time.";
		}
		if(preference.equals("home")) {
			s += "work from home.";
		}
		return name + " works as a " + role.getName() + ", earns " + salary + " per hour, prefers to " + s + "\n";
	}
	
	public boolean equals(Object obj) {
    	if (obj.getClass().equals(this.getClass())) {
    		Employee tmp = (Employee) obj;
    		return tmp.name.equals(name) && tmp.role.equals(role);
    	}
    	return false;
    }
	
	public void setHasDepartment() {
		hasDep = true;
	}
	
	public boolean getHasDepartment() {
		return hasDep;
	}
}
