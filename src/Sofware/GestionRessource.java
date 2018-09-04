package Sofware;

import java.util.concurrent.ConcurrentLinkedQueue;

import Sofware.InterruptAction;
import Sofware.Process;

public class GestionRessource extends Thread {
	private int interruption;
	Process process;
	private boolean ecran;
	private boolean clavier;
	private boolean imprimante;
	private int pid;
	EventManage event;
	ConcurrentLinkedQueue<InterruptAction> interrupt=new ConcurrentLinkedQueue<InterruptAction>();
	InterruptAction inter;
	
	public GestionRessource(EventManage even) {
		this.event=event;
		
	}
	public GestionRessource(int interruption,int pid) {

		inter =new InterruptAction(interruption,pid);
		interrupt.offer(inter);
	}
	
	
	
	public void run() {
		inter=interrupt.poll();
		if(inter!=null) {
		
		int pid=inter.getPid();
		int interruption=inter.getInterrupt();
	
		String ressource="";
		
		switch(interruption) {
		case 2:
			ressource="Ecran";
			break;
		case 9:
			ressource="Ecran";
			break;
		case 12:
			ressource="Clavier";
		}
		System.out.println("Le process de pid " +inter.getPid()+" occupe la ressource '"+ressource+"' pour une duree de 5 secondes");
		try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			
		}
		
		System.out.println("Liberation de la ressource  '"+ressource+"' par le processus " +pid);
		
		}
	}

	
	public boolean isEcran() {
		return ecran;
	}
	
	public void setEcran(boolean ecran) {
		this.ecran = ecran;
	}
	
	public boolean isClavier() {
		return clavier;
	}
	
	public void setClavier(boolean clavier) {
		this.clavier = clavier;
	}
	
	public boolean isImprimante() {
		return imprimante;
	}
	
	public void setImprimante(boolean imprimante) {
		this.imprimante = imprimante;
	}
}