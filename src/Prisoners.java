import javax.realtime.AsynchronouslyInterruptedException;
import javax.realtime.Interruptible;
import javax.realtime.RealtimeThread;

public class Prisoners extends RealtimeThread implements Interruptible {
	AsynchronouslyInterruptedException aie = new AsynchronouslyInterruptedException();
	Prison pr = new Prison();
	int workperiod = 1;
	int stress = 0;
	public Prisoners(Prison pr) {
		this.pr=pr;
	}
	public Prisoners(Prison pr, AsynchronouslyInterruptedException aie) {
		this.pr = pr;
		this.aie = aie;
	}

	public void work() {

		System.out.println("Prisoners\t: Going to Work!!");
		stress += 30;
		pr.setHungry(true);

	}

	@Override
	public void run() {
		while (true) {
			try {
			
			aie.doInterruptible(this);
			
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

	@Override
	public void interruptAction(AsynchronouslyInterruptedException exception) {
		riot();
		stopRiot();

	}
	public void stopRiot() {
		System.out.println("BOTS\t\t: CODE RED!!RIOT DETECTED" );
		System.out.println("BOTS\t\t: SOUND THE ALARM!!" );
		System.out.println("BOTS\t\t: LOCKGATES!!");
		System.out.println("BOTS\t\t: PUT THEM DOWN!!");
		System.out.println("BOTS\t\t: JOBS DONE!!"  );
		
		pr.setPrisonRiot(false);

	}
	@Override
	public void run(AsynchronouslyInterruptedException exception) throws AsynchronouslyInterruptedException {
		// TODO Auto-generated method stub
		workperiod = 1;
		try {
			while (workperiod <= 2) {
				if (!pr.isHungry()) {
					System.out.println("Prisoners\t: Stress levels = " + getStress());
					if (stress < 100) {
						work();
						workperiod++;
						Thread.sleep(2000);

					} else {
						aie.fire();
						workperiod++;
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

}
