package in.techfantasy.qzap.Models;

import java.util.ArrayList;

public class GameSession {

    private int gameId;
    private int point;
    private ArrayList<Question> Questions;

    public GameSession() {
    }


    public GameSession(int gameId, int point, ArrayList<Question> questions) {
        this.gameId = gameId;
        this.point = point;
        Questions = questions;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        Questions = questions;
    }
}
