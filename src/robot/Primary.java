package robot;

import core.Forbid;
import core.Scene;
import core.Setting;

public class Primary{
	/**机器人名：小白(电脑初级)
	 * [评分系统]根据每一个胜利可能已有的己方和对方子数计算各点评分
	 * 一子:20
	 * 二子:400
	 * 三子:6000
	 * 四子:20000
	 * 五子:100000
	 * [选择系统]同样评分选择第一个找到的位置
	 * [学习能力]无
	 */
	//【定义各点评分变量】
	public static int mark[][]=new int[15][15];//每个点的评分
	//自动初始化评分表
	static{
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
	}
	
	//【申请一次分析计算】
	public static int[] go(){
		//[清空计算表]
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
		//[计算水平方向评分]
		for(int i=0;i<15;i++){
			for(int j=0;j<11;j++){
				String five=new String("");
				//计算该条胜利可能性的评分
				int input=0;
				for(int k=0;k<5;k++){
					if(Scene.board[j+k][i]!=0){
						if(Scene.color==Scene.board[j+k][i]){
							input=1;
						}
						else{
							input=2;
						}
					}
					five=five+input;
					input=0;
				}
				//将评分导入评分表
				for(int k=0;k<5;k++){
					if(Scene.board[j+k][i]==0){
						String temp=five.substring(0,k)+"3"+five.substring(k+1);
						int weight=Primary.ask(temp);
						mark[j+k][i]=mark[j+k][i]+weight;
					}
				}
			}
		}
		//[计算垂直方向评分]
		for(int i=0;i<15;i++){
			for(int j=0;j<11;j++){
				String five=new String("");
				//计算该条胜利可能性的评分
				int input=0;
				for(int k=0;k<5;k++){
					if(Scene.board[i][j+k]!=0){
						if(Scene.color==Scene.board[i][j+k]){
							input=1;
						}
						else{
							input=2;
						}
					}
					five=five+input;
					input=0;
				}
				//将评分导入评分表
				for(int k=0;k<5;k++){
					if(Scene.board[i][j+k]==0){
						String temp=five.substring(0,k)+"3"+five.substring(k+1);
						int weight=Primary.ask(temp);
						mark[i][j+k]=mark[i][j+k]+weight;
					}
				}
			}
		}
		//[计算左上-右下方向评分]
		for(int i=0;i<29;i++){
			//[计算该斜列棋子数量、第一颗棋子坐标位置]
			int num=0;//斜列棋子数量
			int x=0;//斜列首x坐标
			int y=0;//斜列首y坐标
			if(i<=14){
				num=i+1;
				x=0;
				y=14-i;
			}
			else{
				num=29-i;
				x=i-14;
				y=0;
			}
			//[计算是否有获胜可能]
			if(num>5){
				for(int j=0;j<num-4;j++){
					String five=new String("");
					//计算该条胜利可能性的评分
					int input=0;
					for(int k=0;k<5;k++){
						if(Scene.board[x+j+k][y+j+k]!=0){
							if(Scene.color==Scene.board[x+j+k][y+j+k]){
								input=1;
							}
							else{
								input=2;
							}
						}
						five=five+input;
						input=0;
					}
					//将评分导入评分表
					for(int k=0;k<5;k++){
						if(Scene.board[x+j+k][y+j+k]==0){
							String temp=five.substring(0,k)+"3"+five.substring(k+1);
							int weight=Primary.ask(temp);
							mark[x+j+k][y+j+k]=mark[x+j+k][y+j+k]+weight;
						}
					}
				}
			}
		}
		//[计算右上-左下方向评分]
		for(int i=0;i<29;i++){
			//[计算该斜列棋子数量、第一颗棋子坐标位置]
			int num=0;//斜列棋子数量
			int x=0;//斜列首x坐标
			int y=0;//斜列首y坐标
			if(i<=14){
				num=i+1;
				x=i;
				y=0;
			}
			else{
				num=29-i;
				x=14;
				y=i-14;
			}
			//[计算是否有获胜可能]
			if(num>5){
				for(int j=0;j<num-4;j++){
					String five=new String("");
					//计算该条胜利可能性的评分
					int input=0;
					for(int k=0;k<5;k++){
						if(Scene.board[x-j-k][y+j+k]!=0){
							if(Scene.color==Scene.board[x-j-k][y+j+k]){
								input=1;
							}
							else{
								input=2;
							}
						}
						five=five+input;
						input=0;
					}
					//将评分导入评分表
					for(int k=0;k<5;k++){
						if(Scene.board[x-j-k][y+j+k]==0){
							String temp=five.substring(0,k)+"3"+five.substring(k+1);
							int weight=Primary.ask(temp);
							mark[x-j-k][y+j+k]=mark[x-j-k][y+j+k]+weight;
						}
					}
				}
			}
		}
		//[获取全盘评分最高位置]
		int nowX=0,nowY=0,nowNum=0;
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(mark[i][j]>nowNum){
					if(Forbid.forbid(i,j,Scene.color)){
						nowNum=mark[i][j];
						nowX=i;
						nowY=j;
					}
				}
				if(mark[i][j]==nowNum){
					//计算两者与棋盘中心的距离
					int nowXab=0,nowYab=0;    //定义绝对值变量
					int Xab=0,Yab=0;    //定义绝对值变量
					if(nowX>=7){
						nowXab=nowX-7;
					}else{
						nowXab=7-nowX;
					}
					if(nowY>=7){
						nowYab=nowY-7;
					}else{
						nowYab=7-nowY;
					}
					if(i>=7){
						Xab=i-7;
					}else{
						Xab=7-i;
					}
					if(j>=7){
						Yab=j-7;
					}else{
						Yab=7-j;
					}
					//判断两者与棋盘中心的距离
					if((Xab+Yab)<(nowXab+nowYab)){
						if(Forbid.forbid(i,j,Scene.color)){
							nowNum=mark[i][j];
							nowX=i;
							nowY=j;
						}
					}
				}
			}
		}
		System.out.println("选择点权重:"+nowNum);
		//[定义返回值]
		int site[]=new int[2];
		site[0]=nowX;
		site[1]=nowY;
		return site;
	}
	
	//【计算评分】
	public static int ask(String five){
		five=five.replace("3","0");
		//[处理有黑棋、有白棋的情况]
		if(five.indexOf("1")!=-1&&five.indexOf("2")!=-1){
			return 0;
		}
		//[处理没有棋子的情况]
		if(five.indexOf("1")==-1&&five.indexOf("2")==-1){
			return 1;
		}
		int count=1;
		//[处理只有己方和空格的情况]
		if(five.indexOf("1")!=-1){
			for(int i=0;i<5;i++){
				if(five.indexOf("1")!=-1){
					count++;
					five=five.substring(five.indexOf("1")+1);
				}else{
					break;
				}
			}
		}
		//[处理只有对手和空格的情况]
		if(five.indexOf("2")!=-1){
			for(int i=0;i<5;i++){
				if(five.indexOf("2")!=-1){
					count++;
					five=five.substring(five.indexOf("2")+1);
				}else{
					break;
				}
			}
		}
		if(count==1){
			return Setting.primary_one;
		}
		if(count==2){
			return Setting.primary_two;
		}
		if(count==3){
			return Setting.primary_three;
		}
		if(count==4){
			return Setting.primary_four;
		}
		if(count==5){
			return Setting.primary_five;
		}
		return 0;
	}
}
