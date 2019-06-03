package yangxiao1006.behavior.magic;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.character.*;
/**
 * 隐身魔法
 * 不显示角色和属性条，持续消耗魔力，再次使用此魔法时解除
 */
public class InvisibleBehavior implements MagicBehavior{

	private String name="隐身魔法";
	private Image appearance;//魔法的图标
	
	public static final int COST=30;//每秒消耗
	public static final String APPEARANCE_PATH="image\\Magic\\Invisible.png";
	
	public InvisibleBehavior() {}
	public InvisibleBehavior(String _name) {
		name=_name;//可以给魔法起别名
	}
	

	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return 未定
	 */
	public int useMagic(Characters attacker, Characters victim) {
		if(attacker.getMP()>COST) {
			attacker.setMP(attacker.getMP()-COST);//扣除魔力
			attacker.setStatus(Characters.ST_INVISIBLE);
		}
		return 0;
	}

	public String getName() {return name;}
	public Image getAppearance() {
		appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_PATH);
		return appearance;
	}

}
