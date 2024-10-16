package BoardR;

import java.time.LocalDate;

public class BoardItem {
    String title;
    LocalDate dueDate;
    Status status;


    public BoardItem(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = Status.Open;
    }

    public void advanceStatus() {
        if (status.equals(Status.Open)) {
            status = Status.Todo;
        } else if (status == Status.Todo) {
            status = Status.InProgress;
        } else if (status == Status.InProgress) {
            status = Status.Done;
        } else if (status.equals(Status.Done)) {
            status = Status.Verified;
        }
    }

    public void revertStatus() {
        if (status.equals(Status.Verified)) {
            status = Status.Done;
        } else if (status.equals(Status.Done)) {
            status = Status.InProgress;
        } else if (status == Status.InProgress) {
            status = Status.Todo;
        } else if (status.equals(Status.Todo)) {
            status = Status.Open;
        }
    }

    public String viewInfo() {
        String output = String.format("'%s', [%s | %s]",title,status,dueDate);
        return output;
    }
}
