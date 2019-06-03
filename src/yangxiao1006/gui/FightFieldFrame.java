package yangxiao1006.gui;
/**
 * project:【战斗领域】小游戏<br/>
 * description: 游戏主页面，由此类启动<br/>
 * @author 杨啸
 * */


import java.awt.*;
import java.awt.event.*;


import yangxiao1006.behavior.*;
import yangxiao1006.behavior.magic.HealBehavior;
import yangxiao1006.behavior.magic.InvisibleBehavior;
import yangxiao1006.behavior.magic.NoneMagicBehavior;
import yangxiao1006.behavior.magic.TeleportBehavior;
import yangxiao1006.behavior.weapon.AxeBehavior;
import yangxiao1006.behavior.weapon.BowBehavior;
import yangxiao1006.behavior.weapon.KnifeBehavior;
import yangxiao1006.behavior.weapon.SwordBehavior;
import yangxiao1006.character.*;
import yangxiao1006.gui.*;
import yangxiao1006.thread.*;

public class FightFieldFrame extends Frame{
	
	
	//窗口位置与大小
	public static final Dimension SCREEN_DIMENSION=Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FFF_X=0;
	public static final int FFF_Y=0;
	public static final int FFF_HEIGHT=SCREEN_DIMENSION.height;
	public static final int FFF_WIDTH=SCREEN_DIMENSION.width;
	
	//玩家初始位置和大小
	public static final int P1_HEIGHT=300;
	public static final int P1_WIDTH=300;
	public static final int P1_X=0;
	public static final int P1_Y=SCREEN_DIMENSION.height/2;
	
	public static final int P2_HEIGHT=300;
	public static final int P2_WIDTH=300;
	public static final int P2_X=SCREEN_DIMENSION.width-P2_WIDTH;
	public static final int P2_Y=SCREEN_DIMENSION.height/2;
	
	
	//属性条的大小
	public static final int STRAND_HEIGHT=20;
	public static final int STRAND_WIDTH=200;
	
	
	//栏位大小和Y坐标
	public static final int SLOT_HEIGHT=100;
	public static final int SLOT_WIDTH=100;
	public static final int SLOT_Y=SCREEN_DIMENSION.height-SLOT_HEIGHT-100;
	public static final int MARGIN=100;//栏边空白
	
	//武器栏的位置
	public static final int WEAPON_SLOT_P1_X=MARGIN;
	public static final int WEAPON_SLOT_P2_X=SCREEN_DIMENSION.width-SLOT_WIDTH-MARGIN;
	//魔法栏的位置
	public static final int MAGIC_SLOT_P1_X=WEAPON_SLOT_P1_X+SLOT_WIDTH+MARGIN;
	public static final int MAGIC_SLOT_P2_X=WEAPON_SLOT_P2_X-SLOT_WIDTH-MARGIN;
	
	//双缓冲
	private static Image imgBuffer;
	private static Graphics gBuffer;
	
	//角色
	private static Characters player1;//玩家1
	private static Characters player2;//玩家2
	private static Thread p1_moveThread;//玩家移动线程，防止键位冲突
	private static Thread p2_moveThread;
	
	/*****************************************初始化方法区****************************************************************/
	
