package game;
import joueurs.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import cartes.cardPile;
import cartes.carte;
import cartes.carte2;
import cartes.carte8;

public class jeu {
	public static int numJoueur; //玩家总人数,要小于5
	private final String[] computer = {"Computer 1","Computer 2","Computer 3","Computer 4"}; //电脑玩家人数
	//private cardPile poker; //一副牌
	public static cardPile poker;
	
	private static ArrayList<player> playor = new ArrayList<>(); //玩家的列表,装载所有玩家
	
	public static int turn=(int)(Math.random()*100); //轮次
	public static int direction=1; //旋转方向
	public static int module=1;
	
	public static player getJoueur(int n) {
		return playor.get(n);
	} 
	
	public void start() {
		playor.clear();
		playor.add(new playerPhy());
		for (int i=1;i < numJoueur;i++) {
			playor.add(new playerVirtu(computer[i-1]));
		}

		poker=new cardPile();//creater une pile de carte
		poker.shuffleCarte();//洗牌
		//发牌 每人8张牌
		for(int i=0;i<8;i++) {
			for(int j=0;j<numJoueur;j++) {
				playor.get(j).piocher(poker.Distribuer());
			}
		}
		//翻出牌顶的一张牌，作为起始牌
		poker.getPile().add(poker.getPile().getFirst());
		poker.getPile().removeFirst();
		
		//轮次循环，在while中循环一次，代表玩家行动一轮
		
		//endNormal();
	}
	
	public static void jouer() {
		module=1;
		turn=mod(turn); //求出本轮是谁的轮次
		player p = playor.get(turn);
		carte c = poker.getPile().getLast();
		design.showCarte.setText(c.toString());
		System.out.println(p.getNom());	
			//如果上一轮有人出了8, 那么这次要出他选择的花色的牌,这个花色叫强制花色forcecolor
		if (!carte8.forceColor.equals("")) {
			design.forceColor.setVisible(true);
			design.forceColor.setText(carte8.forceColor);
		} else {
			design.forceColor.setVisible(false);
		}//也可以直接写在carte8里
		//System.out.println("->"+p+" "); //显示本轮出牌者的姓名 改为出牌者的框的颜色变为高亮			 //本轮出牌者行动
			
		p.initial();
		if(p.getClass().getSimpleName().equals("playerVirtu") ) {
			p.play();
							
		    if(!p.getCarteInHand().isEmpty()) {
		    	p.pass();
		    	callNext();
		    }
		
		} else {
			p.play();
		}			
	}
	
	
	public static void callNext() {
		turn=turn+direction*module; //计算下一个玩家的轮次
			
		jouer();
	
	}
	
	public static void endNormal() {
		System.out.println("\n-----------------------Game Over----------------------");
		design.endjeu(playor.get(turn).toString());
	}
	
	public cardPile getPoker() {
		return poker;
	} 
	
	private static int mod(int integer) {
		if(integer >= 0) {
			integer = integer%numJoueur;
		} else {
			integer = numJoueur+integer%numJoueur;
		}
		return integer;
	}

	
}
