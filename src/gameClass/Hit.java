package gameClass;

import java.awt.*;

/**
 * @author ：VIxyock
 * @description：检测英雄是否被自动击中或者被匪徒击伤
 */

public class Hit {
    double x,y;

    static Image img = GameUtil.getImage("images/Aaron_Hit.png");

    public void draw(Graphics g){
        g.drawImage(img, (int)x, (int)y, null);
    }
    public Hit(double x, double y){
        this.x = x;
        this.y = y;
    }
}
