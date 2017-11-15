package joueurs;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cartes.*;
import game.design;
import game.jeu;

public class playerPhy extends player {
	//private String tCardInHand = "";
	private JTextField sCardInHand = new JTextField("");
	private JButton pioche = new JButton("Piocher");
	
	private JButton[] canCards = new JButton[19];
	public JPanel buttons = new JPanel();
	private actListener chose = new actListener();
	private JButton[] colors = new JButton[4];
	private JPanel cColor = new JPanel();
	private choseColor choseColor = new choseColor();
	
	public playerPhy() {
		// TODO Auto-generated constructor stub
	}
	public playerPhy(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		
	}

	public void showCartePermit() {
		removeButtons();
		if(!getCartePermit().isEmpty()) {
			int size = getCartePermit().size();
			for(int i=0; i< size ; i++) {
				canCards[i].setText(this.getCartePermit().get(i).toString());
				canCards[i].setVisible(true);
			}
		}
		/*canCards = new JButton[size];
		for(int i=0; i< size ; i++) {
			canCards[i] = new JButton(this.getCarteInHand().get(i).toString());
			buttons.add(canCards[i]);
			//pioche.addActionListener(chose);
		}*/
	}
	
	@Override
	public void chose(int begin,int end) {
		pioche.setVisible(false);
		removeButtons();
		buttons.setVisible(true);
		//cColor.setLayout(new GridLayout(0,2));
		cColor.setVisible(true);
		
	}
	
	@Override
	public JPanel setpPan() {
		String[] colorType = {"♦","♥","♠","♣"};
		this.pPan.setOpaque(false);
		this.pPan.setLayout(new BorderLayout());
		sCardInHand.setLayout(new GridLayout(0,1));
		sCardInHand.setFont(new Font("arial",Font.BOLD, 16));
		sCardInHand.setForeground(Color.WHITE);
		sCardInHand.setOpaque(false);
		sCardInHand.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		sCardInHand.setPreferredSize(new Dimension(150,0));
		sCardInHand.setEditable(false);
		sCardInHand.setHorizontalAlignment(JTextField.CENTER);

		this.pPan.add(sCardInHand,BorderLayout.CENTER);
		pioche.setVisible(false);
		buttons.add(pioche);
		pioche.addActionListener(chose);
		for(int i=0; i< 19 ; i++) {
			canCards[i] = new JButton();
			canCards[i].setVisible(false);
			canCards[i].addActionListener(chose);
			buttons.add(canCards[i]);
		}
		
		//cColor.setPreferredSize(new Dimension(50,50));
		for(int i=0; i< 4 ; i++) {
			colors[i] = new JButton();
			colors[i].setText(colorType[i]);
			colors[i].addActionListener(choseColor);
			cColor.add(colors[i]);
		}
		cColor.setOpaque(false);
		cColor.setVisible(false);
		buttons.add(cColor);
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.setPreferredSize(new Dimension(150,70));
		buttons.setOpaque(false);
		this.pPan.add(buttons,BorderLayout.NORTH);
		return this.pPan;
	}
	
	
	@Override 
	public void piocher(carte carteTop) {
		this.getCarteInHand().add(carteTop);
		sCardInHand.setText(sCardInHand.getText()+"|"+carteTop.toString()+"| ");
		canPioche = 0;
	}
	
	@Override 
    public carte poser(int position) {
		
		carte pose = getCarteInHand().get(position);
		getCarteInHand().remove(position);
		System.out.println("出牌 : "+pose);
		if(getCarteInHand().isEmpty()) {
			sCardInHand.setText("You are winner!");
			return null;
		}
		clearCompare();
		sCardInHand.setText("");
		for(int i=0; i< getCarteInHand().size() ; i++) {
			sCardInHand.setText(sCardInHand.getText()+"|"+getCarteInHand().get(i)+"| ");
		}
		return pose;
	}
	
    @Override
	public void play() {
		
		compare(jeu.poker.getPile().getLast().getColor(),jeu.poker.getPile().getLast().getNum());
			//buttons.removeAll();
			buttons.setVisible(true);
			if (canPioche>0) {
				pioche.setVisible(true);	
			} else {
				pioche.setVisible(false);
			}
			if (canPoser>0) {
				this.showCartePermit();
			}
			if (canPioche==0 && getCartePermit().isEmpty()) {
				jeu.callNext();
			}
			/*try{
				Thread.sleep(30000);
				}
				catch(InterruptedException e) {
				}*/
			//chose((this.canPioche-1)*(this.canPioche-1),this.getCartePermit().size())
	}
	private void removeButtons() {
		for(int i=0; i< 19 ; i++) {
			canCards[i].setVisible(false);
		}
	}
	private class actListener implements ActionListener   {  
		public void actionPerformed(ActionEvent e) {
		//利用getActionCommand获得按钮名称 也可以利用getSource()来实现  if (e.getSource() ==button1)  
			int size = getCartePermit().size();
			if(e.getSource() == pioche) {
				if(carte2.carteAdd != 0) {
			 		for(int i=0;i<carte2.carteAdd;i++) {
						piocher(jeu.poker.Distribuer());
		 			}
		 			carte2.carteAdd = 0;
		 		} else {
		 			piocher(jeu.poker.Distribuer());
			 	}
			 	
			 	play();
				//piocher(jeu.poker.Distribuer());
        	} else {
        		for(int i=0; i< size ; i++) {
        			if(e.getActionCommand().equals(getCartePermit().get(i).toString())) {
        				carte8.forceColor="";
    					//jeu.module=1;
    					jeu.poker.rejet( poser( getReflex().get(i) ) );
    					canPoser = 0;	
    					if(getCarteInHand().isEmpty()) {
    						System.out.println("恭喜玩家"+getNom()+"胜利!!!!!!");
    						buttons.setVisible(false);
    						jeu.endNormal();
    					} else {
	    					jeu.poker.getPile().getLast().action();	
    					}
    					break;
        			}
        		}
        		
        		if(!getCarteInHand().isEmpty()&&!jeu.poker.getPile().getLast().getNum().equals("8")) {
        			buttons.setVisible(false);
        			jeu.callNext();
        		}
        	}
			
			
        }  
    }  
	
	private class choseColor implements ActionListener   {  
		public void actionPerformed(ActionEvent e) {
		//利用getActionCommand获得按钮名称 也可以利用getSource()来实现  if (e.getSource() ==button1)  
			for(int i=0; i< 4 ; i++) {
			if (e.getSource() == colors[i])  {
				setNumAct(i);
				break;
				}
			}
			switch (getNumAct()) {
				case 0: carte8.forceColor="♦";break;
				case 1: carte8.forceColor="♥";break;
				case 2: carte8.forceColor="♠";break;
				case 3: carte8.forceColor="♣";break;
			}
			cColor.setVisible(false);
			jeu.callNext(); 	
        }  
    }  
}
