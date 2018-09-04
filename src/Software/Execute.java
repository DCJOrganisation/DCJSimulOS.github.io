package Software;

import Software.GenerateEvent;

public class Execute extends Thread{
	EventManage event;
	GenerateEvent gene;
	public Execute(EventManage event) {
		this.event=event;
	}
	public void run() {
		System.out.println("Execution du procesus");
	}
	}

