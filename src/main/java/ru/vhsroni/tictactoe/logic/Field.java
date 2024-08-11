package ru.vhsroni.tictactoe.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Field {

    private final Map<Integer, Sign> field;

    public Field() {
        this.field = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            field.put(i, Sign.EMPTY);
        }
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    public Sign checkWin() {
        List<List<Integer>> wins= List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(3, 6, 9),
                List.of(1, 5, 9),
                List.of(3, 5, 7)
        );

        for (List<Integer> win : wins) {
            if ((field.get(win.get(0)) == field.get(win.get(1))) &&
            (field.get(win.get(1)) == field.get(win.get(2))) &&
            (field.get(win.get(0)) != Sign.EMPTY)) {
                return field.get(win.get(0));
            }
        }
        return Sign.EMPTY;
    }

    public int getEmptyIndex() {
        return field.entrySet()
                .stream()
                .filter(cell -> cell.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }

    public List<Sign> getFieldData() {
        return field.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }


}
