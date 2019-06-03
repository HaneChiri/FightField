package yangxiao1006.behavior.weapon;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.WeaponBehavior;
import yangxiao1006.character.*;
/**
 * 剑
 * 实现武器接口
 * 威力中等，攻击距离中等
 * @author 杨啸
 *
 */
public class SwordBehavior implements WeaponBehavior {

	private String name="剑";
	private Image appearance;
	private static final int DAMAGE=6;//武器基础威力
	private static final int ATTACK_RANGE=200;//武器攻击距离,单位px
	
	private static final String APPEARANCE_PATH="image\\Weapon\\Sword.png";
	
	public SwordBehavior() {}
	public SwordBehavior(String _name) {
		name=_name;//剑，岂能无名OVO
	}
	
	
	/**
	 * 使用武器攻击
	 * @param attacker 攻击者
	 * @param victim 被攻击者
	 */
	@Override
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
