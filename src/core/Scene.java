package core;

public class Scene{
	
	//【定义当前局势变量】
	public static int color=1;//1为该黑方，2为该白方
	public static int board[][]=new int[15][15];//0为空白，1为黑棋，2为白棋
	public static int user=1;//用户选择黑棋/白棋
	
	//【定义悔棋支持变量】
	public static int lastX_uesr=-1;
	public static int lastY_uesr=-1;
	public static int lastX_robot=-1;
	public static int lastY_robot=-1;
	
	//【变量初始化】
	static{
		clear();
	}
	
	//【清空棋盘内容】
	public static void clear(){
		//改为该黑方下子
		color=1;
		//清空棋盘上棋子
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				board[i][j]=0;
			}
		}
	}
	
	//【落子】执行一次落子操作
	public static boolean move(int boardX,int boardY){
		if(boardX>=0&&boardX<=14&&boardY>=0&&boardY<=14){
			//[判断该位置是否已有棋子]
			if(board[boardX][boardY]!=0){
				return false;
			}
			if(color==1){
				if(user==1){
					lastX_uesr=boardX;
					lastY_uesr=boardY;
				}
				else{
					lastX_robot=boardX;
					lastY_robot=boardY;
				}
				board[boardX][boardY]=1;
				color=2;
				return true;
			}
			else{
				if(user==2){
					lastX_uesr=boardX;
					lastY_uesr=boardY;
				}
				else{
					lastX_robot=boardX;
					lastY_robot=boardY;
				}
				board[boardX][boardY]=2;
				color=1;
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	
	//【操作】执行一次悔棋操作
	public static boolean regret(){
		//[判断悔棋记录是否已被使用]
		if(lastX_uesr==-1||lastY_uesr==-1||lastX_robot==-1||lastY_robot==-1){
			return false;
		}
		//[移除棋盘上被悔棋的棋子]
		board[lastX_uesr][lastY_uesr]=0;
		board[lastX_robot][lastY_robot]=0;
		//[清除悔棋记录]
		lastX_uesr=-1;
		lastY_uesr=-1;
		lastX_robot=-1;
		lastY_robot=-1;
		//[如果已经结束则重新命令开始]
		if(Control.start==false){
			Control.start=true;
		}
		return true;
	}
}
