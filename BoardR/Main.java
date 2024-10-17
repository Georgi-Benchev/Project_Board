package BoardR;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BoardItem item = new BoardItem("Refactor this mess", LocalDate.now().plusDays(2));
        item.setDueDate(item.getDueDate().plusYears(2));
        item.setTitle("Not that important");
        item.revertStatus();
        item.advanceStatus();
        item.revertStatus();

        item.displayHistory();

        System.out.println("\n--------------\n");

        BoardItem anotherItem = new BoardItem("Don't refactor anything",  LocalDate.now().plusDays(10));
        anotherItem.advanceStatus();
        anotherItem.advanceStatus();
        anotherItem.advanceStatus();
        anotherItem.advanceStatus();
        anotherItem.advanceStatus();
        anotherItem.displayHistory();

        BoardItem item1 = new BoardItem("Implement login/logout", LocalDate.now().plusDays(3));
        BoardItem item2 = new BoardItem("Secure admin endpoints", LocalDate.now().plusDays(5));

        Board board = new Board();

        board.addItem(item1); // add item1
        board.addItem(item2); // add item2
        board.addItem(item1); // Exception thrown: Item already in the list

        System.out.println(board.totalItems()); // count: 2   will be printed after the issue is fixed

    }
}