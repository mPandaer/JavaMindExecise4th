package enumExecise.multichannel;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static enumExecise.multichannel.OutCome.*;

interface Item {
    OutCome compete(Item it);
    OutCome eval(Paper p);
    OutCome eval(Rock r);
    OutCome eval(Scissors s);
}

class Paper implements Item {

    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public OutCome eval(Paper p) {
        return DRAW;
    }

    @Override
    public OutCome eval(Rock r) {
        return LOSE;
    }

    @Override
    public OutCome eval(Scissors s) {
        return WIN;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}


class Rock implements Item {

    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public OutCome eval(Paper p) {
        return WIN;
    }

    @Override
    public OutCome eval(Rock r) {
        return DRAW;
    }

    @Override
    public OutCome eval(Scissors s) {
        return LOSE;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}

class Scissors implements Item {

    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public OutCome eval(Paper p) {
        return LOSE;
    }

    @Override
    public OutCome eval(Rock r) {
        return WIN;
    }

    @Override
    public OutCome eval(Scissors s) {
        return DRAW;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}

public class RoShamBo1 {
    public static void match(Item item1, Item item2) {
        System.out.format("%s vs %s : %s\n",item1,item2,item1.compete(item2));
    }
    static ArrayList<Item> list = new ArrayList<>();
    static {
        Collections.addAll(list,new Rock(),new Paper(),new Scissors());
    }
    public static Item random() {
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            match(random(),random());
        }
    }
}
