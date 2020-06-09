package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Docu{
	/**
	 * 模块名：文件处理工具
	 * [信息模块]
	 * 读取当前路径:readPath();
	 * 读取文件长度:length(String path);
	 * 判断文件是否存在:exist(String path);
	 * [读取模块]
	 * 普通文件:read(String path,long index,int extent);
	 * tXT文件:readTXT(String path,long line);
	 * 读取一行TXT文档的坐标:readTXTIndex(String path,long line);
	 * [写入模块]
	 * 初始化:initialize(long length,String path);
	 * 定点写入字符串:write(String path,long index,String read);
	 * 定点写入数据:write(String path,long index,int num);
	 * 末尾写入字符串:write(String path,String read);
	 * 末尾写入数据:(String path,int num);
	 * 无检测写入:justwrite(String path,long index,String read);
	 * [操作模块]
	 * 建立路径:foundPath(String path);
	 * 创建文件:foundFile(String path,boolean rebuild);
	 * 创建定长文件:foundFile(String path,long size,boolean rebuild);
	 * 创建文件不覆盖:foundFile(String path);
	 * 复制文件:copyFile(String path1,String path2);
	 * 删除文件:deleteFile(String path);
	 * 寻找最小空编号:leastNumber(String path,String name,String suffix);
	 * [模块基础]
	 * tool.Stri(字符串处理工具)
	 * [最近修改]
	 * ①修复文件写入超过文件大小后写入怪符的bug
	 * ②文件读写时文件不存在崩溃bug
	 * ③增加寻找最小空编号功能
	 * ④增加末尾写入数据功能
	 */
	//[TXT读写暂存变量]
	static long sline=0;
	static long sindex=0;
	
	//【信息】判断文件是否存在(输入文件路径，返回是否存在且为文件(存在返回true，不存在返回false))
	public static boolean exist(String path){
		File file=new File(path);
		boolean Test=false;
		boolean existFile=file.exists();                              //判断文件是否存在
		boolean isFile=file.isFile();                                 //判断是否为文件
		if(existFile==true&&isFile==true){
			Test=true;
		}
		return Test;
	}
	
	//【信息】读取文件长度(输入文件路径)
	public static long length(String path){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return -1;
		}
		//[读取文件长度]
		File filelong=new File(path);
		long length=filelong.length();                                //读取文件长度(字节数)
		return length;
	}
	
	//【读取】读取文件(输入文件路径，读取指针，读取长度)
	public static String read(String path,long index,int extent){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return "";
		}
		//[判断读取坐标是否在文件内]
		String back=new String("");
		RandomAccessFile file=null;
		File filelong=new File(path);
		long length=filelong.length();                                //读取文件长度(字节数)
		if((index+extent)>length){                                    //读取坐标超过文件长度
			if(length>index){                                         //若读取坐标位于文件内，读取到文件结尾
				extent=(int)(length-index);
			}
			else{
				return null;
			}
		}
		if((index+extent)>length||index<0||extent<=0){                //判断是否符合读取标准
			if(index<0){                                              //读取坐标为负数
				System.out.print("读取坐标为负数，");
			}
			if(extent<=0){                                            //读取长度为负数
				System.out.print("读取长度为负数，");
			}
			System.out.println("读取失败！");
		}
		//[文件读取]
		else{
			try{
				file=new RandomAccessFile(path, "r");                 //打开一个随机访问文件流，按只读方式("rw"为读写)
				file.seek(index);                                     //将读文件的开始位置移到index位置
				byte[] bytes=new byte[extent];
				file.read(bytes);                                     //将一次读取的字节数赋给byteread
				back=new String(bytes);
			}
			catch(IOException e){
				System.out.println("读取读取出现其他异常");
			}
			finally{
				if(file!=null){
					try{file.close();}
					catch(IOException e1){
						System.out.println("读取读取关闭异常");
					}
				}
			}
		}
		return back;
	}
	
	//【读取】读取一行TXT文档(输入文件路径，输入读取行数)(单行最长不超过1024字节)
	public static String readTXT(String path,long line){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return "";
		}
		//[定义读取临时变量]
		String temp=null;                                             //临时读取变量
		long index=0;                                                 //坐标指针
		int search=0;                                                 //查找坐标
		long safe=0;                                                  //循环保护
		//[存储记录为当前行]
		if(sline==line&&sindex!=0){
			//读取当前行内容
			temp=read(path,sindex,1024);
			if(temp==null){
				return null;
			}
			//搜索换行符位置
			search=temp.indexOf("\r\n");			                  //在temp中搜索当前"\r\n"，>=0为存在,=-1为不存在
			//修改临时存储记录
			if(search==-1){                                           //若未搜索到\r\n
				sindex=sindex+temp.getBytes().length;
			}
			if(search==0){                                            //若搜索到"\r\n"位于首字节
				temp=null;
				sindex=sindex+2;
			}
			if(search>0){
				temp=temp.substring(0,search);
				sindex=sindex+temp.getBytes().length+2;               //存储下一行起始处文件坐标
			}
			sline=line+1;                                             //存储下一行行数
		}
		//[没有存储记录或存储记录在读取行数后]
		else{
			//清空存储记录
			if(sline==0||sline>line){                           
				sline=0;
				sindex=0;
			}
			long i=sline;                                             //临时行数记录
			index=sindex;
			//移动指针到目标行
			while(safe<1000000){
				temp=read(path,index,1024);
				if(temp==null){
					return null;
				}
				search=temp.indexOf("\r\n");			              //在temp中搜索当前"\r\n"，>=0为存在,=-1为不存在
				if(search==-1){                                       //若未搜索到\r\n
					sline=i+1;                                        //存储下一行行数
					sindex=sindex+temp.getBytes().length;
				}
				if(search>=0){                                        //搜索到\r\n
					temp=temp.substring(0,search);
					index=index+temp.getBytes().length+2;
				}
				if(i==line){
					sline=i+1;                                        //存储下一行行数
					sindex=index;                                     //存储下一行起始处文件坐标
					break;
				}
				i++;
				safe++;                                               //循环安全保护记录
			}
		}
		return temp;
	}
	
	//【读取】读取一行TXT文档的坐标(输入文件路径，输入读取行数)(单行最长不超过1024字节)
	public static long readTXTIndex(String path,long line){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return -1;
		}
		//[定义读取临时变量]
		long index=0;                                                 //坐标指针
		long i=0;
		//移动指针到目标行
		while(index<1000){
			String temp=read(path,index,1024);
			if(temp==null){
				return -1;
			}
			int search=temp.indexOf("\r\n");			              //在temp中搜索当前"\r\n"，>=0为存在,=-1为不存在
			if(search==-1){                                       //若未搜索到\r\n
				System.out.print("单行超长异常");
				index=index+1024;
			}
			if(i==line){
				break;
			}
			i++;
			index=index+temp.getBytes().length+2;
		}
		return index;
	}
	
	//【写入】文件填"空"初始化(输入目标文件长度，输入文件路径)
	public static boolean initialize(long length,String path){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[文件初始化]
		File file=new File(path);
		long now=file.length();
		for(long i=now;i<length;){
			if((length-i)>=128){                                      //当剩余128个字节以上需要填写时
				if(!justwrite(path,i,"                                                                                                                                ")){
					return false;
				}
				i=i+128;
			}else if((length-i)>=64){                                 //当剩余64个字节以上需要填写时
				if(!justwrite(path,i,"                                                                ")){
					return false;
				}
				i=i+64;
			}else if((length-i)>=32){                                 //当剩余32个字节以上需要填写时
				if(!justwrite(path,i,"                                ")){
					return false;
				}
				i=i+16;
			}else if((length-i)>=16){                                 //当剩余16个字节以上需要填写时
				if(!justwrite(path,i,"                ")){
					return false;
				}
				i=i+16;
			}else if((length-i)>=8){                                  //当剩余8个字节以上需要填写时
				if(!justwrite(path,i,"        ")){
					return false;
				}
				i=i+8;
			}else if((length-i)>=4){                                  //当剩余4个字节以上需要填写时
				if(!justwrite(path,i,"    ")){
					return false;
				}
				i=i+4;
			}else if((length-i)>=2){                                  //当剩余2个字节以上需要填写时
				if(!justwrite(path,i,"  ")){
					return false;
				}
				i=i+2;
			}else{                                                    //当剩余1个字节需要填写时
				if(!justwrite(path,i," ")){
					return false;
				}
				i++;
			}
		}
		return true;
	}
	
	//【写入】文件检测文件大小写入(输入文件路径、写入坐标、写入内容)
	public static boolean write(String path,long index,String read){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[判断写入内容是否在文件内]
		File files=new File(path);
		long now=files.length();
		if(now<index+read.length()){
			if(!initialize((long)index+read.length(),path)){
				System.out.println("文件初始化异常");
				return false;
			}
		}
		//[文件写入]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //将content转化为字节串
		try {
            file=new RandomAccessFile(path,"rw");                     //打开一个随机访问文件流，按只读方式("rw"为读写)
			file.seek(index);                                         //将读文件的开始位置移到index位置。
			file.write(bytes);                                        //文件写入
		}
		catch(IOException e){
			System.out.println("文件写入异常");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //关闭文件
					return true;
				}
				catch(IOException e){
					System.out.println("文件关闭异常");
					return false;
				}
			}
		}
		return true;
    }
	
	//【写入】文件检测文件大小写入(输入文件路径、写入坐标、写入内容)
	public static boolean write(String path,long index,int num){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[判断写入内容是否在文件内]
		File files=new File(path);
		long now=files.length();
		if(now<index+8){
			if(!initialize(index+8,path)){
				System.out.println("文件初始化异常");
				return false;
			}
		}
		//[文件写入]
		RandomAccessFile file=null;
		String read=""+num;
		byte bytes[]=read.getBytes();                                 //将content转化为字节串
		try {
	        file=new RandomAccessFile(path,"rw");                     //打开一个随机访问文件流，按只读方式("rw"为读写)
			file.seek(index);                                         //将读文件的开始位置移到index位置。
			file.write(bytes);                                        //文件写入
		}
		catch(IOException e){
			System.out.println("文件写入异常");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //关闭文件
					return true;
				}
				catch(IOException e){
					System.out.println("文件关闭异常");
					return false;
				}
			}
		}
		return true;
    }
	
	//【写入】文件无检测写入(输入文件路径、写入坐标、写入内容)
	public static boolean justwrite(String path,long index,String read){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[文件写入]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //将content转化为字节串
		try {
	        file=new RandomAccessFile(path,"rw");                     //打开一个随机访问文件流，按只读方式("rw"为读写)
			file.seek(index);                                         //将读文件的开始位置移到index位置。
			file.write(bytes);                                        //文件写入
		}
		catch(IOException e){
			System.out.println("文件写入异常");
			return false;
	    }
		finally{
			if(file!=null){
				try{
					file.close();                                     //关闭文件
					return true;
				}
				catch(IOException e){
					System.out.println("文件关闭异常");
					return false;
				}
			}
		}
		return true;
	}
	
	//【写入】文件写在文件末尾(输入文件路径、写入内容)
	public static boolean write(String path,String read){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[文件写入]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //将content转化为字节串
		try {
            file=new RandomAccessFile(path,"rw");                     //打开一个随机访问文件流，按只读方式("rw"为读写)
			long fileLength=file.length();                            //文件长度，字节数
			file.seek(fileLength);                                    //将读文件的开始位置移到文件末尾
			file.write(bytes);                                        //文件写入
		}
		catch(IOException e){
			System.out.println("文件写入异常");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //关闭文件
					return true;
				}
				catch(IOException e){
					System.out.println("文件关闭异常");
					return false;
				}
			}
		}
		return true;
    }
	
	//【写入】文件写在文件末尾(输入文件路径、写入数据)
	public static boolean write(String path,int num){
		//[判断文件是否存在]
		if(!exist(path)){
			System.out.print("文件不存在异常");
			return false;
		}
		//[文件写入]
		RandomAccessFile file=null;
		String read=""+num;
		byte bytes[]=read.getBytes();                                 //将content转化为字节串
		try{
			file=new RandomAccessFile(path,"rw");                     //打开一个随机访问文件流，按只读方式("rw"为读写)
			long fileLength=file.length();                            //文件长度，字节数
			file.seek(fileLength);                                    //将读文件的开始位置移到文件末尾
			file.write(bytes);                                        //文件写入
		}
		catch(IOException e){
			System.out.println("文件写入异常");
			return false;
		}
		finally{
			if(file!=null){
				try{
					file.close();                                     //关闭文件
					return true;
				}
				catch(IOException e){
					System.out.println("文件关闭异常");
					return false;
				}
			}
		}
		return true;
	}
	
	//【信息】读取当前地址(返回当前主类所在地址)
	public static String readPath(){
		String path=new String("");
		File file=new File("."); 
        try{
			path=file.getCanonicalPath();                             //得到当前地址
		}
		catch(IOException e){
			System.out.println("读取当前地址异常");
		}
 		return path;
	}
	
	//【操作】创建文件夹(输入文件夹地址路径)
	public static void foundPath(String path){
		File file=new File(path);
		if(!file.exists()){                                           //判断文件夹是否存在
			file.mkdir();                                             //创建文件夹
	    }
	}
	
	//【操作】创建文件，如已有不重建(输入创建文件的地址)
	public static boolean foundFile(String path){
		boolean temp=true,execute=true;                               //声明临时变量、执行变量
		File file=new File(path);
		temp=file.exists();                                           //判断文件是否存在：false为不存在
		if(temp==true){
			return false;
		}
		temp=file.getParentFile().exists();                           //判断文件所在目录是否存在：true为存在
		if(temp==false){                                              //若父目录不存在
			temp=file.getParentFile().mkdirs();                       //创建父目录
			if(temp==false){                                          //若父目录创建失败
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //创建文件
			}
			catch(IOException e){
				System.out.println("文件创建失败");
				return false;
			}
		}
		return true;
	}
	
	//【操作】创建文件(输入创建文件的地址、是否重建(true为替换原文件，false为保留原文件))
	public static boolean foundFile(String path,boolean rebuild){
		boolean temp=true,execute=true;                               //声明临时变量、执行变量
		File file=new File(path);
		temp=file.exists();                                           //判断文件是否存在：false为不存在
		if(temp==true){
			if(rebuild==false){                                       //文件存在且保留原文件
				return false;
			}
			else{
				if(!deleteFile(path)){
					System.out.println("删除已有文件异常");//删除原文件
					return false;
				}
			}
		}
		temp=file.getParentFile().exists();                           //判断文件所在目录是否存在：true为存在
		if(temp==false){                                              //若父目录不存在
			temp=file.getParentFile().mkdirs();                       //创建父目录
			if(temp==false){                                          //若父目录创建失败
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //创建文件
			}
			catch(IOException e){
				System.out.println("文件创建失败");
				return false;
			}
		}
		return true;
	}
	
	//【操作】创建文件(输入创建文件的地址、初始化文件体积、是否重建(true为替换原文件，false为保留原文件))
	public static boolean foundFile(String path,long size,boolean rebuild){
		boolean temp=true,execute=true;                               //声明临时变量、执行变量
		File file=new File(path);
		temp=file.exists();                                           //判断文件是否存在：false为不存在
		if(temp==true){
			if(rebuild==false){                                       //文件存在且保留原文件
				return false;
			}
			else{
				if(!deleteFile(path)){
					System.out.println("删除已有文件异常");//删除原文件
					return false;
				}
			}
		}
		temp=file.getParentFile().exists();                           //判断文件所在目录是否存在：true为存在
		if(temp==false){                                              //若父目录不存在
			temp=file.getParentFile().mkdirs();                       //创建父目录
			if(temp==false){                                          //若父目录创建失败
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //创建文件
			}
			catch(IOException e){
				System.out.println("文件创建失败");
				return false;
			}
			if(initialize(size,path)){
				return true;
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	//【操作】复制文件(输入目标路径，来源路径)
	public static boolean copyFile(String path1,String path2){
		//[检验目标路径存在]
		if(!exist(path1)){
			foundFile(path1,0,true);
		}
		//[复制文件]
		File file1=new File(path1);
		File file2=new File(path2);
		FileInputStream inStream=null;
		FileOutputStream outStream=null;
		FileChannel in=null;
        FileChannel out=null;
        try{
        	inStream=new FileInputStream(file2);
        	outStream=new FileOutputStream(file1);
        	in=inStream.getChannel();//得到对应的文件通道
        	out=outStream.getChannel();//得到对应的文件通道
        	in.transferTo(0,in.size(),out);//连接两个通道，并且从in通道读取，然后写入out通道
        }
        catch(IOException e){
        	e.printStackTrace();
        	return false;
        }
        finally{
        	try{
        		inStream.close();
        		in.close();
        		outStream.close();
        		out.close();
        	}
        	catch(IOException e){
        		e.printStackTrace();
        		return false;
        	}
        }
        return true;
	}
	
	//【文件】删除文件(输入删除文件路径，返回是否删除成功)
	public static boolean deleteFile(String path){  
		File file=new File(path);
		boolean temp=file.exists();                                   //判断目录或文件是否存在：false为正常 
		if(temp==true){                                                
			temp=file.isFile();                                       //判断是否为文件
			if(temp==true){
				file.delete();                                        //删除文件
			}
		}
		return temp;
	}
	
	//【文件】寻找文件最小空编号(输入文件路径、文件名前半部分、后缀，返回最小编号)
	public static int leastNumber(String path,String name,String suffix){
		foundPath(path);                                              //确认存在该文件夹
		String front=path+name;
		for(int i=1;i<999999;i++){
			String temp=front+Integer.toString(i,10)+suffix;
			if(!exist(temp)){
				return i;
			}
		}
		System.out.println("在1000000以下未发现有效空编号");
		return -1;
	}
}