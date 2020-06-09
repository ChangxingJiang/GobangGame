package facade;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import core.Control;

public class Window extends JFrame implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID=1L;
	/**
	 * @名称：窗口消息处理器
	 * @功能
	 * [模块-窗口显示器]绘制窗口UI界面
	 * [模块-消息接收调配器]接受用户在界面上的鼠标操作消息，传递给对应模块
	 * [模块-基本操作实现]实现基本操作(窗口拖动/最小化/关闭)
	 */

	//【定义运行常量】
	//[显示器特征]显示器分辨率(以像素为单位)
	public static int screenX=0;
	public static int screenY=0;
	//[窗口特征]
	//窗口名称
	public static String name="五子棋人机对弈";
	//窗口尺寸(以像素为单位)
	public static int winX=810;
	public static int winY=920;
	//窗口左上角坐标(以像素为单位)
	public static int sitX=0;
	public static int sitY=0;

	//【定义窗口变量】
	//窗口图片绘制器(调用facade.Paint类)
	public Paint paint;

	//【初始化程序类】
	//核心控制器(调用core.Control类)
	Control control=new Control(this);

	/**
	 * @窗口显示器模块
	 * [运行]创建窗口对象时调用
	 * [功能]绘制窗口UI界面(读取屏幕分辨率、计算窗口位置、设置窗口图标、定义并绘制窗口)
	*/
	//【构造器】窗口显示器(窗口对象构造器)
	public Window(){
		//[读取屏幕分辨率]
		//初始化抽象超类Toolkit(用以读取显示器分辨率)
		Toolkit tool=getToolkit();
		//读取显示器分辨率到Dimension对象(dim.width为宽度,dim.height为高度)
		Dimension dim=tool.getScreenSize();
		screenX=dim.width;
		screenY=dim.height;

		//[窗口位置计算]居中放置
		//计算窗口左上角横坐标
		sitX=(screenX-winX)/ 2;
		//计算窗口左上角纵坐标
		sitY=(screenY-winY)/ 2;

		//[设置窗口图标]
		//使用tool类从图片(图标图)地址读取图片
		Image ico=tool.getImage("ico.png");
		//设置窗口图标
		setIconImage(ico);

		//[窗口尺寸/位置定义]
		//定义窗口名称(Win7显示在鼠标滑动到任务栏时显示的预览处)
		setTitle(name);
		//定义窗口尺寸(固定不变)
		setSize(winX,winY);
		//定义窗口左上角(随显示器分辨率改变)
		setLocation(sitX,sitY);

		//[窗口功能定义]
		//定义窗口去除标题栏(true为去除，false为保留)
		setUndecorated(true);
		//定义窗口扩展状态为默认
		setExtendedState(JFrame.NORMAL);
		//设置窗口为不可随意变换大小
		setResizable(false);

		//[窗口消息定义]
		//添加鼠标消息(使用MouseListener接口)
		addMouseListener(this);
		//添加鼠标移动消息(使用MouseMotionListener接口)
		addMouseMotionListener(this);

		//[初始化窗口图片绘制器]
		//创建内容面板
		paint=new Paint();
		//应用内容面板
		setContentPane(paint);
		//面板不使用布局管理器
		paint.setLayout(null);

		//[窗口重画]
		setVisible(true);
	}

	/**
	 * @消息接受调配器
	 * [运行]当窗口鼠标活动时自动产生消息运行各方法
	 */
	//【鼠标消息】鼠标按键在组件上按下并拖动
	public void mouseDragged(MouseEvent e){
		//[窗口拖动支持器]
		//读取拖动过程中的当前鼠标位置坐标
		site2=e.getPoint();
		//调用窗口拖动处理方法
		move();
	}

	//【鼠标消息】鼠标光标移动到组件上但无按键按下
	public void mouseMoved(MouseEvent e){

	}

	//【鼠标消息】鼠标按键在组件上单击(按下并释放)时调用
	public void mouseClicked(MouseEvent e){
		//[点击鼠标左键]
		if(e.getButton()== MouseEvent.BUTTON1){
			//获取鼠标点击位置坐标
			int x=e.getX();
			int y=e.getY();
			//向核心控制器转达消息(调用core.Control类)
			control.click(x,y);
			//检测关闭按钮点击情况
			close(x,y);
			//检测最小化按钮点击情况
			minimize(x,y);
		}
		//[点击鼠标滑轮]
		if(e.getButton()== MouseEvent.BUTTON2){
			//获取滚轮点击位置坐标
			int x=e.getX();
			int y=e.getY();
			//显示抓点结果
			System.out.println("滚轮坐标：x="+x+";y="+y);
		}
		//[点击鼠标右键]
		if(e.getButton()== MouseEvent.BUTTON3){
			//获取右键点击位置坐标
			int x=e.getX();
			int y=e.getY();
			//向核心控制器转达消息(调用core.Control类)
			control.right(x,y);
			//显示抓点结果
			System.out.println("右键坐标：x="+x+";y="+y);
		}
	}

	//【鼠标消息】鼠标进入到组件上
	public void mousePressed(MouseEvent e){
		//[窗口拖动支持器]
		//读取拖动过程前的鼠标位置坐标
		site1=e.getPoint();
	}

	//【鼠标消息】鼠标离开组件
	public void mouseReleased(MouseEvent e){
		//[窗口拖动支持器]
		//读取拖动过程中的当前鼠标位置坐标
		site2=e.getPoint();
		//调用窗口拖动处理方法
		move();
	}

	//【鼠标消息】鼠标按键在组件上按下
	public void mouseEntered(MouseEvent e){

	}

	//【鼠标消息】鼠标按钮在组件上释放
	public void mouseExited(MouseEvent e){

	}

	/**
	 * @窗口基本功能实现
	 * [运行]当用户在窗口做出使用窗口基本功能时，由消息接收调配器调用运行
	 * [功能]①窗口拖动②窗口最小化③窗口关闭
	 */
	//【基本功能】窗口拖动
	//[定义功能变量]
	Point site1,site2; //定义鼠标点坐标

	//[功能主程序]
	public void move(){
		//读取鼠标拖动起点的坐标
		int startX=site1.x;
		int startY=site1.y;
		//读取鼠标拖动终点的坐标
		int endX=site2.x;
		int endY=site2.y;
		//判断拖动起点是否在标题栏
		if(startY>=0&&startY<=40){
			//当横坐标发生变化时，将窗口左上角坐标作出对应修正
			if(startX!=endX){
				sitX=sitX+(endX-startX);
			}
			//当纵坐标发生变化时，将窗口右上角坐标作出对应修正
			if(startY!=endY){
				sitY=sitY+(endY-startY);
			}
			//重新定义窗口位置
			setLocation(sitX,sitY);
			//重新显示窗口
			setVisible(true);
		}
	}

	//【功能】窗口关闭
	public void close(int x,int y){
		//判断点击位置是否在关闭按钮上
		if(x>=769&&x<=800&&y>=5&&y<=35){
			//执行关闭程序操作
			System.exit(0);
		}
	}

	//【功能】窗口最小化
	public void minimize(int x,int y){
		//判断点击位置是否在最小化按钮上
		if(x>=731&&x<=762&&y>=5&&y<=35){
			//执行窗口最小化
			setExtendedState(JFrame.ICONIFIED);
		}
	}
}