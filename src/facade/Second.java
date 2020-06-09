package facade;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tool.Gadget;
import core.Setting;

public class Second implements ActionListener{
	//���ڳߴ�(������Ϊ��λ)
	public static int winX=1080;
	public static int winY=390;
	
	//[���ڱ���]
	int screenX;
	int screenY;
	
	//���������б�����
	public int open=0;//��ǰ�򿪴��ڱ��
	
	//�����崰��ԭ����
	private JDialog dialog;
	private PaintS panel;//��������
	//[��������Ԫ��]
	public JPanel content;//��������
	public JButton[] button=new JButton[4];//���尴ť
	public JLabel[] text=new JLabel[22];//�����ǩ
	private JTextField[] field=new JTextField[12];//�����ı���
	//�����ı���
	public TextArea[] area=new TextArea[4];
	
	//����������
	public Second(JFrame container,int screenX,int screenY){
		//[����λ�ü���]
		this.screenX=screenX;
		this.screenY=screenY;
		int sitX=(screenX-winX)/2;                                      //���㴰�����ϽǺ�����
		int sitY=(screenY-winY)/2;                                     //���㴰�����Ͻ�������	
		
		dialog=new JDialog(container,"һ�Ż����������޸�",false);
		
		//[���崰������]
		panel=new PaintS();//�����������
		panel.setBorder(new EmptyBorder(0,0,0,0));//�������ı߿�
		dialog.setContentPane(panel);//Ӧ���������
		panel.setLayout(null);//��ղ��ֹ�����
		
		text[0]=new JLabel();
		text[0].setText("<html>" +
				"��2�Ż����ˡ�<br/>" +
				"����������������ϴ��ڵ�ÿ��ʤ���Ŀ����ԡ�����ʤ���Ŀ����Խ����ڼ������ӻ�з�����ʱ��������˫������ʱ���򲻿�����һ������ͨ����һ�����Ի�ʤ�������ݸÿ������ڵ�����Ϊ��ʤ���������ϵ�ÿ�������Ȩ�ء�<br/>" +
				"���������1�Ż����ˣ�������⿼�ǿ������ڵ����κ������ڸÿ����������λ�õ������<br/>" +
				"�������������������е��ʤ�������Ժ�ѡ��������Ȩ����ߵĵ����ӡ�<br/>" +
				"�����������������������Ȩ�����Ҳ���ʾ�������ֱ�����Ҳ��޸ģ������ȷ������ѡ���޸ĵ�2�Ż����ˣ���Ϳ��Ժ��Լ��޸�Ȩ�غ�Ļ����˶����ˡ�<br/>" +
				"PS:���򲻻��¼����޸ģ�����رպ����ý�����ʧ��" +
				"</html>");
		text[0].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[0].setBounds(10,10,500,330);                             //����Ԫ��λ��
		panel.add(text[0]);
		
		text[1]=new JLabel();
		text[1].setText("<html>û������Ȩ�أ�</html>");
		text[1].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[1].setBounds(520,10,170,30);                             //����Ԫ��λ��
		panel.add(text[1]);
		field[0]=new JTextField(Setting.medium_one+"",5);
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
		field[1]=new JTextField(Setting.medium_two+"",5);
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
		field[2]=new JTextField(Setting.medium_three+"",5);
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
		field[3]=new JTextField(Setting.medium_four+"",5);
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
		field[4]=new JTextField(Setting.medium_five+"",5);
		field[4].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[4].addActionListener(this);
		field[4].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[4].setBounds(690,170,80,30);                            //����Ԫ��λ��
		panel.add(field[4]);
		
		text[12]=new JLabel();
		text[12].setText("<html>����λ�ø���Ȩ��</html>");
		text[12].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[12].setBounds(840,10,180,30);                             //����Ԫ��λ��
		panel.add(text[12]);
		text[6]=new JLabel();
		text[6].setText("<html>����λ������λ�ã�</html>");
		text[6].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[6].setBounds(790,50,170,30);                             //����Ԫ��λ��
		panel.add(text[6]);
		field[5]=new JTextField(Setting.medium_third+"",5);
		field[5].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[5].addActionListener(this);
		field[5].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[5].setBounds(970,50,60,30);                            //����Ԫ��λ��
		panel.add(field[5]);
		text[7]=new JLabel();
		text[7].setText("<html>%</html>");
		text[7].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[7].setBounds(1040,50,30,30);                             //����Ԫ��λ��
		panel.add(text[7]);
		text[8]=new JLabel();
		text[8].setText("<html>����λ���ڲ�λ�ã�</html>");
		text[8].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[8].setBounds(790,90,170,30);                             //����Ԫ��λ��
		panel.add(text[8]);
		field[6]=new JTextField(Setting.medium_second+"",5);
		field[6].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[6].addActionListener(this);
		field[6].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[6].setBounds(970,90,60,30);                            //����Ԫ��λ��
		panel.add(field[6]);
		text[9]=new JLabel();
		text[9].setText("<html>%</html>");
		text[9].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[9].setBounds(1040,90,30,30);                             //����Ԫ��λ��
		panel.add(text[9]);
		text[10]=new JLabel();
		text[10].setText("<html>����λ���ڲ�λ�ã�</html>");
		text[10].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[10].setBounds(790,130,170,30);                             //����Ԫ��λ��
		panel.add(text[10]);
		field[7]=new JTextField(Setting.medium_first+"",5);
		field[7].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[7].addActionListener(this);
		field[7].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[7].setBounds(970,130,60,30);                            //����Ԫ��λ��
		panel.add(field[7]);
		text[11]=new JLabel();
		text[11].setText("<html>%</html>");
		text[11].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[11].setBounds(1040,130,30,30);                             //����Ԫ��λ��
		panel.add(text[11]);
		
		text[15]=new JLabel();
		text[15].setText("<html>�����������Ȩ��</html>");
		text[15].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[15].setBounds(840,170,180,30);                             //����Ԫ��λ��
		panel.add(text[15]);
		text[13]=new JLabel();
		text[13].setText("<html>����һ�������</html>");
		text[13].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[13].setBounds(790,210,170,30);                             //����Ԫ��λ��
		panel.add(text[13]);
		field[8]=new JTextField(Setting.medium_skip+"",5);
		field[8].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[8].addActionListener(this);
		field[8].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[8].setBounds(970,210,60,30);                            //����Ԫ��λ��
		panel.add(field[8]);
		text[14]=new JLabel();
		text[14].setText("<html>%</html>");
		text[14].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[14].setBounds(1040,210,30,30);                             //����Ԫ��λ��
		panel.add(text[14]);
		text[16]=new JLabel();
		text[16].setText("<html>�������������</html>");
		text[16].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[16].setBounds(790,250,170,30);                             //����Ԫ��λ��
		panel.add(text[16]);
		field[9]=new JTextField(Setting.medium_double+"",5);
		field[9].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[9].addActionListener(this);
		field[9].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[9].setBounds(970,250,60,30);                            //����Ԫ��λ��
		panel.add(field[9]);
		text[17]=new JLabel();
		text[17].setText("<html>%</html>");
		text[17].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[17].setBounds(1040,250,30,30);                             //����Ԫ��λ��
		panel.add(text[17]);
		text[18]=new JLabel();
		text[18].setText("<html>�������������</html>");
		text[18].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[18].setBounds(790,290,170,30);                             //����Ԫ��λ��
		panel.add(text[18]);
		field[10]=new JTextField(Setting.medium_touble+"",5);
		field[10].setFont(new Font("΢���ź�",Font.PLAIN,18));          //�����ı�������(΢���ź�18��)
		field[10].addActionListener(this);
		field[10].setHorizontalAlignment(JTextField.CENTER);             //�����ı�����뷽ʽ(JTextField.LEFT�����,JTextField.CENTER����,JTextField.RIGHT�Ҷ���)
		field[10].setBounds(970,290,60,30);                            //����Ԫ��λ��
		panel.add(field[10]);
		text[19]=new JLabel();
		text[19].setText("<html>%</html>");
		text[19].setFont(new Font("΢���ź�",Font.PLAIN,18));
		text[19].setBounds(1040,290,30,30);                             //����Ԫ��λ��
		panel.add(text[19]);
		
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
			Setting.medium_one=Gadget.toNum(field[0].getText());
			Setting.medium_two=Gadget.toNum(field[1].getText());
			Setting.medium_three=Gadget.toNum(field[2].getText());
			Setting.medium_four=Gadget.toNum(field[3].getText());
			Setting.medium_five=Gadget.toNum(field[4].getText());
			Setting.medium_third=Gadget.toNum(field[5].getText());
			Setting.medium_second=Gadget.toNum(field[6].getText());
			Setting.medium_first=Gadget.toNum(field[7].getText());
			Setting.medium_skip=Gadget.toNum(field[8].getText());
			Setting.medium_double=Gadget.toNum(field[9].getText());
			Setting.medium_touble=Gadget.toNum(field[10].getText());
			dialog.dispose();
		}
	}
}
