package yangxiao1006.behavior;

import yangxiao1006.character.*;

public class HealBehavior implements MagicBehavior{

	private String name="����ħ��";
	public static final int COST=6;
	public static final int HEAL_VALUE=6;
	
	
	
	/**
	 * ħ�������һָ�������ħ�����ָ�����
	 */
	public int useMagic(Characters attacker, Characters victim) {
		//���һָ�
		attacker.setMP(attacker.getMP()-COST);//�۳�ħ��
		attacker.setHP(attacker.getHP()+HEAL_VALUE);//�ָ�Ѫ��
		System.out.println(attacker.getName()+"�ָ���"+HEAL_VALUE+"��HP");
		attacker.display();
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
