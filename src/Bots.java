
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.realtime.AsynchronouslyInterruptedException;
import javax.realtime.Interruptible;
import javax.realtime.RealtimeThread;

public class Bots extends RealtimeThread implements Interruptible {



	AsynchronouslyInterruptedException aie=new AsynchronouslyInterruptedException();
	private Prison pr = new Prison();
	int condition = 100;
	
	public Bots(Prison pr) {
		this.pr=pr;
	}
	
	public Bots(Prison pr, AsynchronouslyInterruptedException aie) {
		this.pr = pr;
		this.aie = aie;
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

	public void stopRiot() {
		System.out.println("BOTS\t\t: CODE RED!!RIOT DETECTED" );
		System.out.println("BOTS\t\t: SOUND THE ALARM!!" );
		System.out.println("BOTS\t\t: LOCKGATES!!");
		System.out.println("BOTS\t\t: PUT THEM DOWN!!");
		System.out.println("BOTS\t\t: JOBS DONE!!"  );
		condition = condition - 20;
		pr.setPrisonRiot(false);

	}

	public void checkForMaintenance() {
		if (condition <= 0) {
			System.out.println("BOTS\t\t: MALFUNCTION DETECTED!!" );
			System.out.println("BOTS\t\t: Calling Technical Personnel!!" );
			System.out.println("BOTS\t\t: JOBS DONE!!");
			condition = 100;
		}
	}

	@Override
	public void interruptAction(AsynchronouslyInterruptedException exception) {
		// TODO Auto-generated method stub
		checkForMaintenance();
	}

	@Override
	public void run(AsynchronouslyInterruptedException exception) throws AsynchronouslyInterruptedException {
		if (condition <= 0) {
			aie.fire();
		} else {
			try {
				
				sleep(1000);
				this.patrol();
				condition = condition - 5;

			} catch (InterruptedException ex) {
				Logger.getLogger(Bots.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

}
