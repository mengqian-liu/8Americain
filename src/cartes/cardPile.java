package cartes;
import java.util.*;
//import 


public class cardPile {

	private final String[] colorType = {"♦","♥","♠","♣"};
	private final String[] numTypeNF = {"3","4","5","6","9","Q","K"};
	private final String pileEnd = "0";
	//public int numJoueur;
	
	public static LinkedList<carte> pile = new LinkedList<carte>();
	//public static LinkedList<carte> pile2 = new LinkedList<carte>();
	
	//public carte reference;
	
	public cardPile() {
		// TODO Auto-generated constructor stub
		pile.clear();
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

	}

	
	public void shuffleCarte(){

	    Collections.shuffle(pile);
	    pile.add(new carte(pileEnd,pileEnd));
	}
	
	public LinkedList<carte> getPile(){
		return pile;
	}

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
