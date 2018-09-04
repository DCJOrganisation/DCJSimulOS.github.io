package Software;

import Software.EventManage;

public class AddProcess extends Thread{
	
	EventManage event;
	
	public AddProcess(EventManage event) {
		this.event=event;
		
	}

	public void run() {
		event.AddPcb();
	}

}
