package yangxiao1006.behavior;

import yangxiao1006.character.*;

public class HealBehavior implements MagicBehavior{

	private String name="治疗魔法";
	public static final int COST=6;
	public static final int HEAL_VALUE=6;
	
	
	
	/**
	 * 魔法：自我恢复，消耗魔力，恢复生命
	 */
	public int useMagic(Characters attacker, Characters victim) {
		//自我恢复
		attacker.setMP(attacker.getMP()-COST);//扣除魔力
		attacker.setHP(attacker.getHP()+HEAL_VALUE);//恢复血量
		System.out.println(attacker.getName()+"恢复了"+HEAL_VALUE+"点HP");
		attacker.display();
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
