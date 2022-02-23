package gameClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author ��VIxyock
 * @description����Ϸ������
 */

public class GameUtil {
    private GameUtil(){};

    //��ȡ������ͼƬ����
    public static Image getImage(String path){
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
