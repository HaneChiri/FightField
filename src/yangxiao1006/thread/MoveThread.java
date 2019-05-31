package yangxiao1006.thread;

import yangxiao1006.character.Characters;

public class MoveThread implements Runnable{

	private Characters character;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MoveThread(Characters c) {
		character=c;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//线程只负责调用，让角色自己判断移动
			character.moveRight();
			character.moveLeft();
		}
	}

}
