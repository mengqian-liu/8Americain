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

	@Override //�Ƶ�����ֻ��Ϊ1
	public void setNum(String num) {
		super.setNum("2");
	}
	@Override
	public void action() {
		//carte8.forceColor="";
		//game.module=1;
		carteAdd = carteAdd+2;
		System.out.println("��һλ���Ҫ�� "+carteAdd+" ����");
		//game.getJoueur().end();
	}
}
