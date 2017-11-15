package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cartes.cartePile;

public class table {

	public game jeu = new game();
	private static JFrame tableFrame = new JFrame("8 Americains");//实例化窗体对象
	private JTextField lastCarte; // 显示运算结果的文本框
	private JButton[] cartes; // 所有的按钮对象
	private JButton Piocher;
	private JButton Poser;
	
	public static void main(String[] args) throws IOException {
	        // 显示应用 GUI
		test1();
	   }
	
	public static void test1(){
		ImageIcon icon = new ImageIcon("images/backgroud.png");

		JPanel fondImage;
		
		JLabel fond = new JLabel(icon);// 把背景图片显示在一个标签里面  
		  // 把标签的大小位置设置为图片刚好填充整个面板  
		fond.setBounds(-5, -10, icon.getIconWidth(),icon.getIconHeight()+15);  
		  // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
		fondImage = (JPanel)tableFrame.getContentPane();  
		fondImage.setOpaque(false);  
		  // 内容窗格默认的布局管理器为BorderLayout  
		fondImage.setLayout(new FlowLayout());  
		  /**imagePanel.add(new JButton("测试按钮"));  
		  
		  frame.getLayeredPane().setLayout(null);  
		  */ // 把背景图片添加到分层窗格的最底层作为背景  
		tableFrame.getLayeredPane().add(fond, new Integer(Integer.MIN_VALUE));  
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		tableFrame.setSize(icon.getIconWidth(), icon.getIconHeight()+15);  
		new table().generateInterface();
		//tableFrame.setResizable(false);  
		tableFrame.setVisible(true);
		
	}
	
	private void generateInterface() {

		JPanel center = new JPanel();
		center.setLayout(null);
		center.setSize(500,500);
		/* 文本框 */
		lastCarte = new JTextField(" ");
		cartePile poker = new cartePile();
		String acarte = poker.getPile().getLast().toString();
		lastCarte.setText(acarte);
		// 不允许编辑
		lastCarte.setEditable(false);
		// 将文本框添加到窗体北方
		center.add(lastCarte, BorderLayout.CENTER);
		//tableFrame.getLayeredPane().add(center,BorderLayout.CENTER); 
	}
}
