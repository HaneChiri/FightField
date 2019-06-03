package yangxiao1006.behavior.weapon;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.WeaponBehavior;
import yangxiao1006.character.Characters;
/**
 * 斧
 * 实现武器接口
 * 威力大，但是攻击范围小
 * @author 杨啸
 *
 */
public class AxeBehavior implements WeaponBehavior{

	private String name="斧";
	private Image appearance;
	
	
	private static final int DAMAGE=10;//武器基础威力
	private static final int ATTACK_RANGE=50;//武器攻击距离,单位px
	private static final String APPEARANCE_PATH="image\\Weapon\\Axe.png";
	
	public AxeBehavior() {}
	public AxeBehavior(String _name) {
		name=_name;
	}
	
	/**
	 * 使用武器攻击
	 * @param attacker 攻击者
	 * @param victim 被攻击者
	 */
	public int useWeapon(Characters attacker,Characters victim) {
		int attackDamage=DAMAGE+attacker.getDamage();//造成的伤害为攻击者的伤害加上武器威力
		int finalDamage=victim.hitBy(attacker, attackDamage);
		
		System.out.println(attacker.getName()+"使用"+name+"对"+victim.getName()+"造成了"+finalDamage+"点伤害");
		return finalDamage;//返回最终伤害
	}
	@Override
	public String getName() {return name;}
	@Override
	public int getAttackRange() {return ATTACK_RANGE;}
	@Override
	public Image getAppearance() {
		// TODO Auto-generated method stub
		appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_PATH);
		return appearance;
	}
}
