package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gadget{
	//【转化】字符串转化为数字(输入字符串)
	public static int toNum(String x){
		if(x.equals("")){
			System.out.println("tool-Gadget-toNum:将空字符串转化为数字异常");
			return -1;
		}
		//处理字符串中的特殊符号(空格、逗号、换行符)
		x=x.replace(" ","");
		x=x.replace(",","");
		x=x.replace("\n","");
		//将字符串转化为数字
		int back=-1;
		try{
			back=Integer.valueOf(x).intValue();                       //将字符串转化为为int格式
		}
		catch (NumberFormatException e) {
			if(x.equals("")){
				System.out.println("tool-Gadget-toNum:将无数字字符转化为数字异常");
			}
			else{
				System.out.println("tool-Gadget-toNum:将字符串("+x+")转换为数字异常！");
			}
		}
		return back;
	}
	
	//【转化】字符串转化为逻辑型(输入字符串)
	public static boolean toBoolean(String x){
		if(x.equals("false")){
			return false;
		}
		if(x.equals("true")){
			return true;
		}
		if(x.indexOf("false")!=-1){
			return false;
		}
		if(x.indexOf("true")!=-1){
			return true;
		}
		return false;
	}
	
	//【计算】根据URL计算域名
	public static String getArea(String URL){
		String area="";//定义返回变量
		//删除"http://"部分
		String pattern="[Hh][Tt][Tt][Pp][Ss]{0,1}://";      //定义正则表达式
		Pattern ask=Pattern.compile(pattern);                         //创建 Pattern对象
		Matcher matcher=ask.matcher(URL);                          //创建 matcher对象
		String temp=matcher.replaceAll("");
		//删除"/"后的部分
		if(temp.indexOf("/")==-1){
			area=temp;
		}
		else{
			area=temp.substring(0,temp.indexOf("/"));
		}
		return area;
	}
	
}
