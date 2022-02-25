package gameClass;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author ：VIxyock
 * @description：匪徒对象
 */

public class Ganster extends GameObject {
    //匪徒帮派，匪徒将向帮派的反方向行走
    private char gang;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Character direction;
    boolean life = true;

    public Ganster(Image img, double x, double y, char gang) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = new Random().nextInt(4) + 1;
        this.gang = gang;
        //妈的这里忘了给宽高赋值debug到凌晨十二点，傻逼
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

        switch (gang) {
            case 'E':
                direction='W';
                break;
            case 'S':
                direction='N';
                break;
            case 'W':
                direction='E';
                break;
            case 'N':
                direction='S';
                break;
        }
    }

    public void reload(){
        Bullet bullet = new Bullet(this.x+20, this.y+20,this.speed+2);
        bullet.setDirection(direction);
        bullets.add(bullet);
    }

    public void shoot() {
        if(this.life)
            reload();
    }


    public void drawGanster(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
        switch (gang) {
            case 'E':
                x -= speed;
                break;
            case 'S':
                y -= speed;
                break;
            case 'W':
                x += speed;
                break;
            case 'N':
                y += speed;
                break;
        }
//        System.out.println(this.toString());
        if (x < 0 || x > Constant.GAME_WIDTH - width) {
            x = Constant.GAME_WIDTH - x;
            this.speed = new Random().nextInt(4) + 1;
        }
        if (y < 0 || y > Constant.GAME_HEIGHT - height) {
            y = Constant.GAME_HEIGHT;
            this.speed = new Random().nextInt(4) + 1;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
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
