package application;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class CompanyModel {
	public static final String F_NAME = "1.txt";
	ArrayList<Department> departmentList;
	ArrayList<Role> roleList;
	ArrayList<Employee> employeeList;
	ArrayList<CompanyEventsListener> listeners;

	public CompanyModel() {
		departmentList = new ArrayList<Department>();
		roleList = new ArrayList<Role>();
		employeeList = new ArrayList<Employee>();
		listeners = new ArrayList<CompanyEventsListener>();
	}

	public boolean startProgram(boolean readingApproval) {
		if (readingApproval) {
			try {
				readFile();
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				return false;
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				return false;
			} catch (IOException e) {
				//e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public void addDepartment(String name, int[] arr, boolean changeable, boolean synchronizable) {
		departmentList.add(new Department(name, arr, changeable, synchronizable, roleList));
	}

	public void addRole(String name, boolean changeable, boolean synchronizable) {
		roleList.add(new Role(name, changeable, synchronizable));
	}

	public void addEmployee(String name, String preference, String role, String salaryMethod, int salary, boolean changeable, boolean synchronizable, int bonus) {
		int temp = -1;
		for(int i = 0; i < roleList.size(); i++) {
			if(role == roleList.get(i).getName()) {
				temp = i;
			}
		}
		Employee employee = new Employee(name, preference, roleList.get(temp), salaryMethod, salary, synchronizable, changeable, bonus); 
		employeeList.add(employee);
		roleList.get(temp).addEmployee(employee);
	}

	public ArrayList<String> getRoleNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Role r : roleList) {
			res.add(r.getName());
		}
		return res;
	}

	public ArrayList<String> getEmployeeNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Employee e : employeeList) {
			res.add(e.getName());
		}
		return res;
	}

	public ArrayList<String> getDemartmentNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Department d : departmentList) {
			res.add(d.getName());
		}
		return res;
	}

	public String printAllEmployees() {
		String res = "";
		for(int i = 0; i < employeeList.size(); i++) {
			res += employeeList.get(i).toString();
		}
		return res;
	}

	public String printAllRoles() {
		String res = "";
		for(int i = 0; i < roleList.size(); i++) {
			res += roleList.get(i).toString();
		}
		return res;
	}

	public String printAllDepartments() {
		String res = "";
		for(int i = 0; i < departmentList.size(); i++) {
			res += departmentList.get(i).toString();
		}
		return res;
	}

	public ArrayList<String> empNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Employee e : employeeList) {
			if (!e.getHasDepartment()) {
				res.add(e.getName());
			}
		}
		return res;
	}

	public boolean addEmpToDep(String dep, String emp) {
		int tempE = -1, tempD = -1;
		for(int i = 0; i < employeeList.size(); i++) {
			if(emp.equals(employeeList.get(i).getName())) {
				tempE = i;
			}
		}
		for (int i = 0; i < departmentList.size(); i++) {
			if(dep.equals(departmentList.get(i).getName())) {
				tempD = i;
			}
		}
		int x = 3;
		if(departmentList.get(tempD).addEmployeeToDepartment(employeeList.get(tempE))) {
			employeeList.get(tempE).setHasDepartment();
			return true;
		}
		return false;
	}

	public void saveToFile() throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(F_NAME));
		o.writeObject(departmentList);
		o.writeObject(roleList);
		o.writeObject(employeeList);
		o.close();
	}

	@SuppressWarnings("unchecked")
	public void readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream f;
		ObjectInputStream i = new ObjectInputStream(f = new FileInputStream(F_NAME));
		while (f.available() > 0) {
			departmentList = (ArrayList<Department>) i.readObject();
			roleList = (ArrayList<Role>) i.readObject();
			employeeList = (ArrayList<Employee>) i.readObject();
		}
		i.close();
	}

	public void registerListener(CompanyEventsListener listener) {
		listeners.add(listener);
	}

	public boolean usedDepName(String depName) {
		for(int i = 0; i < departmentList.size(); i++) {
			if (departmentList.get(i).getName().equals(depName)) {
				return true;
			}
		}
		return false;
	}

	public boolean usedRoleName(String roleName) {
		for(int i = 0; i < roleList.size(); i++) {
			if (roleList.get(i).getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public int getDepNum() {
		return departmentList.size();
	}

	public int getEmpNum() {
		return employeeList.size();
	}

	public int getRoleNum() {
		return roleList.size();
	}

	public Role getRoleInIdx(int i) {
		return roleList.get(i);
	}

	public ArrayList<Role> getArrListRoles() {
		return roleList;
	}

	public void changeDepHours(String dep, Integer change) {
		int finalChange = change;
		if(finalChange > 8) {
			finalChange = 8;
		}
		int tempD = -1;
		for (int i = 0; i < departmentList.size(); i++) {
			if(dep.equals(departmentList.get(i).getName())) {
				tempD = i;
			}
		}
		for (int i = 0; i < departmentList.get(tempD).getNumOfEmps(); i++) {
			if(departmentList.get(tempD).employees[i] == null) {
				continue;
			}
			if (departmentList.get(tempD).isSynchronizable()) {
				departmentList.get(tempD).employees[i].setEfficiency(finalChange);
			}
			else {
				if(departmentList.get(tempD).employees[i].isChangeable() && departmentList.get(tempD).employees[i].getRole().isChangeable()) {
					departmentList.get(tempD).employees[i].setEfficiency(finalChange);
				}
				else {
					continue;
				}
			}
		}
	}

	public void changeRoleHours(String role, Integer change) {
		int finalChange = change;
		if(finalChange > 8) {
			finalChange = 8;
		}
		int tempD = -1;
		for (int i = 0; i < roleList.size(); i++) {
			if(role.equals(roleList.get(i).getName())) {
				tempD = i;
			}
		}
		for (int i = 0; i < roleList.get(tempD).allEmployees.size(); i++) {
			if (roleList.get(tempD).isSynchronizable()) {
				roleList.get(tempD).allEmployees.get(i).setEfficiency(finalChange);
			}
			else {
				if(roleList.get(tempD).allEmployees.get(i).isChangeable()) {
					roleList.get(tempD).allEmployees.get(i).setEfficiency(finalChange);
				}
				else {
					continue;
				}
			}
		}
	}

	public String printEmpEfficiency() {
		String res = "All the employees will now work at ";
		double temp = 0;
		for(int i = 0; i < employeeList.size(); i++) {
			temp += employeeList.get(i).getEfficiency();
		}
		temp /= employeeList.size();
		res += temp + "% efficiency after the changes";
		return res;
	}

	public String printDepEfficiency() {
		String res = "New efficiency for the departments:\n";
		double temp = 0;
		int empCount = 0;
		for (Department d : departmentList) {
			for(int i = 0; i < d.getNumOfEmps(); i++) {
				if(d.employees[i] == null) {
					continue;
				}
				temp += d.employees[i].getEfficiency();
				empCount++;
			}
			if(temp == 0) {
				res += d.getName() + " is empty.";
			}
			else {
				temp /= empCount;
				res += d.getName() + " operates at " + temp + "% efficiency after the changes.\n";
			}
		}
		return res;
	}

	public String printRoleEfficiency() {
		String res = "New efficiency for the roles:\n";
		double temp = 0;
		for (Role r : roleList) {
			res += r.getName() + " operates at ";
			for(int i = 0; i < r.allEmployees.size(); i++) {
				temp += r.allEmployees.get(i).getEfficiency();
				//res += r.allEmployees.get(i).getEfficiency() + "% efficiency after the changes\n";
			}
			if(temp == 0) {
				res += "No one is working as " + r.getName() + " yet.";
			}
			else {
				temp /= r.allEmployees.size();
				res += temp + "% efficiency after the changes\n";
			}
		}
		return res;
	}

	public ArrayList<String> getChangeableDemartmentNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Department d : departmentList) {
			if(d.isChangeable()) {
				res.add(d.getName());
			}
		}
		return res;
	}

	public ArrayList<String> getChangeableRoleNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (Role r : roleList) {
			if(r.isChangeable()) {
				res.add(r.getName());
			}
		}
		return res;
	}
}