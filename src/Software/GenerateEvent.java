package Software;
import java.util.Random;

import Software.Process;
import Software.SystemCall;

public class GenerateEvent {
	private int random;
	private int SizeProcess;
	private int PriorityProcess;
	
	Process process;
	private boolean value=false;
	private boolean interrupt=false;
	private boolean processcreate=false;
	SystemCall sys;
	EventManage event;
		
	
	public Process GenerateProcess() {
		Random r=new Random();
	
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e) {
			}
		random=r.nextInt(5);
		switch(random) {
		case 0:
			SizeProcess=10;
			PriorityProcess=1;
			setProcesscreate(true);
			break;
		case 1:
			SizeProcess=7;
			PriorityProcess=3;
			setProcesscreate(true);
			break;
		case 2:
			SizeProcess=15;
			PriorityProcess=5;
			setProcesscreate(true);
			break;
		case 3:
			SizeProcess=12;
			PriorityProcess=2;
			setProcesscreate(true);
			break;
		case 4:
			SizeProcess=20;
			PriorityProcess=4;
			setProcesscreate(true);
		}
		process=new Process(random,PriorityProcess,SizeProcess);
		System.out.println("Creation d'un nouveau processus");
		return process;
		
	}

	
	public int GenerateInterrupt() {
		Random random=new Random();
		try {
			Thread.sleep(50);
			}
		catch(InterruptedException e) {
		}
		int interruption=random.nextInt(20);
		
		switch(interruption) {
		case 0:
			System.out.println("Processus termine");
			setProcesscreate(true);
			break;
			
		case 2:
			System.out.println("Demande d'acceder a l'ecran");
			setProcesscreate(true);
			break;
		case 9:
			System.out.println("Demande d'utiliser l'imprimante");
			setProcesscreate(true);
			break;
		case 12:
			System.out.println("Utilisation du clavier");
			setProcesscreate(true);
			break;
			default:
			System.out.println("Processus en train d'executer");
		}
		return interruption;
	}
	
		public void run() {
		event.AddPcb();
	
	}
	public int getRandom() {
		return random;
	}
	
	public void setRandom(int random) {
		this.random = random;
	}

	public int getSizeProcess() {
		return SizeProcess;
	}

	public void setSizeProcess(int sizeProcess) {
		SizeProcess = sizeProcess;
	}

	public Process getProcess() {
		return process;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean isInterrupt() {
		return interrupt;
	}


	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}

	
	public boolean isProcesscreate() {
		return processcreate;
	}
	public void setProcesscreate(boolean processcreate) {
		this.processcreate = processcreate;
	}
}