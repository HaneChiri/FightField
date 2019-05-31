package yangxiao1006.gui;
/**
 * project:��ս������С��Ϸ<br/>
 * description: ��Ϸ��ҳ��<br/>
 * @author ��Х
 * */


import java.awt.*;
import java.awt.event.*;
import java.awt.peer.LightweightPeer;

import javax.swing.text.DefaultStyledDocument;

import yangxiao1006.behavior.*;
import yangxiao1006.character.*;
import yangxiao1006.gui.*;
public class FightFieldFrame extends Frame{
	
	
	
	public static final Dimension SCREEN_DIMENSION=Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FFF_X=0;
	public static final int FFF_Y=0;
	public static final int FFF_HEIGHT=SCREEN_DIMENSION.height;
	public static final int FFF_WIDTH=SCREEN_DIMENSION.width;
	
	//��ɫ��ʼλ�úʹ�С
	public static final int P1_HEIGHT=300;
	public static final int P1_WIDTH=300;
	public static final int P1_X=0;
	public static final int P1_Y=SCREEN_DIMENSION.height/2;
	
	public static final int P2_HEIGHT=300;
	public static final int P2_WIDTH=300;
	public static final int P2_X=SCREEN_DIMENSION.width-P2_WIDTH;
	public static final int P2_Y=SCREEN_DIMENSION.height/2;
	
	
	//Ѫ��������С
	public static final int STRAND_HEIGHT=20;
	public static final int STRAND_WIDTH=300;
	
	
	
	//˫����
	private Image imgBuffer;
	private Graphics gBuffer;
	
	
	private static Characters player1;//���1
	private static Characters player2;//���2
	
	//ֻ����һ������ʹ�õ���ģʽ
	private static FightFieldFrame fff;//����ģʽʹ�õĶ���
	private FightFieldFrame(String title) {
		super(title);
	}
	public static FightFieldFrame getInstance(String title) {
		if(fff==null) {
			fff=new FightFieldFrame(title);
		}
		return fff;
	}
	public void initFrame() {
		//����׼��һЩ���������֮�������������
		fff.setVisible(true);
		setBounds(FFF_X, FFF_Y, FFF_WIDTH, FFF_HEIGHT);
		
		Dimension d=getSize();
		imgBuffer=createImage(d.width, d.height);//һ��Ҫ��ʾ֮�����ʹ��
		gBuffer=imgBuffer.getGraphics();
	}
	
	public void initCharacter() {
		player1=new Knight("jack", 100, 100, 3, 1);
		player1.setWeaponBehavior(new SwordBehavior("������õĽ�"));
		player1.setBounds(P1_X, P1_Y, P1_WIDTH, P1_HEIGHT);
		player1.setMagicBehavior(new HealBehavior());
		
		player2=new Knight("tom", 100, 100, 3, 5);
		player2.setWeaponBehavior(new SwordBehavior("����ڶ��õĽ�"));
		player2.setBounds(P2_X, P2_Y, P2_WIDTH, P2_HEIGHT);
		player2.setDirection(true);
	}
	
	
	
	public static void main(String args[]) {
		FightFieldFrame f=getInstance("ս������");
		f.initFrame();
		//��ʼ����ɫ
		f.initCharacter();		
		//����¼�������
		f.addWindowListener(new MyWindowListener());
		f.addKeyListener(new GamePad(player1,player2,f));
	}
	
	/** ���ƽ�ɫ
	 * @param g ��ͼ����
	 * @param c Ҫ���ƵĽ�ɫ
	 * 
	 */
	public void drawCharacter(Graphics g,Characters c) {
		Image appearance=c.getAppearance();
		if(appearance!=null) {
			g.drawImage(appearance,c.getX(),c.getY(),c.getWidth(),c.getHeight(),this);
		}
	}
	
	public void drawStrand(Graphics g,Characters c) {
		
		double hpRate=((double)c.getHP())/c.getHPU();
		double mpRate=((double)c.getMP())/c.getMPU();
		int curHPStrandWidth=(int)(hpRate* ((double)STRAND_WIDTH));//����Ѫ������
		int curMPStrandWidth=(int)(mpRate* ((double)STRAND_WIDTH));//����Ѫ������
		
		//todo:���Ͼ�����ֵ��ʾ
		
		
		//����Ѫ��
		g.setColor(Color.red);
		g.drawRect(c.getX(), c.getY()-STRAND_HEIGHT*2, STRAND_WIDTH, STRAND_HEIGHT);

		g.fillRect(c.getX(), c.getY()-STRAND_HEIGHT*2,curHPStrandWidth , STRAND_HEIGHT);
		
		//����ħ����
		g.setColor(Color.blue);
		g.drawRect(c.getX(), c.getY()-STRAND_HEIGHT, STRAND_WIDTH, STRAND_HEIGHT);
		g.fillRect(c.getX(), c.getY()-STRAND_HEIGHT, curMPStrandWidth, STRAND_HEIGHT);
		
	}
	
	
	public void paint(Graphics g) {
		
		//ȫ���Ȼ����ڻ�����
		
		//���Ʊ���
		Image background=getToolkit().getImage("image\\background.jpg");
		if(background!=null) {
			gBuffer.drawImage(background, FFF_X, FFF_Y, FFF_WIDTH, FFF_HEIGHT, this);
		}
		
		
		//��������
		if(player1!=null) {
			drawCharacter(gBuffer,player1);
			drawStrand(gBuffer, player1);
		}
		
		if(player2!=null) {
			drawCharacter(gBuffer,player2);
			drawStrand(gBuffer, player2);
			
		}
		//����ʹ���˱���ͼƬ�����Բ����ص���ձ���
		g.drawImage(imgBuffer, 0, 0, this);
		
	}
	
	
	public void update(Graphics g) {
        //����ԭ���ķ���
		paint(g);
    }
	
	
}
