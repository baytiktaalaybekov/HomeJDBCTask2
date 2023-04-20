package peaksoft.dao.Impl;

import peaksoft.config.Util;
import peaksoft.dao.EmployeeDao;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createEmployee() {
        String sql= """
                create table if not exists employees(
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age int,
                email varchar,
                job_id int references job(id)
                );
            """;
        try( Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Successfully createdEmployee!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = """
                insert into employees(first_name,last_name,age,email,job_id)
                values(?,?,?,?,?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropTable() {
        String sql = """
                drop table employees;
                """;
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Drop Employee!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void cleanTable() {
        String sql = """
                truncate table employees;
                """;
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Clear table!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = """
                update employees set first_name=?,last_name=?,age=?,email=?,job_id=? where id=?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "select * from employees;";
        List<Employee> employees = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                employees.add(new Employee(
                       resultSet.getLong("id"),
                       resultSet.getString("first_name"),
                      resultSet.getString("last_name"),
                       resultSet.getInt("age"),
                       resultSet.getString("email"),
                       resultSet.getInt("job_id")
                ));
            }
            System.out.println("Successfully get all Employee!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql= """
                select * from Employees where email= ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> jobMap = new LinkedHashMap<>();
        String sql = """
                Select * from employees e join job j on e.job_id=j.id where e.id =?; """;
        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee= new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jobMap.put(employee,job);
            }resultSet.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return jobMap;

    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();
        String sql = """
                select * from employees e join Job j on e.job_id=j.id where position = ?;
                """;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
            System.out.println("Successfully getEmployeeByPosition!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
