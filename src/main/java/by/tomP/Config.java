package by.tomP;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.meta.TelegramBotsApi;


@Configuration
@ComponentScan("by.tomP")
@Import(DBConfig.class)
public class Config {

    @Bean
    public TelegramBotsApi telegramBotsApi(){
        return  new TelegramBotsApi();
    }
}
