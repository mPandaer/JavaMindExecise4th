package enumExecise.multichannel;

import java.util.Random;

import static enumExecise.multichannel.OutCome.*;
public enum RoShamBo5 {
     ROCK,PAPER,SCISSORS;
     private static OutCome[][] table = {
             {DRAW,LOSE,WIN},//Rock
             {WIN,DRAW,LOSE},//Paper
             {LOSE,WIN,DRAW}//Scissors
     };

     public OutCome compete(RoShamBo5 it){
         return table[this.ordinal()][it.ordinal()];
     }

     public static void match(RoShamBo5 item1,RoShamBo5 item2) {
         System.out.format("%s vs %s : %s\n",item1,item2,item1.compete(item2));
     }
     public static RoShamBo5 random() {
         return values()[new Random().nextInt(values().length)];
     }

    public static void main(String[] args) {
        for (int i = 0; i< 20;i++) {
            match(random(),random());
        }
    }
}
