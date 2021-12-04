package de.advent.of.code.day3.data;

import java.io.IOException;
import java.io.InputStream;

public class InputFile {
    private final static int BINARY_LENGTH = 12;
    private final static int BINARY_NUMBERS_AMOUNT = 1000;

    private final int[] summarizedBitColumns = new int[BINARY_LENGTH];

    public InputFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("day3/input.txt");
        if (is != null) {
            try {
                int number;
                int counter = 0;
                while((number = is.read()) != -1) {
                    if (number > 13) {
                        summarizedBitColumns[counter % BINARY_LENGTH] = summarizedBitColumns[counter % BINARY_LENGTH] + (number - 48);
                        counter++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getGamma() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < BINARY_LENGTH; i++) {
            stringBuilder.append(getMostCommonOfCollumn(i, false));
        }
        return Integer.parseInt(stringBuilder.toString(),2);
    }
    public int getEpsilon() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < BINARY_LENGTH; i++) {
            stringBuilder.append(getMostCommonOfCollumn(i, true));
        }
        return Integer.parseInt(stringBuilder.toString(),2);
    }

    private int getMostCommonOfCollumn(int col, boolean inverted) {
        // be carefully for odd amounts
        if (inverted) {
            return summarizedBitColumns[col] > BINARY_NUMBERS_AMOUNT / 2 ? 0 : 1;
        }
        return summarizedBitColumns[col] < BINARY_NUMBERS_AMOUNT / 2 ? 0 : 1;
    }
}
