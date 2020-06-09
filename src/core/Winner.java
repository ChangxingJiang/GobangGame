package core;

public class Winner{
	/**
	 * @名称：胜者判断类
	 * @功能：判断当前局面下是否出现胜者
	 */
	//【定义胜者变量】
	//(1为黑棋获胜,2为白棋获胜)
	public static int winner=0;
	
	//【判断是否出现胜者】
	public static boolean winner(){
		winner=0;
		//[判断垂直方向是否出现五子连珠]
		if(vertical()){
			return true;
		}
		//[判断水平方向是否出现五子连珠]
		if(horizontal()){
			return true;
		}
		//[判断左上-右下方向是否出现五子连珠]
		if(WN_ES()){
			return true;
		}
		//[判断右上-左下方向是否出现五子连珠]
		if(EN_WS()){
			return true;
		}
		return false;
	}
	
	//【判断】垂直方向是否出现五子连珠
	private static boolean vertical(){
		//[定义临时变量]
		//定义当前连续颜色变量
		//(1为黑棋,2为白棋)
		int color=0;
		//定义当前连续棋子数量
		int count=0;
		
		//[该行列循环统计]
		for(int i=0;i<15;i++){
			//新行列重置连续数
			color=0;
			count=0;
			//行列中各元素循环统计
			for(int j=0;j<15;j++){
				//当该元素的为空格的情况
				if(Scene.board[i][j]==0){
					color=0;
					count=0;
				}
				//当该元素的为黑棋的情况
				else if(Scene.board[i][j]==1){
					//若当前连续颜色为白棋或空格
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//若当前连续颜色为黑棋
					else if(color==1){
						count++;
					}
				}
				//当该元素的为白棋的情况
				else{
					//若当前连续颜色为黑棋或空格
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//若当前连续颜色为白棋
					else if(color==2){
						count++;
					}
				}
				//当前连续颜色是否超过5个
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//【判断】水平方向是否出现胜者
	private static boolean horizontal(){
		//[定义临时变量]
		//定义当前连续颜色变量
		//(1为黑棋,2为白棋)
		int color=0;
		//定义当前连续棋子数量
		int count=0;
		
		//[该行列循环统计]
		for(int i=0;i<15;i++){
			//新行列重置连续数
			color=0;
			count=0;
			//行列中各元素循环统计
			for(int j=0;j<15;j++){
				//当该元素的为空格的情况
				if(Scene.board[j][i]==0){
					color=0;
					count=0;
				}
				//当该元素的为黑棋的情况
				else if(Scene.board[j][i]==1){
					//若当前连续颜色为白棋或空格
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//若当前连续颜色为黑棋
					else if(color==1){
						count++;
					}
				}
				//当该元素的为白棋的情况
				else{
					//若当前连续颜色为黑棋或空格
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//若当前连续颜色为白棋
					else if(color==2){
						count++;
					}
				}
				//当前连续颜色是否超过5个
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//【判断】左上-右下方向是否出现胜者
	private static boolean WN_ES(){
		//[定义临时变量]
		//定义当前连续颜色变量
		//(1为黑棋,2为白棋)
		int color=0;
		//定义当前连续棋子数量
		int count=0;
		
		//[该行列循环统计]
		for(int i=0;i<29;i++){
			//新行列重置连续数
			color=0;
			count=0;
			//定义该斜列棋子数量
			int num=0;
			//定义该斜列第一颗棋子x坐标
			int x=0;
			//定义该斜列第一颗棋子y坐标
			int y=0;
			//计算该斜列的棋子数量、第一颗棋子的坐标
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
			//该斜列连续同色计量
			for(int j=0;j<num;j++){
				//当该元素的为空格的情况
				if(Scene.board[x+j][y+j]==0){
					color=0;
					count=0;
				}
				//当该元素的为黑棋的情况
				else if(Scene.board[x+j][y+j]==1){
					//若当前连续颜色为白棋或空格
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//若当前连续颜色为黑棋
					else if(color==1){
						count++;
					}
				}
				//当该元素的为白棋的情况
				else{
					//若当前连续颜色为黑棋或空格
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//若当前连续颜色为白棋
					else if(color==2){
						count++;
					}
				}
				//当前连续颜色是否超过5个
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//【判断右上-左下是否出现胜者】
	private static boolean EN_WS(){
		//[定义临时变量]
		//定义当前连续颜色变量
		//(1为黑棋,2为白棋)
		int color=0;
		//定义当前连续棋子数量
		int count=0;
		
		//[该行列循环统计]
		for(int i=0;i<29;i++){
			//新行列重置连续数
			color=0;
			count=0;
			//计算该斜列棋子数量
			int num=0;
			//计算该斜列第一颗棋子x坐标
			int x=0;
			//计算该斜列第一颗棋子y坐标
			int y=0;
			//计算该斜列的棋子数量、第一颗棋子的坐标
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
			//该斜列连续同色计量
			for(int j=0;j<num;j++){
				//当该元素的为空格的情况
				if(Scene.board[x-j][y+j]==0){
					color=0;
					count=0;
				}
				//当该元素的为黑棋的情况
				else if(Scene.board[x-j][y+j]==1){
					//若当前连续颜色为白棋或空格
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//若当前连续颜色为黑棋
					else if(color==1){
						count++;
					}
				}
				//当该元素的为白棋的情况
				else{
					//若当前连续颜色为黑棋或空格
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//若当前连续颜色为白棋
					else if(color==2){
						count++;
					}
				}
				//当前连续颜色是否超过5个
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
}
