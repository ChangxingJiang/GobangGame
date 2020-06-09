package facade;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.Control;
import core.Scene;


public class Paint extends JPanel{
	private static final long serialVersionUID=1L;
	/**
	*@名称：窗口图片绘制器
	*@功能：重写JPanel类的paintComponent方法(在每次绘制JPanel时都会调用绘制成分的方法)，实现在JPanel上绘制目标图片
	*@用法：将窗口内需要绘制的内容添加到此对象中，绘制的所有内容都相当于JPanel的背景图；将重写过的JPanel类(Paint类)作为窗口主容器
	*/

	//【重写】paintComponent方法
	//[运行]在运行该window类的repaint()时会自动运行paintComponent模块
	//[特点]后绘制的图片自动覆盖先绘制的图片
	public void paintComponent(Graphics g){
		//[绘制]界面背景图-最底层
		//从图片(背景图)地址读取图片
		Image back=new ImageIcon("img\\back.png").getImage();
		//绘制背景图
		g.drawImage(back,0,0,this);

		//[绘制]棋盘上所有棋子
		//从图片(黑棋图)地址读取图片
		Image black=new ImageIcon("img\\black.png").getImage();
		//从图片(白棋图)地址读取图片
		Image white=new ImageIcon("img\\white.png").getImage();
		//绘制棋盘上的棋子
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				//计算该棋盘坐标棋子的绘制坐标
				int x=13+i*52;
				int y=48+j*52;
				//绘制黑棋图
				if(Scene.board[i][j]==1){
					g.drawImage(black,x,y,this);
				}
				//绘制白棋图
				if(Scene.board[i][j]==2){
					g.drawImage(white,x,y,this);
				}
			}
		}

		//[绘制选择黑方/白方子窗口]
		if(Control.win_choose==true){
			//从图片(选择黑方/白方窗口图)地址读取图片
			Image choose=new ImageIcon("img\\choose.png").getImage();
			//绘制选择黑方/白方窗口图
			g.drawImage(choose,152,365,this);
		}
		
		//[绘制胜利页面]
		if(Control.win_winner==true){
			//如果胜利方为黑方
			if(Control.winner==1){
				//从图片(胜利窗口图)地址读取图片
				Image choose=new ImageIcon("img\\winBlack.png").getImage();
				//绘制胜利窗口图
				g.drawImage(choose,234,325,this);
			}
			else if(Control.winner==2){
				//从图片(胜利窗口图)地址读取图片
				Image choose=new ImageIcon("img\\winWhite.png").getImage();
				//绘制胜利窗口图
				g.drawImage(choose,234,325,this);
			}
		}
		
		//[绘制黑方禁手提示图]
		if(Control.forbid==true){
			//从图片(胜利窗口图)地址读取图片
			Image choose=new ImageIcon("img\\forbid.png").getImage();
			//绘制胜利窗口图
			g.drawImage(choose,10,325,this);
		}

		//[绘制选中机器人]
		//从图片(选中框)地址读取图片
		Image robot=new ImageIcon("img\\robot.png").getImage();
		if(Control.robot_type==1){
			//在1号机器人位置绘制选中框
			g.drawImage(robot,596,846,this);
		}else if(Control.robot_type==2){
			//在2号机器人位置绘制选中框
			g.drawImage(robot,666,846,this);
		}else if(Control.robot_type==3){
			//在3号机器人位置绘制选中框
			g.drawImage(robot,736,846,this);
		}
	}
}
