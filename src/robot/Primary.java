package robot;

import core.Forbid;
import core.Scene;
import core.Setting;

public class Primary{
	/**����������С��(���Գ���)
	 * [����ϵͳ]����ÿһ��ʤ���������еļ����ͶԷ����������������
	 * һ��:20
	 * ����:400
	 * ����:6000
	 * ����:20000
	 * ����:100000
	 * [ѡ��ϵͳ]ͬ������ѡ���һ���ҵ���λ��
	 * [ѧϰ����]��
	 */
	//������������ֱ�����
	public static int mark[][]=new int[15][15];//ÿ���������
	//�Զ���ʼ�����ֱ�
	static{
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
	}
	
	//������һ�η������㡿
	public static int[] go(){
		//[��ռ����]
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				mark[i][j]=0;
			}
		}
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
						int weight=Primary.ask(temp);
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
						int weight=Primary.ask(temp);
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
							int weight=Primary.ask(temp);
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
							int weight=Primary.ask(temp);
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
		//[�����к��塢�а�������]
		if(five.indexOf("1")!=-1&&five.indexOf("2")!=-1){
			return 0;
		}
		//[����û�����ӵ����]
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
		}
		if(count==1){
			return Setting.primary_one;
		}
		if(count==2){
			return Setting.primary_two;
		}
		if(count==3){
			return Setting.primary_three;
		}
		if(count==4){
			return Setting.primary_four;
		}
		if(count==5){
			return Setting.primary_five;
		}
		return 0;
	}
}
