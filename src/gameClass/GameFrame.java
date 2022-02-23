package gameClass;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * @author ：VIxyock
 * @description：游戏主界面
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
    int period;  //游戏持续时间

    @Override
    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0, null);

        hero.drawSelf(g);

        //画出所有的匪徒

        for (Ganster ganster : gansterGroupN.getGansters()) {
            ganster.drawGanster(g);

            ////匪徒和英雄的碰撞检测
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

            //计时功能，给出提示
            if (!hero.live) {
                Font f = new Font("宋体", Font.BOLD, 30);
                g.setFont(f);
                g.setColor(Color.red);
                g.drawString("游戏时间：" + period + "秒", 150, 250);
            }
        }

        for (Ganster ganster : gansterGroupW.getGansters()) {
            ganster.drawGanster(g);

            //匪徒和英雄的碰撞检测
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

            //计时功能，给出提示
            if (!hero.live) {
                Font f = new Font("宋体", Font.BOLD, 30);
                g.setFont(f);
                g.setColor(Color.red);
                g.drawString("游戏时间：" + period + "秒", 150, 250);
            }
        }

        for (Ganster ganster : gansterGroupS.getGansters()) {
            ganster.drawGanster(g);

            //匪徒和英雄的碰撞检测
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

            //计时功能，给出提示
            if (!hero.live) {
                Font f = new Font("宋体", Font.BOLD, 30);
                g.setFont(f);
                g.setColor(Color.red);
                g.drawString("游戏时间：" + period + "秒", 150, 250);
            }
        }

    }


    //此线程生产匪徒
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

    //此线程帮助我们反复重画窗口
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();  //重画
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //定义键盘监听内部类
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
        this.setTitle("测试");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(300, 0);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start(); //启动重画窗口的线程
        new MakeGansterThread().start();
        addKeyListener(new KeyMonitor());

        //初始化匪徒
    }

    public static void main(String[] args) {
        GameFrame f = new GameFrame();
        f.launchFrame();
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
