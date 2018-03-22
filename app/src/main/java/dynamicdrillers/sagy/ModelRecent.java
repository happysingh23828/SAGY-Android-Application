package dynamicdrillers.sagy;

/**
 * Created by Krishana Sony on 22-03-2018.
 */

public class ModelRecent {
private String  description,image,time,title;

    public ModelRecent(String description, String image, String time, String title) {
        this.description = description;
        this.image = image;
        this.time = time;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ModelRecent() {
    }
}
