package Main;

import Parsing.Contents;
import Parsing.Processor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SBJiraBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage()) {

            Contents contents = new Contents(update.getMessage().getChatId());
            contents.date = update.getMessage().getDate();
            contents.chat = update.getMessage().getChat();

            //Collect all contents and set it as needed.
            if (update.getMessage().hasText()) {
                contents.text = update.getMessage().getText();
            }

            if (update.getMessage().hasContact()) {
                contents.contact = update.getMessage().getContact();
            }

            if (update.getMessage().hasDocument()) {
                contents.document = update.getMessage().getDocument();
            }

            SendMessage msg = new SendMessage(contents.chatId, Processor.process(contents));

            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return ("SBJiraBot_bot");
    }

    public String getBotToken() {
        return ("698269670:AAGC6NyhfnhFoR9Ak__KcDKgBVgS0uqdn9A");
    }

}
