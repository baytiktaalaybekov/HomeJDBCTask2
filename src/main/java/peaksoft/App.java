package peaksoft;

import peaksoft.config.Util;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.EmployeeSer;
import peaksoft.service.Impl.EmployeeSerImpl;
import peaksoft.service.Impl.JobSerImpl;
import peaksoft.service.JobSer;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {
        Util.getConnection();

        JobSer jobSer = new JobSerImpl();
        EmployeeSer employeeSer = new EmployeeSerImpl();

        while (true) {
            System.out.println("" +
                    "\n1-> create table jobs:" +
                    "\n2-> add job:" +
                    "\n3-> get job by id:" +
                    "\n4-> Press 4 and Write asc and desc for sort by Experience:" +
                    "\n5-> get job by employee id:" +
                    "\n6-> delete description column:"+
                    "\n7-> create table employees:"+
                    "\n8-> add Employee"+
                    "\n9-> drop table Employee!"+
                    "\n10-> clean table Employee!"+
                    "\n11-> update table Employee!"+
                    "\n12-> get all Employee!"+
                    "\n13-> find by email!"+
                    "\n14-> Map get Employee by id"+
                    "\n15-> get Employee by position!");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1:
                    jobSer.createJobTable();
                    break;
                case 2:
                    jobSer.addJob(new Job("Mentor","Java","Backend developer",2));
                    jobSer.addJob(new Job("Management","JavaScript","Frontend developer",1));
                    jobSer.addJob(new Job("Instructor","Java","Backend developer",3));
                    break;
                case 3:
                    System.out.println(jobSer.getJobById(2L));
                    break;
                case 4:
                    System.out.println(jobSer.sortByExperience(new Scanner(System.in).nextLine()));
                    break;
                case 5:
                    System.out.println(jobSer.getJobByEmployeeId(1L));
                    break;
                case 6:
                    jobSer.deleteDescriptionColumn();
                    break;
                case 7:
                    employeeSer.createEmployee();
                    break;
                case 8:
                    employeeSer.addEmployee(new Employee("Baytik","Taalaybekov",21,"baytik@gmail.com",1));
                    employeeSer.addEmployee(new Employee("Nurseyit","Sadirov",16,"nurseyit@gmail.com",2));
                    employeeSer.addEmployee(new Employee("Temirlan","Juzukulov",22,"temirlan@gmail.com",4));
                    employeeSer.addEmployee(new Employee("Davran","Joldoshbaev",18,"davran@gmail.com",3));
                    break;
                case 9:
                    employeeSer.dropTable();
                    break;
                case 10:
                    employeeSer.cleanTable();
                    break;
                case 11:
                    employeeSer.updateEmployee(1L,new Employee("Syimyk","Jumabekov",23,"syimyk@gmail.com",1));
                    break;
                case 12:
                    System.out.println(employeeSer.getAllEmployees());
                    break;
                case 13:
                    System.out.println(employeeSer.findByEmail("davran@gmail.com"));
                    break;
                case 14:
                    System.out.println(employeeSer.getEmployeeById(2L));
                    break;
                case 15:
                    System.out.println(employeeSer.getEmployeeByPosition("Mentor"));
                    break;
                default:
                    System.out.println(num + "NO!!!");

            }
        }


    }
}
