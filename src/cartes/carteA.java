package cartes;

public class carteA extends carte {
	

	public carteA() {
		// TODO Auto-generated constructor stub
		this.setNum("A");
	}

	public carteA(String color) {
		// TODO Auto-generated constructor stub
		this.setNum("A");
		this.setColor(color);
	}
	@Override //牌的数字只能为1
	public void setNum(String num) {
		super.setNum("A");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		//game.module=1;
	}
}
