package dynamicdrillers.sagy;

/**
 * Created by Krishana Sony on 22-03-2018.
 */

public class ModelVillage {
    private String village,adopted_by;

    public ModelVillage(String village, String adopted_by) {
        this.village = village;
        this.adopted_by = adopted_by;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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
