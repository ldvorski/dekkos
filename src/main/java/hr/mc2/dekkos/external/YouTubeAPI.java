package hr.mc2.dekkos.external;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class YouTubeAPI {
    private static final String DEVELOPER_KEY = "AIzaSyBxsNhZQyltAzw9hlcMQ6dvpKBsBOOH6pc";

    private static final String APPLICATION_NAME = "Dekkos YTDAPI";
    @SuppressWarnings("deprecation")
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static SearchListResponse search(String q) throws GeneralSecurityException, IOException{
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        return request.setKey(DEVELOPER_KEY)
                .setMaxResults(4L)
                .setQ(q)
                .execute();
    }
}
/*
    parseati json, kreirati tempSong entitete
    kreirati grid sa odabirom tempSonga
    kreirati odabrani Song objekt i dodati ga trenutnom partiju
    prikazati popis pjesama


 */