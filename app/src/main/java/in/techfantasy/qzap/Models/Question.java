package in.techfantasy.qzap.Models;

public class Question {
    private String ImgUrl;
    private String Answer;
    private int Difficulty;

    public Question() {
    }

    public Question(String imgUrl, String answer, int difficulty) {
        ImgUrl = imgUrl;
        Answer = answer;
        Difficulty = difficulty;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(int difficulty) {
        Difficulty = difficulty;
    }
}
