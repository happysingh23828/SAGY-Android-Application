package dynamicdrillers.sagy;

/**
 * Created by mayank on 3/2/18.
 */

public class ModalSearchItem {

    String image;
    String mp,village,tahshil;

    public ModalSearchItem() {
    }

    public ModalSearchItem(String image, String mp, String village, String tahshil) {
        this.image = image;
        this.mp = mp;
        this.village = village;
        this.tahshil = tahshil;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getTahshil() {
        return tahshil;
    }

    public void setTahshil(String tahshil) {
        this.tahshil = tahshil;
    }
}
