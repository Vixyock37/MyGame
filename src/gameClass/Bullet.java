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
    private Set<Character> direction = new LinkedHashSet<>();

    public Bullet(double x, double y){
        this.x = x;
        this.y = y;
        width = 13;
        height = 13;
        speed = 4;
        direction = null;
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

//        Iterator i = direction.iterator();
//        System.out.println(this);
//        while (i.hasNext()) {
//            System.out.print(i.next());
//        }
//        System.out.println();

        if(this.direction.contains('E')) this.x+=speed;
        if(this.direction.contains('W')) this.x-=speed;
        if(this.direction.contains('N')) this.y-=speed;
        if(this.direction.contains('S')) this.y+=speed;

        g.setColor(c);
    }

    public boolean judgePosOutOfBorder(){
        if(x < 0 || x > Constant.GAME_WIDTH-width||y < 30 || y > Constant.GAME_HEIGHT-height) {
            return true;
        }
        else return false;
    }

}
