package core;

public class Scene{
	
	//�����嵱ǰ���Ʊ�����
	public static int color=1;//1Ϊ�úڷ���2Ϊ�ð׷�
	public static int board[][]=new int[15][15];//0Ϊ�հף�1Ϊ���壬2Ϊ����
	public static int user=1;//�û�ѡ�����/����
	
	//���������֧�ֱ�����
	public static int lastX_uesr=-1;
	public static int lastY_uesr=-1;
	public static int lastX_robot=-1;
	public static int lastY_robot=-1;
	
	//��������ʼ����
	static{
		clear();
	}
	
	//������������ݡ�
	public static void clear(){
		//��Ϊ�úڷ�����
		color=1;
		//�������������
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				board[i][j]=0;
			}
		}
	}
	
	//�����ӡ�ִ��һ�����Ӳ���
	public static boolean move(int boardX,int boardY){
		if(boardX>=0&&boardX<=14&&boardY>=0&&boardY<=14){
			//[�жϸ�λ���Ƿ���������]
			if(board[boardX][boardY]!=0){
				return false;
			}
			if(color==1){
				if(user==1){
					lastX_uesr=boardX;
					lastY_uesr=boardY;
				}
				else{
					lastX_robot=boardX;
					lastY_robot=boardY;
				}
				board[boardX][boardY]=1;
				color=2;
				return true;
			}
			else{
				if(user==2){
					lastX_uesr=boardX;
					lastY_uesr=boardY;
				}
				else{
					lastX_robot=boardX;
					lastY_robot=boardY;
				}
				board[boardX][boardY]=2;
				color=1;
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	
	//��������ִ��һ�λ������
	public static boolean regret(){
		//[�жϻ����¼�Ƿ��ѱ�ʹ��]
		if(lastX_uesr==-1||lastY_uesr==-1||lastX_robot==-1||lastY_robot==-1){
			return false;
		}
		//[�Ƴ������ϱ����������]
		board[lastX_uesr][lastY_uesr]=0;
		board[lastX_robot][lastY_robot]=0;
		//[��������¼]
		lastX_uesr=-1;
		lastY_uesr=-1;
		lastX_robot=-1;
		lastY_robot=-1;
		//[����Ѿ��������������ʼ]
		if(Control.start==false){
			Control.start=true;
		}
		return true;
	}
}
