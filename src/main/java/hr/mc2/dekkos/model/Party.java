package hr.mc2.dekkos.model;

import hr.mc2.dekkos.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Random;


@Entity
@Table(name = "party")
public class Party {
    @Id
    @Column(name = "party_code")
    private String partyCode;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "party")
    private User partyAdmin;

    @Autowired
    public Party(User user){
        this.isActive = true;
        this.partyAdmin = user;
        this.partyCode = generate(PartyService partyService);
    }
    protected Party(){}

    public String getPartyCode() {
        return partyCode;
    }
    public boolean partyIsActive(){
        return isActive;
    }


    public String generate(PartyService partyService) {

        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        if (partyService.existsPartyByPartyCode(sb.toString()))
            return generate(partyService);
        return sb.toString();
    }

}
