package BoardR;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private static final int TITLE_MIN_LENGTH = 5;
    private static final int TITLE_MAX_LENGTH = 30;

    private static final Status INITIAL_STATUS = Status.OPEN;
    private static final Status FINAL_STATUS = Status.VERIFIED;

    private String title;
    private Status status;
    private LocalDate dueDate;
    protected final List<EventLog> history = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate,Status status) {
        validateDueDate(dueDate);
        validateTitle(title);

        this.title = title;
        this.dueDate = dueDate;
        this.status = status;

        logEvent(String.format("Item created: %s", viewInfo()));
    }
    public BoardItem(String title, LocalDate dueDate){
        this(title,dueDate,INITIAL_STATUS);
    }

    public Status getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        validateTitle(title);

        logEvent(String.format("Title changed from %s to %s", getTitle(), title));
        this.title = title;
    }



    public void setDueDate(LocalDate dueDate) {
        validateDueDate(dueDate);

        logEvent(String.format("DueDate changed from %s to %s", getDueDate(), dueDate));
        this.dueDate = dueDate;
    }

    private void setStatus(Status status) {
        logEvent(String.format("Status changed from %s to %s", getStatus(), status));
        this.status = status;
    }

    public void revertStatus() {
        if (status != INITIAL_STATUS) {
            setStatus(Status.values()[status.ordinal() - 1]);
        } else {
            logEvent(String.format("Can't revert, already at %s", getStatus()));
        }
    }

    public void advanceStatus() {
        if (status != FINAL_STATUS) {
            setStatus(Status.values()[status.ordinal() + 1]);
        } else {
            logEvent(String.format("Can't advance, already at %s", getStatus()));
        }
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

    public void displayHistory() {
        for (EventLog log : history) {
            System.out.println(log.viewInfo());
        }
    }

    private void logEvent(String event) {
        history.add(new EventLog(event));
    }

    private void validateTitle(String title) {
        if (title.length() < TITLE_MIN_LENGTH || title.length() > TITLE_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(
                    "Please provide a title with length between %d and %d chars",
                    TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        }
    }

    private void validateDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("DueDate can't be in the past");
        }
    }

}
