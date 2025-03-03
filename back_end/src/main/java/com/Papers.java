package main.java.com;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class Papers {
    private int test_paper_id;
    private String test_paper_name;
    private String data;
    private String subject_name;

    public Papers(){}
    public Papers(int test_paper_id,String test_paper_name,String data,String subject_name){
        this.test_paper_id = test_paper_id;
        this.test_paper_name = test_paper_name;
        this.data = data;
        this.subject_name = subject_name;
    }

    public int getPaperId() {
        return test_paper_id;
    }

    public String getPaperName() {
        return test_paper_name;
    }

    public String getData() {
        return data;
    }

    public String getSubjectName() {
        return subject_name;
    }
}
