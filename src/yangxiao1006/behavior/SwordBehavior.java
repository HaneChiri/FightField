package yangxiao1006.behavior;

import yangxiao1006.character.Characters;

public class SwordBehavior implements WeaponBehavior {

	private int damage=6;//武器威力
	private String name="剑";
	
	public SwordBehavior() {}
	public SwordBehavior(String _name) {
		name=_name;//剑，岂能无名OVO
	}
	
	
	
	@Override
	public int useWeapon(Characters attacker,Characters victim) {
		int attackDamage=damage+attacker.getDamage();//造成的伤害为攻击者的伤害加上武器威力
		int finalDamage=victim.hitBy(attacker, attackDamage);
		
		
		System.out.println(attacker.getName()+"使用"+name+"对"+victim.getName()+"造成了"+finalDamage+"点伤害");
		return finalDamage;//返回最终伤害
	}
	@Override
	public String getName() {return name;}

}
