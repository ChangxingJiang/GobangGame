package core;


public class Forbid{
	/**
	 * @���ƣ����ַ�����
	 * @���ܣ��жϸ�λ���Ƿ���ڽ��ֵ����
	 * @�÷��������ǽ��ֵ�ʱ�򣬷���true�����ǽ��ֵ�ʱ�򣬷���false
	 */
	
	//���������������
	//[����ͳ���Ա���]
	//�õ㹹�ɵĻ�������
	private static int threeCount=0;
	//�õ㹹�ɵ���������
	private static int fourCount=0;
	//[������ʱ�Ա���]
	//��(��)����(��)������һ���ո�ĺ�����
	static int up=0;
	static int down=0;
	//��(��)����(��)��������ո�ĺ�����
	static int spaceUp=0;
	static int spaceDown=0;
	//��(��)����(��)�����ӽ���λ��Ϊ���ӻ��Ե
	static boolean endUp=false;
	static boolean endDown=false;
	
	//�����ֲ�ѯģ�顿���������������ꡢ��ǰ����ɫ
	public static boolean forbid(int boardX,int boardY,int color){
		//[��ǰ����Ϊ����ʱ�Զ����Խ���]
		if(color==2){
			return true;
		}
		
		//[���ͳ���Ա���]
		threeCount=0;
		fourCount=0;
		
		//[ˮƽ�������]
		//ͳ��ˮƽ������������
		countW_E(boardX,boardY);
		//�����Ƿ���ֻ���
		if(analyseThree()==true){
			threeCount++;
		}
		//�����Ƿ��������
		if(analyseFour()==true){
			fourCount++;
		}
		//�����Ƿ���ֳ���
		if(analyseLong()==true){
			return false;
		}
		//�����Ƿ����Ľ���(����)
		if(analyseLineFour()==true){
			return false;
		}
		//�����Ƿ����Ľ���(�ⵣ��)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[��ֱ�������]
		//ͳ�ƴ�ֱ������������
		countN_S(boardX,boardY);
		//�����Ƿ���ֻ���
		if(analyseThree()==true){
			threeCount++;
		}
		//�����Ƿ��������
		if(analyseFour()==true){
			fourCount++;
		}
		//�����Ƿ���ֳ���
		if(analyseLong()==true){
			return false;
		}
		//�����Ƿ����Ľ���(����)
		if(analyseLineFour()==true){
			return false;
		}
		//�����Ƿ����Ľ���(�ⵣ��)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[����-���·������]
		//ͳ������-���·�����������
		countNW_SE(boardX,boardY);
		//�����Ƿ���ֻ���
		if(analyseThree()==true){
			threeCount++;
		}
		//�����Ƿ��������
		if(analyseFour()==true){
			fourCount++;
		}
		//�����Ƿ���ֳ���
		if(analyseLong()==true){
			return false;
		}
		//�����Ƿ����Ľ���(����)
		if(analyseLineFour()==true){
			return false;
		}
		//�����Ƿ����Ľ���(�ⵣ��)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[����-���·������]
		//ͳ������-���·�����������
		countNE_SW(boardX,boardY);
		//�����Ƿ���ֻ���
		if(analyseThree()==true){
			threeCount++;
		}
		//�����Ƿ��������
		if(analyseFour()==true){
			fourCount++;
		}
		//�����Ƿ���ֳ���
		if(analyseLong()==true){
			return false;
		}
		//�����Ƿ����Ľ���(����)
		if(analyseLineFour()==true){
			return false;
		}
		//�����Ƿ����Ľ���(�ⵣ��)
		if(analyseCarryFour()==true){
			return false;
		}
		
		//[������������]
		if(threeCount>=2){
			return false;
		}
		
		//[������������]
		if(fourCount>=2){
			return false;
		}
		
		return true;
	}
	
