package Parsing;

import Main.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Processor {

    public static String process(Contents content) {

        List<String> args = new ArrayList<String>();

        args = Processor.parse(content.text);

        if (Main.db.getUserById(content.chatId) == null) {
            Main.db.createUser(content.chatId);
        }

        return(Commands.reply(args, content));
    }



    public static List<String> parse(String str) {
        str = str.toLowerCase();

        str = str.trim().replaceAll(" +", " ");
        str = str.replaceAll("\n", " ");

        String[] temp_args = str.split(" ");

        List<String> args = new ArrayList<String>();

        args.addAll(Arrays.asList(temp_args));

        return args;
    }
}
