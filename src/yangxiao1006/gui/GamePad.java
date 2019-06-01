package yangxiao1006.gui;
import java.awt.*;
import java.awt.event.*;

import yangxiao1006.character.*;
/*游戏手柄类
 * 用于将键位与角色的动作对应起来
 * 
 * */



public class GamePad implements KeyListener{

	Characters player1;//玩家1
	Characters player2;//玩家2
	FightFieldFrame fff;
	
	
	

	public GamePad(Characters p1, Characters p2, FightFieldFrame f) {
		// TODO Auto-generated constructor stub
		player1=p1;
		player2=p2;
		fff=f;
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		
		switch (code) {
		case KeyEvent.VK_J://玩家1攻击
			player1.fight(player2);
			player2.display();
			break;
		case KeyEvent.VK_K://玩家1使用魔法
			player1.performMagic(player2);
			break;
		case KeyEvent.VK_A://玩家1左
			player1.setMoveLeftFlag(true);
			player1.setDirection(true);//false为朝右，true为朝左
			break;
		case KeyEvent.VK_D://玩家1右
			player1.setMoveRightFlag(true);
			player1.setDirection(false);
			break;		
		
			
		/****************************************************************/
		case KeyEvent.VK_NUMPAD1://玩家2攻击
			player2.fight(player1);
			player1.display();
			
			break;
		case KeyEvent.VK_NUMPAD2://玩家2使用魔法
			player2.performMagic(player1);
			break;
		case KeyEvent.VK_LEFT://玩家2左
			player2.setMoveLeftFlag(true);
			player2.setDirection(true);//false为朝右，true为朝左
			break;
			
		case KeyEvent.VK_RIGHT://玩家2右
			player2.setMoveRightFlag(true);
			player2.setDirection(false);
			break;
		
		default:
			break;
		}
		fff.repaint();//重绘
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		
		switch (code) {
		case KeyEvent.VK_J:

			
			break;
		case KeyEvent.VK_A://左
			player1.setMoveLeftFlag(false);
			
			break;
		case KeyEvent.VK_D://右
			player1.setMoveRightFlag(false);
			
			break;
			
		case KeyEvent.VK_K:

			break;
		case KeyEvent.VK_NUMPAD1:

			
			break;
		case KeyEvent.VK_LEFT:
			player2.setMoveLeftFlag(false);
			break;
			
		case KeyEvent.VK_RIGHT:
			player2.setMoveRightFlag(false);
			break;
		default:
			break;
		}
		fff.repaint();//重绘
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

