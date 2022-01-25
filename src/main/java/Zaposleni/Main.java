package Zaposleni;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {


    public static void printHeader(){
        System.out.println("--------------------");
        System.out.println("|   Make a choice   |");
        System.out.println("--------------------");
    }

    public static void printMenu(){
        System.out.println("1) Add new employee.");
        System.out.println("2) Ask for vacation.");
        System.out.println("3) See list of employees.");
        System.out.println("0) Exit application");
    }




    public static void main(String[] args) {

        printHeader();
        printMenu();


        Map<String, Employee> employeesJmbg = new HashMap<>();
        List<Employee> employees = new ArrayList<Employee>();
        Employee Marko = new Employee("marko","markovic", 15);
        Marko.setJmbg("1234567890123");
        employees.add(Marko);
        employeesJmbg.put("1234567890123", Marko);



        Scanner scan = new Scanner(System.in);

        int choice = scan.nextInt();
        while (choice != 0) {
            switch(choice) {
                case 1:
                    String answer = "yes";
                    while (answer.equals("yes")) {
                        //Entering data in console
                        System.out.println("Enter name: ");
                        Scanner sc1 = new Scanner(System.in);
                        String name1 = sc1.next();                   // NAME
                        System.out.println("Enter surname: ");
                        Scanner sc2 = new Scanner(System.in);       // SURNAME
                        String surname1 = sc2.next();


                        System.out.println("Enter jmbg.(JMBG must include 13 integers) ");
                        Scanner sc7 = new Scanner(System.in);       //ENTERING VALID JMBG
                        String num = sc7.next();
                        String correctJmbg = "";
                        while (num.length()!=13 || !isNumeric(num)){
                            System.out.println("Try again. (JMBG must include 13 integers) ");
                            Scanner sc01 = new Scanner(System.in);
                            num = sc01.next();
                        }
                        correctJmbg = num;



                        System.out.println("Enter starting date in DD/MM/YYYY format.");
                        Date date01 = null;
                        while (date01 == null) {
                            Scanner sc3 = new Scanner(System.in);
                            String date = sc3.next();

                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            //Date date1 = format.parse(date);

                            try {
                                date01 = format1.parse(date);
                            } catch (ParseException e) {
                                System.out.println("Wrong date format,please try again.");

                            }
                        }


                        System.out.println("In how many firms did employee work? ");
                        Scanner sc6 = new Scanner(System.in);
                        int nums = sc6.nextInt();                    // NUMBER OF FIRMS (used to create list of jobs)


                        System.out.println("What are names of firms? ");

                        // MAKING A JOB LIST
                        List<String> A = new ArrayList<>();
                        for (int i = 0; i < nums; i++) {
                            Scanner s = new Scanner(System.in);
                            String job = s.next();
                            A.add(i, job);
                        }

                        System.out.println("What is employee's internship duration in total? ");
                        Scanner sc9 = new Scanner(System.in);
                        int internship = sc7.nextInt();             //YEARS OF WORK
                        System.out.println("Number of employee's free vacation days is: " + vacationDuration(internship));

                        int vacation = 20;


                        Employee first = new Employee(name1, surname1, vacationDuration(internship));
                        first.setInternship(internship);
                        first.setJmbg(correctJmbg);
                        employees.add(first);
                        employeesJmbg.put(correctJmbg, first);
                        display(employees);

                        System.out.println("Do you have more new employees?");
                        Scanner sca = new Scanner(System.in);
                        answer = sca.next();

                    }
                    if (answer.equals("no")) {
                        display(employees);
                    }
                    break;
                // Setting starting and ending date for employee's vacation

                case 2:


                    String answ1 = "yes";

                    while (answ1.equals("yes")) {
                        System.out.println("Enter JMBG of an employee you want to put vacation for");

                        Scanner sc7 = new Scanner(System.in);       //ENTERING VALID JMBG
                        String num = sc7.next();
                        String correctJmbg01 = "";
                        while (num.length() != 13 || !isNumeric(num)) {
                            System.out.println("Try again. (JMBG must include 13 integers) ");
                            Scanner sc01 = new Scanner(System.in);
                            num = sc01.next();
                        }
                        correctJmbg01 = num;

                        if (employeesJmbg.containsKey(correctJmbg01)) {
                            Employee employee = employeesJmbg.get(correctJmbg01);
                            System.out.println("Enter vacation starting date for " + employee.getName());
                            Date date1 = null;
                            while (date1 == null) {
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

                            System.out.println("Enter vacation ending date for " + employee.getName());
                            Date date2 = null;
                            while (date2 == null) {
                                Scanner d = new Scanner(System.in);
                                String date = d.next();


                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                //Date date1 = format.parse(date);
                                try {
                                    date2 = format.parse(date);
                                } catch (ParseException e) {
                                    System.out.println("Wrong date format, try again.");
                                }
                                while (date1.compareTo(date2) > 0) {
                                    System.out.println("Invalid ending date, try again. ");
                                    Scanner sc02 = new Scanner(System.in);
                                    date = d.next();
                                    format = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        date2 = format.parse(date);
                                    } catch (ParseException e) {
                                        System.out.println("Wrong date format, try again.");
                                    }
                                }



                            }
                            employee.setEndVacation(date2);
                            System.out.println("Do you want to add vacation for another employee? ");
                            Scanner answ = new Scanner(System.in);
                            answ1 = answ.next();

                        }
                        else{
                            System.out.println("There is no employee with this JMBG. Enter another one. ");
                            answ1="yes";
                        }


                    }


                    // Setting up free days for employees
                    for (Employee employee : employees) {
                        employee.setRequestedVacation(employee.getStartVacation(), employee.getEndVacation());
                        employee.setFreeDaysLeft(employee.getRequestedVacation(), employee.getVacationDuration());
                    }

                    display(employees);
                case 3:
                    display(employees);
            }

            printMenu();
            choice = scan.nextInt();
        }

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
                        + "; JMBG"
                        + employee.getJmbg()
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

    public static boolean isNumeric(String string){
        try{
            new BigInteger(string);
            return true;
        } catch(NumberFormatException e){
            // ignore
        }
        return false;
    }


}
