package by.tomP.service;


import by.tomP.entity.User;
import by.tomP.repository.MovieRepository;
import by.tomP.repository.UserRepository;
import com.omertron.omdbapi.OMDBException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MybotTest extends TelegramLongPollingBot {

    private FilmsData filmsData = new FilmsData();
    private UserRepository userRepository;
    private MovieRepository movieRepository;

    public MybotTest(UserRepository userRepository, MovieRepository movieRepository) {
        this.filmsData = filmsData;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {

        sendCustomKeyboard(update.getMessage().getChatId());
        // TODO
        // We check if the update has a message and the message has text

        String resultString = null;

        if (update.hasMessage() && update.getMessage().hasText()) {

            User user = new User(update.getMessage().getFrom());

            userRepository.save(user);


            String trigger = update.getMessage().toString();


            if (trigger == "random") {
                resultString = filmsData.getRandomMovie();
            } else {
                try {
                    resultString = filmsData.getConcreteMovie(update.getMessage().getText());
                } catch (OMDBException e) {
                    e.printStackTrace();
                }
            }

            if (resultString == null)
                resultString = "Empty text!!!";
            else {

                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText(resultString);
                /*SendMessage message1 = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Thnks," + userRepository.getUserByFirstName("Tom"));*/
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendCustomKeyboard(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Custom message text");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Get Random movie");

        // Add the first row to the keyboard
        keyboard.add(row);

        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        try {
            // Send the message
            message.setText(filmsData.getRandomMovie());
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        // TODO
        return "Tomtest01_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "botToken";
    }

}