	//只能有一个窗体对象，使用单例模式
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
		imgBuffer=createImage(d.width, d.height);//一定要显示之后(fff.setVisible(true);)才能使用
		gBuffer=imgBuffer.getGraphics();
	}
	
	/**
	 * 初始化玩家的属性以及武器、魔法
	 */
	public void initCharacter() {
		
		WeaponBehavior[] ws1=new WeaponBehavior[]{
				new SwordBehavior("誓约胜利之剑"),
				new BowBehavior("复合弓"),
				new AxeBehavior("军用手斧"),
				new KnifeBehavior("淬毒匕首")
		};
		MagicBehavior[] ms1=new MagicBehavior[] {
				new HealBehavior("圣愈术"),
				new InvisibleBehavior("潜行"),
				new NoneMagicBehavior("什么都不做的")
		};
		
		//初始化玩家1
		player1=new King("jack", 100, 100, 3, 5,ws1,ms1);
		player1.setBounds(P1_X, P1_Y, P1_WIDTH, P1_HEIGHT);
		player1.setDirection(false);
		
		//初始化玩家2
		
		WeaponBehavior[] ws2=new WeaponBehavior[]{
				new SwordBehavior(),
				new BowBehavior(),
				new AxeBehavior(),
				new KnifeBehavior()
		};
		MagicBehavior[] ms2=new MagicBehavior[] {
				new HealBehavior(),
				new InvisibleBehavior(),
				new TeleportBehavior()
		};
		
		player2=new Troll("tom", 100, 100, 3, 5,ws2,ms2);
		player2.setBounds(P2_X, P2_Y, P2_WIDTH, P2_HEIGHT);
		player2.setDirection(true);
			
		//初始化移动线程
		p1_moveThread=new Thread(new MoveThread(player1),"p1_moveThread");
		p2_moveThread=new Thread(new MoveThread(player2),"p2_moveThread");
		
		p1_moveThread.start();
		p2_moveThread.start();
	
		
	}
	
	/*****************************************绘制方法区****************************************************************/
	
	
	/** 绘制角色
	 * @param g 绘图对象
	 * @param c 要绘制的角色
	 * 
	 */
	public void drawCharacter(Graphics g,Characters c) {
		if(c.getStatus()==Characters.ST_INVISIBLE) return;//如果隐身就不画
		Image appearance=c.getAppearance();
		if(appearance!=null) {
			g.drawImage(appearance,c.getX(),c.getY(),c.getWidth(),c.getHeight(),this);
		}
	}
	
	public void drawStrand(Graphics g,Characters c) {
		
		if(c.getStatus()==Characters.ST_INVISIBLE) return;//隐身不绘制
		
		double hpRate=((double)c.getHP())/c.getHPU();
		double mpRate=((double)c.getMP())/c.getMPU();
		int curHPStrandWidth=(int)(hpRate* ((double)STRAND_WIDTH));//计算血条长度
		int curMPStrandWidth=(int)(mpRate* ((double)STRAND_WIDTH));//计算血条长度
		
		//todo:加上具体数值显示
		
		g.setFont(new Font("宋体", Font.BOLD, 25));
		
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
	/**
	 * 绘制绝对位置属性条
	 * @param g 画笔对象
	 */
	public void drawStrand(Graphics g) {
		double p1_hpRate=((double)player1.getHP())/player1.getHPU();
		double p1_mpRate=((double)player1.getMP())/player1.getMPU();
		int p1_curHPStrandWidth=(int)(p1_hpRate* ((double)STRAND_WIDTH));//计算血条长度
		int p1_curMPStrandWidth=(int)(p1_mpRate* ((double)STRAND_WIDTH));//计算血条长度
		

		int strandX=100;
		int strandY=200;
		
		
		//绘制p1血条
		
		g.setColor(Color.red);
		g.drawRect(strandX, strandY-STRAND_HEIGHT*2, STRAND_WIDTH, STRAND_HEIGHT);
		g.fillRect(strandX, strandY-STRAND_HEIGHT*2,p1_curHPStrandWidth , STRAND_HEIGHT);
		g.setColor(Color.white);
		g.drawString("HP:"+player1.getHP()+"/"+player1.getHPU(),strandX, strandY-STRAND_HEIGHT);

		
		
		//绘制p1魔法条
		g.setColor(Color.blue);
		g.drawRect(strandX, strandY-STRAND_HEIGHT, STRAND_WIDTH, STRAND_HEIGHT);
		g.fillRect(strandX, strandY-STRAND_HEIGHT, p1_curMPStrandWidth, STRAND_HEIGHT);
		g.setColor(Color.white);
		g.drawString("MP:"+player1.getMP()+"/"+player1.getMPU(),strandX, strandY);
		
		
		//绘制p1名字
		g.setColor(Color.white);
		g.drawString(player1.getName(),strandX, strandY-STRAND_HEIGHT*3);
		
		//p2暂时不弄，原理一样，现在使用的是相对位置属性条
		
		
	}
	
	
	public void drawSlot(Graphics g) {
			
		//绘制玩家1武器栏
		g.drawImage(player1.getWeapon().getAppearance(), WEAPON_SLOT_P1_X,SLOT_Y,SLOT_WIDTH, SLOT_HEIGHT, this);
		g.drawString(player1.getWeapon().getName(), WEAPON_SLOT_P1_X,SLOT_Y-STRAND_HEIGHT);
		
		//绘制玩家2武器栏
		g.drawImage(player2.getWeapon().getAppearance(), WEAPON_SLOT_P2_X,SLOT_Y,SLOT_WIDTH, SLOT_HEIGHT, this);
		g.drawString(player2.getWeapon().getName(), WEAPON_SLOT_P2_X,SLOT_Y-STRAND_HEIGHT);
		
		//绘制玩家1魔法栏
		g.drawImage(player1.getMagic().getAppearance(), MAGIC_SLOT_P1_X, SLOT_Y, SLOT_WIDTH, SLOT_HEIGHT, this);
		g.drawString(player1.getMagic().getName(),MAGIC_SLOT_P1_X,SLOT_Y-STRAND_HEIGHT);
		
		//绘制玩家2魔法栏
		g.drawImage(player2.getMagic().getAppearance(), MAGIC_SLOT_P2_X, SLOT_Y, SLOT_WIDTH, SLOT_HEIGHT, this);
		g.drawString(player2.getMagic().getName(),MAGIC_SLOT_P2_X,SLOT_Y-STRAND_HEIGHT);
	}
	
	
	
	
	public void paint(Graphics g) {
		
		//全都先绘制在缓冲区
		
		//绘制背景
		Image background=getToolkit().getImage("image\\background.jpg");
		if(background!=null) {
			gBuffer.drawImage(background, FFF_X, FFF_Y, FFF_WIDTH, FFF_HEIGHT, this);
		}
		
		
		//绘制人物
		if(player1!=null) {
			drawCharacter(gBuffer,player1);
			drawStrand(gBuffer, player1);
		}
		
		if(player2!=null) {
			drawCharacter(gBuffer,player2);
			drawStrand(gBuffer, player2);
			
		}
		
		drawSlot(gBuffer);
		
		
		
		//drawStrand(gBuffer);//绘制绝对位置的属性条，由于没有什么技术含量就只做了一个示例
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
		//新建时钟线程，用于游戏中的周期性属性检查
		Thread clockThread=new Thread(new ClockThread(player1, player2, fff));
		
		clockThread.start();
	}
	
	
	
}
