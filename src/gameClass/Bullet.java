package gameClass;

import java.awt.*;

/**
 * @author ：VIxyock
 * @description：游戏中人物射出的子弹对象
 */

public class Bullet extends GameObject{
    Character direction;

    public Bullet(double x, double y){
        this.x = x;
        this.y = y;
        width = 13;
        height = 13;
        speed = 1;
    }

    public Bullet(double x, double y, Character tdirection){
        this.x = x;
        this.y = y;
        width = 13;
        height = 13;
        speed = 1;
        direction = tdirection;
    }

    public void drawBullet(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int) x, (int) y, width, height);

        //        子弹沿着任意方向飞
//        x += speed * Math.cos(degree);
//        y += speed * Math.sin(degree);
//
//        if(x < 0 || x > Constant.GAME_WIDTH-width) {
//            degree = Math.PI - degree;
//        }
//        if(y < 30 || y > Constant.GAME_HEIGHT-height) {
//            degree = - degree;
//        }

        switch (direction) {
            case 'W':
                x -= speed;
                break;
            case 'N':
                y += speed;
                break;
            case 'E':
                x += speed;
                break;
            case 'S':
                y -= speed;
                break;

        }

        g.setColor(c);
    }


}
