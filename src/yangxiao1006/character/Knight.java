package yangxiao1006.character;
/**
 * project:【战斗领域】小游戏<br/>
 * description: 游戏主页面<br/>
 * @author 杨啸
 * */
import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.behavior.WeaponBehavior;
import yangxiao1006.behavior.weapon.SwordBehavior;

public class Knight extends Characters {

	
	public Knight(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense) {
		super(_name,_hitPoint,_magicPoint,_damage,_defense);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"的HP："+getHP());
	}
	
	public static void main(String args[]) {
		Knight p1=new Knight("jack", 10, 100, 3, 1);
		WeaponBehavior w=new SwordBehavior("村里最好的剑");
		p1.setWeaponBehavior(w);
		Knight p2=new Knight("tom", 10, 100, 3, 5);
		WeaponBehavior w2=new SwordBehavior("村里第二好的剑");
		p2.setWeaponBehavior(w2);
		
		p1.fight(p2);
		p1.changeWeapon();
		p1.changeWeapon();
		p1.changeWeapon();
		p1.changeWeapon();
		p1.changeWeapon();
		p1.display();
		p2.display();
	}

	@Override
	public Image getAppearance() {
		
		if(getDirection()) {
				//false为右，true为左
			appearance=Toolkit.getDefaultToolkit().getImage("image\\Characters\\Knight\\Knight_L.png");
		}
		else {
			appearance=Toolkit.getDefaultToolkit().getImage("image\\Characters\\Knight\\Knight_R.png");
		}
		
		return appearance;
	}
	

}
