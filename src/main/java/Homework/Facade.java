package Homework;

import Builders.StreamBuilder;
import Builders.StreamerBuilder;
import Builders.UserBuilder;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

/**
 * Class where i parsate all the information from the stars into my database
 * that has 3 hashmaps
 */
public class Facade {

    //streamers method with lambdas where i just put my input in a hashmap
    public HashMap<Integer, Streamer> allStreamers(String[] args) {
        HashMap<Integer, Streamer> allData = new HashMap<>();
        String path = "src/main/resources/" + args[0];
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            reader.readNext();
            reader.readAll().forEach(row -> {
                Streamer newStreamer = new StreamerBuilder()
                            .withId(Integer.parseInt(row[1]))
                            .withType(Integer.parseInt(row[0]))
                            .withName(row[2])
                            .build();
                newStreamer.setAllStreams(new LinkedHashMap<>());
                allData.put(newStreamer.getId(), newStreamer);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }

    //same as above

    public HashMap<Integer, Stream> allStream(String[] args) {
        HashMap<Integer, Stream> allStreams = new HashMap<>();

        String path = "src/main/resources/" + args[1];
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            reader.readNext();
            reader.readAll().forEach(row -> {
                Stream newStream = new StreamBuilder()
                        .withStreamType(Integer.parseInt(row[0]))
                        .withNoOfStream(Long.parseLong(row[3]))
                        .withStreamGenre(Integer.parseInt(row[2]))
                        .withId(Integer.parseInt(row[1]))
                        .withDateAdded(Long.parseLong(row[6]))
                        .withStreamerId(Integer.parseInt(row[4]))
                        .withLenght(Long.parseLong(row[5]))
                        .withName(row[7])
                        .build();
                newStream.setObservers(new ArrayList<>());
                allStreams.put(newStream.getId(), newStream);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allStreams;


    }

    //same as above

    public HashMap<Integer, User> allUsers(String[] args, HashMap<Integer, Stream> allStreams) {
        HashMap<Integer, User> allUsers = new HashMap<>();
        try {
            String path = "src/main/resources/";
            path += args[2];
            CSVReader reader = new CSVReader(new FileReader(path));
            reader.readNext();
            reader.readAll().forEach(row-> {
                User newUser;
                ArrayList<Stream> allStreamsOfUser = new ArrayList<>();
                String[] data = row[2].split(" ");
                Arrays.stream(data).forEach(id -> allStreamsOfUser.add(allStreams.get(Integer.parseInt(id))));
                newUser = new UserBuilder()
                        .withId(Integer.parseInt(row[0]))
                        .withName(row[1])
                        .withListenedStreams(allStreamsOfUser)
                        .build();
                allUsers.put(newUser.getId(), newUser);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;

    }


    //main method where i initiate my singleton database and

    public DataBase mainFacade(String[] args) {

        Facade newFacade = new Facade();
        HashMap<Integer, Stream> allStreams;
        HashMap<Integer, Streamer> allStreamers;
        HashMap<Integer, User> allUsers;
        allStreamers = newFacade.allStreamers(args);
        allStreams = newFacade.allStream(args);

        //i have for each streamre a hashmap of created stream
        //here i put them
        allStreams.forEach((id, stream) -> {
            Streamer currentStreamer = allStreamers.get(stream.getStreamerId());
            currentStreamer.getAllStreams().put(stream.getId(), stream);
            stream.addObserver(currentStreamer);
        });

        //for observer design i need to do the array of observers that will be in stream
        allUsers = allUsers(args, allStreams);
        allUsers.forEach((id, user) -> {
            ArrayList<Stream> currentUserStreams = user.getListenedStreams();
            currentUserStreams.forEach(stream -> stream.addObserver(user));
        });
        DataBase allData =  DataBase.Instance();
        allData.setAllStreamers(allStreamers);
        allData.setAllUsers(allUsers);
        allData.setAllStreams(allStreams);
        return allData;

    }
}
