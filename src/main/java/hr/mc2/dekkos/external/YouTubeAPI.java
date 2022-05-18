package hr.mc2.dekkos.external;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class YouTubeAPI {
    private static final String DEVELOPER_KEY = "AIzaSyBxsNhZQyltAzw9hlcMQ6dvpKBsBOOH6pc";

    private static final String APPLICATION_NAME = "Dekkos YTDAPI";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

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
    public static List<SearchResult> search(String q) throws GeneralSecurityException, IOException{
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response =request.setKey(DEVELOPER_KEY)
                .setMaxResults(4L)
                .setQ(q)
                .execute();
        return response.getItems();
    }
}
/*
    parseati json, kreirati tempSong entitete
    kreirati grid sa odabirom tempSonga
    kreirati odabrani Song objekt i dodati ga trenutnom partiju
    prikazati popis pjesama


 */