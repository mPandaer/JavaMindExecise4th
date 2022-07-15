package enumExecise.multichannel;

import java.util.EnumMap;
import java.util.Random;

import static enumExecise.multichannel.OutCome.*;
public enum RoShamBo4 implements Competitor<RoShamBo4>{
    PAPER,ROCK,SCISSORS;
    static EnumMap<RoShamBo4,EnumMap<RoShamBo4,OutCome>> table =
            new EnumMap<>(RoShamBo4.class);

    static {
        for (RoShamBo4 item : RoShamBo4.values()) {
            table.put(item,new EnumMap<>(item.getDeclaringClass()));
        }
        initRow(PAPER,DRAW,WIN,LOSE);
        initRow(ROCK,LOSE,DRAW,WIN);
        initRow(SCISSORS,WIN,LOSE,DRAW);
    }

    static void initRow(RoShamBo4 it, OutCome vPaper, OutCome vRock, OutCome vScissors) {
        EnumMap<RoShamBo4,OutCome> row = RoShamBo4.table.get(it);
        row.put(RoShamBo4.PAPER,vPaper);
        row.put(RoShamBo4.ROCK,vRock);
        row.put(RoShamBo4.SCISSORS,vScissors);
    }

    @Override
    public OutCome compare(RoShamBo2 item) {
        return null;
    }

    @Override
    public OutCome compare(RoShamBo4 item) {
        return table.get(this).get(item);
    }

    public static void match(RoShamBo4 item1, RoShamBo4 item2) {
        System.out.format("%s VS %s : %s\n",item1,item2,item1.compare(item2));
    }
    public static RoShamBo4 random() {
        return values()[new Random().nextInt(values().length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i< 20; i++) {
            match(random(),random());
        }
    }
}
