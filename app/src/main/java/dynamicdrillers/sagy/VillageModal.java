package dynamicdrillers.sagy;

/**
 * Created by mayank on 3/2/18.
 */

public class VillageModal {

    public VillageModal() {
    }


    String tahshil;
    String mp;
    String village;

    public VillageModal(String tahshil, String mp, String village) {
        this.tahshil = tahshil;
        this.mp = mp;
        this.village = village;
    }

    public String getTahshil() {
        return tahshil;
    }

    public void setTahshil(String tahshil) {
        this.tahshil = tahshil;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
