package yangxiao1006.behavior;

import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.character.*;

public class SwordBehavior implements WeaponBehavior {

	private String name="剑";
	private Image appearance;
	public static final int DAMAGE=6;//武器基础威力
	public static final int ATTACK_RANGE=200;//武器攻击距离,单位px
	public SwordBehavior() {}
	public SwordBehavior(String _name) {
		name=_name;//剑，岂能无名OVO
	}
	
	
	
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
		appearance=Toolkit.getDefaultToolkit().getImage("image\\Weapon\\Bow.gif");
		return appearance;
	}

}
