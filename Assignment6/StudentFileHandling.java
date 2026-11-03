
import java.io.*;
import java.util.Scanner;

public class StudentFileHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Write student details using character stream
        try (FileWriter fw = new FileWriter("students.txt")) {
            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Enter student name: ");
                String name = sc.nextLine();

                System.out.print("Enter roll number: ");
                int roll = sc.nextInt();
                sc.nextLine();

                fw.write(roll + " " + name + "\n");
            }

            System.out.println("Student records saved.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Read student details using character stream
        try (FileReader fr = new FileReader("students.txt")) {
            int ch;
            System.out.println("\nStudent Records:");

            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Copy binary file using byte streams
        try (FileOutputStream fos =
                 new FileOutputStream("original.bin")) {
            fos.write(new byte[]{10, 20, 30, 40, 50});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (FileInputStream fis =
                 new FileInputStream("original.bin");
             FileOutputStream fos =
                 new FileOutputStream("copy.bin")) {

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("\nBinary file copied successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
