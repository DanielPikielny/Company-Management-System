package application;

import java.util.ArrayList;

public interface CompanyEventsListener {
	
	ArrayList<String> getEmployeeNames();
	ArrayList<String> getRoleNames();
	ArrayList<String> getDepartmentNames();
	void addEmpToModelEvent(String name, String preference, String role, String salaryMethod, int salary, boolean changeable, boolean synchronizable, int bonus);
	String getEmployees();
	void addRoleToModelEvent(String name, boolean changeable, boolean synchronizable);
	String getRoles();
	void addDepToModelEvent(String name, int[] arr, boolean changeable, boolean synchronizable);
	String getDepartments();
	boolean addEmpToDep(String dep, String emp);
	ArrayList<String> getEmpNRole();
	boolean usedDepName (String depName);
	boolean usedRoleName (String roleName);
	void saveResults();
	boolean readData(boolean readingApproval);
	int getDepNum();
	int getEmpNum();
	int getRoleNum();
	Role getRoleInIdx(int i);
	ArrayList<Role> getArrListRoles();
	void changeDepHours(String dep, Integer change);
	void changeRoleHours(String role, Integer change);
	ArrayList<String> getChangeableDepartmentNames();
	ArrayList<String> getChangeableRoleNames();
	String getEmployeesEfficiency();
	String getRolesEfficiency();
	String getDepartmentsEfficiency();
}
