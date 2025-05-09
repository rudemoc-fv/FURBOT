package moc;

import java.io.IOException;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
	public static void main(String[] args) throws IOException, TelegramApiException {
		new ImageParser();
		TelegramBotsApi botsApi = new TelegramBotsApi((Class<? extends BotSession>) DefaultBotSession.class);
		botsApi.registerBot(new Bot());
	}

}