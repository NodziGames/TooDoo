package Parsing;

import java.util.List;

import Database.Todo;
import Main.Main;

public class Commands {

    //Declare commands here
    public static final String C_HELP = "help";
    public static final String C_USERS = "users"; //DEBUG! Remove command in prod
    public static final String C_TODO = "todo";
    public static final String C_TODOS = "todos";
    public static final String C_DONE = "done";
    public static final String C_RESET = "reset";
    public static final String C_REMOVE = "remove";
    public static final String C_WIPE = "wipe";
    public static final String C_CLEANUP = "cleanup";





    //Process the commands here
    public static String reply(List<String> args, Contents content) {

        String reply;

        switch (args.get(0)) {
            case C_HELP:
                reply = c_help(args);
                break;
            case C_USERS:
                reply = c_users(args);
                break ;
            case C_TODO:
                reply = c_todo(args, content);
                break ;
            case C_TODOS:
                reply = c_todos(args, content);
                break ;
            case C_DONE:
                reply = c_done(args, content);
                break ;
            case C_RESET:
                reply = c_reset(args, content);
                break ;
            case C_REMOVE:
                reply = c_remove(args, content);
                break ;
            case C_WIPE:
                reply = c_wipe(args, content);
                break ;
            case C_CLEANUP:
                reply = c_cleanup(args, content);
                break ;
            default:
                reply = "Unknown Command, type 'help' for a list of commands";
                break;
        }
        return reply;
    }









    public static String c_help(List<String> args) {

        return "COMMANDS: " + "\n" +
                "help : Displays a list of all commands" + "\n" +
                "users : Displays a list of all users" + "\n" +
                "jira : Displays all your outstanding Jira tickets" +
                "todo : Adds a new task to your Todo list" + "\n" +
                "todos : Lists all your Todo tasks" + "\n" +
                "done : Ticks a task off your Todo list" + "\n" +
                "reset : Sets a complete task to unfinished" + "\n" +
                "remove: Removes a task from your todo list" + "\n" +
                "cleanup: Removes all finished tasks from your todo list";
    }

    public static String c_users(List<String> args) {
        String reply = Main.db.listUsers();

        return reply;
    }

    public static String c_todo(List<String> args, Contents content) {

        if (args.size() <= 1) {
            return "No task has been provided! Ex: 'todo Do The Laundry'";
        }

        String task = "";

        for (int i = 1; i < args.size(); i++) {
            task += args.get(i) + " ";
        }

        Long user = content.chatId;

        Main.db.getUserById(user).addTodo(task, false);

        return ("TODO successfully added!");
    }

    public static String c_todos(List<String> args, Contents content) {
        if (Main.db.getUserById(content.chatId).getTodos().size() == 0)
            return "No TODO's have been added yet!";
        return Main.db.getUserById(content.chatId).listTodos();
    }

    public static String c_done(List<String> args, Contents content) {

        if (args.size() != 2) {
            return ("Insufficient arguments! Ex: 'done 2'");
        }

        int index = Integer.parseInt(args.get(1));

        if (index >= Main.db.getUserById(content.chatId).getTodos().size()) {
            return ("Todo does not exist");
        }
        else {
            Main.db.getUserById(content.chatId).getTodoByIndex(index).setFinished(true);
            return ("Todo has been completed!");
        }
    }


    public static String c_reset(List<String> args, Contents content) {
        if (args.size() != 2) {
            return ("Insufficient arguments! Ex: 'reset 3'");
        }

        int index = Integer.parseInt(args.get(1));

        if (index >= Main.db.getUserById(content.chatId).getTodos().size()) {
            return ("Todo does not exist");
        }
        else if (Main.db.getUserById(content.chatId).getTodoByIndex(index).getFinished()) {
            Main.db.getUserById(content.chatId).getTodoByIndex(index).setFinished(false);
            return ("Todo has been set to unfinished!");
        }
        else {
            return ("Task hasn't been finished yet");
        }
    }

    public static String c_remove(List<String> args, Contents content) {

        if (args.size() != 2) {
            return ("Insufficient arguments! Ex: 'remove 7'");
        }

        int index = Integer.parseInt(args.get(1));

        if (index >= Main.db.getUserById(content.chatId).getTodos().size()) {
            return ("Todo does not exist");
        }
        else {
            Main.db.getUserById(content.chatId).getTodos().remove(Main.db.getUserById(content.chatId).getTodos().get(index));
            return ("Todo successfully removed!");
        }
    }

    public static String c_wipe(List<String> args, Contents content) {

        Main.db.getUserById(content.chatId).getTodos().clear();

        return ("All Todo's have been successfully removed!");
    }

    public static String c_cleanup(List<String> args, Contents content) {

        for (int i = 0; i < Main.db.getUserById(content.chatId).getTodos().size(); i++) {
            if (Main.db.getUserById(content.chatId).getTodoByIndex(i).getFinished()) {
                Main.db.getUserById(content.chatId).getTodos().remove(i);
                i--;
            }
        }
        return "All finished tasks have been erased!";
    }
}
