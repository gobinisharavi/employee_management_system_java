package employee;

import java.util.Scanner;

class Employee {

    int empId;
    String name;
    String department;
    double salary;

    boolean isBusy = false;
    String projectName = "";
    int workHours = 0;
    int rating = 0;

    Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    void assignProject(String projectName, int hours) {

        if (!isBusy) {
            this.projectName = projectName;
            this.workHours = hours;
            this.isBusy = true;
            System.out.println("Project assigned to " + name);
        } else {
            System.out.println(name + " already working on " + this.projectName);
        }
    }

    void removeProject() {

        if (isBusy) {
            System.out.println("Project removed from " + name);
            projectName = "";
            workHours = 0;
            isBusy = false;
        } else {
            System.out.println(name + " is already free");
        }
    }

    void incrementSalary(double amount) {

        double oldSalary = salary;

        salary = salary + amount;

        System.out.println("Employee Name : " + name);
        System.out.println("Old Salary    : " + oldSalary);
        System.out.println("Increment     : " + amount);
        System.out.println("New Salary    : " + salary);
    }

    void giveRating(int rating) {
        this.rating = rating;
        System.out.println("Rating updated for " + name);
    }

    void showDetails() {

        System.out.println("----------------------------------");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + name);
        System.out.println("Department    : " + department);
        System.out.println("Salary        : " + salary);
        System.out.println("Rating        : " + rating);

        if (isBusy) {
            System.out.println("Status        : Busy");
            System.out.println("Project       : " + projectName);
            System.out.println("Work Hours    : " + workHours);
        } else {
            System.out.println("Status        : Free");
        }
        System.out.println("----------------------------------");
    }
}

public class MainClass {

    static void showSystemStatus(Employee[] employees, int count) {

        int busy = 0;

        for (int i = 0; i < count; i++) {
            if (employees[i].isBusy)
                busy++;
        }

        int free = count - busy;

        System.out.println("---- SYSTEM STATUS ----");
        System.out.println("Total Employees : " + count);
        System.out.println("Busy Employees  : " + busy);
        System.out.println("Free Employees  : " + free);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        Employee[] employees = new Employee[50];
        int count = 0;

        System.out.println("===== COMPANY MANAGEMENT SYSTEM =====");
        System.out.println("Enter Admin Username:");
        String user = sc.nextLine();

        System.out.println("Enter Admin Password:");
        String pass = sc.nextLine();

        if (!(user.equals("admin") && pass.equals("1234"))) {
            System.out.println("Invalid Login!");
            return;
        }

        int choice;

        do {

            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Assign Project");
            System.out.println("4. Remove Project");
            System.out.println("5. Increment Salary");
            System.out.println("6. Give Rating");
            System.out.println("7. View All Employees");
            System.out.println("8. Company Summary Report");
            System.out.println("9. Exit");
            System.out.println("Enter choice:");

            choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("Enter Employee ID:");
                int id = sc.nextInt();
                sc.nextLine();

                boolean duplicate = false;

                for (int i = 0; i < count; i++) {
                    if (employees[i].empId == id) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    System.out.println("Employee ID already exists!");
                } else {

                    System.out.println("Enter Name:");
                    String name = sc.nextLine();

                    System.out.println("Enter Department:");
                    String dept = sc.nextLine();

                    System.out.println("Enter Salary:");
                    double salary = sc.nextDouble();

                    employees[count] = new Employee(id, name, dept, salary);
                    count++;

                    System.out.println("Employee Added Successfully!");
                    showSystemStatus(employees, count);
                }
            }

            else if (choice == 2) {

                System.out.println("Enter Employee ID to delete:");
                int id = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < count; i++) {

                    if (employees[i].empId == id) {

                        found = true;

                        for (int j = i; j < count - 1; j++) {
                            employees[j] = employees[j + 1];
                        }

                        employees[count - 1] = null;
                        count--;

                        System.out.println("Employee Deleted Successfully!");
                        showSystemStatus(employees, count);
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found!");
                }
            }

            else if (choice >= 3 && choice <= 6) {

                System.out.println("Enter Employee ID:");
                int id = sc.nextInt();
                sc.nextLine();

                boolean found = false;

                for (int i = 0; i < count; i++) {

                    if (employees[i].empId == id) {

                        found = true;

                        if (choice == 3) {

                            System.out.println("Enter Project Name:");
                            String project = sc.nextLine();

                            System.out.println("Enter Work Hours:");
                            int hours = sc.nextInt();

                            employees[i].assignProject(project, hours);
                            showSystemStatus(employees, count);
                        }

                        else if (choice == 4) {
                            employees[i].removeProject();
                            showSystemStatus(employees, count);
                        }

                        else if (choice == 5) {

                            System.out.println("Enter Increment Amount:");
                            double amt = sc.nextDouble();
                            employees[i].incrementSalary(amt);
                        }

                        else if (choice == 6) {

                            System.out.println("Enter Rating (1-5):");
                            int rating = sc.nextInt();
                            employees[i].giveRating(rating);
                        }
                    }
                }

                if (!found) {
                    System.out.println("Employee not found!");
                }
            }

            else if (choice == 7) {

                if (count == 0) {
                    System.out.println("No employees available.");
                } else {
                    for (int i = 0; i < count; i++) {
                        employees[i].showDetails();
                    }
                }
            }

            else if (choice == 8) {

                double totalSalary = 0;
                int busyCount = 0;
                int totalRating = 0;

                for (int i = 0; i < count; i++) {

                    totalSalary += employees[i].salary;

                    if (employees[i].isBusy)
                        busyCount++;

                    totalRating += employees[i].rating;
                }

                double averageRating = 0;

                if (count > 0)
                    averageRating = (double) totalRating / count;

                System.out.println("----- COMPANY SUMMARY -----");
                System.out.println("Total Employees      : " + count);
                System.out.println("Busy Employees       : " + busyCount);
                System.out.println("Total Salary Expense : " + totalSalary);
                System.out.println("Average Rating       : " + averageRating);
            }

            else if (choice == 9) {
                System.out.println("Exiting System...");
            }

            else {
                System.out.println("Invalid choice");
            }

        } while (choice != 9);
    }
}