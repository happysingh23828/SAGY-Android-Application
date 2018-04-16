package dynamicdrillers.sagy;

/**
 * Created by Krishana Sony on 23-03-2018.
 */

public class ModelVideos  {
    private String time,title,youtubeid;

    public ModelVideos(String time, String title, String youtubeid) {
        this.time = time;
        this.title = title;
        this.youtubeid = youtubeid;
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

    public String getYoutubeid() {
        return youtubeid;
    }

    public void setYoutubeid(String youtubeid) {
        this.youtubeid = youtubeid;
    }

    public ModelVideos() {
    }
}
