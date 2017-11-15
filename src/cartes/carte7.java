package cartes;

import game.game;
import game.jeu;

public class carte7 extends carte {

	public carte7() {
		// TODO Auto-generated constructor stub
	}

	public carte7(String color) {
		this.setNum("7");
		this.setColor(color);
		// TODO Auto-generated constructor stub
	}
	@Override //牌的数字只能为1
	public void setNum(String num) {
		super.setNum("7");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		//game.module=2;
		jeu.module=2;
		System.out.println("跳过下一位玩家");
	}
}
