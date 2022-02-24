package gameClass;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author ��VIxyock
 * @description��һȺ��ͽ��
 */

public class GansterGroup {

    private ArrayList<Ganster> gansters = new ArrayList<>();
    //��ͽ�İ���,������ͽ�����ĵط�
    private char gang;

    public GansterGroup(char gang) {
        this.gang = gang;
    }

    //����һ����ͽ����������ɣ������ϱ��
    public Ganster makeGanster(Image img) {
        Random random = new Random();
        switch (gang) {
            case 'N':
                return new Ganster(img, random.nextInt(Constant.GAME_WIDTH), 0, 'N');
            case 'S':
                return new Ganster(img, random.nextInt(Constant.GAME_WIDTH), Constant.GAME_HEIGHT, 'S');
            case 'W':
                return new Ganster(img, 0, random.nextInt(Constant.GAME_HEIGHT), 'W');
            case 'E':
                return new Ganster(img, Constant.GAME_WIDTH, random.nextInt(Constant.GAME_HEIGHT), 'E');
            default:
                return null;
        }
    }

    //��ͽ�������
    public void makeGansterGroup(Image img) {
        if (gansters.size() < 10) {
            for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
                Ganster ganster = this.makeGanster(img);
                gansters.add(ganster);
            }
        }
    }

    public ArrayList<Ganster> getGansters() {
        return gansters;
    }

}
