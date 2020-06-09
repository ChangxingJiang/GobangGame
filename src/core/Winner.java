package core;

public class Winner{
	/**
	 * @���ƣ�ʤ���ж���
	 * @���ܣ��жϵ�ǰ�������Ƿ����ʤ��
	 */
	//������ʤ�߱�����
	//(1Ϊ�����ʤ,2Ϊ�����ʤ)
	public static int winner=0;
	
	//���ж��Ƿ����ʤ�ߡ�
	public static boolean winner(){
		winner=0;
		//[�жϴ�ֱ�����Ƿ������������]
		if(vertical()){
			return true;
		}
		//[�ж�ˮƽ�����Ƿ������������]
		if(horizontal()){
			return true;
		}
		//[�ж�����-���·����Ƿ������������]
		if(WN_ES()){
			return true;
		}
		//[�ж�����-���·����Ƿ������������]
		if(EN_WS()){
			return true;
		}
		return false;
	}
	
	//���жϡ���ֱ�����Ƿ������������
	private static boolean vertical(){
		//[������ʱ����]
		//���嵱ǰ������ɫ����
		//(1Ϊ����,2Ϊ����)
		int color=0;
		//���嵱ǰ������������
		int count=0;
		
		//[������ѭ��ͳ��]
		for(int i=0;i<15;i++){
			//����������������
			color=0;
			count=0;
			//�����и�Ԫ��ѭ��ͳ��
			for(int j=0;j<15;j++){
				//����Ԫ�ص�Ϊ�ո�����
				if(Scene.board[i][j]==0){
					color=0;
					count=0;
				}
				//����Ԫ�ص�Ϊ��������
				else if(Scene.board[i][j]==1){
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==1){
						count++;
					}
				}
				//����Ԫ�ص�Ϊ��������
				else{
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==2){
						count++;
					}
				}
				//��ǰ������ɫ�Ƿ񳬹�5��
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//���жϡ�ˮƽ�����Ƿ����ʤ��
	private static boolean horizontal(){
		//[������ʱ����]
		//���嵱ǰ������ɫ����
		//(1Ϊ����,2Ϊ����)
		int color=0;
		//���嵱ǰ������������
		int count=0;
		
		//[������ѭ��ͳ��]
		for(int i=0;i<15;i++){
			//����������������
			color=0;
			count=0;
			//�����и�Ԫ��ѭ��ͳ��
			for(int j=0;j<15;j++){
				//����Ԫ�ص�Ϊ�ո�����
				if(Scene.board[j][i]==0){
					color=0;
					count=0;
				}
				//����Ԫ�ص�Ϊ��������
				else if(Scene.board[j][i]==1){
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==1){
						count++;
					}
				}
				//����Ԫ�ص�Ϊ��������
				else{
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==2){
						count++;
					}
				}
				//��ǰ������ɫ�Ƿ񳬹�5��
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//���жϡ�����-���·����Ƿ����ʤ��
	private static boolean WN_ES(){
		//[������ʱ����]
		//���嵱ǰ������ɫ����
		//(1Ϊ����,2Ϊ����)
		int color=0;
		//���嵱ǰ������������
		int count=0;
		
		//[������ѭ��ͳ��]
		for(int i=0;i<29;i++){
			//����������������
			color=0;
			count=0;
			//�����б����������
			int num=0;
			//�����б�е�һ������x����
			int x=0;
			//�����б�е�һ������y����
			int y=0;
			//�����б�е�������������һ�����ӵ�����
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
			//��б������ͬɫ����
			for(int j=0;j<num;j++){
				//����Ԫ�ص�Ϊ�ո�����
				if(Scene.board[x+j][y+j]==0){
					color=0;
					count=0;
				}
				//����Ԫ�ص�Ϊ��������
				else if(Scene.board[x+j][y+j]==1){
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==1){
						count++;
					}
				}
				//����Ԫ�ص�Ϊ��������
				else{
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==2){
						count++;
					}
				}
				//��ǰ������ɫ�Ƿ񳬹�5��
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
	
	//���ж�����-�����Ƿ����ʤ�ߡ�
	private static boolean EN_WS(){
		//[������ʱ����]
		//���嵱ǰ������ɫ����
		//(1Ϊ����,2Ϊ����)
		int color=0;
		//���嵱ǰ������������
		int count=0;
		
		//[������ѭ��ͳ��]
		for(int i=0;i<29;i++){
			//����������������
			color=0;
			count=0;
			//�����б����������
			int num=0;
			//�����б�е�һ������x����
			int x=0;
			//�����б�е�һ������y����
			int y=0;
			//�����б�е�������������һ�����ӵ�����
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
			//��б������ͬɫ����
			for(int j=0;j<num;j++){
				//����Ԫ�ص�Ϊ�ո�����
				if(Scene.board[x-j][y+j]==0){
					color=0;
					count=0;
				}
				//����Ԫ�ص�Ϊ��������
				else if(Scene.board[x-j][y+j]==1){
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==2){
						color=1;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==1){
						count++;
					}
				}
				//����Ԫ�ص�Ϊ��������
				else{
					//����ǰ������ɫΪ�����ո�
					if(color==0||color==1){
						color=2;
						count=1;
					}
					//����ǰ������ɫΪ����
					else if(color==2){
						count++;
					}
				}
				//��ǰ������ɫ�Ƿ񳬹�5��
				if(count>=5){
					winner=color;
					return true;
				}
			}
		}
		return false;
	}
}
