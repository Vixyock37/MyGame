package gameClass;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ：VIxyock
 * @description：游戏中人物射出的子弹对象
 */

public class Bullet extends GameObject{
    Set<Character> direction = new LinkedHashSet<>();

    public Bullet(double x, double y){
        this.x = x;
        this.y = y;
        width = 13;
        height = 13;
        speed = 4;
    }

    public Bullet(double x, double y, Set<Character> tdirection){
        this.x = x;
        this.y = y;
        width = 13;
        height = 13;
        speed = 3;
        direction = tdirection;
    }

    public void drawBullet(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int) x, (int) y, width, height);

        if(direction.contains('E')) x+=speed;
        if(direction.contains('W')) x-=speed;
        if(direction.contains('N')) y-=speed;
        if(direction.contains('S')) y+=speed;

        g.setColor(c);
    }

    public boolean JudgePosOutOfBorder(){
        if(x < 0 || x > Constant.GAME_WIDTH-width||y < 30 || y > Constant.GAME_HEIGHT-height) {
            return true;
        }
        else return false;
    }

}
