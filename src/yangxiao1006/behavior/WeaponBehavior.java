package yangxiao1006.behavior;

import yangxiao1006.character.Characters;

public interface WeaponBehavior {
	/**
	 * ʹ������
	 * @param attacker ����������
	 * @param victim ��������
	 * @return ��ɵ���ʵ�˺�
	 */
	public int useWeapon(Characters attacker,Characters victim);//ʹ������
	public String getName();
}
