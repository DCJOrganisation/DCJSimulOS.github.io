package projet_SE;

public  class Ram 
{ 
	//try
		//{
		// cette classe gere la memoire centrale.
			public int pid;
			public int size_Process;
			public boolean creer_ou_detruire;
			public int priorite;
			final int memory=64;
			public static int memoire_utilise =0 ; // c'est la memoire utilise 
			public static int tab_process[][] = new int[19][3];// un tableau de 19 lignes et trois colonnes, comprend respectivement le size_proces, le pid et le niveau de priorite
			 
			Proces ram[] = new Proces[memory];// on declare un tableau de 64 cases, chaque  case represente un octet
		public Ram(int pid,int size_pid,boolean creer_ou_detruire, int priorite)// c'est un constructeur qui prend le PID, le size du proces et un boolean qui prend true si on va creer un process, false si on va detruire un proces   en parametre.
			{
				this.size_Process = size_pid;
				this.pid = pid;
				this.creer_ou_detruire= creer_ou_detruire;
				System.out.println("le processus qui vient d'etre cree est de  "+this.pid);
				this.priorite = priorite;
			}
		public void storage( int pid, int priorite,int size_proces,boolean action)// action prend true si on vient de creer un proces et false dans le cas contraire
			{
			if (action == true)
			{
				
			}
			}
			
		 public int memoire_Libre()// cette methode calcule la valeur de memoire non utilise.
			{
				 if (this.creer_ou_detruire == true)// si on vient de creer un processus
					 { 		
					 	if (Ram.memoire_utilise +this.size_Process >=memory)// tester s'il ya de l'espace libre
					 		Ram.memoire_utilise = Ram.memoire_utilise+0;
					 	else
					 		Ram.memoire_utilise=Ram.memoire_utilise + this.size_Process;// s'il y'a de l'espace libre
					 }
				 else
							Ram.memoire_utilise=Ram.memoire_utilise - this.size_Process;// si on vient de detruire un process
				 System.out.println(Ram.memoire_utilise);//teste
					
				 return (memory-memoire_utilise); // retourne la valeur de la memoire non utilise.		 
		   }
		 public void ajouteer_ou_enlever_process(int pid,int size_Process,int priorite, boolean action )
		 {
			 int i = 0;
			 this.creer_ou_detruire = action;
			if (action == true)
				{
					if (Ram.memoire_utilise +this.size_Process >=memory)// tester s'il ya de l'espace libre
			 			{
							Swapping();
							memoire_Libre();
			 			}
					else
						{// il faut blocker  <boucle infini>!
							while (tab_process[i][3]== 0)
								i++;
							tab_process[i][1]=pid;
							tab_process[i][2]= size_Process;
							tab_process[i][3]= priorite;
							memoire_Libre();// 
						}
					
				}
			else
				{
					while(pid == tab_process[i][1])// cherche le process a detruire dans le tableau des process
						i++;
					tab_process[i][1]=0;
					tab_process[i][2]= 0;
					tab_process[i][3]= 0;
					memoire_Libre();// 
					
				}
		 }
		public void Swapping()// c'est la methode qui fait le Swap in et Swap out 
		   {
				int i = 0;	
				while ((tab_process[2][i]>= this.size_Process)&&(tab_process[3][i]<= this.priorite))
					i++;
				Ram.memoire_utilise = Ram.memoire_utilise -tab_process[i][2];// libere l'espace occupe par le processus <swap out>
				tab_process[i][2] = this.size_Process;
				Ram.memoire_utilise = Ram.memoire_utilise +tab_process[i][2];
				tab_process[i][3] = this.priorite;
		   }
		}
			//catch(ArithmeticExeption a)
			//{
			//system.out.println("problem");
			//}
//}