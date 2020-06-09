package core;


public class Forbid{
	/**
	 * @名称：禁手分析类
	 * @功能：判断该位置是否存在禁手的情况
	 * @用法：当不是禁手的时候，返回true；当是禁手的时候，返回false
	 */
	
	//【定义分析变量】
	//[分析统计性变量]
	//该点构成的活三数量
	private static int threeCount=0;
	//该点构成的四子数量
	private static int fourCount=0;
	//[分析临时性变量]
	//上(左)、下(右)方允许一个空格的黑子数
	static int up=0;
	static int down=0;
	//上(左)、下(右)方不允许空格的黑子数
	static int spaceUp=0;
	static int spaceDown=0;
	//上(左)、下(右)方黑子结束位置为白子或边缘
	static boolean endUp=false;
	static boolean endDown=false;
	
	//【禁手查询模块】输入棋盘落子坐标、当前子颜色
	public static boolean forbid(int boardX,int boardY,int color){
		//[当前棋子为白棋时自动忽略禁手]
		if(color==2){
			return true;
		}
		
		//[清空统计性变量]
		threeCount=0;
		fourCount=0;
		
		//[水平方向计数]
		//统计水平方向棋子数据
		countW_E(boardX,boardY);
		//分析是否出现活三
		if(analyseThree()==true){
			threeCount++;
		}
		//分析是否出现四子
		if(analyseFour()==true){
			fourCount++;
		}
		//分析是否出现长连
		if(analyseLong()==true){
			return false;
		}
		//分析是否四四禁手(单行)
		if(analyseLineFour()==true){
			return false;
		}
		//分析是否四四禁手(扁担阵)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[垂直方向计数]
		//统计垂直方向棋子数据
		countN_S(boardX,boardY);
		//分析是否出现活三
		if(analyseThree()==true){
			threeCount++;
		}
		//分析是否出现四子
		if(analyseFour()==true){
			fourCount++;
		}
		//分析是否出现长连
		if(analyseLong()==true){
			return false;
		}
		//分析是否四四禁手(单行)
		if(analyseLineFour()==true){
			return false;
		}
		//分析是否四四禁手(扁担阵)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[左上-右下方向计数]
		//统计左上-右下方向棋子数据
		countNW_SE(boardX,boardY);
		//分析是否出现活三
		if(analyseThree()==true){
			threeCount++;
		}
		//分析是否出现四子
		if(analyseFour()==true){
			fourCount++;
		}
		//分析是否出现长连
		if(analyseLong()==true){
			return false;
		}
		//分析是否四四禁手(单行)
		if(analyseLineFour()==true){
			return false;
		}
		//分析是否四四禁手(扁担阵)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[右上-左下方向计数]
		//统计右上-左下方向棋子数据
		countNE_SW(boardX,boardY);
		//分析是否出现活三
		if(analyseThree()==true){
			threeCount++;
		}
		//分析是否出现四子
		if(analyseFour()==true){
			fourCount++;
		}
		//分析是否出现长连
		if(analyseLong()==true){
			return false;
		}
		//分析是否四四禁手(单行)
		if(analyseLineFour()==true){
			return false;
		}
		//分析是否四四禁手(扁担阵)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[分析活三总数]
		if(threeCount>=2){
			return false;
		}
		
		//[分析四字总数]
		if(fourCount>=2){
			return false;
		}
		
		return true;
	}
	
