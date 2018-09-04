package Software;

public class InterruptManage {
	private int interrupt;
	private boolean value;
	
	public InterruptManage() {
		interrupt=0;
		value=false;
	}
	public InterruptManage(int interrupt) {
		this.setInterrupt(interrupt);
		InterruptGenerate();
	}

	public int getInterrupt() {
		return interrupt;
		
	}

	public void InterruptGenerate() {
		switch(interrupt) {
		case 1:
			System.out.println("Demade d'acceder a l'ecran");
			value=true;
		}
	}
	public void setInterrupt(int interrupt) {
		this.interrupt = interrupt;
	}

	public boolean IsInterrupt() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
