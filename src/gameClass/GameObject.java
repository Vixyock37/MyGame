package gameClass;

import java.awt.*;

/**
 * @author ：VIxyock
 * @description：游戏中所有物体的父类
 */

public class GameObject {
    Image img;
    double x,y;
    int speed;
    int width,height;

    //在x,y绘制img
    public void drawSelf(Graphics g) {
        g.drawImage(img, (int)x, (int)y,null);
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject(){

    }

    //获取物体所在的矩形用于检测碰撞
    public Rectangle getRect(){return new Rectangle((int)x,(int)y, this.width, this.height);}

    //物体消亡
    public void die(){
        this.height=this.width=this.speed=0;
    }
}
