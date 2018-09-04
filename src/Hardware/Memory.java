package Hardware;
import Software.Process;

	public class Memory {
	private int size;
	Process process[]=new Process[100];
	public Memory(Process proces,int address) {
		process[address]=proces;
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
