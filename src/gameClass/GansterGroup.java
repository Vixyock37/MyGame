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

    public GansterGroup(char gang){
        this.gang = gang;
    }

    //����һ����ͽ����������ɣ������ϱ��
    public Ganster makeGanster(Image img) {
        Random random = new Random();
        switch (gang){
            case 'N': return new Ganster(img,random.nextInt(650),0, 'N');
            case 'S': return new Ganster(img,random.nextInt(650),780, 'S');
            case 'W': return new Ganster(img,0,random.nextInt(780), 'W');
            case 'E': return new Ganster(img,0,random.nextInt(0), 'E');
            default: return null;
        }
    }

    //��ͽ�������
    public void makeGansterGroup(Image img){
        if(gansters.size()<25) {
            for (int i = 0; i < new Random().nextInt(4)+1; i++) {
                Ganster ganster = this.makeGanster(img);
                gansters.add(ganster);
            }
        }
    }

    public ArrayList<Ganster> getGansters() {
        return gansters;
    }

}
