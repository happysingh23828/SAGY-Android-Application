package dynamicdrillers.sagy;

/**
 * Created by Krishana Sony on 22-03-2018.
 */

public class Modelmp {
    private  String address,constituency,dob, image,party,name,state,villageadopted;

    public Modelmp(String address, String constituency, String dob, String image, String party, String name, String state, String villageadopted) {
        this.address = address;
        this.constituency = constituency;
        this.dob = dob;
        this.image = image;
        this.party = party;
        this.name = name;
        this.state = state;
        this.villageadopted = villageadopted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVillageadopted() {
        return villageadopted;
    }

    public void setVillageadopted(String villageadopted) {
        this.villageadopted = villageadopted;
    }

    public Modelmp() {
    }
}
