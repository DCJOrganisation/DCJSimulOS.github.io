package Software;

public class Instruction {
	private int size;
	private int instruct;
	
	public Instruction(int instruct,int size) {
		this.setSize(size);
		this.setInstruct(instruct);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getInstruct() {
		return instruct;
	}

	public void setInstruct(int instruct) {
		this.instruct = instruct;
	}

}
