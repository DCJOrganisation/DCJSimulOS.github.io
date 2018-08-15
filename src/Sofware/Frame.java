package Sofware;

public class Frame {
	private int Pid;
	private int number;
	private boolean value;
	
	public Frame() {
		/*Pid=-1;
		number=-1;
		value=false;
		System.out.println("initialisation des cases de la memoire virtuelle");*/

	}
	public Frame(int Pid,int number,boolean value) {
		this.Pid=Pid;
		this.setNumber(number);
		this.value=value;
	}
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}