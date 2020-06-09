package core;

import tool.Docu;

public class Record{
	/**
	 * @名称：棋局记录类
	 */
	//【定义存储变量】
	static int step=0;
	static int manual[][]=new int[225][2];
	
	//【添加一颗棋子】
	public static void add(int x,int y){
		manual[step][0]=x;
		manual[step][1]=y;
		step++;
	}
	
	//【清空棋局记录】
	public static void clear(){
		step=0;
	}
	
	//【保存棋谱到文件】
	public static boolean save(){
		//计算文件最小空编号
		int num=Docu.leastNumber("record\\","manual",".txt");
		//计算棋谱文件地址
		String path="record\\manual"+num+".txt";
		//创建棋谱文件
		if(!Docu.foundFile(path,true)){
			return false;
		}
		//[写入数据库]
		//写入步数
		if(!Docu.write(path,0,step)){
			return false;
		}
		//写入棋谱
		for(int i=0;i<step;i++){
			if(!Docu.write(path,10+i*10+0,i)){
				return false;
			}
			if(!Docu.write(path,10+i*10+3,"(")){
				return false;
			}
			if(!Docu.write(path,10+i*10+4,manual[i][0])){
				return false;
			}
			if(!Docu.write(path,10+i*10+6,",")){
				return false;
			}
			if(!Docu.write(path,10+i*10+7,manual[i][1])){
				return false;
			}
			if(!Docu.write(path,10+i*10+9,")")){
				return false;
			}
		}
		return true;
	}
	
	//【删除棋谱中的上一手】
	public static void delete(){
		step--;
	}
	
}
