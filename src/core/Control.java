package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import robot.Medium;
import robot.Possible;
import robot.Primary;
import facade.First;
import facade.Second;
import facade.Window;

public class Control implements ActionListener{
	/**
	 * @���ƣ����Ŀ�����
	 * @���ܣ�������Ϣ���յ��������ݵ���Ϣ��ʵ�ֶ�Ӧ�Ĺ���
	 */
	
	//�����崰����ر�����
	//[���ڶ������](����facade.Window��)
	Window window;
	//[�򿪴������]
	//ѡ��ڷ�/�׷�����
	public static boolean win_choose=false;
	//ʤ������
	public static boolean win_winner=false;

	//��������Ʊ�����
	//�Ծ�״̬����(trueΪ��ʼ,falseΪû��ʼ)
	public static boolean start=false;
	//ѡ�еĻ���������
	public static int robot_type=3;
	//��������������
	public static boolean robot=false;
	//ʤ����
	public static int winner=0;
	//����
	public static boolean forbid=false;
	
	//�����嶨ʱ����
	//��ʱ������(����Timer��)
	Timer time=new Timer(20,this);
	//ѭ������������
	int times=0;
	
	//������������ȡ���ڶ�������
	public Control(Window window){
		//ת����򴰿ڶ���
		this.window=window;
		//������ʱ��
		time.start();
	}
	
	//����Ϣ����������
	public void click(int winX,int winY){
		//���ȴ���:ѡ��ڷ�/�׷�����
		//�жϡ�ѡ��ڷ�/�׷����ڡ��Ƿ��
		if(win_choose==true){
			//[��ť�ж�]ѡ��ڷ�
			if(winX>=154&&winX<=346&&winY>=392&&winY<=499){
				//�����û�ѡ��Ϊ�ڷ�
				Scene.user=1;
				//���á�ѡ��ڷ�/�׷����ڡ�Ϊ�ر�
				win_choose=false;
				//���öԾ�״̬����Ϊ��ʼ
				start=true;
				//�������ػ�
				window.repaint();
			}
			//[��ť�ж�]ѡ��׷�
			if(winX>=456&&winX<=657&&winY>=392&&winY<=499){
				//�����û�ѡ��Ϊ�׷�
				Scene.user=2;
				//���á�ѡ��ڷ�/�׷����ڡ�Ϊ�ر�
				win_choose=false;
				//���öԾ�״̬����Ϊ��ʼ
				start=true;
				//�������ػ�
				window.repaint();
				//����ִ��һ����������
				robotGo();
			}
			//�������������������Ч�����ص����֣����д������ٲ�ѯ������ť
			return;
		}
		
		//[�����ж�]
		//�жϵ��λ���Ƿ���������
		if(winX>=13&&winX<=793&&winY>=48&&winY<=828){
			//����������Ļ�����Ӧ����������
			int boardX=(winX-13)/52;
			int boardY=(winY-48)/52;
			//ִ��һ�����Ӳ���
			move(boardX,boardY);
		}
		
		//[��ť�ж�]��ʼ��ť
		if(winX>=18&&winX<=166&&winY>=850&&winY<=909){
			//�����������
			Scene.clear();
			//�����������
			Record.clear();
			//���á�ѡ��ڷ�/�׷����ڡ�Ϊ��
			win_choose=true;
			//����ʤ������Ϊ�ر�
			win_winner=false;
			winner=0;
			//�������ػ�
			window.repaint();
		}
		
		//[��ť�ж�]���尴ť
		if(winX>=188&&winX<=345&&winY>=850&&winY<=909){
			//ִ�л������
			regret();
		}
		
		//[��ť�ж�]������ѡ��-������1
		if(winX>=600&&winX<=659&&winY>=850&&winY<=909){
			//����ѡ�еĻ���������Ϊ1
			robot_type=1;
			//�������ػ�
			window.repaint();
		}
		
		//[��ť�ж�]������ѡ��-������2
		if(winX>=670&&winX<=729&&winY>=850&&winY<=909){
			//����ѡ�еĻ���������Ϊ2
			robot_type=2;
			//�������ػ�
			window.repaint();
		}
		
		//[��ť�ж�]������ѡ��-������3
		if(winX>=740&&winX<=799&&winY>=850&&winY<=909){
			//����ѡ�еĻ���������Ϊ2
			robot_type=3;
			//�������ػ�
			window.repaint();
		}
		
		//[�жϵ�ǰ�Ƿ���ɵ�������]
		if(Scene.color!=Scene.user){
			//������ִ��һ�����Ӳ���
			robot=true;
			times=0;
		}
	}
	
