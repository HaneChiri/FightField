package yangxiao1006.behavior;

import yangxiao1006.character.Characters;

public interface MagicBehavior {
	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return
	 */
	public int useMagic(Characters attacker,Characters victim);//使用魔法
	public String getName();//获取魔法名字
}
