package si.fir.prpo.skupina57.api.v1.dtos;

import java.util.Objects;

public class Kanal {

    private Integer id;
    private String link;
    private String password;
    private String key;


    private String naziv;

    public Kanal(String naziv) {
        this.naziv = naziv;
    }

    public Kanal() { }


    public String getNaziv() {
        return naziv;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !obj.getClass().isAssignableFrom(Kanal.class)) {
            return false;
        }
        return this.getNaziv().equals(((Kanal) obj).getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }
}
