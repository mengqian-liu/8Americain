package joueurs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cartes.carte;
import cartes.carte2;
import cartes.carte8;
import game.jeu;

public class playerVirtu extends player{
	
	private JTextField sNumCard = new JTextField("");
	private JTextField sAction = new JTextField("");
	
	public playerVirtu() {
		// TODO Auto-generated constructor stub
	}
	public playerVirtu(String name) {
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
		
		//改成在屏幕里显示
	}
	@Override
	public void play() {
	
		//player p = playor.get(turn);
		carte c = jeu.poker.getPile().getLast();
		
			
		while(true) {
			
			if (canPoser==0) {
				break;
			}
		
			compare(c.getColor(),c.getNum());

			if(getCartePermit().isEmpty() && canPioche==0) {
				break;
			}
			
			if(this.getCartePermit().isEmpty()) {
				setNumAct(0);
				sAction.setText("Piocher");
				
			} else {
				chose(1,this.getCartePermit().size());
				sAction.setText("Poser");
				
			}
			
			switch(getNumAct()) {
			//case 0 piocher carte
				case 0 : 
					if(carte2.carteAdd != 0) {
				 		for(int i=0;i<carte2.carteAdd;i++) {
							piocher(jeu.poker.Distribuer());
			 			}
			 			carte2.carteAdd = 0;
			 		} else {
			 			piocher(jeu.poker.Distribuer());
				 	}
				 	//System.out.println("->完成抽牌");
					sNumCard.setText(getCarteInHand().size()+"");
				 	break;
				default : 
					carte8.forceColor="";
					//jeu.module=1;
					jeu.poker.rejet( poser( getReflex().get( getNumAct()-1 ) ) );
					sNumCard.setText(getCarteInHand().size()+"");
					canPoser = 0;
					if(getCarteInHand().isEmpty()) {
						System.out.println("恭喜玩家"+getNom()+"胜利!!!!!!");
						jeu.endNormal();
					} else {
						jeu.poker.getPile().getLast().action();
				    	//jeu.callNext();
					}
			}
		}
	}

	@Override
	public JPanel setpPan() {
		this.pPan.setLayout(new GridLayout());
		this.pPan.setOpaque(false);
		sNumCard.setText(getCarteInHand().size()+"");
		sNumCard.setFont(new Font("arial",Font.PLAIN, 14));
		sNumCard.setForeground(Color.WHITE);
		sNumCard.setOpaque(false);
		sNumCard.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		sNumCard.setEditable(false);
		sNumCard.setHorizontalAlignment(JTextField.CENTER);
		
		sAction.setText("...........");
		sAction.setFont(new Font("arial",Font.PLAIN, 14));
		sAction.setForeground(Color.WHITE);
		sAction.setOpaque(false);
		sAction.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		sAction.setEditable(false);
		sAction.setHorizontalAlignment(JTextField.CENTER);
		this.pPan.add(sNumCard);
		this.pPan.add(sAction);
		return this.pPan;
	}
}
