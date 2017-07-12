package blog.geek.entity;

import java.util.List;

/**
 * 训练项目
 * @author yuanyang
 * @version 1.0
 */
public class Train {

    private String trainId;
    private String trainName;
    private String trainTime;
    private String trainContent;
    private String trainLink;
    private List<String> trainImg;

    //start getter and setter
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getTrainLink() {
        return trainLink;
    }

    public void setTrainLink(String trainLink) {
        this.trainLink = trainLink;
    }

    public List<String> getTrainImg() {
        return trainImg;
    }

    public void setTrainImg(List<String> trainImg) {
        this.trainImg = trainImg;
    }

    //end getter and setter
}
