package BoardR;
import BoardR.contracts.ConsoleLogger;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {



        LocalDate tomorrow = LocalDate.now().plusDays(1);
        BoardItem task = new Task("Write unit tests", "Pesho", tomorrow);
        BoardItem issue = new Issue("Review tests", "Someone must review Pesho's tests.", tomorrow);

        Board board = new Board();

        board.addItem(task);
        board.addItem(issue);

        ConsoleLogger logger = new ConsoleLogger();
        board.displayHistory(logger); // pass a ConsoleLogger type where an Logger is expected:


    }
}