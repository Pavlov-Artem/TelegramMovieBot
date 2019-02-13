package by.tomP;

import by.tomP.service.MybotTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Configuration
@Import(Config.class)
public class BotLauncher {


    public static void main(String[] args) {
        ApiContextInitializer.init();

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        TelegramBotsApi telegramBotsApi = context.getBean(TelegramBotsApi.class);

        try {
            telegramBotsApi.registerBot(context.getBean(MybotTest.class));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
   /* public static void main(String[] args) {




        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new MybotTest());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/

}
