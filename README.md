# FightField

# 相关链接

- [哔哩哔哩对应视频的传送门](https://www.bilibili.com/video/av54526303/)
- [项目总结博客](https://hanechiri.github.io/post/java_game_FightFieldFrame/#more)

# 课设目的与要求

根据讲义中策略模式的案例，设计和实现一个基于**策略模式**的角色扮演游戏。其中包括主要有角色类及其子类、相关的行为类集合和测试类等。

通过本次实验，能够在掌握面向对象程序设计的基本思想基础上；深化理解 Java 面向对象程序设计中消息、继承、多态、接口、抽象类和抽象方法等概念和实现方式；并进一步掌握 Java 程序设计中的基本语法和 Java程序运行方法等；理解和应用包（package）。

# 内容

一个游戏中有多种角色(Character)，例如：国王（King）、皇后（Queen）、骑士（Knight）、老怪（Troll）。角色之间可能要发生战斗(fight)，每场战斗都是一个角色与另一角色之间的一对一战 斗。

每个角色都有自己的生命值 (hitPoint) 、 魔法值（magicPoint）、攻击力值(damage)和防御力值(defense)。

每种角色都有一种武器进行攻击（fight）；在程序运行中，可以动态修改角色的武器(setWeaponBehavior)。

每种角色都有一种魔法对自己或者其他角色施法（performMagic）；可以动态改变拥有的魔法（setMagicBehavior）。

1. 首先设计和实现抽象类 Characters。

2. 设计和实现 Character 类的几个子类：King、Queen、Knight、Troll。位

3. 设计接口 WeaponBehavior 和 MagicBehavior。

   - 接 口 WeaponBehavior 的 实 现 类 ：

     - KnifeBehavior （ 用 刀 ）
     - BowAndArrowBehavior （ 用 弓 箭 ）
     - AxeBehavior （ 用 斧 ）
     - SwordBehavior（用剑）

   - 接口MagicBehavior的实现类：

     - HealBehavior（治疗）
     - InvisibleBehavior（隐身）。

     

实现接口中的抽象方法，可以只在屏幕输出简单信息，也可以结合生命值(hitPoint)、攻击力值(damage)和防御力值(defense)计算。

4. 编写测试代码，对以上设计的系统进行测试。要求在程序运行期间，能动态改变角色拥有的武器或者魔法。
5. 自己添加一种角色、或者添加一种武器及魔法，设计相应的类，并编写测试代码进行测试。
6. 按照 Java 的规范，添加详细的文档注释，并用 Javadoc 生成标准的帮助文档。
7. 将上述编译、运行、生成帮助文档的命令，填写至实验报告相应位置。
8. 填写实验报告。并将程序代码及生成的帮助文档打包上交。



# 涉及的主要内容

1. 单例模式。游戏窗口只能有一个对象，因此使用了单例模式。
2. 策略模式。在角色类中有两个抽象策略（武器策略和魔法策略），具体策略在类中实现。
3. 双缓冲技术。在绘制游戏画面的时候使用了双缓冲技术，防止画面闪烁。
4. 多线程。在两处使用了多线程，一处是为了解决按键冲突的问题，另一处是为了实现游戏周期性判定的功能。
5. awt。