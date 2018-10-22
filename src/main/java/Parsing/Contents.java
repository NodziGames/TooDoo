package Parsing;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Document;

public class Contents {

    public Long chatId;

    public Integer date;

    public String text;

    public Chat chat;

    public Contact contact;

    public Document document;

    public Contents (Long chatId) {

        this.chatId = chatId;

        text = null;
        date = null;
        chat = null;
        contact = new Contact();
        document = new Document();
    }

    @Override
    public String toString() {
        return "(CONTENTS)" + "\n" +
                "chatId = " + Long.toString(chatId) + "\n" +
                "date = " + Integer.toString(date) + "\n" +
                "text = " + text + "\n" +
                "contact = " + contact.toString() + "\n" +
                "document = " + document.toString() + "\n";
    }
}
