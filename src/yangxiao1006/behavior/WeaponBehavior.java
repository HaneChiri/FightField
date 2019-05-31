package yangxiao1006.behavior;

import yangxiao1006.character.Characters;

public interface WeaponBehavior {
	/**
	 * 使用武器
	 * @param attacker 武器持有者
	 * @param victim 被攻击者
	 * @return 造成的真实伤害
	 */
	public int useWeapon(Characters attacker,Characters victim);//使用武器
	public String getName();
}
