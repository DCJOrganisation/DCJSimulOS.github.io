package Hardware;
import Software.EventManage;
import Software.GenerateEvent;

	public class CPU extends Thread{
		EventManage event;
		
	public CPU(EventManage event) {
		this.event=event;
	}
	
	public void run() {
		event.Execute();
	}
		
}