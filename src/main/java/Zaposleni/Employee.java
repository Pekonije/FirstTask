package Zaposleni;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Employee {
    private String name;
    private String surname;
    private int vacationDuration;
    private int requestedVacation;
    private int freeDaysLeft;
    private int internship;
    private String jmbg;
    private Date startVacation;
    private Date endVacation;

    public Employee(String name, String surname, int vacation) {
        this.name = name;
        this.surname = surname;
        this.vacationDuration = vacation;
    }

    public void setVacationDuration(int vacationDuration) {
        this.vacationDuration = vacationDuration;
    }

    public void setStartVacation(Date startVacation) {
        this.startVacation = startVacation;
    }

    public void setEndVacation(Date endVacation) {
        this.endVacation = endVacation;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setFreeDaysLeft(int a, int b) {
        a = this.requestedVacation;
        b = this.vacationDuration;
        if(a <= b){
            this.freeDaysLeft= b-a;
        }
        else{
            this.freeDaysLeft=-1;
        }
    }

    public void setRequestedVacation(Date starting, Date finish) {
        int weekCount = 0;
        this.requestedVacation = 0;
        starting = this.startVacation;
        finish = this.endVacation;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(starting);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(finish);
        if(starting.compareTo(finish)<0) {
            for (c1.setTime(starting); c1.before(c2); c1.add(Calendar.DATE, 1)) {
                if(c1.get(Calendar.DAY_OF_WEEK) == 1 || c1.get(Calendar.DAY_OF_WEEK) == 7 ){
                    weekCount++;
                }
                else{
                    this.requestedVacation++;
                }
            }
        }
        else{
            System.out.println("Number of free days is 0.");
        }

    }

    public void setInternship(int internship) {
        this.internship = internship;
    }

    public int getInternship() {
        return internship;
    }

    public int getRequestedVacation() {
        return requestedVacation;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getStartVacation() {
        return startVacation;
    }

    public Date getEndVacation() {
        return endVacation;
    }

    public int getVacationDuration() {
        return vacationDuration;
    }

    public int getFreeDaysLeft() {
        return this.freeDaysLeft;
    }

    public String getJmbg() {
        return jmbg;
    }
}
