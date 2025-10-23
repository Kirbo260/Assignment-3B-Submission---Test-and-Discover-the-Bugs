import java.util.Scanner;
import java.util.ArrayList;

public class GradeToolkit {

    /*
     * Purpose: Calculate GPA based on user-entered grades
     * Input: grades from user
     * Output: GPA
     */
    static double calculateGPA(Scanner input) {
        ArrayList<Integer> grades = new ArrayList<>();
        double sum = 0;

        System.out.println("Enter grades (enter -1 to stop):");

        while (true) {
            System.out.print("Grade " + (grades.size() + 1) + ": ");
            int grade = input.nextInt();
            if (grade == -1) break;
            grades.add(grade);
            sum += grade;
        }

        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
            return 0.0;
        }

        double gpa = sum / grades.size();
        System.out.printf("You entered %d grades. GPA = %.2f%n", grades.size(), gpa);
        return gpa;
    }

    /*
     * Purpose: Enter assignment scores and determine pass/fail
     * Input: assignment scores from users
     * Output: pass/fail status
     */
    static void PassOrFail(Scanner input) {
        int[] assignments = new int[5];
        double total = 0;

        System.out.println("Enter five assignment scores:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Assignment " + (i + 1) + ": ");
            assignments[i] = input.nextInt();
        }

        System.out.println("Your assignments:");
        for (int i = 0; i < 5; i++) {
            System.out.print(assignments[i] + " ");
            total += assignments[i];
        }
        System.out.println();

        double average = total / 5;
        if (average < 60) {
            System.out.println("Fail");
        } else {
            System.out.println("Pass");
        }
    }

    /**
     * Purpose: Convert numeric grade to letter grade
     * Input: Numeric grade from user
     * Output: Letter grade
     */
    static void grade_To_letter(Scanner input) {
        System.out.print("Enter numeric grade (out of 100): ");
        int grade = input.nextInt();

        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade entered.");
            return;
        }

        switch (grade / 10) {
            case 10:
            case 9:
                System.out.println("A");
                break;
            case 8:
                System.out.println("B");
                break;
            case 7:
                System.out.println("C");
                break;
            case 6:
                System.out.println("D");
                break;
            default:
                System.out.println("F");
                break;
        }
    }

    /*
     * Purpose: Calculate total grade with assignment categories of different weight.
     * Input: Assignment scores & weights from user
     * Output: Total/final grade
     */
    static void calculate_weighted_grade(Scanner input) {
        System.out.println("Weights of categories should be between 0-1. Enter -1 to finish.");
        ArrayList<Float> weights = new ArrayList<>();
        float totalWeight = 0f;
        int i = 1;

        while (true) {
            System.out.println("Enter weight of category " + i + " (-1 to finish):");
            float weight = input.nextFloat();

            if (weight == -1f) break;

            if (weight < 0f || weight > 1f || totalWeight + weight > 1f) {
                System.out.println("Invalid weight. Restarting weights entry...");
                weights.clear();
                totalWeight = 0f;
                i = 1;
                continue;
            }

            weights.add(weight);
            totalWeight += weight;
            i++;

            if (Math.abs(totalWeight - 1f) < 1e-6) break;
        }

        if (weights.isEmpty()) {
            System.out.println("No values entered.");
            return;
        }

        float totalGrade = 0f;
        for (int x = 0; x < weights.size(); x++) {
            System.out.println("Enter grade for category " + (x + 1) + ":");
            float grade = input.nextFloat();
            totalGrade += grade * weights.get(x);
        }

        System.out.println("Your total grade is: " + totalGrade);
    }

    /*
     * Purpose: Calculate cumulative grade point average with a weight on credits
     * Input: Semester number, grade, credits
     * Output: Cumulative GPA.
     */
    static void calculate_qpa(Scanner input) {
        double totalGradePoints = 0.0;
        int totalCredits = 0;

        System.out.println("Enter number of classes taken:");
        int classes = input.nextInt();

        for (int i = 1; i <= classes; i++) {
            System.out.println("Enter grade for class " + i + ": (A-F)");
            String grade = input.next().toUpperCase();

            while (!grade.matches("[ABCDF]")) {
                System.out.println("Grade entered is invalid. Reenter: (A-F)");
                grade = input.next().toUpperCase();
            }

            double gradePoints;
            switch (grade) {
                case "A": gradePoints = 4; break;
                case "B": gradePoints = 3; break;
                case "C": gradePoints = 2; break;
                case "D": gradePoints = 1; break;
                default:  gradePoints = 0; break; // F
            }

            System.out.println("Enter credits for class " + i + ":");
            int credits = input.nextInt();
            while (credits <= 0) {
                System.out.println("Credits entered invalid. Reenter:");
                credits = input.nextInt();
            }

            totalGradePoints += gradePoints * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            System.out.println("No credits entered.");
            return;
        }

        double qpa = totalGradePoints / totalCredits;
        System.out.printf("Your QPA is: %.2f%n", qpa);
        System.out.println("Your total credits are: " + totalCredits);
    }

    /*
     * Purpose: Calculate the grade needed on the final exam to achieve a desired overall course grade.
     * Input: Current grade, weight of final exam, desired overall grade
     * Output: Required final exam grade
     */
    public static void calculate_finals_grade_needed(Scanner input) {
        System.out.print("Enter your current grade as a number: ");
        double currentGrade = input.nextDouble();

        System.out.print("Enter the weight % of your final exam as a number: ");
        double finalWeightPercent = input.nextDouble();

        System.out.print("Enter your desired overall grade as a number: ");
        double desiredGrade = input.nextDouble();

        double finalWeight = finalWeightPercent / 100.0;
        if (finalWeight <= 0 || finalWeight > 1) {
            System.out.println("Invalid final exam weight.");
            return;
        }

        double currentWeight = 1 - finalWeight;
        double requiredFinal = (desiredGrade - (currentGrade * currentWeight)) / finalWeight;

        if (requiredFinal > 100) {
            System.out.println("You’ll need over 100% on the final to reach your goal.");
        } else if (requiredFinal < 0) {
            System.out.println("You have already reached your desired grade! Even a 0 on the final would be fine.");
        } else {
            System.out.printf("You need to score at least %.2f%% on the final exam to get a %.2f%% overall grade.%n",
                    requiredFinal, desiredGrade);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your school toolkit!\n Please select an option:\n 1. Calculate GPA\n 2. Pass or Fail\n 3. Grade to Letter \n 4. Calculate Weighted Grade\n 5. Calculate QPA\n 6. Calculate Final's Grade Needed");
        System.out.println("------------------------------");
        System.out.print("Enter option: ");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                calculateGPA(scanner);
                break;
            case 2:
                PassOrFail(scanner);
                break;
            case 3:
                grade_To_letter(scanner);
                break;
            case 4:
                calculate_weighted_grade(scanner);
                break;
            case 5:
                calculate_qpa(scanner);
                break;
            case 6:
                calculate_finals_grade_needed(scanner);
                break;
            default:
                System.out.println("Invalid option. Please select 1, 2, 3, 4, 5, or 6.");
                break;
        }

        scanner.close();
    }
}
