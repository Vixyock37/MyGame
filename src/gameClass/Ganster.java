package gameClass;

import java.awt.*;
import java.util.Random;

/**
 * @author ��VIxyock
 * @description����ͽ����
 */

public class Ganster extends GameObject {
    //��ͽ���ɣ���ͽ������ɵķ���������
    private char gang;

    public Ganster(Image img, double x, double y,char gang) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = new Random().nextInt(4)+1;
        this.gang = gang;
        //����������˸���߸�ֵdebug���賿ʮ���㣬ɵ��
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }



    public void drawGanster(Graphics g){
        g.drawImage(img, (int)x, (int)y, null);
        switch (gang) {
            case 'E':
                x -= speed;
                break;
            case 'S':
                y += speed;
                break;
            case 'W':
                x += speed;
                break;
            case 'N':
                y -= speed;
                break;
        }
//        System.out.println(this.toString());
        if(x < 0 || x > Constant.GAME_WIDTH-width) {
            x = Constant.GAME_WIDTH-x;
            this.speed = new Random().nextInt(4)+1;;
        }
        if(y < 0 || y > Constant.GAME_HEIGHT-height) {
            y = Constant.GAME_HEIGHT;
            this.speed = new Random().nextInt(4)+1;;
        }
    }

    @Override
    public String toString() {
        return "Ganster{" +
                "gang=" + gang +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
