package yangxiao1006.behavior.magic;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.character.*;
/**
 * 治疗魔法
 * 自我恢复，消耗魔力，恢复生命
 */
public class HealBehavior implements MagicBehavior{

	private String name="治疗魔法";
	private Image appearance;//魔法的图标
	
	public static final int COST=6;
	public static final int HEAL_VALUE=6;
	public static final String APPEARANCE_PATH="image\\Magic\\Heal.png";
	
	public HealBehavior() {}
	public HealBehavior(String _name) {
		name=_name;//可以给魔法起别名
	}
	

	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return 未定
	 */
	public int useMagic(Characters attacker, Characters victim) {
		//自我恢复
		if(attacker.incMP(-COST)==0) {//如果魔力扣除成功
			attacker.incHP(HEAL_VALUE);//自带防溢出
			System.out.println(attacker.getName()+"恢复了"+HEAL_VALUE+"点HP");
			attacker.display();
		}
		
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
