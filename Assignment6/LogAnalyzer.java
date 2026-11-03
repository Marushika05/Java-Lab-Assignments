
import java.io.*;

public class LogAnalyzer {
    public static void main(String[] args) {

        // Create sample log file
        try (BufferedWriter bw =
                 new BufferedWriter(new FileWriter("app.log"))) {

            bw.write("INFO: Application started\n");
            bw.write("ERROR: Database connection failed\n");
            bw.write("INFO: User logged in\n");
            bw.write("WARNING: Low memory\n");
            bw.write("ERROR: File not found\n");
            bw.write("INFO: Application stopped\n");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        int total = 0;
        int errors = 0;
        int warnings = 0;

        try (BufferedReader br =
                 new BufferedReader(new FileReader("app.log"));
             BufferedWriter bw =
                 new BufferedWriter(
                     new FileWriter("filtered.log"))) {

            String line;

            while ((line = br.readLine()) != null) {
                total++;

                if (line.contains("ERROR")) {
                    errors++;
                    bw.write(line);
                    bw.newLine();
                } else if (line.contains("WARNING")) {
                    warnings++;
                    bw.write(line);
                    bw.newLine();
                }
            }

            System.out.println("Total log entries: " + total);
            System.out.println("Errors: " + errors);
            System.out.println("Warnings: " + warnings);
            System.out.println("Filtered logs saved to filtered.log");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
