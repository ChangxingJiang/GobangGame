package robot;

import core.Forbid;
import core.Scene;
import core.Setting;

public class Medium{
	/**权重计算原则
	 * [1]活一:20
	 * [1]死一:4
	 * [2]活二:400
	 * [2]死二:90
	 * [3]活三:6000
	 * [3]死三:800
	 * [4]活四:20000
	 * [4]死四:10000
	 * [5]五子连珠:50000
	 * 
	 * 权重计算其他规则
	 * [二子]
	 * ①靠近中间+10
	 * [三子]
	 * ①中间+200
	 * ②每隔一格-100
	 * [四子]
	 * ①两边-2000
	 * ②每隔一格-1000
	 */
	
	public static int mark[][]=new int[15][15];//每个点的评分
	//自动初始化评分表
	static{
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
	}
	
	//【申请一次计算】
	public static int[] mediumGo(){
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
	
	public static int ask(String five){
		//[处理有黑棋、有白棋的情况]
		if(five.indexOf("1")!=-1&&five.indexOf("2")!=-1){
			return 0;
		}
		//[转换为单色/空格情况]
		five=five.replace("2","1");
		//[处理单色/空格情况权重]
		return monochrome(five);
	}
	
	//【处理单色情况】
	public static int monochrome(String five){
		//一颗子
		if(five.equals("30000")){return Setting.medium_one*(Setting.medium_first)/100;}
		if(five.equals("03000")){return Setting.medium_one*(Setting.medium_second)/100;}
		if(five.equals("00300")){return Setting.medium_one*(Setting.medium_third)/100;}
		if(five.equals("00030")){return Setting.medium_one*(Setting.medium_second)/100;}
		if(five.equals("00003")){return Setting.medium_one*(Setting.medium_first)/100;}
		//两颗子
		if(five.equals("31000")){return Setting.medium_two*(Setting.medium_first)/100;}
		if(five.equals("13000")){return Setting.medium_two*(Setting.medium_second)/100;}
		if(five.equals("30100")){return Setting.medium_two*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("10300")){return Setting.medium_two*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("30010")){return Setting.medium_two*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("10030")){return Setting.medium_two*(Setting.medium_second+Setting.medium_double)/100;}
		if(five.equals("30001")){return Setting.medium_two*(Setting.medium_first+Setting.medium_touble)/100;}
		if(five.equals("10003")){return Setting.medium_two*(Setting.medium_first+Setting.medium_touble)/100;}
		if(five.equals("03100")){return Setting.medium_two*(Setting.medium_second)/100;}
		if(five.equals("01300")){return Setting.medium_two*(Setting.medium_third)/100;}
		if(five.equals("03010")){return Setting.medium_two*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("01030")){return Setting.medium_two*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("03001")){return Setting.medium_two*(Setting.medium_second+Setting.medium_double)/100;}
		if(five.equals("01003")){return Setting.medium_two*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("00310")){return Setting.medium_two*(Setting.medium_third)/100;}
		if(five.equals("00130")){return Setting.medium_two*(Setting.medium_third)/100;}
		if(five.equals("00301")){return Setting.medium_two*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("00103")){return Setting.medium_two*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("00031")){return Setting.medium_two*(Setting.medium_second)/100;}
		if(five.equals("00013")){return Setting.medium_two*(Setting.medium_first)/100;}
		//三颗子
		if(five.equals("31100")){return Setting.medium_three*(Setting.medium_first)/100;}
		if(five.equals("13100")){return Setting.medium_three*(Setting.medium_second)/100;}
		if(five.equals("11300")){return Setting.medium_three*(Setting.medium_third)/100;}
		if(five.equals("31010")){return Setting.medium_three*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("13010")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("11030")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("31001")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("13001")){return Setting.medium_three*(Setting.medium_second+Setting.medium_double)/100;}
		if(five.equals("11003")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("30110")){return Setting.medium_three*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("10310")){return Setting.medium_three*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("10130")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("30101")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("10301")){return Setting.medium_three*(Setting.medium_third+Setting.medium_double)/100;}
		if(five.equals("10103")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("30011")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("10031")){return Setting.medium_three*(Setting.medium_second+Setting.medium_double)/100;}
		if(five.equals("10013")){return Setting.medium_three*(Setting.medium_first+Setting.medium_double)/100;}
		if(five.equals("03110")){return Setting.medium_three*(Setting.medium_second)/100;}
		if(five.equals("01310")){return Setting.medium_three*(Setting.medium_third)/100;}
		if(five.equals("01130")){return Setting.medium_three*(Setting.medium_second)/100;}
		if(five.equals("03101")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("01301")){return Setting.medium_three*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("01103")){return Setting.medium_three*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("03011")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("01031")){return Setting.medium_three*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("01013")){return Setting.medium_three*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("00311")){return Setting.medium_three*(Setting.medium_third)/100;}
		if(five.equals("00131")){return Setting.medium_three*(Setting.medium_second)/100;}
		if(five.equals("00113")){return Setting.medium_three*(Setting.medium_first)/100;}
		//四颗子
		if(five.equals("31110")){return Setting.medium_four*(Setting.medium_first)/100;}
		if(five.equals("13110")){return Setting.medium_four*(Setting.medium_second)/100;}
		if(five.equals("11310")){return Setting.medium_four*(Setting.medium_third)/100;}
		if(five.equals("11130")){return Setting.medium_four*(Setting.medium_second)/100;}
		if(five.equals("31101")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("13101")){return Setting.medium_four*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("11301")){return Setting.medium_four*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("11103")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("31011")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("13011")){return Setting.medium_four*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("11031")){return Setting.medium_four*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("11013")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("30111")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("10311")){return Setting.medium_four*(Setting.medium_third+Setting.medium_skip)/100;}
		if(five.equals("10131")){return Setting.medium_four*(Setting.medium_second+Setting.medium_skip)/100;}
		if(five.equals("10113")){return Setting.medium_four*(Setting.medium_first+Setting.medium_skip)/100;}
		if(five.equals("03111")){return Setting.medium_four*(Setting.medium_second)/100;}
		if(five.equals("01311")){return Setting.medium_four*(Setting.medium_third)/100;}
		if(five.equals("01131")){return Setting.medium_four*(Setting.medium_second)/100;}
		if(five.equals("01113")){return Setting.medium_four*(Setting.medium_first)/100;}
		//五子连珠
		if(five.equals("31111")){return Setting.medium_five*(Setting.medium_first)/100;}
		if(five.equals("13111")){return Setting.medium_five*(Setting.medium_second)/100;}
		if(five.equals("11311")){return Setting.medium_five*(Setting.medium_third)/100;}
		if(five.equals("11131")){return Setting.medium_five*(Setting.medium_second)/100;}
		if(five.equals("11113")){return Setting.medium_five*(Setting.medium_first)/100;}
		return 0;
	}
}
