package yangxiao1006.behavior.magic;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.character.*;
/**
 * 无魔法
 * 
 */
public class NoneMagicBehavior implements MagicBehavior{

	private String name="虚无魔法";
	private Image appearance;//魔法的图标
	
	public static final int COST=0;
	public static final String APPEARANCE_PATH="image\\Magic\\None.png";
	
	public NoneMagicBehavior() {}
	public NoneMagicBehavior(String _name) {
		name=_name;//可以给魔法起别名
	}
	

	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return 未定
	 */
	public int useMagic(Characters attacker, Characters victim) {
		
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public Image getAppearance() {
		// TODO Auto-generated method stub
		appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_PATH);
		return appearance;
	}

}
