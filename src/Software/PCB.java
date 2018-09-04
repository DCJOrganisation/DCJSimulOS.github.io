package Software;

public class PCB {
	private int Pid;
	private int Etat=1;
	private int Compt_Ord;
	private int Alloc_Mem;
	private int Size_Process;
	private int Ressource=0;
	public PCB() {
		
	}
	public PCB(int Pid,int Alloc_Mem,int size_Process) {
		this.Pid=Pid;
		this.Alloc_Mem=Alloc_Mem;
		this.Size_Process=size_Process;
	}
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public int getEtat() {
		return Etat;
	}
	public void setEtat(int etat) {
		Etat = etat;
	}
	public int getComptOrd() {
		return Compt_Ord;
	}
	public void setComptOrd(int comptOrd) {
		Compt_Ord = comptOrd;
	}
	public int getAlloc_Mem() {
		return Alloc_Mem;
	}
	public void setAlloc_Mem(int alloc_Mem) {
		Alloc_Mem = alloc_Mem;
	}
	public int getSize_Process() {
		return Size_Process;
	}
	public void setSize_Process(int size_Process) {
		Size_Process = size_Process;
	}
	public int getRessource() {
		return Ressource;
	}
	public void setRessource(int ressource) {
		Ressource = ressource;
	}
	

}
