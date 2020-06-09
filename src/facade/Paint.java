package facade;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.Control;
import core.Scene;


public class Paint extends JPanel{
	private static final long serialVersionUID=1L;
	/**
	*@���ƣ�����ͼƬ������
	*@���ܣ���дJPanel���paintComponent����(��ÿ�λ���JPanelʱ������û��Ƴɷֵķ���)��ʵ����JPanel�ϻ���Ŀ��ͼƬ
	*@�÷�������������Ҫ���Ƶ�������ӵ��˶����У����Ƶ��������ݶ��൱��JPanel�ı���ͼ������д����JPanel��(Paint��)��Ϊ����������
	*/

	//����д��paintComponent����
	//[����]�����и�window���repaint()ʱ���Զ�����paintComponentģ��
	//[�ص�]����Ƶ�ͼƬ�Զ������Ȼ��Ƶ�ͼƬ
	public void paintComponent(Graphics g){
		//[����]���汳��ͼ-��ײ�
		//��ͼƬ(����ͼ)��ַ��ȡͼƬ
		Image back=new ImageIcon("img\\back.png").getImage();
		//���Ʊ���ͼ
		g.drawImage(back,0,0,this);

		//[����]��������������
		//��ͼƬ(����ͼ)��ַ��ȡͼƬ
		Image black=new ImageIcon("img\\black.png").getImage();
		//��ͼƬ(����ͼ)��ַ��ȡͼƬ
		Image white=new ImageIcon("img\\white.png").getImage();
		//���������ϵ�����
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				//����������������ӵĻ�������
				int x=13+i*52;
				int y=48+j*52;
				//���ƺ���ͼ
				if(Scene.board[i][j]==1){
					g.drawImage(black,x,y,this);
				}
				//���ư���ͼ
				if(Scene.board[i][j]==2){
					g.drawImage(white,x,y,this);
				}
			}
		}

		//[����ѡ��ڷ�/�׷��Ӵ���]
		if(Control.win_choose==true){
			//��ͼƬ(ѡ��ڷ�/�׷�����ͼ)��ַ��ȡͼƬ
			Image choose=new ImageIcon("img\\choose.png").getImage();
			//����ѡ��ڷ�/�׷�����ͼ
			g.drawImage(choose,152,365,this);
		}
		
		//[����ʤ��ҳ��]
		if(Control.win_winner==true){
			//���ʤ����Ϊ�ڷ�
			if(Control.winner==1){
				//��ͼƬ(ʤ������ͼ)��ַ��ȡͼƬ
				Image choose=new ImageIcon("img\\winBlack.png").getImage();
				//����ʤ������ͼ
				g.drawImage(choose,234,325,this);
			}
			else if(Control.winner==2){
				//��ͼƬ(ʤ������ͼ)��ַ��ȡͼƬ
				Image choose=new ImageIcon("img\\winWhite.png").getImage();
				//����ʤ������ͼ
				g.drawImage(choose,234,325,this);
			}
		}
		
		//[���ƺڷ�������ʾͼ]
		if(Control.forbid==true){
			//��ͼƬ(ʤ������ͼ)��ַ��ȡͼƬ
			Image choose=new ImageIcon("img\\forbid.png").getImage();
			//����ʤ������ͼ
			g.drawImage(choose,10,325,this);
		}

		//[����ѡ�л�����]
		//��ͼƬ(ѡ�п�)��ַ��ȡͼƬ
		Image robot=new ImageIcon("img\\robot.png").getImage();
		if(Control.robot_type==1){
			//��1�Ż�����λ�û���ѡ�п�
			g.drawImage(robot,596,846,this);
		}else if(Control.robot_type==2){
			//��2�Ż�����λ�û���ѡ�п�
			g.drawImage(robot,666,846,this);
		}else if(Control.robot_type==3){
			//��3�Ż�����λ�û���ѡ�п�
			g.drawImage(robot,736,846,this);
		}
	}
}
