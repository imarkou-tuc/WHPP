package WHPP;


import java.util.Random;

public class Rules {

    enum shift_type {
        MORNING,
        AFTERNOON,
        NIGHT,
        NO_SHIFT
    }

    Random rand=new Random();

    public int getWorkShiftInt(shift_type st) {
        int result=-1;

        switch (st) {
            case MORNING:
                result=1;
            break;
            case AFTERNOON:
                result=2;
            break;
            case NIGHT:
                result=3;
            break;
            case NO_SHIFT:
                result=0;
            break;
            default:
                result=-1;
            System.out.print("Not a valid shift!");
        }
        return result;
    }

    public int getShiftHours(int shift_type) {
        int hours=0;

        switch (shift_type) {
            case 1:
                hours=8;
                break;
            case 2:
                hours=8;
                break;
            case 3:
                hours=10;
                break;
            case 0:
                hours=0;
                break;
            default:
                hours=-1;
                System.out.print("Not a valid shift!");
        }
        return hours;
    }

    public int hardConstraints(int day, shift_type st) {
        int numberOfEmployees=0;

        switch (day%7) {
            case 0:
            case 1:
                switch (st) {
                    case MORNING:
                        numberOfEmployees=10;
                         break;
                    case AFTERNOON:
                        numberOfEmployees=10;
                        break;
                    case NIGHT:
                        numberOfEmployees=5;
                        break;
                    default:
                        System.out.println("Not a valid shift!");
                        break;
                }
                break;
            case 2:
            case 4:
                switch (st) {
                    case MORNING:
                        numberOfEmployees=5;
                        break;
                    case AFTERNOON:
                        numberOfEmployees=10;
                        break;
                    case NIGHT:
                        numberOfEmployees=5;
                        break;
                    default:
                        System.out.println("Not a valid shift!");
                        break;
                }
                break;
            case 3:
            case 5:
            case 6:
                switch (st) {
                    case MORNING:
                        numberOfEmployees=5;
                        break;
                    case AFTERNOON:
                        numberOfEmployees=5;
                        break;
                    case NIGHT:
                        numberOfEmployees=5;
                        break;
                    default:
                        System.out.println("Not a valid shift!");
                        break;
                }
                break;
        }
        return numberOfEmployees;
    }

    public shift_type randomShift() {
        shift_type st=shift_type.NO_SHIFT;
        switch(rand.nextInt(1000)%3) {
            case 0:
                st=shift_type.MORNING;
                break;
            case 1:
                st=shift_type.AFTERNOON;
                break;
            case 2:
                st=shift_type.NIGHT;
                break;
        }
        return st;
    }


}
