package yangxiao1006.character;
import java.awt.Image;
import java.awt.Toolkit;

import yangxiao1006.behavior.MagicBehavior;
import yangxiao1006.behavior.WeaponBehavior;
import yangxiao1006.behavior.weapon.SwordBehavior;


/**
 * 角色：王
 * @author 杨啸
 *
 */
public class King extends Characters {

	public static final String APPEARANCE_L_PATH="image\\Characters\\King\\King_L.png";
	public static final String APPEARANCE_R_PATH="image\\Characters\\King\\King_R.png";
	public static final String APPEARANCE_DEATH_PATH="image\\Characters\\grave.png";
	public King(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense,WeaponBehavior[] ws,MagicBehavior[] ms) {
		super(_name,_hitPoint,_magicPoint,_damage,_defense,ws,ms);
	}

	@Override
	public void display() {
		System.out.println(getName()+"的HP："+getHP());
	}
	
	@Override
	public Image getAppearance() {
		
		
		if(!getIsAliveFlag()) {
			appearance=Toolkit.getDefaultToolkit().getImage(APPEARANCE_DEATH_PATH);
			return appearance;
		}
		
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
