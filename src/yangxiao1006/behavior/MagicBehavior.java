package yangxiao1006.behavior;

import java.awt.Image;

import yangxiao1006.character.Characters;

public interface MagicBehavior {
	/**
	 * 使用魔法
	 * @param attacker 魔法释放者
	 * @param victim 魔法承受者
	 * @return 未定
	 */
	public int useMagic(Characters attacker,Characters victim);//使用魔法
	public String getName();//获取魔法名字
	public int getCost();//获取魔力消耗
	public Image getAppearance();
}
