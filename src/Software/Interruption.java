package Software;

public class Interruption {
	private char Name;
	private int Pid;
	private int value;
	
	public Interruption(int value) {
		this.setValue(value);
	}

	public char getName() {
		return Name;
	}

	public void setName(char name) {
		Name = name;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	

}
