package Database;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private List<User> users;

    public Database() {
         users = new ArrayList<User>();
    }


    //Getters and Setters
    public List<User> getUsers() {
        return this.users;
    }


    //DB methods

    public void createUser(long id) {
        if (getUserById(id) == null) {
            users.add(new User(id));
        }
    }

    public User getUserByIndex(int index) {
        if (index > this.users.size() || index < 0)
            return null;

        return this.users.get(index);
    }

    public int getUserIndex(User user) {
        if (this.users.contains(user)) {
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).equals(user))
                    return i;
            }
        }
        return (-1);
    }

    public User getUserById(long id) {
        for (int i = 0; i < this.users.size(); i++) {
            if (users.get(i).getId() == id)
                return users.get(i);
        }
        return null;
    }

    public String listUsers() {
        String response = "";
        for (int i = 0; i < users.size(); i++) {
            response += users.get(i).toString() + "\n";
        }

        return response;
    }
}
