package dynamicdrillers.sagy;

/**
 * Created by mayank on 3/2/18.
 */

public class ConstituencyModal {

    public ConstituencyModal() {
    }


    String tahshil;
    String mp;
    String constituency;
    String villageadopted;

    public ConstituencyModal(String tahshil, String mp, String constituency, String villageadopted) {
        this.tahshil = tahshil;
        this.mp = mp;
        this.constituency = constituency;
        this.villageadopted = villageadopted;
    }

    public String getVillageadopted() {
        return villageadopted;
    }

    public void setVillageadopted(String villageadopted) {
        this.villageadopted = villageadopted;
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

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
