// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice;

public enum DiceSide {

    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private final int VALUE;

    DiceSide(int value) {
        VALUE = value;
    }

    public int getValue() {
        return VALUE;
    }

    public String toString() {

        switch (VALUE) {

            case 1:
                return "⚀";

            case 2:
                return "⚁";

            case 3:
                return "⚂";

            case 4:
                return "⚃";

            case 5:
                return "⚄";

            case 6:
                return "⚅";

            default:
                return "";

        }

    }

}
