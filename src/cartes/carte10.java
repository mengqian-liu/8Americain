package cartes;

import game.game;
import game.jeu;

public class carte10 extends carte {

	public carte10() {
		// TODO Auto-generated constructor stub
		this.setNum("10");
	}

	public carte10(String color) {
		this.setNum("10");
		this.setColor(color);
		// TODO Auto-generated constructor stub
	}
	@Override //�Ƶ�����ֻ��Ϊ1
	public void setNum(String num) {
		super.setNum("10");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		//game.playor.get(game.turn).play(game.poker);
		System.out.println("���»���ִ�");
		//game.module = 0;
		jeu.module = 0;
	}
}
