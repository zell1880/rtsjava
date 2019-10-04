/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import static java.lang.Thread.sleep;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zi Hao
 */
public class Prison implements Runnable {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    private volatile boolean hungry = true;
    private volatile boolean prisonRiot = false;
    int meal = 1;

    @Override
    public void run() {

        meal = 1;
        System.out.println("-------------------------------------------------");
        System.out.println("NEW DAY!!");
        System.out.println("-------------------------------------------------");

        try {
            while (meal <= 3) {
                while (hungry) {
                    if (prisonRiot) {
                    } else {
                        System.out.println("System\t\t: Send Prisoner to Canteen");
                        this.VisitCall();
                        hungry = false;
                        meal++;
                        sleep(1000);
                    }
                }

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Prison.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("END DAY!!");
        System.out.println("-------------------------------------------------");
        hungry = true;
    }

    public static void main(String[] args) {
        Prison pr = new Prison();
        Prisoners p = new Prisoners(pr);
        Bots b = new Bots(pr);

        ScheduledExecutorService prison = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService prisoner = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService bots = Executors.newScheduledThreadPool(1);

        prison.scheduleAtFixedRate(pr, 0, 200, TimeUnit.MILLISECONDS);
        prisoner.scheduleAtFixedRate(p, 0, 200, TimeUnit.MILLISECONDS);
        bots.scheduleAtFixedRate(b, 0, 200, TimeUnit.MILLISECONDS);

    }


    public void VisitCall() {
        if (randomOccurance()) {
            System.out.println("System\t:"+ANSI_BLUE + "VISITOR FOR INMATES!!"+ANSI_RESET);
            System.out.println("System\t:"+ANSI_BLUE + "Excorting Prisoners to Visit Room!!"+ANSI_RESET);
            System.out.println("System\t:"+ANSI_BLUE + "Excorting Prisoners Back to WORK!!"+ANSI_RESET);
            System.out.println("System\t:"+ANSI_GREEN + "JOBS DONE!!"+ANSI_RESET);
        }
    }


    public static boolean randomOccurance() {
        return Math.random() <= 0.1;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public boolean isPrisonRiot() {
        return prisonRiot;
    }

    public void setPrisonRiot(boolean prisonRiot) {
        this.prisonRiot = prisonRiot;
    }

}
