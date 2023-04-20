package peaksoft.service.Impl;

import peaksoft.dao.EmployeeDao;
import peaksoft.dao.Impl.EmployeeDaoImpl;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.EmployeeSer;

import java.util.List;
import java.util.Map;

public class EmployeeSerImpl implements EmployeeSer {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void createEmployee() {
        employeeDao.createEmployee();
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    public void dropTable() {
        employeeDao.dropTable();
    }

    public void cleanTable() {
        employeeDao.createEmployee();
    }

    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id,employee);

    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
