package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Docu{
	/**
	 * ģ�������ļ�������
	 * [��Ϣģ��]
	 * ��ȡ��ǰ·��:readPath();
	 * ��ȡ�ļ�����:length(String path);
	 * �ж��ļ��Ƿ����:exist(String path);
	 * [��ȡģ��]
	 * ��ͨ�ļ�:read(String path,long index,int extent);
	 * tXT�ļ�:readTXT(String path,long line);
	 * ��ȡһ��TXT�ĵ�������:readTXTIndex(String path,long line);
	 * [д��ģ��]
	 * ��ʼ��:initialize(long length,String path);
	 * ����д���ַ���:write(String path,long index,String read);
	 * ����д������:write(String path,long index,int num);
	 * ĩβд���ַ���:write(String path,String read);
	 * ĩβд������:(String path,int num);
	 * �޼��д��:justwrite(String path,long index,String read);
	 * [����ģ��]
	 * ����·��:foundPath(String path);
	 * �����ļ�:foundFile(String path,boolean rebuild);
	 * ���������ļ�:foundFile(String path,long size,boolean rebuild);
	 * �����ļ�������:foundFile(String path);
	 * �����ļ�:copyFile(String path1,String path2);
	 * ɾ���ļ�:deleteFile(String path);
	 * Ѱ����С�ձ��:leastNumber(String path,String name,String suffix);
	 * [ģ�����]
	 * tool.Stri(�ַ���������)
	 * [����޸�]
	 * ���޸��ļ�д�볬���ļ���С��д��ַ���bug
	 * ���ļ���дʱ�ļ������ڱ���bug
	 * ������Ѱ����С�ձ�Ź���
	 * ������ĩβд�����ݹ���
	 */
	//[TXT��д�ݴ����]
	static long sline=0;
	static long sindex=0;
	
	//����Ϣ���ж��ļ��Ƿ����(�����ļ�·���������Ƿ������Ϊ�ļ�(���ڷ���true�������ڷ���false))
	public static boolean exist(String path){
		File file=new File(path);
		boolean Test=false;
		boolean existFile=file.exists();                              //�ж��ļ��Ƿ����
		boolean isFile=file.isFile();                                 //�ж��Ƿ�Ϊ�ļ�
		if(existFile==true&&isFile==true){
			Test=true;
		}
		return Test;
	}
	
	//����Ϣ����ȡ�ļ�����(�����ļ�·��)
	public static long length(String path){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return -1;
		}
		//[��ȡ�ļ�����]
		File filelong=new File(path);
		long length=filelong.length();                                //��ȡ�ļ�����(�ֽ���)
		return length;
	}
	
	//����ȡ����ȡ�ļ�(�����ļ�·������ȡָ�룬��ȡ����)
	public static String read(String path,long index,int extent){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return "";
		}
		//[�ж϶�ȡ�����Ƿ����ļ���]
		String back=new String("");
		RandomAccessFile file=null;
		File filelong=new File(path);
		long length=filelong.length();                                //��ȡ�ļ�����(�ֽ���)
		if((index+extent)>length){                                    //��ȡ���곬���ļ�����
			if(length>index){                                         //����ȡ����λ���ļ��ڣ���ȡ���ļ���β
				extent=(int)(length-index);
			}
			else{
				return null;
			}
		}
		if((index+extent)>length||index<0||extent<=0){                //�ж��Ƿ���϶�ȡ��׼
			if(index<0){                                              //��ȡ����Ϊ����
				System.out.print("��ȡ����Ϊ������");
			}
			if(extent<=0){                                            //��ȡ����Ϊ����
				System.out.print("��ȡ����Ϊ������");
			}
			System.out.println("��ȡʧ�ܣ�");
		}
		//[�ļ���ȡ]
		else{
			try{
				file=new RandomAccessFile(path, "r");                 //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
				file.seek(index);                                     //�����ļ��Ŀ�ʼλ���Ƶ�indexλ��
				byte[] bytes=new byte[extent];
				file.read(bytes);                                     //��һ�ζ�ȡ���ֽ�������byteread
				back=new String(bytes);
			}
			catch(IOException e){
				System.out.println("��ȡ��ȡ���������쳣");
			}
			finally{
				if(file!=null){
					try{file.close();}
					catch(IOException e1){
						System.out.println("��ȡ��ȡ�ر��쳣");
					}
				}
			}
		}
		return back;
	}
	
	//����ȡ����ȡһ��TXT�ĵ�(�����ļ�·���������ȡ����)(�����������1024�ֽ�)
	public static String readTXT(String path,long line){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return "";
		}
		//[�����ȡ��ʱ����]
		String temp=null;                                             //��ʱ��ȡ����
		long index=0;                                                 //����ָ��
		int search=0;                                                 //��������
		long safe=0;                                                  //ѭ������
		//[�洢��¼Ϊ��ǰ��]
		if(sline==line&&sindex!=0){
			//��ȡ��ǰ������
			temp=read(path,sindex,1024);
			if(temp==null){
				return null;
			}
			//�������з�λ��
			search=temp.indexOf("\r\n");			                  //��temp��������ǰ"\r\n"��>=0Ϊ����,=-1Ϊ������
			//�޸���ʱ�洢��¼
			if(search==-1){                                           //��δ������\r\n
				sindex=sindex+temp.getBytes().length;
			}
			if(search==0){                                            //��������"\r\n"λ�����ֽ�
				temp=null;
				sindex=sindex+2;
			}
			if(search>0){
				temp=temp.substring(0,search);
				sindex=sindex+temp.getBytes().length+2;               //�洢��һ����ʼ���ļ�����
			}
			sline=line+1;                                             //�洢��һ������
		}
		//[û�д洢��¼��洢��¼�ڶ�ȡ������]
		else{
			//��մ洢��¼
			if(sline==0||sline>line){                           
				sline=0;
				sindex=0;
			}
			long i=sline;                                             //��ʱ������¼
			index=sindex;
			//�ƶ�ָ�뵽Ŀ����
			while(safe<1000000){
				temp=read(path,index,1024);
				if(temp==null){
					return null;
				}
				search=temp.indexOf("\r\n");			              //��temp��������ǰ"\r\n"��>=0Ϊ����,=-1Ϊ������
				if(search==-1){                                       //��δ������\r\n
					sline=i+1;                                        //�洢��һ������
					sindex=sindex+temp.getBytes().length;
				}
				if(search>=0){                                        //������\r\n
					temp=temp.substring(0,search);
					index=index+temp.getBytes().length+2;
				}
				if(i==line){
					sline=i+1;                                        //�洢��һ������
					sindex=index;                                     //�洢��һ����ʼ���ļ�����
					break;
				}
				i++;
				safe++;                                               //ѭ����ȫ������¼
			}
		}
		return temp;
	}
	
	//����ȡ����ȡһ��TXT�ĵ�������(�����ļ�·���������ȡ����)(�����������1024�ֽ�)
	public static long readTXTIndex(String path,long line){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return -1;
		}
		//[�����ȡ��ʱ����]
		long index=0;                                                 //����ָ��
		long i=0;
		//�ƶ�ָ�뵽Ŀ����
		while(index<1000){
			String temp=read(path,index,1024);
			if(temp==null){
				return -1;
			}
			int search=temp.indexOf("\r\n");			              //��temp��������ǰ"\r\n"��>=0Ϊ����,=-1Ϊ������
			if(search==-1){                                       //��δ������\r\n
				System.out.print("���г����쳣");
				index=index+1024;
			}
			if(i==line){
				break;
			}
			i++;
			index=index+temp.getBytes().length+2;
		}
		return index;
	}
	
	//��д�롿�ļ���"��"��ʼ��(����Ŀ���ļ����ȣ������ļ�·��)
	public static boolean initialize(long length,String path){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ļ���ʼ��]
		File file=new File(path);
		long now=file.length();
		for(long i=now;i<length;){
			if((length-i)>=128){                                      //��ʣ��128���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"                                                                                                                                ")){
					return false;
				}
				i=i+128;
			}else if((length-i)>=64){                                 //��ʣ��64���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"                                                                ")){
					return false;
				}
				i=i+64;
			}else if((length-i)>=32){                                 //��ʣ��32���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"                                ")){
					return false;
				}
				i=i+16;
			}else if((length-i)>=16){                                 //��ʣ��16���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"                ")){
					return false;
				}
				i=i+16;
			}else if((length-i)>=8){                                  //��ʣ��8���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"        ")){
					return false;
				}
				i=i+8;
			}else if((length-i)>=4){                                  //��ʣ��4���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"    ")){
					return false;
				}
				i=i+4;
			}else if((length-i)>=2){                                  //��ʣ��2���ֽ�������Ҫ��дʱ
				if(!justwrite(path,i,"  ")){
					return false;
				}
				i=i+2;
			}else{                                                    //��ʣ��1���ֽ���Ҫ��дʱ
				if(!justwrite(path,i," ")){
					return false;
				}
				i++;
			}
		}
		return true;
	}
	
	//��д�롿�ļ�����ļ���Сд��(�����ļ�·����д�����ꡢд������)
	public static boolean write(String path,long index,String read){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ж�д�������Ƿ����ļ���]
		File files=new File(path);
		long now=files.length();
		if(now<index+read.length()){
			if(!initialize((long)index+read.length(),path)){
				System.out.println("�ļ���ʼ���쳣");
				return false;
			}
		}
		//[�ļ�д��]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //��contentת��Ϊ�ֽڴ�
		try {
            file=new RandomAccessFile(path,"rw");                     //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
			file.seek(index);                                         //�����ļ��Ŀ�ʼλ���Ƶ�indexλ�á�
			file.write(bytes);                                        //�ļ�д��
		}
		catch(IOException e){
			System.out.println("�ļ�д���쳣");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //�ر��ļ�
					return true;
				}
				catch(IOException e){
					System.out.println("�ļ��ر��쳣");
					return false;
				}
			}
		}
		return true;
    }
	
	//��д�롿�ļ�����ļ���Сд��(�����ļ�·����д�����ꡢд������)
	public static boolean write(String path,long index,int num){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ж�д�������Ƿ����ļ���]
		File files=new File(path);
		long now=files.length();
		if(now<index+8){
			if(!initialize(index+8,path)){
				System.out.println("�ļ���ʼ���쳣");
				return false;
			}
		}
		//[�ļ�д��]
		RandomAccessFile file=null;
		String read=""+num;
		byte bytes[]=read.getBytes();                                 //��contentת��Ϊ�ֽڴ�
		try {
	        file=new RandomAccessFile(path,"rw");                     //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
			file.seek(index);                                         //�����ļ��Ŀ�ʼλ���Ƶ�indexλ�á�
			file.write(bytes);                                        //�ļ�д��
		}
		catch(IOException e){
			System.out.println("�ļ�д���쳣");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //�ر��ļ�
					return true;
				}
				catch(IOException e){
					System.out.println("�ļ��ر��쳣");
					return false;
				}
			}
		}
		return true;
    }
	
	//��д�롿�ļ��޼��д��(�����ļ�·����д�����ꡢд������)
	public static boolean justwrite(String path,long index,String read){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ļ�д��]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //��contentת��Ϊ�ֽڴ�
		try {
	        file=new RandomAccessFile(path,"rw");                     //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
			file.seek(index);                                         //�����ļ��Ŀ�ʼλ���Ƶ�indexλ�á�
			file.write(bytes);                                        //�ļ�д��
		}
		catch(IOException e){
			System.out.println("�ļ�д���쳣");
			return false;
	    }
		finally{
			if(file!=null){
				try{
					file.close();                                     //�ر��ļ�
					return true;
				}
				catch(IOException e){
					System.out.println("�ļ��ر��쳣");
					return false;
				}
			}
		}
		return true;
	}
	
	//��д�롿�ļ�д���ļ�ĩβ(�����ļ�·����д������)
	public static boolean write(String path,String read){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ļ�д��]
		RandomAccessFile file=null;
		byte bytes[]=read.getBytes();                                 //��contentת��Ϊ�ֽڴ�
		try {
            file=new RandomAccessFile(path,"rw");                     //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
			long fileLength=file.length();                            //�ļ����ȣ��ֽ���
			file.seek(fileLength);                                    //�����ļ��Ŀ�ʼλ���Ƶ��ļ�ĩβ
			file.write(bytes);                                        //�ļ�д��
		}
		catch(IOException e){
			System.out.println("�ļ�д���쳣");
			return false;
        }
		finally{
			if(file!=null){
				try{
					file.close();                                     //�ر��ļ�
					return true;
				}
				catch(IOException e){
					System.out.println("�ļ��ر��쳣");
					return false;
				}
			}
		}
		return true;
    }
	
	//��д�롿�ļ�д���ļ�ĩβ(�����ļ�·����д������)
	public static boolean write(String path,int num){
		//[�ж��ļ��Ƿ����]
		if(!exist(path)){
			System.out.print("�ļ��������쳣");
			return false;
		}
		//[�ļ�д��]
		RandomAccessFile file=null;
		String read=""+num;
		byte bytes[]=read.getBytes();                                 //��contentת��Ϊ�ֽڴ�
		try{
			file=new RandomAccessFile(path,"rw");                     //��һ����������ļ�������ֻ����ʽ("rw"Ϊ��д)
			long fileLength=file.length();                            //�ļ����ȣ��ֽ���
			file.seek(fileLength);                                    //�����ļ��Ŀ�ʼλ���Ƶ��ļ�ĩβ
			file.write(bytes);                                        //�ļ�д��
		}
		catch(IOException e){
			System.out.println("�ļ�д���쳣");
			return false;
		}
		finally{
			if(file!=null){
				try{
					file.close();                                     //�ر��ļ�
					return true;
				}
				catch(IOException e){
					System.out.println("�ļ��ر��쳣");
					return false;
				}
			}
		}
		return true;
	}
	
	//����Ϣ����ȡ��ǰ��ַ(���ص�ǰ�������ڵ�ַ)
	public static String readPath(){
		String path=new String("");
		File file=new File("."); 
        try{
			path=file.getCanonicalPath();                             //�õ���ǰ��ַ
		}
		catch(IOException e){
			System.out.println("��ȡ��ǰ��ַ�쳣");
		}
 		return path;
	}
	
	//�������������ļ���(�����ļ��е�ַ·��)
	public static void foundPath(String path){
		File file=new File(path);
		if(!file.exists()){                                           //�ж��ļ����Ƿ����
			file.mkdir();                                             //�����ļ���
	    }
	}
	
	//�������������ļ��������в��ؽ�(���봴���ļ��ĵ�ַ)
	public static boolean foundFile(String path){
		boolean temp=true,execute=true;                               //������ʱ������ִ�б���
		File file=new File(path);
		temp=file.exists();                                           //�ж��ļ��Ƿ���ڣ�falseΪ������
		if(temp==true){
			return false;
		}
		temp=file.getParentFile().exists();                           //�ж��ļ�����Ŀ¼�Ƿ���ڣ�trueΪ����
		if(temp==false){                                              //����Ŀ¼������
			temp=file.getParentFile().mkdirs();                       //������Ŀ¼
			if(temp==false){                                          //����Ŀ¼����ʧ��
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //�����ļ�
			}
			catch(IOException e){
				System.out.println("�ļ�����ʧ��");
				return false;
			}
		}
		return true;
	}
	
	//�������������ļ�(���봴���ļ��ĵ�ַ���Ƿ��ؽ�(trueΪ�滻ԭ�ļ���falseΪ����ԭ�ļ�))
	public static boolean foundFile(String path,boolean rebuild){
		boolean temp=true,execute=true;                               //������ʱ������ִ�б���
		File file=new File(path);
		temp=file.exists();                                           //�ж��ļ��Ƿ���ڣ�falseΪ������
		if(temp==true){
			if(rebuild==false){                                       //�ļ������ұ���ԭ�ļ�
				return false;
			}
			else{
				if(!deleteFile(path)){
					System.out.println("ɾ�������ļ��쳣");//ɾ��ԭ�ļ�
					return false;
				}
			}
		}
		temp=file.getParentFile().exists();                           //�ж��ļ�����Ŀ¼�Ƿ���ڣ�trueΪ����
		if(temp==false){                                              //����Ŀ¼������
			temp=file.getParentFile().mkdirs();                       //������Ŀ¼
			if(temp==false){                                          //����Ŀ¼����ʧ��
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //�����ļ�
			}
			catch(IOException e){
				System.out.println("�ļ�����ʧ��");
				return false;
			}
		}
		return true;
	}
	
	//�������������ļ�(���봴���ļ��ĵ�ַ����ʼ���ļ�������Ƿ��ؽ�(trueΪ�滻ԭ�ļ���falseΪ����ԭ�ļ�))
	public static boolean foundFile(String path,long size,boolean rebuild){
		boolean temp=true,execute=true;                               //������ʱ������ִ�б���
		File file=new File(path);
		temp=file.exists();                                           //�ж��ļ��Ƿ���ڣ�falseΪ������
		if(temp==true){
			if(rebuild==false){                                       //�ļ������ұ���ԭ�ļ�
				return false;
			}
			else{
				if(!deleteFile(path)){
					System.out.println("ɾ�������ļ��쳣");//ɾ��ԭ�ļ�
					return false;
				}
			}
		}
		temp=file.getParentFile().exists();                           //�ж��ļ�����Ŀ¼�Ƿ���ڣ�trueΪ����
		if(temp==false){                                              //����Ŀ¼������
			temp=file.getParentFile().mkdirs();                       //������Ŀ¼
			if(temp==false){                                          //����Ŀ¼����ʧ��
				execute=false;
			}
		}
		if(execute==true){
			try{  
				file.createNewFile();                                 //�����ļ�
			}
			catch(IOException e){
				System.out.println("�ļ�����ʧ��");
				return false;
			}
			if(initialize(size,path)){
				return true;
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	//�������������ļ�(����Ŀ��·������Դ·��)
	public static boolean copyFile(String path1,String path2){
		//[����Ŀ��·������]
		if(!exist(path1)){
			foundFile(path1,0,true);
		}
		//[�����ļ�]
		File file1=new File(path1);
		File file2=new File(path2);
		FileInputStream inStream=null;
		FileOutputStream outStream=null;
		FileChannel in=null;
        FileChannel out=null;
        try{
        	inStream=new FileInputStream(file2);
        	outStream=new FileOutputStream(file1);
        	in=inStream.getChannel();//�õ���Ӧ���ļ�ͨ��
        	out=outStream.getChannel();//�õ���Ӧ���ļ�ͨ��
        	in.transferTo(0,in.size(),out);//��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��
        }
        catch(IOException e){
        	e.printStackTrace();
        	return false;
        }
        finally{
        	try{
        		inStream.close();
        		in.close();
        		outStream.close();
        		out.close();
        	}
        	catch(IOException e){
        		e.printStackTrace();
        		return false;
        	}
        }
        return true;
	}
	
	//���ļ���ɾ���ļ�(����ɾ���ļ�·���������Ƿ�ɾ���ɹ�)
	public static boolean deleteFile(String path){  
		File file=new File(path);
		boolean temp=file.exists();                                   //�ж�Ŀ¼���ļ��Ƿ���ڣ�falseΪ���� 
		if(temp==true){                                                
			temp=file.isFile();                                       //�ж��Ƿ�Ϊ�ļ�
			if(temp==true){
				file.delete();                                        //ɾ���ļ�
			}
		}
		return temp;
	}
	
	//���ļ���Ѱ���ļ���С�ձ��(�����ļ�·�����ļ���ǰ�벿�֡���׺��������С���)
	public static int leastNumber(String path,String name,String suffix){
		foundPath(path);                                              //ȷ�ϴ��ڸ��ļ���
		String front=path+name;
		for(int i=1;i<999999;i++){
			String temp=front+Integer.toString(i,10)+suffix;
			if(!exist(temp)){
				return i;
			}
		}
		System.out.println("��1000000����δ������Ч�ձ��");
		return -1;
	}
}