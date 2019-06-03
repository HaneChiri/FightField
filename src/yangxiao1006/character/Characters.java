package yangxiao1006.character;
/**
 * project:【战斗领域】小游戏<br/>
 * description: 角色类，用于描述角色的共性特征<br/>
 * @author 杨啸
 * */


import java.awt.Image;

import yangxiao1006.behavior.*;
import yangxiao1006.behavior.magic.HealBehavior;
import yangxiao1006.behavior.magic.InvisibleBehavior;
import yangxiao1006.behavior.weapon.AxeBehavior;
import yangxiao1006.behavior.weapon.BowBehavior;
import yangxiao1006.behavior.weapon.KnifeBehavior;
import yangxiao1006.behavior.weapon.SwordBehavior;

public abstract class Characters {

	public static final int ST_NORMAL=0;//正常状态
	public static final int ST_INVISIBLE=1;//隐身状态
	
	public static final int HP_RECOVER=3;//回血
	public static final int MP_RECOVER=5;//回蓝
	
	private String name;//名字
	private int hitPoint;//HP
	private int magicPoint;//MP
	private int damage;//攻击力
	private int defense;//防御力
	

	
	private int hitPointUpper;//HP上限
	private int magicPointUpper;//MP上限
	
	//图形绘制
	private int x;
	private int y;
	private int height;
	private int width;
	protected Image appearance;//角色外貌，由子类决定，只能通过方法获得
	private boolean direction=false;//角色面向的方向，false为朝右，true为朝左
	
	
	//状态
	volatile private boolean isAliveFlag=true;//是否活着
	volatile private boolean moveLeftFlag=false;//是否正在向左移动，用于多线程
	volatile private boolean moveRightFlag=false;//是否正在向右移动
	volatile private int status=ST_NORMAL;//角色状态
	
	
	protected WeaponBehavior weapon;//武器
	protected MagicBehavior magic;//魔法
	
	protected WeaponBehavior weaponSlots[];//武器栏位，用于存储角色携带的武器，可以切换
	protected MagicBehavior magicSlots[];//魔法栏位
	protected int weaponSlotsIndex=0;//栏位索引
	protected int magicSlotsIndex=0;
	

	
	
	/**
	 * 角色的构造函数
	 * @param _name 角色名
	 * @param _hitPoint 生命值
	 * @param _magicPoint 魔力值
	 * @param _damage 攻击力
	 * @param _defense 防御力
	 */
	public Characters(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense,WeaponBehavior[] ws,MagicBehavior[] ms) {
		name=_name;
		hitPoint=_hitPoint;
		magicPoint=_magicPoint;
		damage=_damage;
		defense=_defense;
		setBounds(0, 0, 300, 300);//位置默认值
		direction=false;//默认朝右
		
		//初始化上限
		hitPointUpper=hitPoint;
		magicPointUpper=magicPoint;
		
		//初始化武器和魔法
		weaponSlots= ws;
		magicSlots=ms;
		changeWeapon();
		changeMagic();
		
	}
	/**
	 * 展示角色数据
	 */
	public abstract void display ();
	
	/**
	 * 按顺序切换武器
	 */
	public void changeWeapon() {
		
		setWeaponBehavior(weaponSlots[weaponSlotsIndex]);
		
		if(weaponSlotsIndex+1>=weaponSlots.length) {
			weaponSlotsIndex=0;
		}
		else {
			weaponSlotsIndex++;
		}
		
	}
	/**
	 * 按顺序切换魔法
	 */
	public void changeMagic() {
		setMagicBehavior(magicSlots[magicSlotsIndex]);
		
		if(magicSlotsIndex+1>=magicSlots.length) {
			magicSlotsIndex=0;
		}
		else {
			magicSlotsIndex++;
		}
		
	}
	
