package joueurs;

public class joueurVirtu extends joueur{

	public joueurVirtu() {
		// TODO Auto-generated constructor stub
	}
	public joueurVirtu(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void chose(int begin,int end) {
			setNumAct((int)(Math.random()*(end-begin))+begin);
	}
	
	@Override
	public void pass() {
		//顺着出牌方向,将轮次移到下一个玩家
		System.out.println("手牌数量 : ["+this.getCarteInHand().size()+"]");
	}
	@Override
	public void play() {
		//System.out.println("->"+this.getCarteInHand());
		if(this.getCartePermit().isEmpty()) {
			setNumAct(0);
		} else {
			chose(1,this.getCartePermit().size());
		}
	}
		/*while(true) {
			
			if (canPoser==0) {
				break;
			}
			
			compare();
			
			if(this.getCartePermit().size()==0&&canPioche==0) {
				break;
			}*/
			
			
			/*switch (this.getCartePermit().size()) {
			//case 0 piocher carte
			case 0 : 
				if(carte2.carteAdd != 0) {
			 		for(int i=0;i<carte2.carteAdd;i++) {
						this.piocher(poker.Distribuer());
		 			}
		 			carte2.carteAdd = 0;
		 		} else {
		 			this.piocher(poker.Distribuer());
			 	}
			 	canPioche = 0;
				System.out.println("->完成抽牌");
		 		break;
			default : 
				poker.rejet(poser(this.getReflex().get(chose(0,this.getCartePermit().size()-1))));
				canPoser = 0;
			}
		}
	}*/
}
