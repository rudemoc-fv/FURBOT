package moc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ImageParser {
	
	public static String cur_img;
	public static String randomImageUrl;
	
	public ImageParser() throws IOException {
		Random rn = new Random();
		int chance = rn.nextInt(8);
		String url;
		switch(chance) {
		case 1:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=furry&sort=date-desc";
			break;
		case 2:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=loona&sort=date-desc";
			break;
		case 3:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=loona&sort=date-desc";
			break;
		case 4:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=fox&sort=date-desc";
			break;
		case 5:
			url = "https://www.newgrounds.com/search/conduct/art?terms=cat&sort=date-desc&sort=date-desc";
			break;
		case 6:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=dog&sort=date-desc";
			break;
		case 7:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=hazbin&sort=date-desc";
			break;
		case 8:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=helluva&sort=date-desc";
			break;
		case 9:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=mommy&sort=date-desc";
			break;
		case 10:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=ankha&sort=date-desc";
			break;
		case 11:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=helltaker&sort=date-desc";
			break;
		case 12:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=murder-drones&sort=date-desc";
			break;
		case 13:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=minecraft&sort=date-desc";
			break;
		case 14:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=bee&sort=date-desc";
			break;
		case 15:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=bdsm&sort=date-desc";
			break;
		case 16:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=hentai&sort=date-desc";
			break;
		case 17:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=interneccion-cube&sort=date-desc";
			break;
		case 18:
			url = "https://www.newgrounds.com/search/conduct/art?suitabilities=etm&c=4&terms=toy-chika&sort=date-desc";
			break;
		default:
			url = "https://www.newgrounds.com/search/conduct/art?terms=fnaf&sort=date-desc";
			break;
		}
        Document doc = Jsoup.connect(url).get();

        Elements images = doc.select("img");

        List<String> imageUrls = new ArrayList<>();

        for (Element image : images) {
            String src = image.attr("abs:src");

            if (!src.isEmpty()) {
                imageUrls.add(src);
            }
        }

        if (!imageUrls.isEmpty()) {
        	Random random = new Random();
            int index = random.nextInt(imageUrls.size());
            randomImageUrl = imageUrls.get(index);
            downloadImage(randomImageUrl);
        } else {
            System.out.println("No images found on the page.");
        }
    }
	
	
    private static void downloadImage(String imageUrl) throws IOException {
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

        System.out.println("Downloading: " + fileName);

        try (ReadableByteChannel rbc = Channels.newChannel(new URL(imageUrl).openStream());
             FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        
        cur_img = fileName;
    }
}