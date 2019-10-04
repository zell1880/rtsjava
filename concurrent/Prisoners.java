/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;
import static sequential.Prison.ANSI_GREEN;
import static sequential.Prison.ANSI_RED;

/**
 *
 * @author Zi Hao
 */
public class Prisoners implements Runnable {

    Prison pr = new Prison();
    int workperiod = 1;
    int stress = 0;

    public Prisoners(Prison pr) {
        this.pr = pr;
    }

    public void work() {

        System.out.println("Prisoners\t: Going to Work!!");
        stress += 30;
        pr.setHungry(true);

    }

    @Override
    public void run() {
        workperiod = 1;
        try {
            while (workperiod <= 2) {
                while (!pr.isHungry()) {
                    System.out.println("Prisoners\t: Stress levels = " + getStress());
                    if (stress < 100) {
                        work();
                        workperiod++;
                        Thread.sleep(2000);
                    } else {
                        riot();
                        Thread.sleep(2000);

                    }
                }

            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // setting the flag true to mark it complete
        }

    }

    public void riot() {
        System.out.println("Prisoners\t: RIOTTTTTTTTTTTTTT!!!!");

        pr.setPrisonRiot(true);
        pr.setHungry(true);
        stress = 0;

    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

}
