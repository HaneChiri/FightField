package yangxiao1006.character;
/**
 * project:��ս������С��Ϸ<br/>
 * description: ��Ϸ��ҳ��<br/>
 * @author ��Х
 * */
import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.behavior.SwordBehavior;
import yangxiao1006.behavior.WeaponBehavior;

public class Knight extends Characters {

	public Knight(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense) {
		super(_name,_hitPoint,_magicPoint,_damage,_defense);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"��HP��"+getHP());
	}
	
	public static void main(String args[]) {
		Knight p1=new Knight("jack", 10, 100, 3, 1);
		WeaponBehavior w=new SwordBehavior("������õĽ�");
		p1.setWeaponBehavior(w);
		Knight p2=new Knight("tom", 10, 100, 3, 5);
		WeaponBehavior w2=new SwordBehavior("����ڶ��õĽ�");
		p2.setWeaponBehavior(w2);
		
		p1.fight(p2);
		p1.display();
		p2.display();
	}

	@Override
	public Image getAppearance() {
		if(appearance==null) {
			if(getDirection()) {
				//falseΪ�ң�trueΪ��
				appearance=Toolkit.getDefaultToolkit().getImage("image\\Knight_L.png");
			}
			else {
				appearance=Toolkit.getDefaultToolkit().getImage("image\\Knight_R.png");
			}
		}
		
		return appearance;
	}
	

}
