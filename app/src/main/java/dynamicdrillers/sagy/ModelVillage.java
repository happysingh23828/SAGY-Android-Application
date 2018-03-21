package dynamicdrillers.sagy;

/**
 * Created by Krishana Sony on 22-03-2018.
 */

public class ModelVillage {
    private String village,thasil,district,state,constituency,image,party,adopted_by;

    public ModelVillage(String village, String thasil, String district, String state, String constituency, String image, String party, String adopted_by) {
        this.village = village;
        this.thasil = thasil;
        this.district = district;
        this.state = state;
        this.constituency = constituency;
        this.image = image;
        this.party = party;
        this.adopted_by = adopted_by;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getThasil() {
        return thasil;
    }

    public void setThasil(String thasil) {
        this.thasil = thasil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getAdopted_by() {
        return adopted_by;
    }

    public void setAdopted_by(String adopted_by) {
        this.adopted_by = adopted_by;
    }

    public ModelVillage() {
    }
}
