package gameClass;

import java.awt.*;

/**
 * @author ：VIxyock
 * @description：检测英雄是否被自动击中或者被匪徒击伤
 */

public class Hit {
    double x,y;
//    static Image[] imgs = new Image[16];
    static Image img = GameUtil.getImage("images/Aaron.png");
    static {
//        for(int i=0;i<16;i++){
//            imgs[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
//            imgs[i].getWidth(null);
//        }
    }
    int count;

    public void draw(Graphics g){
        if(count<=15){
            g.drawImage(img, (int)x, (int)y, null);
            count++;
        }
    }
    public Hit(double x, double y){
        this.x = x;
        this.y = y;
    }
}
