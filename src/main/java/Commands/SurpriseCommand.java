package Commands;

import Homework.DataBase;

public class SurpriseCommand implements Command{
    private Integer userId;
    private Integer type;

    public SurpriseCommand(Integer userId, Integer type) {
        this.userId = userId;
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public void execute() {
        DataBase.Instance().surprise(userId, type);
    }
}
