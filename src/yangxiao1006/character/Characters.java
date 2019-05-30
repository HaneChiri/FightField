package yangxiao1006.character;
/**
 * project:��ս������С��Ϸ<br/>
 * description: ��ɫ�࣬����������ɫ�Ĺ�������<br/>
 * @author ��Х
 * */


import java.awt.Image;

import yangxiao1006.behavior.*;

public abstract class Characters {

	private String name;//����
	private int hitPoint;//HP
	private int magicPoint;//MP
	private int damage;//������
	private int defense;//������
	
	private int hitPointUpper;//HP����
	private int magicPointUpper;//MP����
	
	//ͼ�λ���
	private int x;
	private int y;
	private int height;
	private int width;
	protected Image appearance;//��ɫ��ò�������������ֻ��ͨ���������
	private boolean direction;//��ɫ����ķ���falseΪ���ң�trueΪ����
	
	
	
	protected WeaponBehavior weapon;//����
	protected MagicBehavior magic;//ħ��
	/**
	 * ��ɫ�Ĺ��캯��
	 * @param _name ��ɫ��
	 * @param _hitPoint ����ֵ
	 * @param _magicPoint ħ��ֵ
	 * @param _damage ������
	 * @param _defense ������
	 */
	public Characters(String _name,int _hitPoint,int _magicPoint,int _damage,int _defense) {
		name=_name;
		hitPoint=_hitPoint;
		magicPoint=_magicPoint;
		damage=_damage;
		defense=_defense;
		setBounds(0, 0, 300, 300);//λ��Ĭ��ֵ
		direction=false;//Ĭ�ϳ���
		
		//��ʼ������
		hitPointUpper=hitPoint;
		magicPointUpper=magicPoint;
		
	}
	/**
	 * ����ĳ����ɫ
	 * @param c Ҫ�����Ľ�ɫ
	 */
	public void fight(Characters c) {
		
		//���������в�ͬ�����ԣ������˺����߼�������ʵ��
		//������ڱ�д�߼��淨ʱ������Ҫ�������
		weapon.useWeapon(this,c);//�˽�ɫ������ɫc
		
	}
	/**
	 * ʩչħ��
	 * @param c ħ���ĳ�����
	 */
	public void performMagic (Characters c) {}
	
	
	
	/**
	 * ��ĳ����ɫ����
	 * @param attacker ������
	 * @param attackDamage �����߸���Ĺ����˺�
	 * @return �����ɵ���ʵ�˺�
	 */
	public int hitBy(Characters attacker,int attackDamage) {//������
		
		int finalDamage=(attackDamage-defense);//�˺����㣺�����˺�=�з������˺�-�ҷ�������
		if(hitPoint>0) {
			hitPoint-=finalDamage;
		}
		else{
			//�������������˺�
			this.killedBy(attacker);
		}
		
		return finalDamage;//���������ɵ���ʵ�˺�
	}
	/**
	 * ��ɱ�������һЩ��β����
	 * @param killer ����
	 */
	
	private void killedBy(Characters killer) {
		this.die();//�˶���ɱ�����������������չ
	}
	
	/**
	 * ���������һЩ��β����
	 */
	private void die() {
		System.out.println(name+"����");
	}
	

	public void moveLeft() {
		x--;
	}
	public void moveRight() {
		x++;
	}
	
	
	
	/**
	 * ���ý�ɫ�Ļ��Ʊ߽�
	 * @param _x x����
	 * @param _y y����
	 * @param _width ͼƬ���
	 * @param _height ͼƬ�߶�
	 */
	public void setBounds(int _x,int _y,int _width,int _height) {x=_x;y=_y;width=_width;height=_height;}
	/**
	 * �趨����
	 * @param w �趨�ľ�������
	 */
	public void setWeaponBehavior (WeaponBehavior w) {
		weapon=w;
		System.out.println(this.getName()+"������"+w.getName());
	}
	
	/**
	 * �趨ħ��
	 * @param m �趨�ľ���ħ��
	 */
	public void setMagicBehavior (MagicBehavior m) {}
	public void setDirection(Boolean _direction) {direction=_direction;}
	
	
	public String getName () {return name;}
	public int getHP() {return hitPoint;}
	public int getMP() {return magicPoint;}
	public int getHPU() {return hitPointUpper;}
	public int getMPU() {return magicPointUpper;}
	public int getDamage() {return damage;}
	public int getDefense() {return defense;}
	public boolean getDirection() {return direction;}
	/**
	 * ��ȡ��ɫ��ò
	 * @return ��òͼƬ����������������
	 */
	public abstract Image getAppearance();//Ϊ������������趨��ò���趨Ϊ���󷽷�
	public int getX() {return x;}
	public int getY() {return y;}
	public int getHeight(){return height;}
	public int getWidth(){return width;}
	
	public abstract void display ();
	
	
}
