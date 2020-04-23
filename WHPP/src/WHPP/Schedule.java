package WHPP;

import java.util.Random;

public class Schedule {
    public Rules rules = new Rules();
    public Random rand = new Random();
    public Employee[] emp = new Employee[Employee.NUMBER_OF_EMPLOYEES];

    public Employee[] getEmp() {
        return emp;
    }

    public void setEmp(Employee[] emp) {
        this.emp = emp;
    }

    ScheduleScore ss = new ScheduleScore(emp);


    public void initialize() {
        for(int i = 0 ; i < Employee.NUMBER_OF_EMPLOYEES;i++) {
            emp[i] = new Employee();
        }

        for (int day = 0; day < Employee.NUMBER_OF_DAYS; day++) { //Morning shifts
            int remaining_slots=0;
            if(day == 0 || day == 7 ||  day == 11) { //randomness on mondays and  2nd friday mornings
                 remaining_slots = rand.nextBoolean() ? 5 : 10;
            } else
                remaining_slots=rules.hardConstraints(day, Rules.shift_type.MORNING);

            while (remaining_slots != 0) {
                    int worker = rand.nextInt(30);

                    while (emp[worker].getWorkShift(day) != 0) { //while the employees we choose have shifts, pick another
                        worker = rand.nextInt(30);
                    }
                emp[worker].setWorkShift(day, Rules.shift_type.MORNING);
                    remaining_slots--;
            }
        }

        for (int day = 0; day < Employee.NUMBER_OF_DAYS; day++) { //Afternoon shifts
            int remaining_slots = rules.hardConstraints(day, Rules.shift_type.AFTERNOON);

            while (remaining_slots != 0) {
                int worker = rand.nextInt(30);

                while (emp[worker].getWorkShift(day) != 0) { //while the employees we choose have shifts, pick another
                    worker = rand.nextInt(30);
                }
                emp[worker].setWorkShift(day, Rules.shift_type.AFTERNOON);
                remaining_slots--;
            }
        }

        for (int day = 0; day < Employee.NUMBER_OF_DAYS; day++) { //Night shifts
            int remaining_slots = rules.hardConstraints(day, Rules.shift_type.NIGHT);

            while (remaining_slots != 0) {
                int worker = rand.nextInt(30);

                while (emp[worker].getWorkShift(day) != 0) { //while the employees we choose have shifts, pick another
                    worker = rand.nextInt(30);
                }
                emp[worker].setWorkShift(day, Rules.shift_type.NIGHT);
                remaining_slots--;
            }
        }
        ss.totalScore();

    }

        public boolean isFeasible() {
                boolean temp=false;

            for (int day = 0; day < Employee.NUMBER_OF_DAYS; day++) {
                int mornings = rules.hardConstraints(day , Rules.shift_type.MORNING);
                int afternoons = rules.hardConstraints(day , Rules.shift_type.AFTERNOON);
                int nights = rules.hardConstraints(day , Rules.shift_type.NIGHT);
                int temp_morning = 0;
                int temp_night = 0;
                 int temp_afternoon = 0;

                for (int worker = 0; worker < Employee.NUMBER_OF_EMPLOYEES; worker++){
                    switch(emp[worker].getWorkShift(day)){
                        case 1:
                               temp_morning++;
                            break;
                        case 2:
                            temp_afternoon++;
                            break;
                        case 3:
                            temp_night++;
                            break;
                    }
                }
                if ((temp_morning != mornings) || (temp_afternoon!=afternoons) || (temp_night!=nights)) {
                    temp=false;
                    break;
                } else
                    temp=true;
            }
            return temp;
        }

    public ScheduleScore getSs() {
        return ss;
    }

    public void setSs(ScheduleScore ss) {
        this.ss = ss;
    }
}

