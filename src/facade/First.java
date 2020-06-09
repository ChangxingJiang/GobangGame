package facade;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tool.Gadget;
import core.Setting;

public class First implements ActionListener{
	//���ڳߴ�(������Ϊ��λ)
	public static int winX=800;
	public static int winY=340;
	
	//[���ڱ���]
	int screenX;
	int screenY;
	
	//���������б�����
	public int open=0;//��ǰ�򿪴��ڱ��
	
	//�����崰��ԭ����
	private JDialog dialog;
	private PaintF panel;//��������
	//[��������Ԫ��]
	public JPanel content;//��������
	public JButton[] button=new JButton[4];//���尴ť
	public JLabel[] text=new JLabel[6];//�����ǩ
	public JCheckBox[] check=new JCheckBox[4];//�������ѡ��ť
	private JTextField[] field=new JTextField[5];//�����ı���
	//�����ı���
	public TextArea[] area=new TextArea[4];
	
	//����������
	public First(JFrame container,int screenX,int screenY){
		//[����λ�ü���]
		this.screenX=screenX;
		this.screenY=screenY;
		int sitX=(screenX-winX)/2;                                      //���㴰�����ϽǺ�����
		int sitY=(screenY-winY)/2;                                     //���㴰�����Ͻ�������	
		
		dialog=new JDialog(container,"һ�Ż����������޸�",false);
		
		//[���崰������]
		panel=new PaintF();//�����������
		panel.setBorder(new EmptyBorder(0,0,0,0));//�������ı߿�
		dialog.setContentPane(panel);//Ӧ���������
		panel.setLayout(null);//��ղ��ֹ�����
		
		text[0]=new JLabel();
		text[0].setText("<html>" +
				"��1�Ż����ˡ�<br/>" +
				"����������������ϴ��ڵ�ÿ��ʤ���Ŀ����ԡ�����ʤ���Ŀ����Խ����ڼ������ӻ�з�����ʱ��������˫������ʱ���򲻿�����һ������ͨ����һ�����Ի�ʤ����������������Ϊ��ʤ���������ϵ�ÿ�������Ȩ�ء�<br/>" +
				"�������������������е��ʤ�������Ժ�ѡ��������Ȩ����ߵĵ����ӡ�<br/>" +
				"�����������������������Ȩ�����Ҳ���ʾ�������ֱ�����Ҳ��޸ģ������ȷ������ѡ���޸ĵ�1�Ż����ˣ���Ϳ��Ժ��Լ��޸�Ȩ�غ�Ļ����˶����ˡ�<br/>" +
				"PS:���򲻻��¼����޸ģ�����رպ����ý�����ʧ��" +
				"</html>");
		text[0].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[0].setBounds(10,10,500,300);                             //����Ԫ��λ��
		panel.add(text[0]);
		
		text[1]=new JLabel();
		text[1].setText("<html>�����������Ȩ�أ�</html>");
		text[1].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[1].setBounds(520,10,170,30);                             //����Ԫ��λ��
		panel.add(text[1]);
		field[0]=new JTextField(Setting.primary_one+"",5);
		field[0].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[0].addActionListener(this);
		field[0].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[0].setBounds(690,10,80,30);                            //����Ԫ��λ��
		panel.add(field[0]);
		text[2]=new JLabel();
		text[2].setText("<html>����һ������Ȩ�أ�</html>");
		text[2].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[2].setBounds(520,50,170,30);                             //����Ԫ��λ��
		panel.add(text[2]);
		field[1]=new JTextField(Setting.primary_two+"",5);
		field[1].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[1].addActionListener(this);
		field[1].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[1].setBounds(690,50,80,30);                            //����Ԫ��λ��
		panel.add(field[1]);
		text[3]=new JLabel();
		text[3].setText("<html>���ж�������Ȩ�أ�</html>");
		text[3].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[3].setBounds(520,90,170,30);                             //����Ԫ��λ��
		panel.add(text[3]);
		field[2]=new JTextField(Setting.primary_three+"",5);
		field[2].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[2].addActionListener(this);
		field[2].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[2].setBounds(690,90,80,30);                            //����Ԫ��λ��
		panel.add(field[2]);
		text[4]=new JLabel();
		text[4].setText("<html>������������Ȩ�أ�</html>");
		text[4].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[4].setBounds(520,130,170,30);                             //����Ԫ��λ��
		panel.add(text[4]);
		field[3]=new JTextField(Setting.primary_four+"",5);
		field[3].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[3].addActionListener(this);
		field[3].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[3].setBounds(690,130,80,30);                            //����Ԫ��λ��
		panel.add(field[3]);
		text[5]=new JLabel();
		text[5].setText("<html>�����Ŀ�����Ȩ�أ�</html>");
		text[5].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[5].setBounds(520,170,170,30);                             //����Ԫ��λ��
		panel.add(text[5]);
		field[4]=new JTextField(Setting.primary_five+"",5);
		field[4].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[4].addActionListener(this);
		field[4].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[4].setBounds(690,170,80,30);                            //����Ԫ��λ��
		panel.add(field[4]);
		
		button[0]=new JButton("ȷ��");                                //���尴ť
		button[0].setFont(new Font("΢���ź�",Font.PLAIN,18)); 
		button[0].addActionListener(this);
		button[0].setBounds(600,210,90,30);                            //����Ԫ��λ��
		panel.add(button[0]);
		
		//[�����ػ�]
		panel.setVisible(true);
		dialog.setVisible(true);
		
		//[���ڻ���]
		dialog.pack();
		dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.setBounds(sitX,sitY,winX,winY);
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//���ڹرհ�ť��Ч�����رյ���
	}

	//��Ϣ������
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button[0]){
			Setting.primary_one=Gadget.toNum(field[0].getText());
			Setting.primary_two=Gadget.toNum(field[1].getText());
			Setting.primary_three=Gadget.toNum(field[2].getText());
			Setting.primary_four=Gadget.toNum(field[3].getText());
			Setting.primary_five=Gadget.toNum(field[4].getText());
			dialog.dispose();
		}
	}
}
