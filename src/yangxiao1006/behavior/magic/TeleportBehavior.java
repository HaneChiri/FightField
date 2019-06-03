package yangxiao1006.behavior.magic;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.character.*;
/**
 * 传送魔法
 * 将自己传送到敌人身边
 */
public class TeleportBehavior implements MagicBehavior{

	private String name="传送魔法";
	private Image appearance;//魔法的图标
	
	public static final int COST=60;
	public static final String APPEARANCE_PATH="image\\Magic\\Teleport.png";
	
	public TeleportBehavior() {}
	public TeleportBehavior(String _name) {
		name=_name;//可以给魔法起别名
	}
	

	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return 未定
	 */
	public int useMagic(Characters attacker, Characters victim) {
		if(attacker.incMP(-COST)==0) {//如果魔力扣除成功

			attacker.setBounds(victim.getX(), victim.getY(), attacker.getWidth(), attacker.getHeight());
			System.out.println(attacker.getName()+"传送到了"+victim.getName()+"身边");
			
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
