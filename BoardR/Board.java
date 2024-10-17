package BoardR;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;

    private List<BoardItem> items = new ArrayList<>();

    public Board() {
    }


    public void addItem(BoardItem item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("Item already in the list");
        } else {
            items.add(item);
        }
    }


    public int totalItems() {
        return items.size();
    }
}


