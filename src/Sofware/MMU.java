package Sofware;
import Hardware.Memory;
import Sofware.Process;

public class MMU {
	private int Pid;
	Frame Table[]=new Frame [100];
	Memory memory;
	Frame frame;
	private int address;
	
	
	
	public boolean Verified_Space(Process process) {
		int SizeProcess;
		int Compt=0;
		int i=0;
		int addr=0;
		boolean value;
		SizeProcess=process.getSize();
		
		while(Compt<SizeProcess && i<100) {
			if(!Table[i].isValue())
				Compt++;
			i++;
			addr=i;
		}
		
		if(Compt<SizeProcess) {
			System.out.println("Memoire insuffisante");
			value=false;
		}
			else {
				System.out.println("Memoire suffisante");
				value=true;
				setAddress(addr-SizeProcess);
			}
		return value;
	}
	
	void Compact_Mem(int Pid) {
		
		
	}
	
	
	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	

}
