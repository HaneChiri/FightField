package yangxiao1006.behavior;

import yangxiao1006.character.Characters;

public interface MagicBehavior {
	/**
	 * ʹ��ħ��
	 * @param attacker ħ���ͷ���
	 * @param victim ħ��������
	 * @return
	 */
	public int useMagic(Characters attacker,Characters victim);//ʹ��ħ��
	public String getName();//��ȡħ������
}
