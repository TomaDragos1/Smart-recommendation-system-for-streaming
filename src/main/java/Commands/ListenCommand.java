package Commands;

import Homework.DataBase;

public class ListenCommand implements Command {
    private Integer userId;
    private Integer idStream;

    public ListenCommand(Integer userId, Integer idStream) {
        this.userId = userId;
        this.idStream = idStream;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdStream() {
        return idStream;
    }

    public void setIdStream(Integer idStream) {
        this.idStream = idStream;
    }

    @Override
    public void execute() {
        DataBase.Instance().listen(userId, idStream);
    }
}
