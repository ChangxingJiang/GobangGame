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
	//窗口尺寸(以像素为单位)
	public static int winX=1080;
	public static int winY=390;
	
	//[窗口变量]
	int screenX;
	int screenY;
	
	//【定义运行变量】
	public int open=0;//当前打开窗口编号
	
	//【定义窗口原件】
	private JDialog dialog;
	private PaintS panel;//定义容器
	//[定义内容元件]
	public JPanel content;//定义容器
	public JButton[] button=new JButton[4];//定义按钮
	public JLabel[] text=new JLabel[22];//定义标签
	private JTextField[] field=new JTextField[12];//定义文本框
	//定义文本区
	public TextArea[] area=new TextArea[4];
	
	//【构造器】
	public Second(JFrame container,int screenX,int screenY){
		//[窗口位置计算]
		this.screenX=screenX;
		this.screenY=screenY;
		int sitX=(screenX-winX)/2;                                      //计算窗口左上角横坐标
		int sitY=(screenY-winY)/2;                                     //计算窗口左上角纵坐标	
		
		dialog=new JDialog(container,"一号机器人属性修改",false);
		
		//[定义窗口容器]
		panel=new PaintS();//创建内容面板
		panel.setBorder(new EmptyBorder(0,0,0,0));//设置面板的边框
		dialog.setContentPane(panel);//应用内容面板
		panel.setLayout(null);//清空布局管理器
		
		text[0]=new JLabel();
		text[0].setText("<html>" +
				"【2号机器人】<br/>" +
				"　　它会分析棋盘上存在的每种胜利的可能性。当该胜利的可能性仅存在己方棋子或敌方棋子时（当存在双方棋子时，则不可能有一方可以通过这一可能性获胜），根据该可能性内的棋形为该胜利可能性上的每个点分配权重。<br/>" +
				"　　相比于1号机器人，它会额外考虑可能性内的棋形和棋子在该可能性内落点位置的情况。<br/>" +
				"　　分析完棋盘上所有点的胜利可能性后，选择棋盘上权重最高的点落子。<br/>" +
				"　　出现棋子数量所赋予的权重在右侧显示，你可以直接在右侧修改，并点击确定。再选择被修改的2号机器人，你就可以和自己修改权重后的机器人对弈了。<br/>" +
				"PS:程序不会记录你的修改，程序关闭后设置将会消失。" +
				"</html>");
		text[0].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[0].setBounds(10,10,500,330);                             //设置元件位置
		panel.add(text[0]);
		
		text[1]=new JLabel();
		text[1].setText("<html>没有棋子权重：</html>");
		text[1].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[1].setBounds(520,10,170,30);                             //设置元件位置
		panel.add(text[1]);
		field[0]=new JTextField(Setting.medium_one+"",5);
		field[0].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[0].addActionListener(this);
		field[0].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[0].setBounds(690,10,80,30);                            //设置元件位置
		panel.add(field[0]);
		text[2]=new JLabel();
		text[2].setText("<html>已有一颗棋子权重：</html>");
		text[2].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[2].setBounds(520,50,170,30);                             //设置元件位置
		panel.add(text[2]);
		field[1]=new JTextField(Setting.medium_two+"",5);
		field[1].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[1].addActionListener(this);
		field[1].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[1].setBounds(690,50,80,30);                            //设置元件位置
		panel.add(field[1]);
		text[3]=new JLabel();
		text[3].setText("<html>已有二颗棋子权重：</html>");
		text[3].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[3].setBounds(520,90,170,30);                             //设置元件位置
		panel.add(text[3]);
		field[2]=new JTextField(Setting.medium_three+"",5);
		field[2].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[2].addActionListener(this);
		field[2].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[2].setBounds(690,90,80,30);                            //设置元件位置
		panel.add(field[2]);
		text[4]=new JLabel();
		text[4].setText("<html>已有三颗棋子权重：</html>");
		text[4].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[4].setBounds(520,130,170,30);                             //设置元件位置
		panel.add(text[4]);
		field[3]=new JTextField(Setting.medium_four+"",5);
		field[3].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[3].addActionListener(this);
		field[3].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[3].setBounds(690,130,80,30);                            //设置元件位置
		panel.add(field[3]);
		text[5]=new JLabel();
		text[5].setText("<html>已有四颗棋子权重：</html>");
		text[5].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[5].setBounds(520,170,170,30);                             //设置元件位置
		panel.add(text[5]);
		field[4]=new JTextField(Setting.medium_five+"",5);
		field[4].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[4].addActionListener(this);
		field[4].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[4].setBounds(690,170,80,30);                            //设置元件位置
		panel.add(field[4]);
		
		text[12]=new JLabel();
		text[12].setText("<html>落子位置附加权重</html>");
		text[12].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[12].setBounds(840,10,180,30);                             //设置元件位置
		panel.add(text[12]);
		text[6]=new JLabel();
		text[6].setText("<html>棋子位于正中位置：</html>");
		text[6].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[6].setBounds(790,50,170,30);                             //设置元件位置
		panel.add(text[6]);
		field[5]=new JTextField(Setting.medium_third+"",5);
		field[5].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[5].addActionListener(this);
		field[5].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[5].setBounds(970,50,60,30);                            //设置元件位置
		panel.add(field[5]);
		text[7]=new JLabel();
		text[7].setText("<html>%</html>");
		text[7].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[7].setBounds(1040,50,30,30);                             //设置元件位置
		panel.add(text[7]);
		text[8]=new JLabel();
		text[8].setText("<html>棋子位于内部位置：</html>");
		text[8].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[8].setBounds(790,90,170,30);                             //设置元件位置
		panel.add(text[8]);
		field[6]=new JTextField(Setting.medium_second+"",5);
		field[6].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[6].addActionListener(this);
		field[6].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[6].setBounds(970,90,60,30);                            //设置元件位置
		panel.add(field[6]);
		text[9]=new JLabel();
		text[9].setText("<html>%</html>");
		text[9].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[9].setBounds(1040,90,30,30);                             //设置元件位置
		panel.add(text[9]);
		text[10]=new JLabel();
		text[10].setText("<html>棋子位于内部位置：</html>");
		text[10].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[10].setBounds(790,130,170,30);                             //设置元件位置
		panel.add(text[10]);
		field[7]=new JTextField(Setting.medium_first+"",5);
		field[7].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[7].addActionListener(this);
		field[7].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[7].setBounds(970,130,60,30);                            //设置元件位置
		panel.add(field[7]);
		text[11]=new JLabel();
		text[11].setText("<html>%</html>");
		text[11].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[11].setBounds(1040,130,30,30);                             //设置元件位置
		panel.add(text[11]);
		
		text[15]=new JLabel();
		text[15].setText("<html>棋形情况附加权重</html>");
		text[15].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[15].setBounds(840,170,180,30);                             //设置元件位置
		panel.add(text[15]);
		text[13]=new JLabel();
		text[13].setText("<html>存在一个间隔：</html>");
		text[13].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[13].setBounds(790,210,170,30);                             //设置元件位置
		panel.add(text[13]);
		field[8]=new JTextField(Setting.medium_skip+"",5);
		field[8].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[8].addActionListener(this);
		field[8].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[8].setBounds(970,210,60,30);                            //设置元件位置
		panel.add(field[8]);
		text[14]=new JLabel();
		text[14].setText("<html>%</html>");
		text[14].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[14].setBounds(1040,210,30,30);                             //设置元件位置
		panel.add(text[14]);
		text[16]=new JLabel();
		text[16].setText("<html>存在两个间隔：</html>");
		text[16].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[16].setBounds(790,250,170,30);                             //设置元件位置
		panel.add(text[16]);
		field[9]=new JTextField(Setting.medium_double+"",5);
		field[9].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[9].addActionListener(this);
		field[9].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[9].setBounds(970,250,60,30);                            //设置元件位置
		panel.add(field[9]);
		text[17]=new JLabel();
		text[17].setText("<html>%</html>");
		text[17].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[17].setBounds(1040,250,30,30);                             //设置元件位置
		panel.add(text[17]);
		text[18]=new JLabel();
		text[18].setText("<html>存在三个间隔：</html>");
		text[18].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[18].setBounds(790,290,170,30);                             //设置元件位置
		panel.add(text[18]);
		field[10]=new JTextField(Setting.medium_touble+"",5);
		field[10].setFont(new Font("微软雅黑",Font.PLAIN,18));          //设置文本区字体(微软雅黑18号)
		field[10].addActionListener(this);
		field[10].setHorizontalAlignment(JTextField.CENTER);             //设置文本框对齐方式(JTextField.LEFT左对齐,JTextField.CENTER居中,JTextField.RIGHT右对齐)
		field[10].setBounds(970,290,60,30);                            //设置元件位置
		panel.add(field[10]);
		text[19]=new JLabel();
		text[19].setText("<html>%</html>");
		text[19].setFont(new Font("微软雅黑",Font.PLAIN,18));
		text[19].setBounds(1040,290,30,30);                             //设置元件位置
		panel.add(text[19]);
		
		button[0]=new JButton("确定");                                //定义按钮
		button[0].setFont(new Font("微软雅黑",Font.PLAIN,18)); 
		button[0].addActionListener(this);
		button[0].setBounds(600,210,90,30);                            //设置元件位置
		panel.add(button[0]);
		
		//[窗口重画]
		panel.setVisible(true);
		dialog.setVisible(true);
		
		//[窗口绘制]
		dialog.pack();
		dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.setBounds(sitX,sitY,winX,winY);
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//窗口关闭按钮生效，仅关闭弹窗
	}

	//消息接收器
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
