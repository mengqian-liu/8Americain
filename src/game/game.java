package game;
import joueurs.*;
import java.util.*;

import cartes.carte;
import cartes.carte2;
import cartes.carte8;
import cartes.cartePile;

public class game {
	private int numJoueur; //���������,ҪС��5
	private final String[] computer = {"����1","����2","����3","����4"}; //�����������
	private Scanner scan; //��ȡ����
	private cartePile poker; //һ����
	
	private static ArrayList<joueur> playor = new ArrayList<>(); //��ҵ��б�,װ���������
	
	public static int turn=(int)(Math.random()*100); //�ִ�
	public static int direction=1; //��ת����
	public static int module=1;
	
	public static void main(String[] args) {

		new game().start(); //commence jeu
	}
	
	public static joueur getJoueur() {
		return playor.get(turn);
	} 
	
	private void start() {
		System.out.println("-------------Bienvenu �� 8 Americains--------------");
		System.out.println("�������������(2-5)��");
		scan = new Scanner(System.in);
		
		do{ 
			while(!(scan.hasNextInt())) {
			System.out.println("�������������ͣ�");
			scan.nextLine();
			}
			numJoueur=scan.nextInt();
			if(numJoueur<=5 && numJoueur>=2) {break;}
			else {System.out.println("������2-5֮���������");}
		}while(true);
		
		System.out.println("-------------���ڴ������--------------");
		
		for (int i=1;i < numJoueur;i++) {
			System.out.print("���"+i+": "+computer[i-1]);
			playor.add(new joueurVirtu(computer[i-1]));
			System.out.println("������Ϸ");
		}
		
		System.out.println("���"+numJoueur+"\n���������������");
		scan = new Scanner(System.in);
		if(scan.hasNext()) {
		playor.add(new joueurPhy(scan.nextLine()));
		}
		
		System.out.println("��ʼ��Ϸ?(Y/N): ");
		scan = new Scanner(System.in);
		if(scan.next().toLowerCase().equals("y")) {
			System.out.print("Loading------------");
			for(int i=0;i<10;i++) {
				try{
				Thread.sleep(300);
				}
				catch(InterruptedException e) {
				}
				System.out.print("-");
			}
		} else {
			endNormal();
		}
		
		poker=new cartePile();//creater une pile de carte
		poker.shuffleCarte();//ϴ��
		//���� ÿ��8����
		System.out.println("-------------����--------------");
		for(int i=0;i<8;i++) {
			for(int j=0;j<numJoueur;j++) {
				playor.get(j).piocher(poker.Distribuer());
			}
		}
		//�����ƶ���һ���ƣ���Ϊ��ʼ��
		poker.getPile().add(poker.getPile().getFirst());
		poker.getPile().removeFirst();
		
		jouer();
		
		//�ִ�ѭ������while��ѭ��һ�Σ���������ж�һ��
		
		endNormal();
	}
	
	private void jouer() {
		while(true) {
			turn=mod(turn); //���������˭���ִ�
			joueur p = playor.get(turn);
			carte c = poker.getPile().getLast();
			System.out.println("\n->"+c); //����չʾ�ƶ�����,�����ָ�˭�Ƚ�
			//�����һ�����˳���8, ��ô���Ҫ����ѡ��Ļ�ɫ����,�����ɫ��ǿ�ƻ�ɫforcecolor
			if (!carte8.forceColor.equals("")) {
				System.out.println("->"+"Force color: "+carte8.forceColor);
				}
			System.out.println("->"+p+" "); //��ʾ���ֳ����ߵ�����
			 //���ֳ������ж�
			
			p.initial();
			
			action();
			
			p.pass();
			
			turn=turn+direction*module; //������һ����ҵ��ִ�
					
			//�ӳ�1����
			try{
				Thread.sleep(1000);
				}
			catch(InterruptedException e) {
				}
			}	
	}
	
	private void action() {
		
		joueur p = playor.get(turn);
		carte c = poker.getPile().getLast();
		
		while(true) {
			if (p.canPoser==0) {
				break;
			}
		
			p.compare(c.getColor(),c.getNum());

			if(p.getCartePermit().isEmpty() && p.canPioche==0) {
				break;
			}
			
			p.play();
			
			switch(p.getNumAct()) {
			//case 0 piocher carte
			case 0 : 
				if(carte2.carteAdd != 0) {
			 		for(int i=0;i<carte2.carteAdd;i++) {
						p.piocher(poker.Distribuer());
		 			}
		 			carte2.carteAdd = 0;
		 		} else {
		 			p.piocher(poker.Distribuer());
			 	}
			 	p.canPioche = 0;
				System.out.println("->��ɳ���");
		 		break;
			default : 
				carte8.forceColor="";
				module=1;
				poker.rejet( p.poser( p.getReflex().get( p.getNumAct()-1 ) ) );
				if(p.getCarteInHand().isEmpty()) {
					System.out.println("��ϲ���"+p+"ʤ��!!!!!!");
					endNormal();
				}
				p.canPoser = 0;	
				c = poker.getPile().getLast();			
				c.action();
			}
		}
	}
	
	private void endNormal() {
		System.out.println("\n-----------------------Game Over----------------------");
		System.exit(0);
	}
	
	public cartePile getPoker() {
		return poker;
	} 
	
	private int mod(int integer) {
		if(integer >= 0) {
			integer = integer%numJoueur;
		} else {
			integer = numJoueur+integer%numJoueur;
		}
		return integer;
	}

	
}
