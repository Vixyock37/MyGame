package gameClass;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author ：VIxyock
 * @description：一群匪徒类
 */

public class GansterGroup {

    private ArrayList<Ganster> gansters = new ArrayList<>();
    //匪徒的帮派,决定匪徒出生的地方
    private char gang;

    public GansterGroup(char gang) {
        this.gang = gang;
    }

    //生产一个匪徒，待加入帮派（东西南北帮）
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

    //匪徒拉帮结派
    public void makeGansterGroup(Image img) {
        if (gansters.size() < 25) {
            for (int i = 0; i < new Random().nextInt(4) + 1; i++) {
                Ganster ganster = this.makeGanster(img);
                gansters.add(ganster);
            }
        }
    }

    public ArrayList<Ganster> getGansters() {
        return gansters;
    }

}
