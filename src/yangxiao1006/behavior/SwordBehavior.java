package yangxiao1006.behavior;

import yangxiao1006.character.*;

public class SwordBehavior implements WeaponBehavior {

	private String name="��";
	public static final int DAMAGE=6;//������������
	
	public SwordBehavior() {}
	public SwordBehavior(String _name) {
		name=_name;//������������OVO
	}
	
	
	
	@Override
	public int useWeapon(Characters attacker,Characters victim) {
		int attackDamage=DAMAGE+attacker.getDamage();//��ɵ��˺�Ϊ�����ߵ��˺�������������
		int finalDamage=victim.hitBy(attacker, attackDamage);
		
		
		System.out.println(attacker.getName()+"ʹ��"+name+"��"+victim.getName()+"�����"+finalDamage+"���˺�");
		return finalDamage;//���������˺�
	}
	@Override
	public String getName() {return name;}

}