	//【识别】统计水平方向棋子数据
	public static void countW_E(int boardX,int boardY){
		//[清空分析临时性变量]
		clear();
		//[定义是否已包含空格临时变量]
		boolean space=false;
		//[分析该点左侧的情况]
		for(int i=0;i<5;i++){
			if(boardX>i){
				//当该位置为黑子时
				if(Scene.board[boardX-1-i][boardY]==1){
					//如果没有跳跃过空格
					if(space==false){
						up++;
						spaceUp++;
					}
					//如果已经跳跃过空格
					else{
						up++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX-1-i][boardY]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endUp=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endUp=true;
				break;
			}
		}
		//[重置是否已包含空格临时变量]
		space=false;
		//[分析该点右侧的情况]
		for(int i=0;i<5;i++){
			if(boardX<14-i){
				//当该位置为黑子时
				if(Scene.board[boardX+1+i][boardY]==1){
					//如果没有跳跃过空格
					if(space==false){
						down++;
						spaceDown++;
					}
					//如果已经跳跃过空格
					else{
						down++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX+1+i][boardY]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endDown=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//【识别】统计垂直方向棋子数量
	public static void countN_S(int boardX,int boardY){
		//[清空分析临时性变量]
		clear();
		//[定义是否已包含空格临时变量]
		boolean space=false;
		//[分析该点上方的情况]
		for(int i=0;i<5;i++){
			if(boardY>i){
				//当该位置为黑子时
				if(Scene.board[boardX][boardY-1-i]==1){
					//如果没有跳跃过空格
					if(space==false){
						up++;
						spaceUp++;
					}
					//如果已经跳跃过空格
					else{
						up++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX][boardY-1-i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endUp=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[分析该点下方的情况]
		for(int i=0;i<5;i++){
			if(boardY<14-i){
				//当该位置为黑子时
				if(Scene.board[boardX][boardY+1+i]==1){
					//如果没有跳跃过空格
					if(space==false){
						down++;
						spaceDown++;
					}
					//如果已经跳跃过空格
					else{
						down++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX][boardY+1+i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endDown=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//【识别】统计左上-右下方向棋子数量
	public static void countNW_SE(int boardX,int boardY){
		//[清空分析临时性变量]
		clear();
		//[定义是否已包含空格临时变量]
		boolean space=false;
		//[分析该点左上方的情况]
		for(int i=0;i<5;i++){
			if(boardX>i&&boardY>i){
				//当该位置为黑子时
				if(Scene.board[boardX-1-i][boardY-1-i]==1){
					//如果没有跳跃过空格
					if(space==false){
						up++;
						spaceUp++;
					}
					//如果已经跳跃过空格
					else{
						up++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX-1-i][boardY-1-i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endUp=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[分析该点右下方的情况]
		for(int i=0;i<5;i++){
			if(boardX<14-i&&boardY<14-i){
				//当该位置为黑子时
				if(Scene.board[boardX+1+i][boardY+1+i]==1){
					//如果没有跳跃过空格
					if(space==false){
						down++;
						spaceDown++;
					}
					//如果已经跳跃过空格
					else{
						down++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX+1+i][boardY+1+i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endDown=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//【识别】统计右上-左下方向棋子数量
	public static void countNE_SW(int boardX,int boardY){
		//[清空分析临时性变量]
		clear();
		//[定义是否已包含空格临时变量]
		boolean space=false;
		//[分析该点右上方的情况]
		for(int i=0;i<5;i++){
			if(boardX>i&&boardY<14-i){
				//当该位置为黑子时
				if(Scene.board[boardX-1-i][boardY+1+i]==1){
					//如果没有跳跃过空格
					if(space==false){
						up++;
						spaceUp++;
					}
					//如果已经跳跃过空格
					else{
						up++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX-1-i][boardY+1+i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endUp=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[分析该点左下方的情况]
		for(int i=0;i<5;i++){
			if(boardX<14-i&&boardY>i){
				//当该位置为黑子时
				if(Scene.board[boardX+1+i][boardY-1-i]==1){
					//如果没有跳跃过空格
					if(space==false){
						down++;
						spaceDown++;
					}
					//如果已经跳跃过空格
					else{
						down++;
					}
				}
				//当该位置为空格时
				else if(Scene.board[boardX+1+i][boardY-1-i]==0){
					//如果没有跳跃过空格
					if(space==false){
						space=true;
					}
					//如果已经跳跃过空格
					else{
						break;
					}
				}
				//当该位置为白子时
				else{
					endDown=true;
					break;
				}
			}
			//当到达棋盘边缘时
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//【操作】清空分析临时性变量
	public static void clear(){
		up=0;
		down=0;
		spaceUp=0;
		spaceDown=0;
		endUp=false;
		endDown=false;
	}
	
	//【分析】判断是否出现活三
	public static boolean analyseThree(){
		//(□■●●□)或(□●■●□)或(□●●■□)○○□
		if(spaceUp+spaceDown+1==3&&(down>spaceDown||endDown==false)&&(up>spaceUp||endUp==false)){
			return true;
		}
		//(□●□●■□)或(□●●□■□)
		if(up==2&&endUp==false&&spaceDown==0&&(down>0||endDown==false)){
			return true;
		}
		//(□■●□●□)或(□■□●●□)
		if(down==2&&endDown==false&&spaceUp==0&&(up>0||endUp==false)){
			return true;
		}
		//(□●□■●□)
		if(up==1&&spaceUp==0&&endUp==false&&spaceDown==1&&(down>1||endDown==false)){
			return true;
		}
		//(□●■□●□)
		if(down==1&&spaceDown==0&&endDown==false&&spaceUp==1&&(up>1||endUp==false)){
			return true;
		}
		return false;
	}
	
	//【分析】判断是否出现四子
	public static boolean analyseFour(){
		//(■●●●)或(●■●●)或(●●■●)或(●●●■)
		if(spaceUp+spaceDown+1==4&&((up>spaceUp||endUp==false)||(down>spaceDown||endDown==false))){
			return true;
		}
		//(■●●□●)或(■●□●●)或(■□●●●)
		if(down==3&&spaceDown<3&&(endDown==false||(up>0||endUp==false))){
			return true;
		}
		//(●●●□■)或(●●□●■)或(●●●□■)
		if(up==3&&spaceUp<3&&(endUp==false||(down>0||endDown==false))){
			return true;
		}
		//(●■●□●)或(●■□●●)
		if(spaceUp==1&&down==2&&spaceDown<2&&(endDown==false||(up>1||endUp==false))){
			return true;
		}
		//(●□●■●)或(●●□■●)
		if(spaceDown==1&&up==2&&spaceUp<2&&(endUp==false||(down>1||endDown==false))){
			return true;
		}
		//(●□■●●)
		if(up==1&&spaceUp==0&&spaceDown==2&&(endUp==false||(down>2||endDown==false))){
			return true;
		}
		//(●●■□●)
		if(down==1&&spaceDown==0&&spaceUp==2&&(endDown==false||(up>2||endUp==false))){
			return true;
		}
		return false;
	}
	
	//【分析】判断是否出现长连
	public static boolean analyseLong(){
		if(spaceUp+spaceDown+1>=6){
			return true;
		}
		return false;
	}
	
	//【分析】判断是否出现四四禁手(单行)
	public static boolean analyseLineFour(){
		//(●●□●■□●●)
		if(spaceUp==0&&spaceDown==1&&up==2&&down==3){
			return true;
		}
		//(●●□■●□●●)
		else if(spaceDown==0&&spaceUp==1&&down==2&&up==3){
			return true;
		}
		return false;
	}
	
	//【分析】判断是否出现四四禁手(扁担阵)
	public static boolean analyseCarryFour(){
		//(●□●■●□●)
		if(spaceUp==1&&spaceDown==1&&up==2&&down==2){
			return true;
		}
		return false;
	}
}