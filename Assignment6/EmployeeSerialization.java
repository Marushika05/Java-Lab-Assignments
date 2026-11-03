
import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department,
             double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("---------------------");
    }
}

public class EmployeeSerialization {
    public static void main(String[] args) {

        Employee[] employees = {
            new Employee(101, "Aman", "IT", 50000),
            new Employee(102, "Riya", "HR", 45000),
            new Employee(103, "Karan", "Finance", 60000)
        };

        // Serialization
        try (ObjectOutputStream oos =
                 new ObjectOutputStream(
                     new FileOutputStream("employees.dat"))) {

            oos.writeObject(employees);
            System.out.println("Employees serialized successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Deserialization
        try (ObjectInputStream ois =
                 new ObjectInputStream(
                     new FileInputStream("employees.dat"))) {

            Employee[] records = (Employee[]) ois.readObject();

            System.out.println("\nEmployee Records:");
            for (Employee emp : records) {
                emp.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
