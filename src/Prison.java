
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.realtime.AsynchronouslyInterruptedException;
import javax.realtime.Interruptible;
import javax.realtime.PeriodicParameters;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;

import javax.realtime.ReleaseParameters;

public class Prison extends RealtimeThread implements Interruptible {

	AsynchronouslyInterruptedException aie;
	private volatile boolean hungry = true;
	private volatile boolean prisonRiot = false;
	int meal = 1;

	public Prison() {
	}

	public Prison(AsynchronouslyInterruptedException aie) {
		this.aie = aie;
	}

	@Override
	public void run() {
		while (true) {
			aie.doInterruptible(this);
			try {
				waitForNextRelease();
			} catch (IllegalThreadStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		AsynchronouslyInterruptedException aie = new AsynchronouslyInterruptedException();
		Prison pr = new Prison(aie);
		Prisoners p = new Prisoners(pr);
		Bots b = new Bots(pr);

		ReleaseParameters prisonTimer = new PeriodicParameters(new RelativeTime(1000, 0));
		ReleaseParameters prisonersTimer = new PeriodicParameters(new RelativeTime(2000, 0));
		ReleaseParameters botsTimer = new PeriodicParameters(new RelativeTime(1000, 0));

		pr.setReleaseParameters(prisonTimer);
		pr.start();
		p.setReleaseParameters(prisonersTimer);
		p.start();
		b.setReleaseParameters(botsTimer);
		b.start();
		
		Scanner in = new Scanner(System.in);
		while (true) {
			in.nextLine();
			aie.fire();
		} 

	}

	public void VisitCall() {

		System.out.println("System\t:" + "VISITOR FOR INMATES!!");
		System.out.println("System\t:" + "Excorting Prisoners to Visit Room!!");
		System.out.println("System\t:" + "Excorting Prisoners Back to WORK!!");
		System.out.println("System\t:" + "JOBS DONE!!");

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

	@Override
	public void interruptAction(AsynchronouslyInterruptedException exception) {
		VisitCall();

	}

	@Override
	public void run(AsynchronouslyInterruptedException exception) throws AsynchronouslyInterruptedException {
		meal = 1;
		System.out.println("-------------------------------------------------");
		System.out.println("NEW DAY!!");
		System.out.println("-------------------------------------------------");

		try {
			while (meal <= 3) {
				while (hungry) {
					if (prisonRiot) {
						meal++;
					} else {
						System.out.println("System\t\t: Send Prisoner to Canteen");
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

}
