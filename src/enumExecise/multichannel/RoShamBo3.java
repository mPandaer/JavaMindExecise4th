package enumExecise.multichannel;
import java.util.Random;

import static enumExecise.multichannel.OutCome.*;
public enum RoShamBo3 {
    ROCK{
        @Override
        OutCome compete(RoShamBo3 item) {
            switch (item) {
                default:
                case ROCK: return DRAW;
                case SCISSORS: return WIN;
                case PAPER: return LOSE;
            }
        }
    },
    PAPER{
        @Override
        OutCome compete(RoShamBo3 item) {
            switch (item) {
                default:
                case ROCK: return WIN;
                case SCISSORS: return LOSE;
                case PAPER: return DRAW;
            }
        }
    },
    SCISSORS{
        @Override
        OutCome compete(RoShamBo3 item) {
            switch (item) {
                default:
                case ROCK: return LOSE;
                case SCISSORS: return DRAW;
                case PAPER: return WIN;
            }
        }
    };
     abstract OutCome compete(RoShamBo3 item);

     public static void match(RoShamBo3 item1, RoShamBo3 item2) {
         System.out.format("%s vs %s : %s\n",item1,item2,item1.compete(item2));
     }

     public static RoShamBo3 random() {
         return RoShamBo3.values()[new Random().nextInt(RoShamBo3.values().length)];
     }

    public static void main(String[] args) {
        for (int i = 0; i<20;i++) {
            match(random(),random());
        }
    }
}
