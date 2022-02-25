package gameClass;

import java.awt.*;

/**
 * @author £ºVIxyock
 * @description£ºÓ¢ÐÛÉä³öµÄ×Óµ¯
 */

public class HeroBullet extends Bullet{
    private String direction;
    Color color = Color.YELLOW;


    public HeroBullet(double x, double y) {
        super(x, y);
    }

    public void drawBullet(Graphics g) {
        g.setColor(this.color);

        g.fillOval((int) x, (int) y, width, height);

        if(this.direction.contains("E")) this.x+=speed;
        if(this.direction.contains("W")) this.x-=speed;
        if(this.direction.contains("N")) this.y-=speed;
        if(this.direction.contains("S")) this.y+=speed;

    }

    public boolean judgePosOutOfBorder(){
        if(x < 0 || x > Constant.GAME_WIDTH-width||y < 30 || y > Constant.GAME_HEIGHT-height) {
            return true;
        }
        else return false;
    }

    public void setDirection(String tdirection) {
        direction=tdirection;
    }

}
