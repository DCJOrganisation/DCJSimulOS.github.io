package Software;

public class Programme {
	private char name;
	private Process proc;
	
	public Programme(char name, Process proc) {
		this.setName(name);
		this.setProc(proc);
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public Process getProc() {
		return proc;
	}

	public void setProc(Process proc) {
		this.proc = proc;
	}
}
