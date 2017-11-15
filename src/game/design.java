package game;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class design {
	
	public static JFrame frame = new JFrame("8 Americains");
	public static JFrame commencer = new JFrame("Bienvenu ! ");
	public static final Color background = new Color(Integer.decode("#228B22"));
	private static JPanel carteArea = new JPanel(); //牌的框框 
	private JPanel[] players = new JPanel[5]; //玩家的窗口
	private JPanel[] position = new JPanel[4];
	private JRadioButton[] num = new JRadioButton[4];
	public static JPanel window = new JPanel(); //最外面的窗口
	public static JTextField showCarte = new JTextField(""); //显示牌堆的顶牌
	public static JTextField forceColor = new JTextField("");
	private JPanel choice = new JPanel();
	private ButtonGroup group = new ButtonGroup();
	private JPanel setNumber=new JPanel(); 
	private JButton ready = new JButton("Ready !");
	private JTextField entreName = new JTextField("Player South");
	public static jeu jeu;
	private static JPanel result = new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new design().setWindowCommencer();
	
		
	}
	
	public void setWindow() {
		window.removeAll();
		//Color background = new Color(34,139,34);//228B22
		ImageIcon image=new ImageIcon("images/backgroud.png");
		JLayeredPane layeredPane=new JLayeredPane();
		JPanel bg=new JPanel(); //background
		JLabel bgimage=new JLabel(image); //background image
		bg.setBounds(0,-5,image.getIconWidth(),image.getIconHeight());    
		bg.add(bgimage);
		window.setBounds(0, -5, image.getIconWidth(),image.getIconHeight());
		
		window.setLayout(new BorderLayout(5,5)); //window.setLayout(new GridLayout(3, 5, 5, 5));
		
		window.setOpaque(false);
		layeredPane.add(bg,JLayeredPane.DEFAULT_LAYER);
		
		layeredPane.add(window,JLayeredPane.MODAL_LAYER);

		frame.setSize(905, 660);
		
		
		//***********
		frame.setVisible(true);
		//***********
		//设置程序图标
		frame.setIconImage((new ImageIcon("images/Uno-Game.png")).getImage());
		//frame.getContentPane().add(comp, constraints);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setFont(new Font("Helvetica", Font.PLAIN, 14));
		//frame.getContentPane().setBackground(background);
		//窗体居中
		//frame.setLayout(new BorderLayout(5,5)); 
		frame.setLocationRelativeTo(frame.getOwner()); 
		
		frame.setLayeredPane(layeredPane); 
		
		frame.setResizable(false);
		//************//
		setCarteArea();
		//************//
		setPosition(jeu.numJoueur);
		//************//
		
		
		
	}

	public void setWindowCommencer() {
		
		JTextField playerNumber = new JTextField("Nombre de joueurs :");
		JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextField name = new JTextField("Votre nom :");
		
		JPanel namepan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		commencer.setSize(400,200);
		commencer.setLocationRelativeTo(null); 
		
		commencer.getContentPane().setBackground(new Color(34,139,34));
		commencer.getContentPane().setLayout(new GridLayout(0,1));  
		
		choice.setLayout(new FlowLayout());
		choice.setOpaque(false);
		for(int i=0;i<4;i++) {
			num[i] = new JRadioButton(i+2+"");
			num[i].setOpaque(false);
			num[i].setFont(new Font("arial",Font.BOLD, 26));
			num[i].setForeground(Color.WHITE);
			group.add(num[i]);
			choice.add(num[i]);
		}

		//jrb1.addItemListener(this);             //加入事件监听

		setText(playerNumber);
		setText(name);
		
		entreName.setEditable(true);
		entreName.setColumns(18);
		entreName.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
		namepan.add(name);
		namepan.add(entreName);
		namepan.setOpaque(false);
		
		num[2].setSelected(true);
		setNumber.setLayout(new BorderLayout());
		setNumber.add(playerNumber,BorderLayout.CENTER);
		setNumber.setOpaque(false);
		
		ready.setFont(new Font("arial",Font.BOLD, 22));
		ready.setBackground(new Color(204,0,0));
		ready.setForeground(Color.yellow);
		ready.setPreferredSize(new Dimension(150,30));
		
		readyListener waitReady = new readyListener();
		ready.addActionListener(waitReady);
		
		pan.add(ready);
		pan.setOpaque(false);
		
		commencer.getContentPane().add(namepan);
        commencer.getContentPane().add(setNumber); 
        commencer.getContentPane().add(choice); 
        commencer.getContentPane().add(pan); 
        commencer.setVisible(true);
		//commencer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		commencer.setIconImage((new ImageIcon("images/Uno-Game.png")).getImage());
		commencer.setResizable(false);
	}
	
	public void setCarteArea() {
		carteArea.removeAll();
		ImageIcon ico=new ImageIcon("images/card&shade.png");
		ico.setImage(ico.getImage().getScaledInstance(ico.getIconWidth()/2,ico.getIconHeight()/2, Image.SCALE_DEFAULT));
		JLabel cardPileImage=new JLabel(ico);
		JPanel sCarte=new JPanel(new BorderLayout());
		//carteArea.setBorder(BorderFactory.createTitledBorder("Carte Pile"));
		
		setplayorborder(carteArea, "Carte Pile"); //ajouter carteArea sur Windows

		//showCarte.setOpaque(false);	//ajouter un textarea à carteArea pour presenter la carte
		
		showCarte.setText(jeu.getPoker().getPile().getLast().toString());
		showCarte.setEditable(false);
		showCarte.setForeground(Color.white); 
		showCarte.setFont(new Font("arial",Font.BOLD, 22)); //宋体 粗体 16 号
		showCarte.setHorizontalAlignment(JTextField.CENTER);
		showCarte.setOpaque(false);
		showCarte.setBorder(null);
		
		forceColor.setText("");
		forceColor.setEditable(false);
		forceColor.setForeground(Color.red); 
		forceColor.setFont(new Font("arial",Font.BOLD, 22)); //宋体 粗体 16 号
		forceColor.setHorizontalAlignment(JTextField.CENTER);
		forceColor.setOpaque(false);
		forceColor.setBorder(null);
		forceColor.setPreferredSize(new Dimension(0,50));
		forceColor.setVisible(false);
		
		sCarte.setBackground(new Color(190,240,204));
		sCarte.setBorder(new BevelBorder(BevelBorder.RAISED,new Color(170,255,204),Color.gray));
		sCarte.setPreferredSize(new Dimension(ico.getIconWidth()-10,ico.getIconHeight()-5));
		sCarte.add(showCarte,BorderLayout.CENTER);
		sCarte.add(forceColor,BorderLayout.SOUTH);
		
		carteArea.setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
		carteArea.add(cardPileImage);
		carteArea.add(sCarte);
		carteArea.setOpaque(false);
		
		window.add(carteArea,BorderLayout.CENTER);
		
		
	}
	
	public void setPosition(int np) {
		//prepaprer les images
		//int np = Integer.parseInt(numPlayor);
		ImageIcon ico1=new ImageIcon("images/cards&shade.png");
		ImageIcon ico2=new ImageIcon("images/cardshorizon&shade.png");
		ico1.setImage(ico1.getImage().getScaledInstance(ico1.getIconWidth()/3,
				ico1.getIconHeight()/3, Image.SCALE_DEFAULT));
		ico2.setImage(ico2.getImage().getScaledInstance(ico2.getIconWidth()/3,
				ico2.getIconHeight()/3, Image.SCALE_DEFAULT));
		//foundamental playors
		for (int i=0;i<4;i++) {
			position[i] = new JPanel();
			position[i].setPreferredSize(new Dimension(175,175));
			position[i].setOpaque(false);
		}
		window.add(position[0],BorderLayout.WEST);
		window.add(position[1],BorderLayout.EAST);
		window.add(position[2],BorderLayout.NORTH);
		window.add(position[3],BorderLayout.SOUTH);	
		
		for (int i=0;i<np;i++) {
			players[i] = new JPanel();
			players[i].setOpaque(false);
		}
		//showCarte.setText(frame.getContentPane().toString());
		
		if (np==2) {  //Playor 1 virtuelle
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			setplayorborder(players[1], "Computer 1");
			JLabel cardPileImage1=new JLabel(ico2);
			players[1].setPreferredSize(new Dimension(600,160));
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur(1).setpPan());
			position[2].add(players[1]);
			
		} else if (np==3) {   //Playor 2 virtuelle
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "Computer 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "Computer 2");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur(2).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			position[2].setPreferredSize(new Dimension(175,40));
		} else if (np==4) {    //Playor 3 et 4 virtuelle
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "Computer 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "Computer 3");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur(3).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			players[3].setPreferredSize(new Dimension(600,160));
			setplayorborder(players[3], "Computer 2");
			JLabel cardPileImage3=new JLabel(ico2);
			players[3].add(cardPileImage3);
			players[3].add(jeu.getJoueur(2).setpPan());
			position[2].add(players[3]);
		} else if (np==5) {
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "Computer 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "Computer 4");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur(4).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			players[3].setPreferredSize(new Dimension(350,160));
			setplayorborder(players[3], "Computer 2");
			JLabel cardPileImage3=new JLabel(ico2);
			players[3].add(cardPileImage3);
			players[3].add(jeu.getJoueur(2).setpPan());
			position[2].add(players[3]);
			
			players[4].setPreferredSize(new Dimension(350,160));
			setplayorborder(players[4], "Computer 3");
			JLabel cardPileImage4=new JLabel(ico2);
			players[4].add(cardPileImage4);
			players[4].add(jeu.getJoueur(3).setpPan());
			//handCardp4.setVisible(false); s'il y a que 4 joueurs
			position[2].add(players[4]);
		}
		
		//Player physique
		position[3].setLayout(new FlowLayout(FlowLayout.CENTER,10,0)); 
		players[0].setPreferredSize(new Dimension(650,160));
		players[0].setBorder(BorderFactory.createTitledBorder(null,
				entreName.getText(), TitledBorder.DEFAULT_JUSTIFICATION, //玩家的名字需要根据用户更改，joueur.nom
				TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
		players[0].setLayout(new BorderLayout());
		players[0].add(jeu.getJoueur(0).setpPan(),BorderLayout.CENTER);
		jeu.getJoueur(0).setNom(entreName.getText());
		position[3].add(players[0]);
		
	}
	
	private void setplayorborder(JPanel pan, String name) {
		pan.setBorder(BorderFactory.createTitledBorder(null,
				name, TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, Color.WHITE));		
	}

	private void setText(JTextField text) {
		text.setForeground(Color.WHITE); 
		text.setFont(new Font("arial",Font.BOLD, 22));
		text.setOpaque(false);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBorder(null);
		text.setEditable(false);
	}
	
	public static void endjeu(String winner) {
		//JPanel result = new JPanel();
		//carteArea.setVisible(false);
		window.remove(carteArea);
		result.removeAll();
		result.setBorder(BorderFactory.createTitledBorder(null,
				"Result: Congratulation to "+winner, TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, Color.WHITE));	
		result.setOpaque(false);
		result.setLayout(new GridLayout(0,2));
		JTextField[] name= new JTextField[jeu.numJoueur];
		JTextField[] rest= new JTextField[jeu.numJoueur];
		for(int i=0;i<jeu.numJoueur;i++) {
			name[i] = new JTextField(jeu.getJoueur(i).toString());
			name[i].setHorizontalAlignment(JTextField.CENTER);
			name[i].setOpaque(false);
			name[i].setFont(new Font("arial",Font.BOLD, 16));
			name[i].setForeground(Color.WHITE);
			rest[i] = new JTextField("");
			rest[i].setOpaque(false);
			for(int j=0;j<jeu.getJoueur(i).getCarteInHand().size();j++) {
				rest[i].setText(rest[i].getText()+"|"+jeu.getJoueur(i).getCarteInHand().get(j)+"| ");
			}
			if(jeu.getJoueur(i).getCarteInHand().size()==0) {
				rest[i].setText("Congratulations!");
			}
			rest[i].setFont(new Font("arial",Font.BOLD, 16));
			rest[i].setForeground(Color.WHITE);
			result.add(name[i]);
			result.add(rest[i]);
		}
		
		window.add(result,BorderLayout.CENTER);
		commencer.setVisible(true);
	}
	
	private class readyListener implements ActionListener   {  
		public void actionPerformed(ActionEvent e) {
		//利用getActionCommand获得按钮名称 也可以利用getSource()来实现  if (e.getSource() ==button1)  
        //jeu.numJoueur = Integer.parseInt(group.getSelection().getActionCommand());
		jeu= new jeu();
		for(int i=0;i<4;i++) {
			if (num[i].isSelected() == true) {
				jeu.numJoueur = i+2;
				break;
			}
		}
		//jeu.getJoueur().setNom(entreName.getText());
		jeu.start();
		commencer.setVisible(false);
		setWindow();
		jeu.jouer();
        }  
    }  
}
