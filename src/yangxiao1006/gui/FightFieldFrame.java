package yangxiao1006.gui;
/**
 * project:【战斗领域】小游戏<br/>
 * description: 游戏主页面<br/>
 * @author 杨啸
 * */


import java.awt.*;
import java.awt.event.*;


import yangxiao1006.behavior.*;
import yangxiao1006.character.*;
import yangxiao1006.gui.*;
import yangxiao1006.thread.*;

public class FightFieldFrame extends Frame{
	
	
	
	public static final Dimension SCREEN_DIMENSION=Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FFF_X=0;
	public static final int FFF_Y=0;
	public static final int FFF_HEIGHT=SCREEN_DIMENSION.height;
	public static final int FFF_WIDTH=SCREEN_DIMENSION.width;
	
	//角色初始位置和大小
	public static final int P1_HEIGHT=300;
	public static final int P1_WIDTH=300;
	public static final int P1_X=0;
	public static final int P1_Y=SCREEN_DIMENSION.height/2;
	
	public static final int P2_HEIGHT=300;
	public static final int P2_WIDTH=300;
	public static final int P2_X=SCREEN_DIMENSION.width-P2_WIDTH;
	public static final int P2_Y=SCREEN_DIMENSION.height/2;
	
	
	//血条蓝条大小
	public static final int STRAND_HEIGHT=20;
	public static final int STRAND_WIDTH=300;
	
	
	
	//双缓冲
	private static Image imgBuffer;
	private static Graphics gBuffer;
	
	//角色
	private static Characters player1;//玩家1
	private static Characters player2;//玩家2
	private static Thread p1_moveThread;//玩家移动线程
	private static Thread p2_moveThread;
	
	//只能有一个对象，使用单例模式
	private static FightFieldFrame fff;//单例模式使用的对象
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
		//这里准备一些对象构造完成之后才能做的事情
		fff.setVisible(true);
		setBounds(FFF_X, FFF_Y, FFF_WIDTH, FFF_HEIGHT);
		
		Dimension d=getSize();
		imgBuffer=createImage(d.width, d.height);//一定要显示之后才能使用
		gBuffer=imgBuffer.getGraphics();
	}
	
	public void initCharacter() {
		player1=new Knight("jack", 100, 100, 3, 1);
		player1.setWeaponBehavior(new SwordBehavior("村里最好的剑"));
		player1.setBounds(P1_X, P1_Y, P1_WIDTH, P1_HEIGHT);
		player1.setMagicBehavior(new HealBehavior());
		
		player2=new Knight("tom", 100, 100, 3, 5);
		player2.setWeaponBehavior(new SwordBehavior("村里第二好的剑"));
		player2.setBounds(P2_X, P2_Y, P2_WIDTH, P2_HEIGHT);
		player2.setDirection(true);
		player1.setMagicBehavior(new HealBehavior());
		
		
		
		p1_moveThread=new Thread(new MoveThread(player1),"p1_moveThread");
		p2_moveThread=new Thread(new MoveThread(player2),"p2_moveThread");
		
		p1_moveThread.start();
		p2_moveThread.start();
	
		
	}
	
	
	
	
	
	/** 绘制角色
	 * @param g 绘图对象
	 * @param c 要绘制的角色
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
		int curHPStrandWidth=(int)(hpRate* ((double)STRAND_WIDTH));//计算血条长度
		int curMPStrandWidth=(int)(mpRate* ((double)STRAND_WIDTH));//计算血条长度
		
		//todo:加上具体数值显示
		
		
		
		//绘制血条
		g.setColor(Color.red);
		g.drawRect(c.getX(), c.getY()-STRAND_HEIGHT*2, STRAND_WIDTH, STRAND_HEIGHT);
		g.fillRect(c.getX(), c.getY()-STRAND_HEIGHT*2,curHPStrandWidth , STRAND_HEIGHT);
		g.setColor(Color.white);
		g.drawString("HP:"+c.getHP()+"/"+c.getHPU(),c.getX(), c.getY()-STRAND_HEIGHT);

		
		
		//绘制魔法条
		g.setColor(Color.blue);
		g.drawRect(c.getX(), c.getY()-STRAND_HEIGHT, STRAND_WIDTH, STRAND_HEIGHT);
		g.fillRect(c.getX(), c.getY()-STRAND_HEIGHT, curMPStrandWidth, STRAND_HEIGHT);
		g.setColor(Color.white);
		g.drawString("MP:"+c.getMP()+"/"+c.getMPU(),c.getX(), c.getY());
		
		
		//绘制名字
		g.setColor(Color.white);
		g.drawString(c.getName(), c.getX(), c.getY()-STRAND_HEIGHT*3);
		
	}
	
	
	public void paint(Graphics g) {
		
		//全都先绘制在缓冲区
		
		//绘制背景
		Image background=getToolkit().getImage("image\\background.jpg");
		if(background!=null) {
			gBuffer.drawImage(background, FFF_X, FFF_Y, FFF_WIDTH, FFF_HEIGHT, this);
		}
		
		
		//绘制人物
		if(player1!=null && player1.getIsAliveFlag()) {
			drawCharacter(gBuffer,player1);
			drawStrand(gBuffer, player1);
		}
		
		if(player2!=null && player2.getIsAliveFlag()) {
			drawCharacter(gBuffer,player2);
			drawStrand(gBuffer, player2);
			
		}
		//由于使用了背景图片，所以不必特地清空背景
		g.drawImage(imgBuffer, 0, 0, this);
		
		
	}
	
	
	public void update(Graphics g) {
        //覆盖原本的方法
		paint(g);
    }
	
	/*******************************************main函数*********************************************/
	public static void main(String args[]) {
		FightFieldFrame f=getInstance("战斗领域");
		f.initFrame();
		//初始化角色
		f.initCharacter();		
		//添加事件监听者
		f.addWindowListener(new MyWindowListener());
		f.addKeyListener(new GamePad(player1,player2,f));

	}
	
	
	
}
