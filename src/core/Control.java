package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import robot.Medium;
import robot.Possible;
import robot.Primary;
import facade.First;
import facade.Second;
import facade.Window;

public class Control implements ActionListener{
	/**
	 * @名称：核心控制类
	 * @功能：处理消息接收调配器传递的消息并实现对应的功能
	 */
	
	//【定义窗口相关变量】
	//[窗口对象变量](调用facade.Window类)
	Window window;
	//[打开窗口情况]
	//选择黑方/白方窗口
	public static boolean win_choose=false;
	//胜利窗口
	public static boolean win_winner=false;

	//【定义控制变量】
	//对局状态变量(true为开始,false为没开始)
	public static boolean start=false;
	//选中的机器人类型
	public static int robot_type=3;
	//机器人落子命令
	public static boolean robot=false;
	//胜利者
	public static int winner=0;
	//禁手
	public static boolean forbid=false;
	
	//【定义定时器】
	//定时器变量(调用Timer类)
	Timer time=new Timer(20,this);
	//循环次数计数器
	int times=0;
	
	//【构造器】获取窗口对象构造器
	public Control(Window window){
		//转存程序窗口对象
		this.window=window;
		//启动计时器
		time.start();
	}
	
	//【消息处理】左键点击
	public void click(int winX,int winY){
		//优先处理:选择黑方/白方窗口
		//判断“选择黑方/白方窗口”是否打开
		if(win_choose==true){
			//[按钮判断]选择黑方
			if(winX>=154&&winX<=346&&winY>=392&&winY<=499){
				//设置用户选择为黑方
				Scene.user=1;
				//设置“选择黑方/白方窗口”为关闭
				win_choose=false;
				//设置对局状态变量为开始
				start=true;
				//窗口内重画
				window.repaint();
			}
			//[按钮判断]选择白方
			if(winX>=456&&winX<=657&&winY>=392&&winY<=499){
				//设置用户选择为白方
				Scene.user=2;
				//设置“选择黑方/白方窗口”为关闭
				win_choose=false;
				//设置对局状态变量为开始
				start=true;
				//窗口内重画
				window.repaint();
				//电脑执行一次落子命令
				robotGo();
			}
			//弹出窗体与其他点击有效区有重叠部分，若有窗体则不再查询其他按钮
			return;
		}
		
		//[落子判断]
		//判断点击位置是否在棋盘内
		if(winX>=13&&winX<=793&&winY>=48&&winY<=828){
			//计算落子屏幕坐标对应的棋盘坐标
			int boardX=(winX-13)/52;
			int boardY=(winY-48)/52;
			//执行一次落子操作
			move(boardX,boardY);
		}
		
		//[按钮判断]开始按钮
		if(winX>=18&&winX<=166&&winY>=850&&winY<=909){
			//清空棋盘内容
			Scene.clear();
			//清空棋谱内容
			Record.clear();
			//设置“选择黑方/白方窗口”为打开
			win_choose=true;
			//设置胜利窗口为关闭
			win_winner=false;
			winner=0;
			//窗口内重画
			window.repaint();
		}
		
		//[按钮判断]悔棋按钮
		if(winX>=188&&winX<=345&&winY>=850&&winY<=909){
			//执行悔棋操作
			regret();
		}
		
		//[按钮判断]机器人选择-机器人1
		if(winX>=600&&winX<=659&&winY>=850&&winY<=909){
			//设置选中的机器人类型为1
			robot_type=1;
			//窗口内重画
			window.repaint();
		}
		
		//[按钮判断]机器人选择-机器人2
		if(winX>=670&&winX<=729&&winY>=850&&winY<=909){
			//设置选中的机器人类型为2
			robot_type=2;
			//窗口内重画
			window.repaint();
		}
		
		//[按钮判断]机器人选择-机器人3
		if(winX>=740&&winX<=799&&winY>=850&&winY<=909){
			//设置选中的机器人类型为2
			robot_type=3;
			//窗口内重画
			window.repaint();
		}
		
		//[判断当前是否该由电脑落子]
		if(Scene.color!=Scene.user){
			//机器人执行一次落子操作
			robot=true;
			times=0;
		}
	}
	
	//【消息处理】右键点击
	public void right(int winX,int winY){
		//[按钮判断]机器人设置-机器人1
		if(winX>=600&&winX<=659&&winY>=850&&winY<=909){
			new First(window,Window.screenX,Window.screenY);
		}
		//[按钮判断]机器人设置-机器人2
		if(winX>=670&&winX<=729&&winY>=850&&winY<=909){
			new Second(window,Window.screenX,Window.screenY);
		}
	}
	
	//【时间消息接收器】
	public void actionPerformed(ActionEvent e){
		//消息来源为时间消息
		if(e.getSource()==time){
			//时间消息计数累加
			times++;
			//机器落子命令接收
			if(robot==true&&times>10){
				robotGo();
			}
			//机器落子命令接收
			if(winner!=0&&times>50){
				//窗口内重画
				window.repaint();
			}
			if(forbid==true&&times>100){
				forbid=false;
				//窗口内重画
				window.repaint();
			}
        }
	}
	
	//【操作】机器人执行一次落子命令
	public void robotGo(){
		robot=false;
		//[当选中的机器人类型为1的情况]
		if(robot_type==1){
			//使用机器人1分析下棋位置
			int site[]=Primary.go();
			//执行落子操作
			move(site[0],site[1]);
		}
		
		//[当选中的机器人类型为2的情况]
		else if(robot_type==2){
			//使用机器人2分析下棋位置
			int site[]=Medium.mediumGo();
			//执行落子操作
			move(site[0],site[1]);
		}
		//[当选中的机器人类型为3的情况]
		else if(robot_type==3){
			//使用机器人2分析下棋位置
			int site[]=Possible.go();
			//执行落子操作
			move(site[0],site[1]);
		}
	}
	
	//【操作】落子操作
	public boolean move(int boardX,int boardY){
		//[判断对局状态变量是否为开始]
		if(start==false){
			return false;
		}
		
		//[判断落子位置是否禁手]
		if(Forbid.forbid(boardX,boardY,Scene.color)){
			//写入该手到棋谱中
			Record.add(boardX,boardY);
			//执行落子命令操作
			if(Scene.move(boardX,boardY)){
				//窗口内重画
				window.repaint();
				//判断是否出现胜者(调用core.Winner类)
				if(Winner.winner()){
					//自动存储棋谱
					Record.save();
					times=0;
					//当胜者为黑方的情况
					if(Winner.winner==1){
						//设置胜者
						winner=1;
						//设置胜利者窗口为打开
						win_winner=true;
					}
					//当胜者为白方的情况
					if(Winner.winner==2){
						//设置胜者
						winner=2;
						//设置胜利者窗口为打开
						win_winner=true;
					}
					//设置对局状态变量为结束
					start=false;
				}
				return true;
			}
			else{
				return false;
			}
		}
		else{
			forbid=true;
			times=0;
			//窗口内重画
			window.repaint();
			System.out.println("黑棋禁手，不能下在这个位置");
			return false;
		}
	}
	
	//【操作】悔棋操作
	public boolean regret(){
		//棋谱删除两颗棋子
		Record.delete();
		Record.delete();
		//设置胜利窗口为关闭
		win_winner=false;
		winner=0;
		//[执行悔棋操作 ]
		if(Scene.regret()){
			//窗口内重画
			window.repaint();
			return true;
		}
		else{
			return false;
		}
	}
}
