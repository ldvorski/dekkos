package hr.mc2.dekkos.external;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import hr.mc2.dekkos.model.Song;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Component
public class YouTubeClient {
    private static final String DEVELOPER_KEY = "AIzaSyBxsNhZQyltAzw9hlcMQ6dvpKBsBOOH6pc";
    private static final String APPLICATION_NAME = "Dekkos YTDAPI";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final YouTube client;

    public YouTubeClient() {
        try {
            client = instantiateClient();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public List<Song> search(String q) {
        var response = client.search()
                .list(Collections.singletonList("snippet"))
                .setKey(DEVELOPER_KEY)
                .setMaxResults(4L)
                .setQ(q)
                .execute();

        return response.getItems().stream().map(item -> new Song(
                item.getId().getVideoId(),
                item.getSnippet().getThumbnails().getDefault().getUrl(),
                item.getSnippet().getTitle()
        )).toList();
    }

    private YouTube instantiateClient() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}

/*
    parseati json, kreirati tempSong entitete
    kreirati grid sa odabirom tempSonga
    kreirati odabrani Song objekt i dodati ga trenutnom partiju
    prikazati popis pjesama


 */
