package gameClass;

import java.awt.*;

/**
 * @author ��VIxyock
 * @description�����Ӣ���Ƿ��Զ����л��߱���ͽ����
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
