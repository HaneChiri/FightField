package yangxiao1006.thread;

import java.awt.Graphics;

import yangxiao1006.behavior.magic.InvisibleBehavior;
import yangxiao1006.character.Characters;
import yangxiao1006.gui.FightFieldFrame;

public class ClockThread implements Runnable{

	
	private Characters player1;//玩家1
	private Characters player2;//玩家2
	private FightFieldFrame fff;
	private int interval=1000;//时钟周期
	
	
	public ClockThread(Characters p1, Characters p2, FightFieldFrame f) {
		player1=p1;
		player2=p2;
		fff=f;
	}
	
	
	
	
	public void cheackStatus(Characters c) {
		switch (c.getStatus()) {
		case Characters.ST_INVISIBLE://隐身魔法每个周期扣除一定的魔力
			if(c.incMP(-InvisibleBehavior.COST)==-1) {//如果魔力不够
				c.setStatus(Characters.ST_NORMAL);
			}
			break;

		default:
			break;
		}

	}
	
	
	
	
	
	@Override
	public void run() {
		while(true) {
			//做这个周期要做的事情
			cheackStatus(player1);
			cheackStatus(player2);
			
			
			
			fff.repaint();
			//等待下一个周期
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
