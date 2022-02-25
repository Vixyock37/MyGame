package gameClass;

import java.awt.*;

/**
 * @author ��VIxyock
 * @description����Ϸ����������ĸ���
 */

public class GameObject {
    Image img;
    double x,y;
    int speed;
    int width,height;

    //��x,y����img
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

    //��ȡ�������ڵľ������ڼ����ײ
    public Rectangle getRect(){return new Rectangle((int)x,(int)y, this.width, this.height);}

    //��������
    public void die(){
        this.height=this.width=this.speed=0;
    }
}
