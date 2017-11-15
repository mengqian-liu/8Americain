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
	public static int numJoueur; //���������,ҪС��5
	private final String[] computer = {"Computer 1","Computer 2","Computer 3","Computer 4"}; //�����������
	//private cardPile poker; //һ����
	public static cardPile poker;
	
	private static ArrayList<player> playor = new ArrayList<>(); //��ҵ��б�,װ���������
	
	public static int turn=(int)(Math.random()*100); //�ִ�
	public static int direction=1; //��ת����
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
		poker.shuffleCarte();//ϴ��
		//���� ÿ��8����
		for(int i=0;i<8;i++) {
			for(int j=0;j<numJoueur;j++) {
				playor.get(j).piocher(poker.Distribuer());
			}
		}
		//�����ƶ���һ���ƣ���Ϊ��ʼ��
		poker.getPile().add(poker.getPile().getFirst());
		poker.getPile().removeFirst();
		
		//�ִ�ѭ������while��ѭ��һ�Σ���������ж�һ��
		
		//endNormal();
	}
	
	public static void jouer() {
		module=1;
		turn=mod(turn); //���������˭���ִ�
		player p = playor.get(turn);
		carte c = poker.getPile().getLast();
		design.showCarte.setText(c.toString());
		System.out.println(p.getNom());	
			//�����һ�����˳���8, ��ô���Ҫ����ѡ��Ļ�ɫ����,�����ɫ��ǿ�ƻ�ɫforcecolor
		if (!carte8.forceColor.equals("")) {
			design.forceColor.setVisible(true);
			design.forceColor.setText(carte8.forceColor);
		} else {
			design.forceColor.setVisible(false);
		}//Ҳ����ֱ��д��carte8��
		//System.out.println("->"+p+" "); //��ʾ���ֳ����ߵ����� ��Ϊ�����ߵĿ����ɫ��Ϊ����			 //���ֳ������ж�
			
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
		turn=turn+direction*module; //������һ����ҵ��ִ�
			
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
