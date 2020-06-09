package facade;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import core.Control;

public class Window extends JFrame implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID=1L;
	/**
	 * @���ƣ�������Ϣ������
	 * @����
	 * [ģ��-������ʾ��]���ƴ���UI����
	 * [ģ��-��Ϣ���յ�����]�����û��ڽ����ϵ���������Ϣ�����ݸ���Ӧģ��
	 * [ģ��-��������ʵ��]ʵ�ֻ�������(�����϶�/��С��/�ر�)
	 */

	//���������г�����
	//[��ʾ������]��ʾ���ֱ���(������Ϊ��λ)
	public static int screenX=0;
	public static int screenY=0;
	//[��������]
	//��������
	public static String name="�������˻�����";
	//���ڳߴ�(������Ϊ��λ)
	public static int winX=810;
	public static int winY=920;
	//�������Ͻ�����(������Ϊ��λ)
	public static int sitX=0;
	public static int sitY=0;

	//�����崰�ڱ�����
	//����ͼƬ������(����facade.Paint��)
	public Paint paint;

	//����ʼ�������ࡿ
	//���Ŀ�����(����core.Control��)
	Control control=new Control(this);

	/**
	 * @������ʾ��ģ��
	 * [����]�������ڶ���ʱ����
	 * [����]���ƴ���UI����(��ȡ��Ļ�ֱ��ʡ����㴰��λ�á����ô���ͼ�ꡢ���岢���ƴ���)
	*/
	//����������������ʾ��(���ڶ�������)
	public Window(){
		//[��ȡ��Ļ�ֱ���]
		//��ʼ��������Toolkit(���Զ�ȡ��ʾ���ֱ���)
		Toolkit tool=getToolkit();
		//��ȡ��ʾ���ֱ��ʵ�Dimension����(dim.widthΪ���,dim.heightΪ�߶�)
		Dimension dim=tool.getScreenSize();
		screenX=dim.width;
		screenY=dim.height;

		//[����λ�ü���]���з���
		//���㴰�����ϽǺ�����
		sitX=(screenX-winX)/ 2;
		//���㴰�����Ͻ�������
		sitY=(screenY-winY)/ 2;

		//[���ô���ͼ��]
		//ʹ��tool���ͼƬ(ͼ��ͼ)��ַ��ȡͼƬ
		Image ico=tool.getImage("ico.png");
		//���ô���ͼ��
		setIconImage(ico);

		//[���ڳߴ�/λ�ö���]
		//���崰������(Win7��ʾ����껬����������ʱ��ʾ��Ԥ����)
		setTitle(name);
		//���崰�ڳߴ�(�̶�����)
		setSize(winX,winY);
		//���崰�����Ͻ�(����ʾ���ֱ��ʸı�)
		setLocation(sitX,sitY);

		//[���ڹ��ܶ���]
		//���崰��ȥ��������(trueΪȥ����falseΪ����)
		setUndecorated(true);
		//���崰����չ״̬ΪĬ��
		setExtendedState(JFrame.NORMAL);
		//���ô���Ϊ��������任��С
		setResizable(false);

		//[������Ϣ����]
		//��������Ϣ(ʹ��MouseListener�ӿ�)
		addMouseListener(this);
		//�������ƶ���Ϣ(ʹ��MouseMotionListener�ӿ�)
		addMouseMotionListener(this);

		//[��ʼ������ͼƬ������]
		//�����������
		paint=new Paint();
		//Ӧ���������
		setContentPane(paint);
		//��岻ʹ�ò��ֹ�����
		paint.setLayout(null);

		//[�����ػ�]
		setVisible(true);
	}

	/**
	 * @��Ϣ���ܵ�����
	 * [����]���������ʱ�Զ�������Ϣ���и�����
	 */
	//�������Ϣ����갴��������ϰ��²��϶�
	public void mouseDragged(MouseEvent e){
		//[�����϶�֧����]
		//��ȡ�϶������еĵ�ǰ���λ������
		site2=e.getPoint();
		//���ô����϶�������
		move();
	}

	//�������Ϣ��������ƶ�������ϵ��ް�������
	public void mouseMoved(MouseEvent e){

	}

	//�������Ϣ����갴��������ϵ���(���²��ͷ�)ʱ����
	public void mouseClicked(MouseEvent e){
		//[���������]
		if(e.getButton()== MouseEvent.BUTTON1){
			//��ȡ�����λ������
			int x=e.getX();
			int y=e.getY();
			//����Ŀ�����ת����Ϣ(����core.Control��)
			control.click(x,y);
			//���رհ�ť������
			close(x,y);
			//�����С����ť������
			minimize(x,y);
		}
		//[�����껬��]
		if(e.getButton()== MouseEvent.BUTTON2){
			//��ȡ���ֵ��λ������
			int x=e.getX();
			int y=e.getY();
			//��ʾץ����
			System.out.println("�������꣺x="+x+";y="+y);
		}
		//[�������Ҽ�]
		if(e.getButton()== MouseEvent.BUTTON3){
			//��ȡ�Ҽ����λ������
			int x=e.getX();
			int y=e.getY();
			//����Ŀ�����ת����Ϣ(����core.Control��)
			control.right(x,y);
			//��ʾץ����
			System.out.println("�Ҽ����꣺x="+x+";y="+y);
		}
	}

	//�������Ϣ�������뵽�����
	public void mousePressed(MouseEvent e){
		//[�����϶�֧����]
		//��ȡ�϶�����ǰ�����λ������
		site1=e.getPoint();
	}

	//�������Ϣ������뿪���
	public void mouseReleased(MouseEvent e){
		//[�����϶�֧����]
		//��ȡ�϶������еĵ�ǰ���λ������
		site2=e.getPoint();
		//���ô����϶�������
		move();
	}

	//�������Ϣ����갴��������ϰ���
	public void mouseEntered(MouseEvent e){

	}

	//�������Ϣ����갴ť��������ͷ�
	public void mouseExited(MouseEvent e){

	}

	/**
	 * @���ڻ�������ʵ��
	 * [����]���û��ڴ�������ʹ�ô��ڻ�������ʱ������Ϣ���յ�������������
	 * [����]�ٴ����϶��ڴ�����С���۴��ڹر�
	 */
	//���������ܡ������϶�
	//[���幦�ܱ���]
	Point site1,site2; //������������

	//[����������]
	public void move(){
		//��ȡ����϶���������
		int startX=site1.x;
		int startY=site1.y;
		//��ȡ����϶��յ������
		int endX=site2.x;
		int endY=site2.y;
		//�ж��϶�����Ƿ��ڱ�����
		if(startY>=0&&startY<=40){
			//�������귢���仯ʱ�����������Ͻ�����������Ӧ����
			if(startX!=endX){
				sitX=sitX+(endX-startX);
			}
			//�������귢���仯ʱ�����������Ͻ�����������Ӧ����
			if(startY!=endY){
				sitY=sitY+(endY-startY);
			}
			//���¶��崰��λ��
			setLocation(sitX,sitY);
			//������ʾ����
			setVisible(true);
		}
	}

	//�����ܡ����ڹر�
	public void close(int x,int y){
		//�жϵ��λ���Ƿ��ڹرհ�ť��
		if(x>=769&&x<=800&&y>=5&&y<=35){
			//ִ�йرճ������
			System.exit(0);
		}
	}

	//�����ܡ�������С��
	public void minimize(int x,int y){
		//�жϵ��λ���Ƿ�����С����ť��
		if(x>=731&&x<=762&&y>=5&&y<=35){
			//ִ�д�����С��
			setExtendedState(JFrame.ICONIFIED);
		}
	}
}