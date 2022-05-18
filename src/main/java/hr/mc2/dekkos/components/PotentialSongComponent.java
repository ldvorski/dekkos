package hr.mc2.dekkos.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import hr.mc2.dekkos.model.Song;

import java.util.List;
@Tag("potSong")
public class PotentialSongComponent extends Component{
    List<Song> songs;
    Image thumbnail;
    Text title;
    Button select;

    public PotentialSongComponent(Song song){
        this.thumbnail = new Image(song.getThumbnail(), song.getTitle());
        this.title = new Text(song.getTitle());
        this.select = new Button(VaadinIcon.BULLSEYE.create());
        this.select.addClickListener(e -> VaadinService.getCurrentRequest().getWrappedSession().setAttribute("curSong", song.getUrl()));
    }
    public static Div songComp(PotentialSongComponent song){
        String height = song.thumbnail.getHeight();
        Div bottom = new Div(song.title,song.select);
        bottom.setHeight(height);

        return new Div(song.thumbnail,bottom);
    }

    public static HorizontalLayout create(List<Song> songs){
        HorizontalLayout idk = new HorizontalLayout();
        for ( Song song: songs ) {
            idk.add(new VerticalLayout(songComp(new PotentialSongComponent(song))));
        }
        return idk;
    }

}