	/**
	 * 攻击某个角色
	 * @param c 要攻击的角色
	 * @return 造成的真实伤害
	 */
	public int fight(Characters c) {
		
		//由于武器有不同的特性，所以伤害的逻辑让武器实现
		//比如后期编写高级玩法时，弓需要计算射程
		if(!isAliveFlag) return 0;//如果已死亡，直接返回，下同
		if(weapon==null) {
			System.out.println(name+"没有武器，无法攻击");
			return 0;
		}
		
		int attackRange=weapon.getAttackRange();
		if(attackRange>distance(c)) {
			//攻击距离大于角色之间的距离才可攻击
			return weapon.useWeapon(this,c);//此角色攻击角色c
		}
		else {
			return 0;
		}
		
	}
	/**
	 * 施展魔法
	 * @param c 魔法的承受者
	 */
	public void performMagic (Characters c) {
		if(!isAliveFlag) return;
		if(magic==null) {
			System.out.println(name+"没有可以用的魔法");
		}
		
		magic.useMagic(this,c);
	}
	/**
	 * 增加HP(increase HP)
	 * @param value 增加值
	 * @return 上溢返回1，下溢返回-1，正常返回0
	 */
	public int incHP(int value) {
		int finalHP=hitPoint+value;
		if(finalHP>=0 && finalHP<=hitPointUpper) {//HP未越界
			hitPoint=finalHP;
			return 0;
		}
		else {
			if(finalHP<0) {
				hitPoint=0;
				return -1;
			}
			else {
				hitPoint=hitPointUpper;
				return 1;
			}
		}		
	}
	
	
	/**
	 * 增加MP(increase MP)
	 * @param value 增加值
	 * @return 上溢返回1，下溢返回-1，正常返回0
	 */
	public int incMP(int value) {
		int finalMP=magicPoint+value;
		if(finalMP>=0 && finalMP<=magicPointUpper) {//MP未越界
			magicPoint=finalMP;
			return 0;
		}
		else {
			if(finalMP<0) {
				magicPoint=0;
				return -1;
			}
			else {
				magicPoint=magicPointUpper;
				return 1;
			}
		}		
	}
	
	
	/**
	 * 被某个角色攻击
	 * @param attacker 攻击者
	 * @param attackDamage 攻击者给予的攻击伤害
	 * @return 最后造成的真实伤害
	 */
	public int hitBy(Characters attacker,int attackDamage) {//被攻击
		if(!isAliveFlag) return 0;
		
		
		int finalDamage=(attackDamage-defense);//伤害计算：最终伤害=敌方攻击伤害-我方防御力
		
		if(incHP(-finalDamage)==-1) {//如果血量被扣到负数
			this.killedBy(attacker);
		}
		
		return finalDamage;//返回最后造成的真实伤害
	}
	/**
	 * 被杀死，完成一些收尾工作
	 * @param killer 凶手
	 */
	


	/**
	 * 向左移动<br/>
	 * 由于两个线程各自操作自己的角色，所以此函数不需要同步
	 */
	public void moveLeft() {
		if(!isAliveFlag) return;
		
		if(moveLeftFlag) {
			x-=1;
			try {
				Thread.sleep(1);//防止跑得太快
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 向右移动<br/>
	 * 由于两个线程各自操作自己的角色，所以此函数不需要同步
	 */
	public void moveRight() {
		if(!isAliveFlag) return;
		if(moveRightFlag) {
			x+=1;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**********************************************辅助函数*********************************************************/
	
	
	private void killedBy(Characters killer) {
		this.die();//此对象被杀死，这个函数留作扩展
	}
	
	/**
	 * 死亡，完成一些收尾工作
	 */
	private void die() {
		isAliveFlag=false;
		System.out.println(name+"死亡");
	}
	
	/**
	 * 计算与另一个角色的距离
	 * 在还没有设计角色的跳跃之前，距离仅在水平方向上计算
	 * @param c 另一个角色
	 * @return 与另一个角色的距离
	 */
	public int distance(Characters c) {
		return Math.abs(c.getX()-this.x);
	}
	
	
	/**********************************************set函数*********************************************************/
	
	/**
	 * 设置角色的绘制边界
	 * @param _x x坐标
	 * @param _y y坐标
	 * @param _width 图片宽度
	 * @param _height 图片高度
	 */
	public void setBounds(int _x,int _y,int _width,int _height) {x=_x;y=_y;width=_width;height=_height;}
	/**
	 * 设定武器
	 * @param w 设定的具体武器
	 */
	public void setWeaponBehavior (WeaponBehavior w) {
		weapon=w;
		System.out.println(this.getName()+"拿起了"+w.getName());
	}
	
	/**
	 * 设定魔法
	 * @param m 设定的具体魔法
	 */
	public void setMagicBehavior (MagicBehavior m) {
		magic=m;
		System.out.println(this.getName()+"正在准备"+m.getName());
	}
	/**
	 * 设置角色面向的方向
	 * @param _direction 方向，false为朝右，true为朝左
	 */
	public void setDirection(Boolean _direction) {direction=_direction;}
	public void setHP(int _hitPoint) { hitPoint=_hitPoint;}
	public void setMP(int _magicPoint) {magicPoint=_magicPoint;}
	
	
	public void setMoveLeftFlag(boolean flag) {moveLeftFlag=flag;}
	public void setMoveRightFlag(boolean flag) {moveRightFlag=flag;}
	public void setStatus(int _status) {status=_status;}
	
	/**********************************************get函数*********************************************************/
	public String getName () {return name;}
	public int getHP() {return hitPoint;}
	public int getMP() {return magicPoint;}
	public int getHPU() {return hitPointUpper;}
	public int getMPU() {return magicPointUpper;}
	public int getDamage() {return damage;}
	public int getDefense() {return defense;}
	public boolean getDirection() {return direction;}
	public WeaponBehavior getWeapon() {return weapon;}
	public MagicBehavior getMagic() {return magic;}
	public int getStatus() {return status;}
	
	
	/**
	 * 获取角色外貌
	 * @return 外貌图片，交给子类来加载
	 */
	public abstract Image getAppearance();//为了让子类必须设定外貌而设定为抽象方法
	public int getX() {return x;}
	public int getY() {return y;}
	public int getHeight(){return height;}
	public int getWidth(){return width;}

	public boolean getIsAliveFlag() {return isAliveFlag;}
	public boolean getMoveLeftFlag() {return moveLeftFlag;}
	public boolean getMoveRightFlag() {return moveRightFlag;}
	
	
	
	
}
