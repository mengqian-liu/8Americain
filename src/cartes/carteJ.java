package cartes;

import game.game;
import game.jeu;

public class carteJ extends carte {

	public carteJ() {
		// TODO Auto-generated constructor stub
		this.setNum("J");
	}

	public carteJ(String color) {
		this.setNum("J");
		this.setColor(color);
		// TODO Auto-generated constructor stub
	}
	@Override //�Ƶ�����ֻ��Ϊ1
	public void setNum(String num) {
		super.setNum("J");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		jeu.direction=jeu.direction*(-1);
		//game.direction=game.direction*(-1);
		System.out.println("����ת");
		//game.module=1;
	}
}
