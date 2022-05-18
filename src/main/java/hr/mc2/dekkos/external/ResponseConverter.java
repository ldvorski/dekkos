package hr.mc2.dekkos.external;

import com.google.api.client.util.Key;

import java.util.List;
import java.util.Map;

public class ResponseConverter {
        @Key("items")
        private List<SearchResult> searchResults;
    }

    class SearchResult {
        @Key
        private String kind;

        @Key("id")
        private VideoId videoId;

        @Key
        private Snippet snippet;

        public String getKind() {
            return kind;
        }

        public VideoId getId() {
            return videoId;
        }

        public Snippet getSnippet() {
            return snippet;
        }
    }

    class VideoId {
        @Key
        private String kind;

        @Key
        private String videoId;

        public String getKind() {
            return kind;
        }

        public String getVideoId() {
            return videoId;
        }
    }

    class Snippet {
        @Key
        private Map<String,Thumbnail> thumbnails;
        @Key
        private String title;

        @Key
        private String description;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Map<String, Thumbnail> getThumbnails() {
            return thumbnails;
        }
    }

    class Thumbnail {
        @Key
        private String url;

        @Key
        private long width;

        @Key
        private long height;

        public String getUrl() {
            return url;
        }

        public long getWidth() {
            return width;
        }

        public long getHeight() {
            return height;
        }
}
