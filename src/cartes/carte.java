package cartes;

import game.game;

public class carte {
	private String color;
	private String num;
	
	public carte() {
		// TODO Auto-generated constructor stub
	}
	public carte(String color, String num) {
		// TODO Auto-generated constructor stub
		this.color = color;
		this.num = num;
	}
	
	public String toString() {
		return color+" "+num;
	}
	
	public String getColor() {
		return this.color;
	}

	public String getNum() {
		return this.num;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	public void action() {
		carte8.forceColor="";
		game.module = 1;
	}
}
