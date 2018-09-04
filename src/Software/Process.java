package Software;
import java.util.ArrayList;

public class Process {
	private int Pid;
	private int Size;
	private int priority;
	ArrayList<Instruction> instruct=new ArrayList<Instruction>();
	
	public Process(int Pid) {
		this.Pid=Pid;
	}
	public Process(int Pid,int priority,int size) {
		this.priority=priority;
		this.Pid=Pid;
		this.Size=size;
		System.out.println("Creation du processus "+Pid+" de taille "+Size+" et de niveau priorite "+priority+" ");
	}
	
	public int getPid() {
		return Pid;
	}
	
	public void setPid(int pid) {
		Pid = pid;
	}
	
	public int getSize() {
		return Size;
	}
	
	public void setSize(int size) {
		Size = size;
	}
	
	
	
	public void setInstruct(ArrayList<Instruction> instruct) {
		this.instruct=instruct;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