	//����Ϣ�����Ҽ����
	public void right(int winX,int winY){
		//[��ť�ж�]����������-������1
		if(winX>=600&&winX<=659&&winY>=850&&winY<=909){
			new First(window,Window.screenX,Window.screenY);
		}
		//[��ť�ж�]����������-������2
		if(winX>=670&&winX<=729&&winY>=850&&winY<=909){
			new Second(window,Window.screenX,Window.screenY);
		}
	}
	
	//��ʱ����Ϣ��������
	public void actionPerformed(ActionEvent e){
		//��Ϣ��ԴΪʱ����Ϣ
		if(e.getSource()==time){
			//ʱ����Ϣ�����ۼ�
			times++;
			//���������������
			if(robot==true&&times>10){
				robotGo();
			}
			//���������������
			if(winner!=0&&times>50){
				//�������ػ�
				window.repaint();
			}
			if(forbid==true&&times>100){
				forbid=false;
				//�������ػ�
				window.repaint();
			}
        }
	}
	
	//��������������ִ��һ����������
	public void robotGo(){
		robot=false;
		//[��ѡ�еĻ���������Ϊ1�����]
		if(robot_type==1){
			//ʹ�û�����1��������λ��
			int site[]=Primary.go();
			//ִ�����Ӳ���
			move(site[0],site[1]);
		}
		
		//[��ѡ�еĻ���������Ϊ2�����]
		else if(robot_type==2){
			//ʹ�û�����2��������λ��
			int site[]=Medium.mediumGo();
			//ִ�����Ӳ���
			move(site[0],site[1]);
		}
		//[��ѡ�еĻ���������Ϊ3�����]
		else if(robot_type==3){
			//ʹ�û�����2��������λ��
			int site[]=Possible.go();
			//ִ�����Ӳ���
			move(site[0],site[1]);
		}
	}
	
	//�����������Ӳ���
	public boolean move(int boardX,int boardY){
		//[�ж϶Ծ�״̬�����Ƿ�Ϊ��ʼ]
		if(start==false){
			return false;
		}
		
		//[�ж�����λ���Ƿ����]
		if(Forbid.forbid(boardX,boardY,Scene.color)){
			//д����ֵ�������
			Record.add(boardX,boardY);
			//ִ�������������
			if(Scene.move(boardX,boardY)){
				//�������ػ�
				window.repaint();
				//�ж��Ƿ����ʤ��(����core.Winner��)
				if(Winner.winner()){
					//�Զ��洢����
					Record.save();
					times=0;
					//��ʤ��Ϊ�ڷ������
					if(Winner.winner==1){
						//����ʤ��
						winner=1;
						//����ʤ���ߴ���Ϊ��
						win_winner=true;
					}
					//��ʤ��Ϊ�׷������
					if(Winner.winner==2){
						//����ʤ��
						winner=2;
						//����ʤ���ߴ���Ϊ��
						win_winner=true;
					}
					//���öԾ�״̬����Ϊ����
					start=false;
				}
				return true;
			}
			else{
				return false;
			}
		}
		else{
			forbid=true;
			times=0;
			//�������ػ�
			window.repaint();
			System.out.println("������֣������������λ��");
			return false;
		}
	}
	
	//���������������
	public boolean regret(){
		//����ɾ����������
		Record.delete();
		Record.delete();
		//����ʤ������Ϊ�ر�
		win_winner=false;
		winner=0;
		//[ִ�л������ ]
		if(Scene.regret()){
			//�������ػ�
			window.repaint();
			return true;
		}
		else{
			return false;
		}
	}
}
