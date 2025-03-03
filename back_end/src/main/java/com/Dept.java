package main.java.com;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class Dept {
    private int id;
    private String problem;
    private String answer;
    private String analysis;
    private String type;
    private String points;
    private float difficulty;


    public Dept(){}
    public Dept(int id,String problem,String answer,String analysis,String type,String points,float difficulty){
        this.id = id;
        this.problem = problem;
        this.answer = answer;
        this.analysis = analysis;
        this.type = type;
        this.points = points;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getProblem() {
        return problem;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public String getType() {
        return type;
    }

    public String getPoints() {
        return points;
    }

    public float getDifficulty() {
        return difficulty;
    }

}
