// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice;

import java.util.Random;

public class Roll {

    private String dice = "";
    private int total = 0;

    public Roll(int amount) {

        final DiceSide[] SIDES = DiceSide.values();

        for (int i = 0; i < amount; ++i) {
            DiceSide side = SIDES[new Random().nextInt(SIDES.length)];
            total += side.getValue();
            dice += side + (i < amount-1 ? " " : "");
        }

    }

    public String getDice() {return dice;}
    public int getTotal() {return total;}

}
