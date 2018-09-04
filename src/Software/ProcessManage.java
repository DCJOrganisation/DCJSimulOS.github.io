package Software;

import java.util.PriorityQueue;
import java.util.Queue;

public class ProcessManage {
	
	private int IdProcess;
	private int random;
	private boolean value =false;
	Queue<Integer> execute=new PriorityQueue<Integer>();
	Queue<Integer> bloqued=new PriorityQueue<Integer>();
	Interruption inter;
	InterruptManage interrupt;
		
	public ProcessManage() {
		
		
	}
	
	
	public void Manage() {
		int IdProcess= execute.remove();
		while(!value) {
			
			GenerateEvent event=new GenerateEvent();
			interrupt=new InterruptManage(event.getRandom());
			value=interrupt.IsInterrupt();
			}
		bloqued.offer(IdProcess);
		}
	     

	public int getIdProcess() {
		return IdProcess;
	}

	public void setIdProcess(int idProcess) {
		IdProcess = idProcess;
	}

	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}
}
