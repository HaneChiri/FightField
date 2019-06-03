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

	public static final String APPEARANCE_L_PATH="image\\Characters\\Knight\\Knight_L.png";
	public static final String APPEARANCE_R_PATH="image\\Characters\\Knight\\Knight_R.png";
	
	public Knight(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense) {
		super(_name,_hitPoint,_magicPoint,_damage,_defense);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"的HP："+getHP());
	}
	
	@Override
	public Image getAppearance() {
		
		if(getDirection()) {
				//false为右，true为左
			appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_L_PATH);
		}
		else {
			appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_R_PATH);
		}
		
		return appearance;
	}
	

}
