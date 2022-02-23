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
    Image heroImg = GameUtil.getImage("images/Jadu.png");
    ;
    Image gansterImg = GameUtil.getImage("images/kittyfish.png");
    Image bg = GameUtil.getImage("images/bg.png");
    ;

    Hero hero = new Hero(heroImg, 200, 200);
    //    GansterGroup gansterGroupE = new GansterGroup('E');
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

        //�������еķ�ͽ

        for (Ganster ganster : gansterGroupN.getGansters()) {
            ganster.drawGanster(g);

            ////��ͽ��Ӣ�۵���ײ���
            boolean crashed = ganster.getRect().intersects(hero.getRect());
            if (crashed) {
                hero.live = false;
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
                g.setColor(Color.red);
                g.drawString("��Ϸʱ�䣺" + period + "��", 150, 250);
            }
        }

        for (Ganster ganster : gansterGroupW.getGansters()) {
            ganster.drawGanster(g);

            //��ͽ��Ӣ�۵���ײ���
            boolean crashed = ganster.getRect().intersects(hero.getRect());

            if (crashed) {
                hero.live = false;
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
                g.setColor(Color.red);
                g.drawString("��Ϸʱ�䣺" + period + "��", 150, 250);
            }
        }

        for (Ganster ganster : gansterGroupS.getGansters()) {
            ganster.drawGanster(g);

            //��ͽ��Ӣ�۵���ײ���
            boolean crashed = ganster.getRect().intersects(hero.getRect());

            if (crashed) {
                hero.live = false;
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
                g.setColor(Color.red);
                g.drawString("��Ϸʱ�䣺" + period + "��", 150, 250);
            }
        }

    }


    //���߳�������ͽ
    class MakeGansterThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    gansterGroupN.makeGansterGroup(gansterImg);
//                    gansterGroupS.makeGansterGroup(gansterImg);
//                    gansterGroupW.makeGansterGroup(gansterImg);
                    Thread.sleep(10);
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
                    Thread.sleep(10);
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

        new PaintThread().start(); //�����ػ����ڵ��߳�
        new MakeGansterThread().start();
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
}
