/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rts.sequential;

import static java.lang.Thread.sleep;
/**
 *
 * @author Zi Hao
 */
public class RTSSequential {

    /**
     * @param args the command line arguments
     */ 
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        System.out.println("CELL OPEN!!");
        System.out.println("Breakfast!! SEND PRISONERS TO THE CANTEEN");
        System.out.println("Go to Work!!");
        VisitCall();
        RandomEvents();
        System.out.println("Lunch!! SEND PRISONERS TO THE CANTEEN");
        System.out.println("Go to Work!! ");
        VisitCall();
        RandomEvents();
        System.out.println("Dinner!! SEND PRISONERS TO THE CANTEEN");
        System.out.println("Cells CLOSE!!");
        System.out.println("Night Patrol!!");
        RandomEvents();
    }
    public static void RandomEvents(){
        
        PrisonRiot();
        BotMal();
    }
    public static void VisitCall(){
        if (randomOccurance()){
            System.out.println(ANSI_BLUE +"VISITOR FOR INMATES!!");
            System.out.println(ANSI_BLUE +"Excorting Prisoners to Visit Room!!");
            try {
                sleep(100);
            } catch (InterruptedException ex) {}
            System.out.println(ANSI_BLUE +"Excorting Prisoners Back to WORK!!");
            System.out.println(ANSI_GREEN +"JOBS DONE!!");
        }
    }
    
    public static void PrisonRiot(){
        if (randomOccurance()){
            System.out.println(ANSI_RED +"CODE RED!!RIOT DETECTED");
            System.out.println(ANSI_RED +"SOUND THE ALARM!!");
            System.out.println(ANSI_RED +"LOCKGATES!!");
            System.out.println(ANSI_RED +"PUT THEM DOWN!!");
            System.out.println(ANSI_GREEN +"JOBS DONE!!");
        }
    }
    public static void BotMal(){
        if (randomOccurance()){
            System.out.println(ANSI_YELLOW+"MALFUNCTION DETECTED!!");
            System.out.println(ANSI_YELLOW+ "Calling Technical Personnel!!");
            
            System.out.println(ANSI_GREEN +"JOBS DONE!!");
            
        }   
    }
    public static boolean randomOccurance(){
        return Math.random() <=0.5;
    }
}
