package joueurs;
import java.util.Scanner;

import cartes.*;

public class joueurPhy extends joueur {
	private Scanner action;
	
	public joueurPhy() {
		// TODO Auto-generated constructor stub
	}
	public joueurPhy(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	/*public int getNumAct() {
		return this.numAct;
	}*/
	public void showCartePermit() {
		if(!getCartePermit().isEmpty()) {
			for(int i=0; i< getCartePermit().size() ; i++) {
				System.out.print((i+1)+"("+getCartePermit().get(i)+")"+" ");
			}
		}
	}
	@Override
	public void chose(int begin, int end) {
		/*System.out.println("->"+this.getCarteInHand());
		System.out.print("->选择你的动作：");
		if (canPioche>0) {
			System.out.print("摸牌(0) ");
			//this.numAct = 1+this.getCartePermit().size();
		} 
		if (canPoser>0&&!this.getCartePermit().isEmpty()) {
			System.out.print("打牌: ");
			this.showCartePermit();
		}
		*/
		this.action = new Scanner(System.in);
		
		do{ 
			while(!(action.hasNextInt())) {
			System.out.println("请输入整数类型！");
			action.nextLine();
			}
			setNumAct(action.nextInt());
			if( getNumAct() >= begin && getNumAct() <= end) {break;}
			else {System.out.println("请输入"+begin+"-"+end+"之间的整数！");}
		}while(true);
	} 
	
	@Override 
	public void piocher(carte carteTop) {
		this.getCarteInHand().add(carteTop);
		System.out.print("摸牌 ："+carteTop+" ");
		//clearCompare();
		try{
			Thread.sleep(300);
			}
			catch(InterruptedException e) {
			}
	}
	
	@Override
	public void play() {
		//this.initial();
		
		/*while(true) {
			
			if (canPoser==0) {
				break;
			}
			
			compare();
		
		
			if(this.getCartePermit().size()==0&&canPioche==0) {
				break;
			}
			*/
			System.out.println("->"+this.getCarteInHand());
			System.out.print("->选择你的动作：");
			
			if (canPioche>0) {
				System.out.print("摸牌(0) ");
				//this.numAct = 1+this.getCartePermit().size();
			} 
			if (canPoser>0&&!this.getCartePermit().isEmpty()) {
				System.out.print("打牌: ");
				this.showCartePermit();
			}
			
			chose((this.canPioche-1)*(this.canPioche-1),this.getCartePermit().size());
			
			/*switch (numAct) {
			 	case 0 : 
			 		//if(cartePile.pile.getLast().getNum().equals("2")) {
			 		if(carte2.carteAdd != 0) {
			 			for(int i=0;i<carte2.carteAdd;i++) {
			 				this.piocher(poker.Distribuer());
			 			}
			 			carte2.carteAdd = 0;
			 		} else {
			 			this.piocher(poker.Distribuer());
			 		}
			 		canPioche = 0;
			 		break;
			 	default: 
			 		poker.rejet(this.poser(this.getReflex().get(numAct-1)));
			 		canPioche = 0;
			}
		}*/
	}
	
}
