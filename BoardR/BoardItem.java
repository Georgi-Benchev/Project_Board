package BoardR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;
    private List<EventLog> history;

    public BoardItem(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = Status.Open;
        history = new ArrayList<>();
        history.add(new EventLog("Item created: " + viewInfo()));
    }

    public void advanceStatus() {
        if (status.equals(Status.Open)) {
            status = Status.Todo;
            history.add(new EventLog("Status changed from Open to ToDo"));
        } else if (status == Status.Todo) {
            status = Status.InProgress;
            history.add(new EventLog("Status changed from ToDo to In Progress"));
        } else if (status == Status.InProgress) {
            status = Status.Done;
            history.add(new EventLog("Status changed from In Progress to " + this.status));
        } else if (status.equals(Status.Done)) {
            status = Status.Verified;
            history.add(new EventLog("Status changed from Done to " + this.status));
        } else {
            history.add(new EventLog("Can't advance, already at Verified"));

        }


    }

    public void revertStatus() {
        if (status.equals(Status.Verified)) {
            status = Status.Done;
            history.add(new EventLog("Status changed from Verified to " + this.status));
        } else if (status.equals(Status.Done)) {
            status = Status.InProgress;
            history.add(new EventLog("Status changed from Done to In Progress"));
        } else if (status == Status.InProgress) {
            status = Status.Todo;
            history.add(new EventLog("Status changed from In Progress to ToDo"));
        } else if (status.equals(Status.Todo)) {
            status = Status.Open;
            history.add(new EventLog("Status changed from ToDo to " + this.status));
        } else {
            history.add(new EventLog("Can't revert, already at Open"));
        }
    }

    public String viewInfo() {
        String output = String.format("'%s', [%s | %s]", title, status, dueDate);

        return output;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() >= 5 && title.length() <= 30) {

            history.add(new EventLog("Title changed from " + this.title + " to " + title));
            this.title = title;
        } else {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isAfter(this.dueDate)) {
            history.add(new EventLog("DueDate changed from " + this.dueDate + " to " + dueDate));
            this.dueDate = dueDate;
        } else {
            throw new IllegalArgumentException("Date must be in the present");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        history.add(new EventLog("Status changed from " + this.status + " to " + status));

        this.status = status;
    }

    public void displayHistory() {
        for (EventLog log : history) {
            System.out.println(log.viewInfo());
        }

    }

}
