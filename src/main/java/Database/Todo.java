package Database;

public class Todo {
    private String task;
    private boolean finished;

    public Todo(String task, boolean finished) {
        this.task = task;
        this.finished = finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean getFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        String str = "[";
        if (finished) {
            str += "X";
        }
        else {
            str += "  ";
        }
        str += "]     " + task;

        return str;
    }
}
