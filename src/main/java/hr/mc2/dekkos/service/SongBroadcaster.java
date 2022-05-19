package hr.mc2.dekkos.service;

import com.vaadin.flow.shared.Registration;
import hr.mc2.dekkos.model.Song;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class SongBroadcaster {
    static Executor executor = Executors.newSingleThreadExecutor();

    static LinkedList<Consumer<Song>> listeners = new LinkedList<>();

    public static synchronized Registration register(
        Consumer<Song> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (SongBroadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(Song message) {
        for (Consumer<Song> listener : listeners) {
            executor.execute(() -> listener.accept(message));
        }
    }
}
