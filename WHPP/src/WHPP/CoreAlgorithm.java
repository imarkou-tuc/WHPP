package WHPP;

import java.util.ArrayList;

public class CoreAlgorithm {

    int pop;

    public Schedule[] population = new Schedule[100000];
    public ArrayList<Schedule> matingPool = new ArrayList<Schedule>();

    public void initializePopulation(int total) {
        int j = 0;
        for(int i = 0 ; i < total; i++) {
            Schedule sched = new Schedule();
            sched.initialize();
            Employee[] emp = sched.getEmp();
                boolean temp = sched.isFeasible();
                if (temp == true) {
                    population[j] = sched;
                    j++;
                }
        }
       setPop(j);
    }

    public void printPopulation() {
        for(int i = 0; i < this.getPop(); i++) {
            System.out.println("Schedule " + i + " has a score of " + population[i].getSs().totalScore());
        }
    }

    public long totalFitness() {
        long totalFit=0;
        for(int i = 0; i < this.getPop(); i++) {
            totalFit+=population[1].getSs().totalScore();
        }
        System.out.println("Total fitness: " + totalFit);
        return totalFit;

    }


    public void naturalSelection() {
        long totalFit=totalFitness();

        int count = 0;
        for (int i = 0; i < getPop(); i++) {
            int fitness = population[i].getSs().totalScore();
            count++;
            float fitnessNormal = (float) fitness/totalFit;
            double n = Math.floor(fitnessNormal * 10000); // Arbitrary multiplier, consider mapping fix
            for (int j = 0; j < getPop()+10-n; j++) {
               this.getMatingPool().add(population[i]);
            }
        }
        for(int i = 0 ; i < getMatingPool().size(); i++) {
            System.out.println("Element : " + i + " is " + getMatingPool().get(i).getSs().totalScore());
        }

    }

    public Schedule[] getPopulation() {
        return population;
    }

    public void setPopulation(Schedule[] population) {
        this.population = population;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public ArrayList<Schedule> getMatingPool() {
        return matingPool;
    }

    public void setMatingPool(ArrayList<Schedule> matingPool) {
        this.matingPool = matingPool;
    }

    public static void main(String[] args) {
        CoreAlgorithm ca = new CoreAlgorithm();
        ca.initializePopulation(1000);
        ca.printPopulation();
        ca.naturalSelection();

    }

}
