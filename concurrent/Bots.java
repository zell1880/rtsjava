/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import static concurrent.Prison.ANSI_GREEN;
import static concurrent.Prison.ANSI_RED;
import static concurrent.Prison.ANSI_RESET;
import static concurrent.Prison.ANSI_YELLOW;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zi Hao
 */
public class Bots implements Runnable {

    private Prison pr = new Prison();
    int condition = 100;

    public Bots(Prison pr) {
        this.pr = pr;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void patrol() {
        System.out.println("BOTS\t\t: Condition is " + condition);
        System.out.println("BOTS\t\t: PATROLING...");

    }

    @Override
    public void run() {
        if (pr.isPrisonRiot()) {
            stopRiot();
        } else {
            try {
                this.checkForMaintenance();
                sleep(1000);
                this.patrol();
                condition = condition - 5;

            } catch (InterruptedException ex) {
                Logger.getLogger(Bots.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void stopRiot() {
        System.out.println("BOTS\t\t:" + ANSI_RED + " CODE RED!!RIOT DETECTED" + ANSI_RESET);
        System.out.println("BOTS\t\t:" + ANSI_RED + " SOUND THE ALARM!!" + ANSI_RESET);
        System.out.println("BOTS\t\t:" + ANSI_RED + " LOCKGATES!!" + ANSI_RESET);
        System.out.println("BOTS\t\t:" + ANSI_RED + " PUT THEM DOWN!!" + ANSI_RESET);
        System.out.println("BOTS\t\t:" + ANSI_GREEN + " JOBS DONE!!" + ANSI_RESET);
        condition = condition - 20;
        pr.setPrisonRiot(false);

    }

    public void checkForMaintenance() {
        if (condition <= 0) {
            System.out.println("BOTS\t\t:" + ANSI_YELLOW + " MALFUNCTION DETECTED!!"+ANSI_RESET);
            System.out.println("BOTS\t\t:" + ANSI_YELLOW + " Calling Technical Personnel!!"+ANSI_RESET);
            System.out.println("BOTS\t\t:" + ANSI_GREEN + " JOBS DONE!!"+ANSI_RESET);
            condition = 100;
        }
    }

}
