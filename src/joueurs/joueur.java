package joueurs;
import java.util.*;
import cartes.*;

public class joueur {
	private String nom;
	private int numAct;
	
	private ArrayList<carte> carteInHand = new ArrayList<>();
	private ArrayList<carte> cartePermit = new ArrayList<>();
	
	private ArrayList<Integer>  reflex = new ArrayList<Integer>();
	
	public int canPioche;
	public int canPoser;
	
	public joueur() {
		// TODO Auto-generated constructor stub
	}
	
	public joueur(String name) { 
		// TODO Auto-generated constructor stub
		setNom(name);
	}
	public String toString() {
		return nom;
	}

	public void initial() {
		this.canPioche = 1;
		this.canPoser = 1;
	}
	
	/*public void end() {
		this.canPioche = 0;
		this.canPoser = 0;
	}*/
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}
	
	public ArrayList<carte> getCarteInHand() {
		return this.carteInHand;
	}
	public ArrayList<carte> getCartePermit() {
		return this.cartePermit;
	}
	public ArrayList<Integer> getReflex() {
		return this.reflex;
	}
	private void clearCompare() {
		cartePermit.clear();
		reflex.clear();
	}
	

	public void chose(int begin, int end) {
	} 
	
	public void piocher(carte carteTop) {
		this.carteInHand.add(carteTop);
		clearCompare();
	}
	
	public carte poser(int position) {
		
		carte pose = this.carteInHand.get(position);
		this.carteInHand.remove(position);
		System.out.println("出牌 : "+pose);
		if(this.carteInHand.isEmpty()) {
			return null;
		}
		
		clearCompare();
		
		return pose;
	}
	
	public void setNumAct(int numact) {
		this.numAct = numact;
	}
	
	public int getNumAct() {
		return this.numAct;
	}
	
	public void play() {}
	
	public void pass() {
		//顺着出牌方向,将轮次移到下一个玩家
	}
	
	public void compare(String color,String num){
		//System.out.println(top);
		clearCompare();
		if(carte8.forceColor.equals("")) {
			for(int i=0;i<carteInHand.size();i++) {
				if(carteInHand.get(i).getColor().equals(color) && carte2.carteAdd==0) {
					this.cartePermit.add(carteInHand.get(i));
					this.reflex.add(i);
				} else if(carteInHand.get(i).getNum().equals(num)) {
					this.cartePermit.add(carteInHand.get(i));
					this.reflex.add(i);
				} else if(carteInHand.get(i).getNum().equals("8")) {
					this.cartePermit.add(carteInHand.get(i));
					this.reflex.add(i);
				}
			}
		} else {
			for(int i=0;i<carteInHand.size();i++) {
				if(carteInHand.get(i).getColor().equals(carte8.forceColor)) {
					this.cartePermit.add(carteInHand.get(i));
					this.reflex.add(i);
				} else if(carteInHand.get(i).getNum().equals("8")) {
					this.cartePermit.add(carteInHand.get(i));
					this.reflex.add(i);
				}
			}
		}
	}
}

