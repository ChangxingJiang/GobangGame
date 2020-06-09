package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gadget{
	//��ת�����ַ���ת��Ϊ����(�����ַ���)
	public static int toNum(String x){
		if(x.equals("")){
			System.out.println("tool-Gadget-toNum:�����ַ���ת��Ϊ�����쳣");
			return -1;
		}
		//�����ַ����е��������(�ո񡢶��š����з�)
		x=x.replace(" ","");
		x=x.replace(",","");
		x=x.replace("\n","");
		//���ַ���ת��Ϊ����
		int back=-1;
		try{
			back=Integer.valueOf(x).intValue();                       //���ַ���ת��ΪΪint��ʽ
		}
		catch (NumberFormatException e) {
			if(x.equals("")){
				System.out.println("tool-Gadget-toNum:���������ַ�ת��Ϊ�����쳣");
			}
			else{
				System.out.println("tool-Gadget-toNum:���ַ���("+x+")ת��Ϊ�����쳣��");
			}
		}
		return back;
	}
	
	//��ת�����ַ���ת��Ϊ�߼���(�����ַ���)
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
	
	//�����㡿����URL��������
	public static String getArea(String URL){
		String area="";//���巵�ر���
		//ɾ��"http://"����
		String pattern="[Hh][Tt][Tt][Pp][Ss]{0,1}://";      //����������ʽ
		Pattern ask=Pattern.compile(pattern);                         //���� Pattern����
		Matcher matcher=ask.matcher(URL);                          //���� matcher����
		String temp=matcher.replaceAll("");
		//ɾ��"/"��Ĳ���
		if(temp.indexOf("/")==-1){
			area=temp;
		}
		else{
			area=temp.substring(0,temp.indexOf("/"));
		}
		return area;
	}
	
}
