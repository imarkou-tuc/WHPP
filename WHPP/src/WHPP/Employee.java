package WHPP;

public class Employee {

    public static final int NUMBER_OF_DAYS=14;
    public static final int NUMBER_OF_EMPLOYEES=30;
    public int[] shifts=new int[NUMBER_OF_DAYS];
    public static Rules rules=new Rules();
    public Rules.shift_type shift_type;


    public int getWorkShift(int day) {
        if(day>=0 && day<14) {
            return shifts[day];
        } else
            System.out.println("Not a valid day!");
        return -1;
    }

    void setWorkShift(int day,Rules.shift_type st) {
        shifts[day]=rules.getWorkShiftInt(st);
    }

    boolean works(int day) {
        if(shifts[day]!=0) {
            return true;
        } else
            return false;
    }

    int totalWorkHours() {
        int totalHours=0;
        for(int i = 0; i < shifts.length; i++) {
            totalHours+=rules.getShiftHours(shifts[i]);
        }
        return totalHours;
    }


    }


