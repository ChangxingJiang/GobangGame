package core;

import tool.Docu;

public class Record{
	/**
	 * @���ƣ���ּ�¼��
	 */
	//������洢������
	static int step=0;
	static int manual[][]=new int[225][2];
	
	//�����һ�����ӡ�
	public static void add(int x,int y){
		manual[step][0]=x;
		manual[step][1]=y;
		step++;
	}
	
	//�������ּ�¼��
	public static void clear(){
		step=0;
	}
	
	//���������׵��ļ���
	public static boolean save(){
		//�����ļ���С�ձ��
		int num=Docu.leastNumber("record\\","manual",".txt");
		//���������ļ���ַ
		String path="record\\manual"+num+".txt";
		//���������ļ�
		if(!Docu.foundFile(path,true)){
			return false;
		}
		//[д�����ݿ�]
		//д�벽��
		if(!Docu.write(path,0,step)){
			return false;
		}
		//д������
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
	
	//��ɾ�������е���һ�֡�
	public static void delete(){
		step--;
	}
	
}
