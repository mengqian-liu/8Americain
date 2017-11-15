package cartes;
import java.util.*;
//import 


public class cartePile {

	private final String[] colorType = {"Carreau","Coeur","Pique","Trefle"};
	private final String[] numTypeNF = {"3","4","5","6","9","Q","K"};
	private final String pileEnd = "0";
	//public int numJoueur;
	
	public static LinkedList<carte> pile = new LinkedList<carte>();
	//public static LinkedList<carte> pile2 = new LinkedList<carte>();
	
	//public carte reference;
	
	public cartePile() {
		// TODO Auto-generated constructor stub
		System.out.print("\n>创建一副牌");
		for(int i=0;i<4;i++) {
			pile.add(new carteA(colorType[i]));
			pile.add(new carte2(colorType[i]));
			pile.add(new carte7(colorType[i]));
			pile.add(new carte8(colorType[i]));
			pile.add(new carte10(colorType[i]));
			pile.add(new carteJ(colorType[i]));
			for(int j=0;j<7;j++) {
				pile.add(new carte(colorType[i],numTypeNF[j]));
			}
		}
		
		for(int i=0;i<10;i++) {
			try{
				Thread.sleep(50);
				}
				catch(InterruptedException e) {
				}
			System.out.print(">");
			}
		System.out.println(">>>>>>创建成功");
	}

	
	public void shuffleCarte(){
	    System.out.print(">开始洗牌");
	    Collections.shuffle(pile);
	    for(int i=0;i<10;i++) {
			try{
				Thread.sleep(50);
				}
				catch(InterruptedException e) {
				}
			System.out.print(">");
			}
	    pile.add(new carte(pileEnd,pileEnd));
	    System.out.println(">>>>>>洗牌结束");
	}
	
	public LinkedList<carte> getPile(){
		return pile;
	}
	/*public LinkedList<carte> getPile2(){
		return pile2;
	}*/
/*	public static void main (String[] args) {
		cartePile a = new cartePile();
		List<carte> pile0=a.getPile();
		System.out.println(pile0);
	}*/
	public void reshuffleCarte(){
		carte lastCarte=pile.getLast();
		pile.removeFirst();
		pile.removeLast();
	    Collections.shuffle(pile);
	    pile.add(new carte(pileEnd,pileEnd));
	    pile.add(lastCarte);
	}
	
	public carte Distribuer() {
		if(pile.getFirst().getColor().equals("0")) {
			reshuffleCarte();
		}
		carte topCarte=pile.getFirst();
		pile.removeFirst();
		
		return topCarte;
	}
	
	public void rejet(carte carte) {
		if (carte!=null) {
			pile.add(carte);
		} 
	}
}
