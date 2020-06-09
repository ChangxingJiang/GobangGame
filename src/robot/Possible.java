package robot;

import core.Forbid;
import core.Scene;

public class Possible{
	/**
	 * @���ƣ��������㷨������
	 * @ԭ����ȡ����������ʤ�����ܣ�ͨ�������ÿ����������ļ����ͶԷ����������� �Ըÿ��ܵ����е㸳��Ȩ�أ�ͨ���Ƚ�Ȩ�أ���������
	 * @ԭ��
	 * ��ͬ����������£����������ڵз�
	 * ��1-3�Ӽ��Ȩ�ز����С(ģ��)��3-5�Ӽ��Ȩ�ز����(����)
	 * @������ֵ(��ֵΪ��һ�����Ӻ��γɵ�)
	 * [����>0;�Է�>0]Ȩ�ظ�ֵ:0
	 * [����=0;�Է�=0]Ȩ�ظ�ֵ:1
	 * [����=1;�Է�=0]Ȩ�ظ�ֵ:2
	 * [����=2;�Է�=0]Ȩ�ظ�ֵ:4
	 * [����=3;�Է�=0]Ȩ�ظ�ֵ:8
	 * [����=4;�Է�=0]Ȩ�ظ�ֵ:32
	 * [����=5;�Է�=0]Ȩ�ظ�ֵ:512
	 * [����=0;�Է�=1]Ȩ�ظ�ֵ:2
	 * [����=0;�Է�=2]Ȩ�ظ�ֵ:3
	 * [����=0;�Է�=3]Ȩ�ظ�ֵ:6
	 * [����=0;�Է�=4]Ȩ�ظ�ֵ:32
	 * [����=0;�Է�=5]Ȩ�ظ�ֵ:512
	 */
	
	//������Ȩ�ش洢������
	public static int mark[][]=new int[15][15];//ÿ���������
	
	//��ִ��һ�μ�������
	public static int[] go(){
		//���Ȩ�ش洢����
		clear();
		//[����ˮƽ��������]
		for(int i=0;i<15;i++){
			for(int j=0;j<11;j++){
				String five=new String("");
				//�������ʤ�������Ե�����
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
				//�����ֵ������ֱ�
				for(int k=0;k<5;k++){
					if(Scene.board[j+k][i]==0){
						String temp=five.substring(0,k)+"3"+five.substring(k+1);
						int weight=ask(temp);
						mark[j+k][i]=mark[j+k][i]+weight;
					}
				}
			}
		}
		//[���㴹ֱ��������]
		for(int i=0;i<15;i++){
			for(int j=0;j<11;j++){
				String five=new String("");
				//�������ʤ�������Ե�����
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
				//�����ֵ������ֱ�
				for(int k=0;k<5;k++){
					if(Scene.board[i][j+k]==0){
						String temp=five.substring(0,k)+"3"+five.substring(k+1);
						int weight=ask(temp);
						mark[i][j+k]=mark[i][j+k]+weight;
					}
				}
			}
		}
		//[��������-���·�������]
		for(int i=0;i<29;i++){
			//[�����б��������������һ����������λ��]
			int num=0;//б����������
			int x=0;//б����x����
			int y=0;//б����y����
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
			//[�����Ƿ��л�ʤ����]
			if(num>5){
				for(int j=0;j<num-4;j++){
					String five=new String("");
					//�������ʤ�������Ե�����
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
					//�����ֵ������ֱ�
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
		//[��������-���·�������]
		for(int i=0;i<29;i++){
			//[�����б��������������һ����������λ��]
			int num=0;//б����������
			int x=0;//б����x����
			int y=0;//б����y����
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
			//[�����Ƿ��л�ʤ����]
			if(num>5){
				for(int j=0;j<num-4;j++){
					String five=new String("");
					//�������ʤ�������Ե�����
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
					//�����ֵ������ֱ�
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
		//[��ȡȫ���������λ��]
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
					//�����������������ĵľ���
					int nowXab=0,nowYab=0;    //�������ֵ����
					int Xab=0,Yab=0;    //�������ֵ����
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
					//�ж��������������ĵľ���
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
		System.out.println("ѡ���Ȩ��:"+nowNum);
		//[���巵��ֵ]
		int site[]=new int[2];
		site[0]=nowX;
		site[1]=nowY;
		return site;
	}
	
	//���������֡�
	public static int ask(String five){
		five=five.replace("3","0");
		//[����>0;�Է�>0]Ȩ�ظ�ֵ:0
		if(five.indexOf("1")!=-1&&five.indexOf("2")!=-1){
			return 0;
		}
		//[����=0;�Է�=0]Ȩ�ظ�ֵ:1
		if(five.indexOf("1")==-1&&five.indexOf("2")==-1){
			return 1;
		}
		int count=1;
		//[����ֻ�м����Ϳո�����]
		if(five.indexOf("1")!=-1){
			for(int i=0;i<5;i++){
				if(five.indexOf("1")!=-1){
					count++;
					five=five.substring(five.indexOf("1")+1);
				}else{
					break;
				}
			}
			//[����=1;�Է�=0]Ȩ�ظ�ֵ2
			if(count==1){
				return 2;
			}
			//[����=2;�Է�=0]Ȩ�ظ�ֵ:4
			if(count==2){
				return 4;
			}
			//[����=3;�Է�=0]Ȩ�ظ�ֵ:8
			if(count==3){
				return 8;
			}
			//[����=4;�Է�=0]Ȩ�ظ�ֵ:32
			if(count==4){
				return 32;
			}
			//[����=5;�Է�=0]Ȩ�ظ�ֵ:512
			if(count==5){
				return 512;
			}
			return 0;
		}
		//[����ֻ�ж��ֺͿո�����]
		if(five.indexOf("2")!=-1){
			for(int i=0;i<5;i++){
				if(five.indexOf("2")!=-1){
					count++;
					five=five.substring(five.indexOf("2")+1);
				}else{
					break;
				}
			}
			//[����=0;�Է�=1]Ȩ�ظ�ֵ:2
			if(count==1){
				return 2;
			}
			//[����=0;�Է�=2]Ȩ�ظ�ֵ:3
			if(count==2){
				return 3;
			}
			//[����=0;�Է�=3]Ȩ�ظ�ֵ:6
			if(count==3){
				return 6;
			}
			//[����=0;�Է�=4]Ȩ�ظ�ֵ:32
			if(count==4){
				return 32;
			}
			//[����=0;�Է�=5]Ȩ�ظ�ֵ:512
			if(count==5){
				return 512;
			}
			return 0;
		}
		return 0;
	}
	
	//�����������Ȩ�ش洢����
	public static void clear(){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
	}
}
