package ohtu;

import java.util.ArrayList;

public class Submission {
    private String student_number;
    private int hours;
    private String a1;
    private ArrayList<String> tasks;
    private String a2;
    private String a6;
    private String a5;
    private String a4;
    private String a3;
    
    
    public Submission() {
        tasks = new ArrayList<String>();
    }
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public void setTaskOneDone(String a1) {
        this.a1 = a1;
    }
    
    public void setTaskTwoDone(String a2) {
        this.a2 = a2;
    }
    public void setTaskThreeDone(String a3) {
        this.a3 = a3;
    }
    public void setTaskFourDone(String a4) {
        this.a4 = a4;
    }
    public void setTaskFiveDone(String a5) {
        this.a5 = a5;
    }
    public void setTaskSixDone(String a6) {
        this.a6 = a6;
    }
       
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    
    public int getHours() {
        return hours;
    }
    
    public int getTasksDone() {
        return tasks.size();
    }
    
    public void tulosta() {
        System.out.println(tasks);
    }
    
    public void laita() {
        if (a1.equals("true")) {
            tasks.add("1");
        }
        if (a2.equals("true")) {
            tasks.add("2");
        }
        if (a3.equals("true")) {
            tasks.add("3");
        }
        if (a4.equals("true")) {
            tasks.add("4");
        }
        if (a5.equals("true")) {
            tasks.add("5");
        }
        if (a6.equals("true")) {
            tasks.add("6");
        }

    }
    
    @Override
    public String toString() {
        return this.getStudent_number() + "to string :)";
    }
    
}