	//��ʶ��ͳ��ˮƽ������������
	public static void countW_E(int boardX,int boardY){
		//[��շ�����ʱ�Ա���]
		clear();
		//[�����Ƿ��Ѱ����ո���ʱ����]
		boolean space=false;
		//[�����õ��������]
		for(int i=0;i<5;i++){
			if(boardX>i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX-1-i][boardY]==1){
					//���û����Ծ���ո�
					if(space==false){
						up++;
						spaceUp++;
					}
					//����Ѿ���Ծ���ո�
					else{
						up++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX-1-i][boardY]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endUp=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endUp=true;
				break;
			}
		}
		//[�����Ƿ��Ѱ����ո���ʱ����]
		space=false;
		//[�����õ��Ҳ�����]
		for(int i=0;i<5;i++){
			if(boardX<14-i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX+1+i][boardY]==1){
					//���û����Ծ���ո�
					if(space==false){
						down++;
						spaceDown++;
					}
					//����Ѿ���Ծ���ո�
					else{
						down++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX+1+i][boardY]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endDown=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//��ʶ��ͳ�ƴ�ֱ������������
	public static void countN_S(int boardX,int boardY){
		//[��շ�����ʱ�Ա���]
		clear();
		//[�����Ƿ��Ѱ����ո���ʱ����]
		boolean space=false;
		//[�����õ��Ϸ������]
		for(int i=0;i<5;i++){
			if(boardY>i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX][boardY-1-i]==1){
					//���û����Ծ���ո�
					if(space==false){
						up++;
						spaceUp++;
					}
					//����Ѿ���Ծ���ո�
					else{
						up++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX][boardY-1-i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endUp=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[�����õ��·������]
		for(int i=0;i<5;i++){
			if(boardY<14-i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX][boardY+1+i]==1){
					//���û����Ծ���ո�
					if(space==false){
						down++;
						spaceDown++;
					}
					//����Ѿ���Ծ���ո�
					else{
						down++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX][boardY+1+i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endDown=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//��ʶ��ͳ������-���·�����������
	public static void countNW_SE(int boardX,int boardY){
		//[��շ�����ʱ�Ա���]
		clear();
		//[�����Ƿ��Ѱ����ո���ʱ����]
		boolean space=false;
		//[�����õ����Ϸ������]
		for(int i=0;i<5;i++){
			if(boardX>i&&boardY>i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX-1-i][boardY-1-i]==1){
					//���û����Ծ���ո�
					if(space==false){
						up++;
						spaceUp++;
					}
					//����Ѿ���Ծ���ո�
					else{
						up++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX-1-i][boardY-1-i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endUp=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[�����õ����·������]
		for(int i=0;i<5;i++){
			if(boardX<14-i&&boardY<14-i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX+1+i][boardY+1+i]==1){
					//���û����Ծ���ո�
					if(space==false){
						down++;
						spaceDown++;
					}
					//����Ѿ���Ծ���ո�
					else{
						down++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX+1+i][boardY+1+i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endDown=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//��ʶ��ͳ������-���·�����������
	public static void countNE_SW(int boardX,int boardY){
		//[��շ�����ʱ�Ա���]
		clear();
		//[�����Ƿ��Ѱ����ո���ʱ����]
		boolean space=false;
		//[�����õ����Ϸ������]
		for(int i=0;i<5;i++){
			if(boardX>i&&boardY<14-i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX-1-i][boardY+1+i]==1){
					//���û����Ծ���ո�
					if(space==false){
						up++;
						spaceUp++;
					}
					//����Ѿ���Ծ���ո�
					else{
						up++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX-1-i][boardY+1+i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endUp=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endUp=true;
				break;
			}
		}
		space=false;
		//[�����õ����·������]
		for(int i=0;i<5;i++){
			if(boardX<14-i&&boardY>i){
				//����λ��Ϊ����ʱ
				if(Scene.board[boardX+1+i][boardY-1-i]==1){
					//���û����Ծ���ո�
					if(space==false){
						down++;
						spaceDown++;
					}
					//����Ѿ���Ծ���ո�
					else{
						down++;
					}
				}
				//����λ��Ϊ�ո�ʱ
				else if(Scene.board[boardX+1+i][boardY-1-i]==0){
					//���û����Ծ���ո�
					if(space==false){
						space=true;
					}
					//����Ѿ���Ծ���ո�
					else{
						break;
					}
				}
				//����λ��Ϊ����ʱ
				else{
					endDown=true;
					break;
				}
			}
			//���������̱�Եʱ
			else{
				endDown=true;
				break;
			}
		}
	}
	
	//����������շ�����ʱ�Ա���
	public static void clear(){
		up=0;
		down=0;
		spaceUp=0;
		spaceDown=0;
		endUp=false;
		endDown=false;
	}
	
	//���������ж��Ƿ���ֻ���
	public static boolean analyseThree(){
		//(��������)��(��������)��(��������)����
		if(spaceUp+spaceDown+1==3&&(down>spaceDown||endDown==false)&&(up>spaceUp||endUp==false)){
			return true;
		}
		//(����������)��(����������)
		if(up==2&&endUp==false&&spaceDown==0&&(down>0||endDown==false)){
			return true;
		}
		//(����������)��(����������)
		if(down==2&&endDown==false&&spaceUp==0&&(up>0||endUp==false)){
			return true;
		}
		//(����������)
		if(up==1&&spaceUp==0&&endUp==false&&spaceDown==1&&(down>1||endDown==false)){
			return true;
		}
		//(����������)
		if(down==1&&spaceDown==0&&endDown==false&&spaceUp==1&&(up>1||endUp==false)){
			return true;
		}
		return false;
	}
	
	//���������ж��Ƿ��������
	public static boolean analyseFour(){
		//(������)��(������)��(������)��(�����)
		if(spaceUp+spaceDown+1==4&&((up>spaceUp||endUp==false)||(down>spaceDown||endDown==false))){
			return true;
		}
		//(��������)��(��������)��(��������)
		if(down==3&&spaceDown<3&&(endDown==false||(up>0||endUp==false))){
			return true;
		}
		//(�������)��(�������)��(�������)
		if(up==3&&spaceUp<3&&(endUp==false||(down>0||endDown==false))){
			return true;
		}
		//(��������)��(��������)
		if(spaceUp==1&&down==2&&spaceDown<2&&(endDown==false||(up>1||endUp==false))){
			return true;
		}
		//(��������)��(��������)
		if(spaceDown==1&&up==2&&spaceUp<2&&(endUp==false||(down>1||endDown==false))){
			return true;
		}
		//(��������)
		if(up==1&&spaceUp==0&&spaceDown==2&&(endUp==false||(down>2||endDown==false))){
			return true;
		}
		//(��������)
		if(down==1&&spaceDown==0&&spaceUp==2&&(endDown==false||(up>2||endUp==false))){
			return true;
		}
		return false;
	}
	
	//���������ж��Ƿ���ֳ���
	public static boolean analyseLong(){
		if(spaceUp+spaceDown+1>=6){
			return true;
		}
		return false;
	}
	
	//���������ж��Ƿ�������Ľ���(����)
	public static boolean analyseLineFour(){
		//(������������)
		if(spaceUp==0&&spaceDown==1&&up==2&&down==3){
			return true;
		}
		//(������������)
		else if(spaceDown==0&&spaceUp==1&&down==2&&up==3){
			return true;
		}
		return false;
	}
	
	//���������ж��Ƿ�������Ľ���(�ⵣ��)
	public static boolean analyseCarryFour(){
		//(�����������)
		if(spaceUp==1&&spaceDown==1&&up==2&&down==2){
			return true;
		}
		return false;
	}
}