package projet_SE;

public class Menu_projet {

	public static void main(String[] args) {
		Ram r3 = new Ram(8,4, true,7);
		Ram r1 = new Ram(3,6, false,3);
		Ram r4 = new Ram(5,6, true,3);
		r3.memoire_Libre();
		r4.memoire_Libre();
Pagination p1 = new Pagination(3,9,4);
		p1.ajouter_proces();
//r1.memoire_Libre();
System.out.println("l'espace libre est  " +r1.memoire_Libre());
	}

}
