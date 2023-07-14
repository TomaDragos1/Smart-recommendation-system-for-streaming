package Commands;

import Homework.DataBase;
import Homework.Stream;

public class ListCommand implements Command {
    private Integer id;

    public ListCommand(Integer id) {
        this.id = id;
    }

    @Override
    public void execute() {
        DataBase.Instance().list(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
