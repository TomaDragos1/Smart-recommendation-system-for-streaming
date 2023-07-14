package Commands;
import Homework.*;

public class AddStreamCommand implements Command {
    private Stream stream;
    private DataBase dataBase;

    public AddStreamCommand(Stream streamer, DataBase dataBase) {
        this.stream = streamer;
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.addStream(this.stream);
    }

    public Stream getStreamer() {
        return stream;
    }

    public void setStreamer(Stream streamer) {
        this.stream = streamer;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }
}
