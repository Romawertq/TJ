package com.Romawertq;

import com.Romawertq.bot.TelegramBots;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.*;

public class Main {

    // Простой внутренний класс для хранения данных (можно без геттеров, если доступ изнутри)
    public static class Config {
        public String token;
        public String chat_id;
        public Integer topic_id;
        public String username;
    }

    public static void main(String[] args) throws TelegramApiException {
        Yaml yaml = new Yaml();

        // Загружаем YAML как Map
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.yml")) {
            if (inputStream == null) {
                System.err.println("Файл config.yml не найден!");
                return;
            }

            // Парсим в Map
            Map<String, Object> data = yaml.load(inputStream);

            // Создаём объект Config вручную (без рефлексии SnakeYAML)
            Config config = new Config();
            config.token = (String) data.get("token");
            config.chat_id = (String) data.get("chat-id");
            config.topic_id = (int) data.get("topic-id");
            config.username = (String) data.get("username");

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBots(config));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
