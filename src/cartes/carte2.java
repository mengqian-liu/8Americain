package cartes;

public class carte2 extends carte {
	public static int carteAdd=0;
	public carte2() {
		// TODO Auto-generated constructor stub
		this.setNum("2");
	}

	public carte2(String color) {
		this.setNum("2");
		this.setColor(color);
		// TODO Auto-generated constructor stub
	}

	@Override //牌的数字只能为1
	public void setNum(String num) {
		super.setNum("2");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		//game.module=1;
		carteAdd = carteAdd+2;
		System.out.println("下一位玩家要摸 "+carteAdd+" 张牌");
		//game.getJoueur().end();
	}
}
