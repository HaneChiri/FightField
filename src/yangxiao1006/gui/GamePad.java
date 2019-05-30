package yangxiao1006.gui;
import java.awt.*;
import java.awt.event.*;

import yangxiao1006.character.*;
/*��Ϸ�ֱ���
 * ���ڽ���λ���ɫ�Ķ�����Ӧ����
 * 
 * */



public class GamePad implements KeyListener{

	Characters player1;//���1
	Characters player2;//���2
	FightFieldFrame fff;
	
	
	public GamePad(Characters p1,Characters p2,FightFieldFrame f) {
		player1=p1;
		player2=p2;
		fff=f;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		
		switch (code) {
		case KeyEvent.VK_J:
			player1.fight(player2);
			player2.display();
			break;
		case KeyEvent.VK_A:
			player1.moveLeft();
			break;
		case KeyEvent.VK_D:
			player1.moveRight();
			break;

		case KeyEvent.VK_NUMPAD1:
			player2.fight(player1);
			player1.display();
			
			break;
		default:
			break;
		}
		fff.repaint();
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

