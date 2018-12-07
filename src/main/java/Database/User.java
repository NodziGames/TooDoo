package Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private long id;
    private List<Todo> todos;

    public User(long id) {
        this.id = id;
        todos = new ArrayList<Todo>();
    }

    //Getters and setters
    public long getId() {
        return this.id;
    }

    public List<Todo> getTodos() {
        return this.todos;
    }
    //Functionality
    public void addTodo(String task, boolean completed) {
        todos.add(new Todo(task, completed));
    }

    public String listTodos() {
        String list = "";

        for (int i = 0; i < todos.size(); i++) {
            list += Integer.toString(i) + " -- " + todos.get(i).toString() + "\n\n";
        }
        return list;
    }

    public Todo getTodoByIndex(int index) {
        if (index >= todos.size()) {
            return null;
        }

        return todos.get(index);
    }

    @Override
    public String toString() {
        return "id = " + Long.toString(id);
    }
}
