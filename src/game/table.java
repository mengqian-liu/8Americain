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
	private static JFrame tableFrame = new JFrame("8 Americains");//ʵ�����������
	private JTextField lastCarte; // ��ʾ���������ı���
	private JButton[] cartes; // ���еİ�ť����
	private JButton Piocher;
	private JButton Poser;
	
	public static void main(String[] args) throws IOException {
	        // ��ʾӦ�� GUI
		test1();
	   }
	
	public static void test1(){
		ImageIcon icon = new ImageIcon("images/backgroud.png");

		JPanel fondImage;
		
		JLabel fond = new JLabel(icon);// �ѱ���ͼƬ��ʾ��һ����ǩ����  
		  // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
		fond.setBounds(-5, -10, icon.getIconWidth(),icon.getIconHeight()+15);  
		  // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
		fondImage = (JPanel)tableFrame.getContentPane();  
		fondImage.setOpaque(false);  
		  // ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout  
		fondImage.setLayout(new FlowLayout());  
		  /**imagePanel.add(new JButton("���԰�ť"));  
		  
		  frame.getLayeredPane().setLayout(null);  
		  */ // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
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
		/* �ı��� */
		lastCarte = new JTextField(" ");
		cartePile poker = new cartePile();
		String acarte = poker.getPile().getLast().toString();
		lastCarte.setText(acarte);
		// ������༭
		lastCarte.setEditable(false);
		// ���ı�����ӵ����山��
		center.add(lastCarte, BorderLayout.CENTER);
		//tableFrame.getLayeredPane().add(center,BorderLayout.CENTER); 
	}
}
