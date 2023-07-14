package Commands;

import Homework.DataBase;

public class DeleteCommand implements Command{
    private Integer idStreamer;

    private Integer idStream;

    public DeleteCommand(Integer idStreamer, Integer idStream) {
        this.idStreamer = idStreamer;
        this.idStream = idStream;
    }

    @Override
    public void execute() {
        DataBase.Instance().deleteStream(idStreamer, idStream);
    }

    public Integer getIdStreamer() {
        return idStreamer;
    }

    public void setIdStreamer(Integer idStreamer) {
        this.idStreamer = idStreamer;
    }

    public Integer getIdStream() {
        return idStream;
    }

    public void setIdStream(Integer idStream) {
        this.idStream = idStream;
    }
}
