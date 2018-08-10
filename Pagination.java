package projet_SE;

public class Pagination
	{
		public int pid;
		public int size_Proces;// il deverait >0
		public int base;
		public static int  memoire_occupe;
		public static int table_page[][] =new int[16][2];
	public Pagination( int pid, int size_proces, int base)
		{
			this.pid = pid;
			this.size_Proces = size_proces;
			this.base = base;
		}
	public void ajouter_proces()
		{
		int t, i=0;
			if ((this.size_Proces%4)==0)// on calcule le nombre de frame
				t= this.size_Proces/4;
			else 
				t = (this.size_Proces/4)+1;
			memoire_occupe = memoire_occupe + t*4;
			System.out.println("le nombre de page :"+t);// provisoirement
			System.out.println("la memoire occupe :"+memoire_occupe);
		while(t==0)
			{
				if ((table_page[i][1]==0)&&(t>0))
				{
					table_page[i][1]=this.pid;
					table_page[i][2]=this.base;
					this.base = this.base+4;
					
					t--;
				}
				i++;
			}
		}
		public void detruire_proces()
		{
			for (int i=0; i<16;i++)
			{ if(table_page[i][1]==this.pid)
			{
				table_page[i][1]=0;
				table_page[i][2]=0;
			}
			{
				
			}
				
			}
		}
			{
				
			}
		}
	
