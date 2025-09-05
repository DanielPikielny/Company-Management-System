package application;

import java.io.IOException;
import java.util.ArrayList;

public class CompanyController implements CompanyEventsListener {
	
	private CompanyModel model;
	private AbstractCompanyView view;

	public CompanyController(CompanyModel model, AbstractCompanyView  view) {
		this.model = model;
		this.view = view;
		
		model.registerListener(this);
		view.registerListener(this);

	}
	
	@Override
	public ArrayList<String> getRoleNames() {
		return model.getRoleNames();
	}

	@Override
	public ArrayList<String> getEmployeeNames() {
		return model.getEmployeeNames();
	}

	@Override
	public ArrayList<String> getDepartmentNames() {
		return model.getDemartmentNames();
	}

	@Override
	public void addEmpToModelEvent(String name, String preference, String role, String salaryMethod, int salary, boolean changeable, boolean synchronizable, int bonus) {
		model.addEmployee(name, preference, role, salaryMethod, salary, changeable, synchronizable, bonus);
	}
	
	public void addRoleToModelEvent(String name, boolean changeable, boolean synchronizable) {
		model.addRole(name, changeable, synchronizable);
	}
	
	public String getEmployees() {
		String res = model.printAllEmployees();
		return res;
	}
	
	public String getRoles() {
		String res = model.printAllRoles();
		return res;
	}

	@Override
	public void addDepToModelEvent(String name, int[] arr, boolean changeable, boolean synchronizable) {
		model.addDepartment(name, arr, changeable, synchronizable);
	}

	@Override
	public String getDepartments() {
		String res = model.printAllDepartments();
		return res;
	}

	@Override
	public boolean addEmpToDep(String dep, String emp) {
		return model.addEmpToDep(dep, emp);
	}
	
	public ArrayList<String> getEmpNRole() {
		return model.empNames();
	}
	
	public boolean usedDepName(String depName) {
		return model.usedDepName(depName);
	}
	
	public boolean usedRoleName(String roleName) {
		return model.usedDepName(roleName);
	}
	
	public void saveResults() {
		try {
			model.saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean readData(boolean readingApproval) {
		return model.startProgram(readingApproval);
	}

	@Override
	public int getDepNum() {
		return model.getDepNum();
	}

	@Override
	public int getEmpNum() {
		return model.getEmpNum();
	}

	@Override
	public int getRoleNum() {
		return model.getRoleNum();
	}

	@Override
	public Role getRoleInIdx(int i) {
		return model.getRoleInIdx(i);
	}

	@Override
	public ArrayList<Role> getArrListRoles() {
		return model.getArrListRoles();
	}

	@Override
	public void changeDepHours(String dep, Integer change) {
		model.changeDepHours(dep, change);
	}
	
	@Override
	public void changeRoleHours(String role, Integer change) {
		model.changeDepHours(role, change);
	}

	@Override
	public ArrayList<String> getChangeableDepartmentNames() {
		return model.getChangeableDemartmentNames();
	}
	
	public ArrayList<String> getChangeableRoleNames() {
		return model.getChangeableRoleNames();
	}

	@Override
	public String getEmployeesEfficiency() {
		return model.printEmpEfficiency();
	}

	@Override
	public String getRolesEfficiency() {
		return model.printRoleEfficiency();
	}

	@Override
	public String getDepartmentsEfficiency() {
		// TODO Auto-generated method stub
		return model.printDepEfficiency();
	}
}
