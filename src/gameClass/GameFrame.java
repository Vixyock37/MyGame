package gameClass;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * @author ��VIxyock
 * @description����Ϸ������
 */

public class GameFrame extends Frame {
    Image heroImg = GameUtil.getImage("images/Aaron.png");
    Image gansterImg = GameUtil.getImage("images/kittyfish.png");
    Image bg = GameUtil.getImage("images/bg.png");

    Hero hero = new Hero(heroImg, 634, 634);

    GansterGroup gansterGroupE = new GansterGroup('E');
    GansterGroup gansterGroupS = new GansterGroup('S');
    GansterGroup gansterGroupW = new GansterGroup('W');
    GansterGroup gansterGroupN = new GansterGroup('N');
    Hit hit;

    Date startTime = new Date();
    Date endTime;
    int period;  //��Ϸ����ʱ��

    @Override
    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0, null);

        hero.drawSelf(g);

        if (hero.live) {
            for (HeroBullet herobullet : hero.getBullets()) {
                if (herobullet.judgePosOutOfBorder()) {
                    herobullet.die();
                } else {
                    herobullet.drawBullet(g);
                    checkGansterHit(gansterGroupW, herobullet);
                    checkGansterHit(gansterGroupN, herobullet);
                }
            }
        } else {
            hit.draw(g);
        }

        checkHeroHit(g, gansterGroupW, hero);

        checkHeroHit(g, gansterGroupN, hero);

        //�������еķ�ͽ(West gang)
        checkHeroAndGanster(g, gansterGroupW);
        //�������еķ�ͽ(North gang)
        checkHeroAndGanster(g, gansterGroupN);
    }


    //���߳�������ͽ
    class MakeGansterThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    int level = (int) ((new Date().getTime() - startTime.getTime()) / 1000);
                    gansterGroupN.makeGansterGroup(gansterImg, level);
//                    gansterGroupS.makeGansterGroup(gansterImg);
                    gansterGroupW.makeGansterGroup(gansterImg, level);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //���߳���Ӣ�۴�ǹ
    class HeroShoot extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    hero.shoot();
                    Thread.sleep(hero.getShooting_speed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //���߳��÷�ͽ��ǹ
    class GansterShoot extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    for (Ganster ganster : gansterGroupN.getGansters()) {
                        ganster.shoot();
                    }
                    for (Ganster ganster : gansterGroupW.getGansters()) {
                        ganster.shoot();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //���̰߳������Ƿ����ػ�����
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();  //�ػ�
                try {
                    Thread.sleep(9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //������̼����ڲ���
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            hero.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            hero.minusDirection(e);
        }

    }

    public void launchFrame() {
        this.setTitle("����");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(300, 0);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new GansterShoot().start();
        new HeroShoot().start();    //����Ӣ�۴�ǹ����
        new PaintThread().start(); //�����ػ����ڵ��߳�
        new MakeGansterThread().start();    //������ͽ�����߳�

        addKeyListener(new KeyMonitor());

        //��ʼ����ͽ
    }

    public static void main(String[] args) {
        GameFrame f = new GameFrame();
        f.launchFrame();
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);//������Ϸ���ڵĿ�Ⱥ͸߶�

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void checkHeroAndGanster(Graphics g, GansterGroup gansterGroup) {
        for (Ganster ganster : gansterGroup.getGansters()) {
            ganster.drawGanster(g);
            for (Bullet bullet : ganster.getBullets()) {
                bullet.drawBullet(g);
            }

            //��ͽ��Ӣ�۵���ײ���
            boolean crashed_hero_ganster = ganster.getRect().intersects(hero.getRect());

            if (crashed_hero_ganster) {
                ganster.img = null;
                ganster.die();
                hero.live = false;
                hero.die();
                if (hit == null) {
                    hit = new Hit(hero.x, hero.y);
                    endTime = new Date();
                    period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
                }
                hit.draw(g);
            }

            //��ʱ���ܣ�������ʾ
            if (!hero.live) {
                Font f = new Font("����", Font.BOLD, 30);
                g.setFont(f);
                g.setColor(Color.black);
                g.drawString("��Ϸʱ�䣺" + period + "��", 150, 250);
            }
        }
    }

    public void checkGansterHit(GansterGroup gansterGroup, HeroBullet herobullet) {
        for (Ganster ganster : gansterGroup.getGansters()) {
            boolean hit_ganster = herobullet.getRect().intersects(ganster.getRect());
            if (hit_ganster) {
                ganster.img = null;
                ganster.die();
                ganster.life = false;
                herobullet.die();
            }
        }
    }

    public void checkHeroHit(Graphics g, GansterGroup gansterGroup, Hero hero) {
        for (Ganster ganster : gansterGroup.getGansters()) {
            for (Bullet bullet : ganster.getBullets()) {
                boolean crashed_hero_ganster = bullet.getRect().intersects(hero.getRect());

                if (crashed_hero_ganster) {
                    bullet.die();
                    hero.live = false;
                    hero.die();
                    if (hit == null) {
                        hit = new Hit(hero.x, hero.y);
                        endTime = new Date();
                        period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
                    }
                    hit.draw(g);
                }

                //��ʱ���ܣ�������ʾ
                if (!hero.live) {
                    Font f = new Font("����", Font.BOLD, 30);
                    g.setFont(f);
                    g.setColor(Color.black);
                    g.drawString("��Ϸʱ�䣺" + period + "��", 150, 250);
                }
            }
        }

    }
}
