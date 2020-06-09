package robot;

import core.Forbid;
import core.Scene;

public class Possible{
	/**
	 * @名称：可能性算法机器人
	 * @原理：获取棋盘内所有胜利可能，通过分析该可能所包含的己方和对方棋子数量， 对该可能的所有点赋予权重；通过比较权重，作出决定
	 * @原则：
	 * ①同样子数情况下，己方优先于敌方
	 * ②1-3子间的权重差异较小(模糊)，3-5子间的权重差异大(明显)
	 * @具体数值(数值为下一步落子后形成的)
	 * [己方>0;对方>0]权重赋值:0
	 * [己方=0;对方=0]权重赋值:1
	 * [己方=1;对方=0]权重赋值:2
	 * [己方=2;对方=0]权重赋值:4
	 * [己方=3;对方=0]权重赋值:8
	 * [己方=4;对方=0]权重赋值:32
	 * [己方=5;对方=0]权重赋值:512
	 * [己方=0;对方=1]权重赋值:2
	 * [己方=0;对方=2]权重赋值:3
	 * [己方=0;对方=3]权重赋值:6
	 * [己方=0;对方=4]权重赋值:32
	 * [己方=0;对方=5]权重赋值:512
	 */
	
	//【定义权重存储变量】
	public static int mark[][]=new int[15][15];//每个点的评分
	
	//【执行一次计算请求】
	public static int[] go(){
		//清空权重存储变量
		clear();
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
						int weight=ask(temp);
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
						int weight=ask(temp);
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
							int weight=ask(temp);
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
							int weight=ask(temp);
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
		//[己方>0;对方>0]权重赋值:0
		if(five.indexOf("1")!=-1&&five.indexOf("2")!=-1){
			return 0;
		}
		//[己方=0;对方=0]权重赋值:1
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
			//[己方=1;对方=0]权重赋值2
			if(count==1){
				return 2;
			}
			//[己方=2;对方=0]权重赋值:4
			if(count==2){
				return 4;
			}
			//[己方=3;对方=0]权重赋值:8
			if(count==3){
				return 8;
			}
			//[己方=4;对方=0]权重赋值:32
			if(count==4){
				return 32;
			}
			//[己方=5;对方=0]权重赋值:512
			if(count==5){
				return 512;
			}
			return 0;
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
			//[己方=0;对方=1]权重赋值:2
			if(count==1){
				return 2;
			}
			//[己方=0;对方=2]权重赋值:3
			if(count==2){
				return 3;
			}
			//[己方=0;对方=3]权重赋值:6
			if(count==3){
				return 6;
			}
			//[己方=0;对方=4]权重赋值:32
			if(count==4){
				return 32;
			}
			//[己方=0;对方=5]权重赋值:512
			if(count==5){
				return 512;
			}
			return 0;
		}
		return 0;
	}
	
	//【操作】清空权重存储变量
	public static void clear(){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
	}
}
