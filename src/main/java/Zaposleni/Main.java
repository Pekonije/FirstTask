package Zaposleni;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee("marko","markovic", 15));


        System.out.println("Do yo have new employee to add?");
        Scanner scan = new Scanner(System.in);
        String answer = scan.next();

        while(!answer.equals("yes") && !answer.equals("no")){
            System.out.println("Enter 'yes' or 'no'. ");
            Scanner scan1 = new Scanner(System.in);
            answer = scan1.next();
        }

        while(answer.equals("yes")) {
            //Entering data in console
            System.out.println("Enter name: ");
            Scanner sc1 = new Scanner(System.in);
            String name1 = sc1.next();                   // NAME
            System.out.println("Enter surname: ");
            Scanner sc2 = new Scanner(System.in);       // SURNAME
            String surname1 = sc2.next();
            System.out.println("Enter date: ");
            Scanner sc3 = new Scanner(System.in);
            int day = sc3.nextInt();                    //DAY FOR DATE
            Scanner sc4 = new Scanner(System.in);
            int month = sc4.nextInt();                  //MONTH FOR DATE
            Scanner sc5 = new Scanner(System.in);
            int year = sc5.nextInt();                   //YEAR FOR DATE
            System.out.println("In how many firms did employee work? ");
            Scanner sc6 = new Scanner(System.in);
            int num = sc6.nextInt();                    // NUMBER OF FIRMS (used to create list of jobs)


            System.out.println("What are names of firms? ");

            // MAKING A JOB LIST
            List<String> A = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Scanner s = new Scanner(System.in);
                String job = s.next();
                A.add(i, job);
            }

            System.out.println("What is employee's internship duration in total? ");
            Scanner sc7 = new Scanner(System.in);
            int internship = sc7.nextInt();             //YEARS OF WORK
            System.out.println("Number of employee's free vacation days is: " + vacationDuration(internship));

            int vacation = 20;


            Employee first = new Employee(name1, surname1, vacationDuration(internship));
            employees.add(first);
            display(employees);

            System.out.println("Do you have more new employees?");
            Scanner sca = new Scanner(System.in);
            answer = sca.next();

        }
        if(answer.equals("no")){
                display(employees);
        }

        // Setting starting and ending date for employee's vacation

        for (Employee employee:employees){


            System.out.println("Enter starting date for " + employee.getName() + " in DD/MM/YYYY format." );
            Date date1 = null;
            while(date1 == null){
            Scanner d = new Scanner(System.in);
            String date = d.next();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          //Date date1 = format.parse(date);

                try {
                    date1 = format.parse(date);
                } catch (ParseException e) {
                    System.out.println("Wrong date format,please try again.");

                }
            }
            employee.setStartVacation(date1);

            System.out.println("Enter ending date for " + employee.getName() + " in DD/MM/YYYY format." );
            Date date2 = null;
            while (date2==null){
                Scanner d = new Scanner(System.in);
                String date = d.next();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                //Date date1 = format.parse(date);
                try {
                    date2 = format.parse(date);
                } catch (ParseException e) {
                    System.out.println("Wrong date format, try again.");
                }
            }
            employee.setEndVacation(date2);
        }

        // Setting up free days for employees
        for (Employee employee:employees){
            employee.setRequestedVacation(employee.getStartVacation(),employee.getEndVacation());
            employee.setFreeDaysLeft(employee.getRequestedVacation(),employee.getVacationDuration());
        }

        display(employees);

   //     employees.get(0).setRequestedVacation(employees.get(0).getStartVacation(),employees.get(0).getEndVacation());
   //     System.out.println(employees.get(0).getRequestedVacation());

   //     System.out.println("proba " + employees.get(0).getName() + "datum pocetka " + employees.get(0).getStartVacation() + "datum zavrsetka " + employees.get(0).getEndVacation());

    }

    static void display(List<Employee> employeeList){
        System.out.println("Our employees: ");
        System.out.println("<<");
        for(Employee employee:employeeList){
            if(employee.getFreeDaysLeft()>=0) {
                System.out.println("Name: "
                        + employee.getName()
                        + "; Surname: "
                        + employee.getSurname()
                        + "; Number of free vacation days is: "
                        + employee.getVacationDuration()
                        + "; Number of rquested free days are: "
                        + employee.getRequestedVacation()
                        + " and this employee can get "
                        + employee.getFreeDaysLeft()
                        + " more free days");
            }
            else{
                if(employee.getVacationDuration()<=employee.getRequestedVacation()) {
                    System.out.println("Name: "
                            + employee.getName()
                            + "; Surname: "
                            + employee.getSurname()
                            + " can get maximum of "
                            + employee.getVacationDuration()
                            + " days for vacation. ");
                }
            }
        }
        System.out.println(">>");
    }

    static int vacationDuration( int number){
            int x = 20;
            if (number<5){
                x = 20;
            }
            else if (5 <= number && number < 10){
                x += 1;
            }
            else if (10<=number && number < 15){
                x += 2;
            }
            else if (15<= number && number < 20){
                x += 3;
            }
            else if (20<= number && number < 25){
                x += 4;
            }
            else if (25<= number && number < 30){
                x += 5;
            }
            else if (30<= number && number < 35){
                x += 6;
            }
            else if (35<= number && number <= 40){
                x += 7;
            }
            else {
                x=number;
            }
            return x;
    }
}
