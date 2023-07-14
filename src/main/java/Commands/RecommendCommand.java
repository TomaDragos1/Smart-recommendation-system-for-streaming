package Commands;

import Homework.DataBase;

public class RecommendCommand implements Command {
    private Integer userId;
    private Integer type;

    public RecommendCommand(Integer idStreamer, Integer type) {
        this.userId = idStreamer;
        this.type = type;
    }


    @Override
    public void execute() {
        DataBase.Instance().recommend(userId, type);
    }

    public Integer getIdStreamer() {
        return userId;
    }

    public void setIdStreamer(Integer idStreamer) {
        this.userId = idStreamer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
