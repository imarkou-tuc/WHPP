package WHPP;


public class ScheduleScore {
    public Employee[] emp;

    public ScheduleScore(Employee[] emp) {
        this.emp = emp;
    }

        public int scorePerWorker(int worker) {
            int score=0;

            //check total hours
            if(emp[worker].totalWorkHours()>=70) {
                score+=1000;
            }

        //check 7 consecutive days worked
        int consecutive_days_worked=0;
        for(int i = 0; i < Employee.NUMBER_OF_DAYS; i++) {
            if(emp[worker].works(i)) {
                consecutive_days_worked+=1;
            } else
                consecutive_days_worked=0;
        }
        if(consecutive_days_worked>7) {
            score+=1000;
        }

        //check 4 consecutive nights worked
        int consecutive_nights=0;
        for(int i = 0; i < Employee.NUMBER_OF_DAYS; i++) {
            if(emp[worker].getWorkShift(i) == 3) {
                consecutive_nights+=1;
            } else
                consecutive_nights=0;
        }
        if(consecutive_nights>4) {
            score+=1000;
        }

        //check night to day shift
        for(int i = 0; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) == 3) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) == 1)) {
                score+=1000;
            }
        }

        //check afternoon to day shift
        for(int i = 0; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) == 2) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) == 1)) {
                score+=800;
            }
        }

        //check night to afternoon shift
        for(int i = 0; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) == 3) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) == 2)) {
                score+=800;
            }
        }

        //check for work-off day-work
        for(int i = 0 ; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) != 0) && (emp[worker].getWorkShift( (i + 1)%Employee.NUMBER_OF_DAYS) == 0) &&  (emp[worker].getWorkShift((i + 2)%Employee.NUMBER_OF_DAYS) != 0)) {
                score+=1;
            }
        }

        //check for off day-work-off day
        for(int i = 0 ; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) == 0) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) != 0) &&  (emp[worker].getWorkShift((i + 2)%Employee.NUMBER_OF_DAYS) == 0)) {
                score+=1;
            }
        }
        //max 1 weekend work
        if(((emp[worker].getWorkShift(5) != 0) || (emp[worker].getWorkShift(6) != 0)) && ((emp[worker].getWorkShift(12) != 0)|| (emp[worker].getWorkShift(13) != 0)))
            score+=1;
        //at least 2 days off after 4 consecutive night shifts
        for(int i = 0 ; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) == 3) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) == 3) && (emp[worker].getWorkShift((i+2)%Employee.NUMBER_OF_DAYS) == 3) && (emp[worker].getWorkShift((i+3)%Employee.NUMBER_OF_DAYS) == 3)) {
                if((emp[worker].getWorkShift((i+4)%Employee.NUMBER_OF_DAYS) != 0) || ((emp[worker].getWorkShift((i+4)%Employee.NUMBER_OF_DAYS) == 0) && (emp[worker].getWorkShift((i+5)%Employee.NUMBER_OF_DAYS) != 0)))
                    score+=100;
            }
        }

        //at least 2 days off after 7 consecutive shifts
        for(int i = 0 ; i < Employee.NUMBER_OF_DAYS; i++) {
            if((emp[worker].getWorkShift(i) != 0) && (emp[worker].getWorkShift((i+1)%Employee.NUMBER_OF_DAYS) != 0) && (emp[worker].getWorkShift((i+2)%Employee.NUMBER_OF_DAYS) != 0) && (emp[worker].getWorkShift((i+3)%Employee.NUMBER_OF_DAYS) != 0) && (emp[worker].getWorkShift((i+4)%Employee.NUMBER_OF_DAYS) != 0) && (emp[worker].getWorkShift((i+5)%Employee.NUMBER_OF_DAYS) != 0) && (emp[worker].getWorkShift((i+6)%Employee.NUMBER_OF_DAYS) != 0)) {
                if((emp[worker].getWorkShift((i+7)%Employee.NUMBER_OF_DAYS) != 0) || ((emp[worker].getWorkShift((i+7)%Employee.NUMBER_OF_DAYS) == 0) && (emp[worker].getWorkShift((i+8)%Employee.NUMBER_OF_DAYS) != 0)))
                    score+=100;
            }
        }


    return score;
    }

    public int totalScore() {
        int totalScore=0;

        for(int i = 0 ; i < Employee.NUMBER_OF_EMPLOYEES; i++) {
            totalScore+=scorePerWorker(i);
        }
        return totalScore;
    }

}
