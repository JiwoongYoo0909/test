package javaTeam;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Find extends JFrame {
	//���̵�� ��й�ȣã��
	private JPanel contentPane;
	private JButton btnIdFind,btnPwFind;
	
	public Find() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x��ư�������� �α���ȭ�����ΰ���
		setTitle("\uC544\uC774\uB514/\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514/\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnIdFind = new JButton("���̵�� ã��");
		panel_1.add(btnIdFind);
		
		btnPwFind = new JButton("��й�ȣ�� ã��");
		btnPwFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnPwFind);
		pack();
		btnIdFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				FindId id=new FindId();
				id.setVisible(true);
			}
		});
		btnPwFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				FindPw pw=new FindPw();
				pw.setVisible(true);
			}
		});
	}
	
	//���̵�ã�� ��
	class FindId extends JFrame implements ActionListener{
		private JPanel contentPane;
		private JButton btnFind,btnCancel;
		private JLabel mainLabel,nameLabel,numLabel;
		private JTextField nameText,numText;
		public FindId() {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x��ư�������� �α���ȭ�����ΰ���
			setTitle("���̵�ã��");
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			mainLabel=new JLabel("���̵� ã��");
			panel.add(mainLabel);
			
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(new GridLayout(0, 2, 0, 0));
			contentPane.add(centerPanel,BorderLayout.CENTER);		
			nameLabel=new JLabel("�̸�");
			nameText=new JTextField();
			nameText.setColumns(20);
			numLabel=new JLabel("�ڵ�����ȣ");
			numText=new JTextField();
			numText.setColumns(20);
			centerPanel.add(nameLabel);
			centerPanel.add(nameText);
			centerPanel.add(numLabel);
			centerPanel.add(numText);
			
			JPanel southPanel=new JPanel();
			contentPane.add(southPanel,BorderLayout.SOUTH);
			btnFind=new JButton("ã��");
			btnCancel=new JButton("���");
			southPanel.add(btnFind);
			southPanel.add(btnCancel);
			pack(); //UI���� ��
			btnFind.addActionListener(this);
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose(); //��ҹ�ư 
				}
			});
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//ã���ϴ� �κ� �Էµ� �̸��� �ڵ��� ��ȣ�� dB���ִ°͵��� �����ϴ� ��
			String name=nameText.getText();//�Է��� �̸�
			String strnum=numText.getText();//�Է��� �ڵ��� ��ȣ
			String id=null;
			int num=0;
			try {
				num=Integer.parseInt(strnum);
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "���ڸ� �Է��� �ּ���");//����ó��
				nameText.selectAll();
				}
			LoginDAO dao=new LoginDAO();
			LoginVO vo=new LoginVO();
			Vector<LoginVO> vec=new Vector<>();
			vec=dao.userlist();//DB���� �޾ƿ���
			for(int i=0;i<vec.size();i++) {
				vo=vec.get(i);
					if(vo.getName().equals(name) && vo.getPhonenum()==num) { //���ϴ� �κ�
						id=vo.getId();
						JOptionPane.showMessageDialog(this, "����� ���̵�� : "+id+" �Դϴ�");
						dispose();
						break;
					}else {
						JOptionPane.showMessageDialog(this, "�̸� Ȥ�� ��ȭ��ȣ�� ��Ȯ�� �Է��� �ּ���");
						break;
					}
			}
		}	
	}
	//��й�ȣã�� �� �����ߵ�
	class FindPw extends JFrame implements ActionListener{
		private JPanel contentPane;
		private JButton btnFind,btnCancel;
		private JLabel mainLabel,nameLabel,idLabel;
		private JTextField nameText,idText;
		
		public FindPw() {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x��ư�������� �α���ȭ�����ΰ���
			setTitle("��й�ȣã��");
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			mainLabel=new JLabel("��й�ȣ ã��");
			panel.add(mainLabel);
			
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(new GridLayout(0, 2, 0, 0));
			contentPane.add(centerPanel,BorderLayout.CENTER);		
			nameLabel=new JLabel("�̸�");
			nameText=new JTextField();
			nameText.setColumns(20);
			idLabel=new JLabel("���̵�");
			idText=new JTextField();
			idText.setColumns(20);
			centerPanel.add(nameLabel);
			centerPanel.add(nameText);
			centerPanel.add(idLabel);
			centerPanel.add(idText);

			JPanel southPanel=new JPanel();
			contentPane.add(southPanel,BorderLayout.SOUTH);
			btnFind=new JButton("ã��");
			btnCancel=new JButton("���");
			southPanel.add(btnFind);
			southPanel.add(btnCancel);
			pack(); //UI���� ��
			btnFind.addActionListener(this);
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose(); //��ҹ�ư 
				}
			});
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//ã���ϴ� �κ� �Էµ� �̸��� �ڵ��� ��ȣ�� dB���ִ°͵��� �����ϴ� ��
			String name=nameText.getText();//�Է��� �̸�
			String strId=idText.getText();//�Է��� �ڵ��� ��ȣ
			String pwd=null;
			
			LoginDAO dao=new LoginDAO();
			LoginVO vo=new LoginVO();
			Vector<LoginVO> vec=new Vector<>();
			vec=dao.userlist();//DB���� �޾ƿ���
			for(int i=0;i<vec.size();i++) {
				vo=vec.get(i);
					if(vo.getName().equals(name) && vo.getId().equals(strId)) { //���ϴ� �κ�
						pwd=vo.getPwd();
						JOptionPane.showMessageDialog(this, "����� ��й�ȣ�� : "+pwd+" �Դϴ�");
						dispose();
						break;
					}else {
						JOptionPane.showMessageDialog(this, "�̸� Ȥ�� ���̵� ��Ȯ�� �Է��� �ּ���");
						break;
					}
			}
		}	
	}
}



