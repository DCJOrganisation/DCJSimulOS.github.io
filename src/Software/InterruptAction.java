package Software;

public class InterruptAction {
	int interrupt;
	int pid;
	
	public InterruptAction(int interrupt,int pid) {
		this.interrupt=interrupt;
		this.pid=pid;
	}
	
	public void setInterrupt(int interrupt) {
		this.interrupt=interrupt;
	}
	public void setPid(int pid) {
		this.pid=pid;
	}
	public int getPid() {
		return pid;
	}

}
