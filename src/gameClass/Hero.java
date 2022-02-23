package gameClass;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author ��VIxyock
 * @description��Ӣ�����Ƕ���
 */

public class Hero extends GameObject{
    boolean left,up,right,down;
    boolean live = true;
    Bullet bullet = null;


    public Hero(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }


    @Override
    public void drawSelf(Graphics g) {
        if(live) {
            g.drawImage(img, (int)x, (int)y, null);
            if(left) {
                x -= speed;
            }
            if(right) {
                x += speed;
            }
            if(up) {
                y -= speed;
            }
            if(down) {
                y += speed;
            }
        }else {

        }
    }

    //��������뷽��
    //���¼�λʱ,����ĳ������
    public void addDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
        }
    }

    //̧��ĳ������ȡ����Ӧ�ķ���
    public void minusDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
        }
    }
}
