package enumExecise.multichannel;


import java.util.Random;

import static enumExecise.multichannel.OutCome.*;
interface Competitor<T> {
    OutCome compare(RoShamBo2 item);
    OutCome compare(RoShamBo4 item);
}
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW,LOSE,WIN),
    SCISSORS(WIN,DRAW,LOSE),
    ROCK(LOSE,WIN,DRAW);
    private final OutCome vPaper, vScissors, vRock;
    RoShamBo2(OutCome paper, OutCome scissors, OutCome rock) {
        this.vPaper = paper;
        this.vScissors = scissors;
        this.vRock = rock;
    }


    @Override
    public OutCome compare(RoShamBo2 item) {
        switch(item) {
            default:
            case PAPER: return vPaper;
            case ROCK: return vRock;
            case SCISSORS: return vScissors;
        }
    }

    @Override
    public OutCome compare(RoShamBo4 item) {
        return null;
    }

    public static RoShamBo2 random() {
        return RoShamBo2.values()[new Random().nextInt(RoShamBo2.values().length)];
    }

    public static void match(RoShamBo2 item1, RoShamBo2 item2) {
        System.out.format("%s vs %s : %s\n",item1,item2,item1.compare(item2));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            match(random(),random());
        }
    }
}
