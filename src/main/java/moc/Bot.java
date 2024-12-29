package moc;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class Bot extends TelegramLongPollingBot {

  @Override
  public String getBotUsername() {
      return "MOCFURBOT";
  }

  @Override
  public String getBotToken() {
      return "7860998568:AAGrN-qJlEI2hv-MWqEm4cxj7m0dvyZG5fQ";
  }

  @Override
  public void onUpdateReceived(Update update) {
	  if (update.hasMessage() && update.getMessage().hasText()) {
          String command = update.getMessage().getText();
          Long chatId = update.getMessage().getChatId();

          switch (command) {
              case "/start":
			try {		
				new ImageParser();
				System.out.println(ImageParser.randomImageUrl);
				sendPhoto(chatId, ImageParser.randomImageUrl);			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                  break;
              default:
                  // Обработка других команд
                  break;
          }
      }
	  
  }
  
  private void sendPhoto(Long chatId, String filePath) throws MalformedURLException, IOException {
	  InputStream stream = new URL(filePath).openStream();
      SendPhoto sendPhotoRequest = new SendPhoto();
      sendPhotoRequest.setChatId(chatId.toString());
      sendPhotoRequest.setPhoto(new InputFile(stream, filePath));

      try {
          execute(sendPhotoRequest);
      } catch (TelegramApiException e) {
          e.printStackTrace();
      }
  }

}