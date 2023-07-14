package Commands;

import Builders.StreamBuilder;
import Homework.DataBase;
import Homework.Stream;

import java.util.ArrayList;

public class CommandsFactory {

    public Stream createStream(String[] line) {
        StringBuilder text = new StringBuilder();
        for(int i = 6 ; i < line.length ; i++) {
            text.append(line[i]);
            if (i != line.length - 1) {
                text.append(" ");
            }
        }
        Stream currentStream = new StreamBuilder()
                .withStreamType(Integer.parseInt(line[2]))
                .withNoOfStream(0L)
                .withStreamGenre(Integer.parseInt(line[4]))
                .withId(Integer.parseInt(line[3]))
                .withDateAdded(1673625791L)
                .withStreamerId(Integer.parseInt(line[0]))
                .withLenght(Long.parseLong(line[5]))
                .withName(text.toString())
                .build();
        currentStream.setObservers(new ArrayList<>());
        return currentStream;
    }
    public Integer getType(String type) {
        Integer currentType = null;
        switch (type) {
            case "SONG" :{
                currentType = 1;
                break;
            }
            case "PODCAST": {
                currentType = 2;
                break;
            }
            case "AUDIOBOOK": {
                currentType = 3;
                break;
            }
        }
        return currentType;
    }
    public Command createCommand(String[] line) {
        switch (line[1]) {
            case "LIST": {
                return new ListCommand(Integer.parseInt(line[0]));
            }
            case "ADD": {
                return new AddStreamCommand(createStream(line), DataBase.Instance());
            }
            case "DELETE": {
                return new DeleteCommand(Integer.parseInt(line[0]), Integer.parseInt(line[2]));
            }
            case "LISTEN": {
                return new ListenCommand(Integer.parseInt(line[0]), Integer.parseInt(line[2]));
            }
            case "RECOMMEND": {
                return new RecommendCommand(Integer.parseInt(line[0]), getType(line[2]));
            }
            case "SURPRISE": {
                return new SurpriseCommand(Integer.parseInt(line[0]), getType(line[2]));
            }
        }
        return null;
    }
}
