package yangxiao1006.gui;

import java.awt.*;

public class StartGameFrame extends Frame{
	
	//窗口位置与大小
	public static final Dimension SCREEN_DIMENSION=Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SGF_X=0;
	public static final int SGF_Y=0;
	public static final int SGF_HEIGHT=SCREEN_DIMENSION.height;
	public static final int SGF_WIDTH=SCREEN_DIMENSION.width;
	
	
	public StartGameFrame() {}
	public StartGameFrame(String title) {super(title);}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartGameFrame sgf=new StartGameFrame("游戏开始页面");
		//初始化窗口
		sgf.setBounds(SGF_X, SGF_Y, SGF_WIDTH, SGF_HEIGHT);
		sgf.setVisible(true);
		sgf.addWindowListener(new MyWindowListener());
		
		//初始化选项
		
		Button startButton=new Button("hello");
		startButton.setBounds(100,100,300,300);
		sgf.add(startButton);
		startButton.setVisible(true);
		
	}

}
