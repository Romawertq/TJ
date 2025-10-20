package com.Romawertq.bot;

import com.Romawertq.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBots extends TelegramLongPollingBot {
    public String token = "";
    public String chat_id = "";
    public Integer topic_id;
    public String username = "";

    public TelegramBots(Main.Config config){
        this.token = config.token;
        this.chat_id = config.chat_id;
        this.topic_id = config.topic_id;
        this.username = config.username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String firstWord = "";
        String input = update.getMessage().getText();
        String[] parts = input.split(" ");
        if (parts.length == 0) {
            System.out.println("Пустая строка");
        }
        else {
            firstWord = parts[0];
        }

        switch (firstWord){
            case "/start":
                sendMessage("Добро пожаловать, господи я заебался");
                break;
            case "Ура":
                sendMessage("Чё веселишься? Работать иди");
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken(){
        return token;
    }

    public void sendMessage(String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setMessageThreadId(topic_id);
        sendMessage.setText(text);

        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }
}
