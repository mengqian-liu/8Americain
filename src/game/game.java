package game;
import joueurs.*;
import java.util.*;

import cartes.carte;
import cartes.carte2;
import cartes.carte8;
import cartes.cartePile;

public class game {
	private int numJoueur; //玩家总人数,要小于5
	private final String[] computer = {"电脑1","电脑2","电脑3","电脑4"}; //电脑玩家人数
	private Scanner scan; //获取输入
	private cartePile poker; //一副牌
	
	private static ArrayList<joueur> playor = new ArrayList<>(); //玩家的列表,装载所有玩家
	
	public static int turn=(int)(Math.random()*100); //轮次
	public static int direction=1; //旋转方向
	public static int module=1;
	
	public static void main(String[] args) {

		new game().start(); //commence jeu
	}
	
	public static joueur getJoueur() {
		return playor.get(turn);
	} 
	
	private void start() {
		System.out.println("-------------Bienvenu à 8 Americains--------------");
		System.out.println("请设置玩家人数(2-5)：");
		scan = new Scanner(System.in);
		
		do{ 
			while(!(scan.hasNextInt())) {
			System.out.println("请输入整数类型！");
			scan.nextLine();
			}
			numJoueur=scan.nextInt();
			if(numJoueur<=5 && numJoueur>=2) {break;}
			else {System.out.println("请输入2-5之间的整数！");}
		}while(true);
		
		System.out.println("-------------正在创建玩家--------------");
		
		for (int i=1;i < numJoueur;i++) {
			System.out.print("玩家"+i+": "+computer[i-1]);
			playor.add(new joueurVirtu(computer[i-1]));
			System.out.println("进入游戏");
		}
		
		System.out.println("玩家"+numJoueur+"\n请设置玩家姓名：");
		scan = new Scanner(System.in);
		if(scan.hasNext()) {
		playor.add(new joueurPhy(scan.nextLine()));
		}
		
		System.out.println("开始游戏?(Y/N): ");
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
		poker.shuffleCarte();//洗牌
		//发牌 每人8张牌
		System.out.println("-------------发牌--------------");
		for(int i=0;i<8;i++) {
			for(int j=0;j<numJoueur;j++) {
				playor.get(j).piocher(poker.Distribuer());
			}
		}
		//翻出牌顶的一张牌，作为起始牌
		poker.getPile().add(poker.getPile().getFirst());
		poker.getPile().removeFirst();
		
		jouer();
		
		//轮次循环，在while中循环一次，代表玩家行动一轮
		
		endNormal();
	}
	
	private void jouer() {
		while(true) {
			turn=mod(turn); //求出本轮是谁的轮次
			joueur p = playor.get(turn);
			carte c = poker.getPile().getLast();
			System.out.println("\n->"+c); //翻出展示牌顶的牌,代表本轮跟谁比较
			//如果上一轮有人出了8, 那么这次要出他选择的花色的牌,这个花色叫强制花色forcecolor
			if (!carte8.forceColor.equals("")) {
				System.out.println("->"+"Force color: "+carte8.forceColor);
				}
			System.out.println("->"+p+" "); //显示本轮出牌者的姓名
			 //本轮出牌者行动
			
			p.initial();
			
			action();
			
			p.pass();
			
			turn=turn+direction*module; //计算下一个玩家的轮次
					
			//延迟1秒钟
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
				System.out.println("->完成抽牌");
		 		break;
			default : 
				carte8.forceColor="";
				module=1;
				poker.rejet( p.poser( p.getReflex().get( p.getNumAct()-1 ) ) );
				if(p.getCarteInHand().isEmpty()) {
					System.out.println("恭喜玩家"+p+"胜利!!!!!!");
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
