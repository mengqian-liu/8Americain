package cartes;

import game.game;
import game.jeu;

public class carte8 extends carte {
	public static String forceColor="";
	public carte8() {
		// TODO Auto-generated constructor stub
		this.setNum("8");
	}

	public carte8(String color) {
		this.setNum("8");
		this.setColor(color);
		// TODO Auto-generated constructor stub
	}
	@Override //牌的数字只能为1
	public void setNum(String num) {
		super.setNum("8");
	}
	@Override
	public void action() {
		carte2.carteAdd = 0;
		forceColor="♥"; // test
		//System.out.println("选择花色: 0(Carreau),1(Coeur),2(Pique),3(Trefle)");
		//改为在 手牌栏里面添加按钮，添加线程
		jeu.getJoueur(jeu.turn).chose(0,3);
		switch (jeu.getJoueur(jeu.turn).getNumAct()) {
			case 0: forceColor="♦";break;
			case 1: forceColor="♥";break;
			case 2: forceColor="♠";break;
			case 3: forceColor="♣";break;
		}
	}
}
