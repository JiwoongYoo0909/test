package javaTeam;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class membership extends JFrame implements ActionListener{	
	
	private JPanel contentPane;
	private JTextField textID;
	private JPasswordField textPWD;
	private JTextField textName;
	private JTextField textNum;
	private JButton btnConfirm, btnCancle,btnOverlap;
	private JRadioButton rdoman, rdogirl;
	private JLabel idLabel,pwLabel,nameLabel,numLabel ;
	ButtonGroup gen;
	String gender=null;
	LoginDAO dao=new LoginDAO();
	private JLabel label_2;
	private JTextField textAge;
	private JLabel ageLabel;
	
	public membership() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //x��ư�������� �α���ȭ�����ΰ���
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblOo = new JLabel("OO\uD1A1 \uD68C\uC6D0\uAC00\uC785");
		panel.add(lblOo);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		panel_1.add(lblNewLabel);
		
		textID = new JTextField();
		panel_1.add(textID);
		textID.setColumns(10);
		//���̵��Է¹޴ºκ�
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String id=textID.getText();
				if(id.length()>15 || id.length()<4) {
					idLabel.setText("���̵�� 4���� �̻� 15���� ���� �����մϴ�.");
				}else if(id.length()<=15 || id.length()>=4) {
					idLabel.setText("");
				}
			}
		});
		
	
		idLabel = new JLabel("");
		panel_1.add(idLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		panel_1.add(lblNewLabel_1);
		
		textPWD = new JPasswordField();
		panel_1.add(textPWD);
		textPWD.setColumns(20);
		textPWD.setEchoChar('*');
		
		pwLabel = new JLabel("");
		panel_1.add(pwLabel);
		
		JLabel label = new JLabel("\uC774\uB984");
		panel_1.add(label);
		
		textName = new JTextField();
		textName.setColumns(10);
		panel_1.add(textName);
		
		nameLabel = new JLabel("");
		panel_1.add(nameLabel);
		
		label_2 = new JLabel("����");
		panel_1.add(label_2);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		panel_1.add(textAge);
		
		ageLabel = new JLabel("");
		panel_1.add(ageLabel);
		
		JLabel label_1 = new JLabel("\uC804\uD654\uBC88\uD638");
		panel_1.add(label_1);
		
		textNum = new JTextField();
		textNum.setColumns(10);
		panel_1.add(textNum);
		
		numLabel = new JLabel("");
		panel_1.add(numLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("\uC131\uBCC4");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2);
		
		rdoman = new JRadioButton("\uB0A8");
		rdoman.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(rdoman);
		
		rdogirl = new JRadioButton("\uC5EC");
		rdogirl.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(rdogirl);
		
		btnConfirm = new JButton("\uD655\uC778");
		panel_2.add(btnConfirm);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_2.add(lblNewLabel_3);
		
		btnCancle = new JButton("\uCDE8\uC18C");
		panel_2.add(btnCancle);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		btnOverlap = new JButton("�ߺ�Ȯ��");
		btnOverlap.addActionListener(this); 
		panel_3.add(btnOverlap);
		
		btnConfirm.addActionListener(this);
		btnCancle.addActionListener(this);
		
		rdoman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender=rdoman.getActionCommand();
			}
		});
		rdogirl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender=rdogirl.getActionCommand();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();		
		
		if(btn==btnConfirm) {		
			String id=textID.getText();
			char[] password=textPWD.getPassword();
			String pwd=new String(password, 0, password.length);
			String name=textName.getText();
			String strNum=textNum.getText();
			String strAge=textAge.getText();
			int age;
			int	phonenum;
			try {
				age=Integer.parseInt(strAge);
			
			try {				
				phonenum=Integer.parseInt(strNum);  
					try {
						
						if(idOverlap()) {
							JOptionPane.showMessageDialog(this, "���̵� �ߺ��˴ϴ�");
						
						//��ĭ�� �ִ��� Ȯ���ϴ� �κ�
						}else if(age<0){		    	
						    JOptionPane.showMessageDialog(this,"���̸� �ٽ� �Է����ּ���");
						    ageLabel.setText("����� �Է��� �ּ���");							
						}else if(id.equals("") || pwd.equals("") || name.equals("") || gender.equals(null)) {
							if(id.equals("")) {idLabel.setText("id�� �Է����ּ���");
							pwLabel.setText("");
							nameLabel.setText("");
							ageLabel.setText("");
							numLabel.setText("");
							}
							else if(pwd.equals("")) {pwLabel.setText("��й�ȣ�� �Է����ּ���");
							idLabel.setText("");
							nameLabel.setText("");
							ageLabel.setText("");
							numLabel.setText("");
							}
							else if(name.equals("")) {nameLabel.setText("�̸��� �Է����ּ���");
							idLabel.setText("");
							pwLabel.setText("");
							ageLabel.setText("");
							numLabel.setText("");							
							}							
							JOptionPane.showMessageDialog(this, "���� ���� �Է����ּ���");	//������ ������� ����ش�.							
						}else if(id.length()>15 || id.length()<4) {  //���̵� �ʹ���ų� ª���� Ȯ���ϱ�
							JOptionPane.showMessageDialog(this, "���̵�� 4~15���� ���̿����մϴ�.");
						}else {	//�����ǿ� ������ ���� ��쿡 DB�� ȸ���ڷ� ����ֱ�
							int result=dao.login_Insert(id, pwd, name,age, phonenum, gender);
							if(result==1) {
								JOptionPane.showMessageDialog(this, "ȸ������ �Ϸ�");
								dispose();
							}
						}
					}catch(Exception e3) {JOptionPane.showMessageDialog(this, "������ ������ �ּ���");}  //gender.equals(null)������ ����ó��//
			}catch(NumberFormatException nfe) {numLabel.setText("��ȭ��ȣ�� �Է��ϼ���"); 
				JOptionPane.showMessageDialog(this, "�������� �Է����ּ���");
				idLabel.setText("");
				pwLabel.setText("");
				nameLabel.setText("");			
				ageLabel.setText("");	
			} //���̿� �̻��Ѱ� �Ǵ� �����ϰ�� ����ó��
			
			}catch(NumberFormatException nfe2) {ageLabel.setText("���̸� �Է��ϼ���");
			JOptionPane.showMessageDialog(this, "�������� �Է����ּ���");//����ȣ�� �̻��Ѱ� �Ǵ� �����ϰ�� ������ ����ó��
			idLabel.setText("");
			pwLabel.setText("");
			nameLabel.setText("");
			numLabel.setText("");			
			}
		}else if(btn==btnOverlap) { //�ߺ�Ȯ�κκ�
			String id=textID.getText();
			if(idOverlap()) {
				JOptionPane.showMessageDialog(this, "�ߺ��� ���̵��Դϴ�.");
			}else if(id.length()>15 || id.length()<4) {  //���̵� �ʹ���ų� ª���� Ȯ���ϱ�
				JOptionPane.showMessageDialog(this, "���̵�� 4~15���� ���̿����մϴ�.");
			}else {
				JOptionPane.showMessageDialog(this, "��밡���� ���̵��Դϴ�.");
			}
		}else if(btn==btnCancle) {
			textID.setText("");
			textPWD.setText("");
			textName.setText("");
			textAge.setText("");
			textNum.setText("");
			idLabel.setText("");
			dispose();
		}
	}
	
	public boolean idOverlap() {
		Vector<String> vec =new Vector<>();
		try {
			vec=dao.getId();//id����Ʈ�� �޾ƿ���
			String ID=textID.getText(); //�����ϰ����ϴ� ���̵�
				for(int i=0;i<=vec.size();i++) {					
					String dbID=vec.get(i);//DB���ִ¾��̵�
					if(ID.equals(dbID)) { //db���ִ� ���̵�� �����ϰ����ϴ� ���̵�� ��
						return true;
					}
				}
		}catch(Exception e2) {idLabel.setText("��밡���� ���̵��Դϴ�.");}
		return false;
	}
}



