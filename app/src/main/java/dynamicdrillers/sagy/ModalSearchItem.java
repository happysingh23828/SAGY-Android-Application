package dynamicdrillers.sagy;

/**
 * Created by mayank on 3/2/18.
 */

public class ModalSearchItem {

    String image;
    String name,village,constituency;

    public ModalSearchItem() {
    }

    public ModalSearchItem(String image, String name, String village, String constituency) {
        this.image = image;
        this.name = name;
        this.village = village;
        this.constituency = constituency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMp() {
        return name;
    }

    public void setMp(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
