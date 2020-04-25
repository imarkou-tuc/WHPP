package WHPP;

import java.util.ArrayList;
import java.util.Random;

public class CoreAlgorithm {

    int pop;
    long initialFit=0;

    public Schedule[] population = new Schedule[100000];
    public ArrayList<Schedule> matingPool = new ArrayList<Schedule>();
    public Random rand = new Random();

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

        initialFit = totalFitness()/this.getPop();
    }

    public void printPopulation() {
        for(int i = 0; i < this.getPop(); i++) {
            System.out.println("Schedule " + i + " has a score of " + population[i].getSs().totalScore());
        }
    }

    public long totalFitness() {
        long totalFit=0;
        for(int i = 0; i < this.getPop(); i++) {
            totalFit+=population[i].getSs().totalScore();
        }
        System.out.println("Total fitness: " + totalFit);
        System.out.println("Average fitness: " + (totalFit/this.getPop()));
        return totalFit;

    }


    public void naturalSelection() {
        long totalFit=totalFitness();

        int count = 0;
        for (int i = 0; i < getPop(); i++) {
            int fitness = population[i].getSs().totalScore();
            count++;
            float fitnessNormal = (float) totalFit/fitness;
            double n = Math.floor(fitnessNormal/1000); // Arbitrary multiplier, consider mapping fix
            for (int j = 0; j < n; j++) {
                this.getMatingPool().add(population[i]);
            }
        }
    }

    public void generate() {
        long fitness=0;
        for(int i = 0 ; i < getPop(); i++) {
            int a = rand.nextInt(getMatingPool().size());
            int b = rand.nextInt(getMatingPool().size());
            double mutateRate = 0.01;
            Schedule parentOne = this.matingPool.get(a);
            Schedule parentTwo = this.matingPool.get(b);
            Schedule child = parentOne.kPointCrossover(parentTwo); //crossbreeding
            double random=rand.nextDouble();
            if(random<mutateRate) { //mutation
                child.mutateSchedule();
            }
            boolean temp=child.isFeasible(); //feasibility of child
            int childScore = child.getSs().totalScore();
            int parentOneScore = parentOne.getSs().totalScore();
            int parentTwoScore = parentTwo.getSs().totalScore();

            if((childScore < parentOneScore) && (childScore < parentTwoScore) && (temp==true)) { //elitism
               this.population[i] = child;
            } else {
                this.population[i] = (parentOneScore < parentTwoScore) ? parentOne : parentTwo;
            }
            System.out.println("Child score: " + population[i].getSs().totalScore());
        }
        fitness=this.totalFitness();
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

    public long getInitialFit() {
        return initialFit;
    }

    public void setInitialFit(long initialFit) {
        this.initialFit = initialFit;
    }

    public static void main(String[] args) {
        CoreAlgorithm ca = new CoreAlgorithm();

        ca.initializePopulation(10000);
        ca.naturalSelection();
        ca.printPopulation();

        for(int i = 0; i < 10; i++) {
            ca.naturalSelection();
            ca.generate();
            ca.getMatingPool().clear();
        }

        System.out.println("Initial fitness: "+ca.getInitialFit());

    }

}
