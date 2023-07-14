package Homework;
import RecommandationTemplate.Recommand;
import RecommandationTemplate.Surprise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;


/**
 *
 * Database class where i do the commands and keep all the data
 * */
public class DataBase {
    private HashMap<Integer,Streamer> allStreamers;
    private HashMap<Integer, Stream> allStreams;
    private HashMap<Integer, User> allUsers;

    private static DataBase uniqeInstance;

    public HashMap<Integer, Streamer> getAllStreamers() {
        return allStreamers;
    }

    public void setAllStreamers(HashMap<Integer, Streamer> allStreamers) {
        this.allStreamers = allStreamers;
    }

    public HashMap<Integer, Stream> getAllStreams() {
        return allStreams;
    }

    public void setAllStreams(HashMap<Integer, Stream> allStreams) {
        this.allStreams = allStreams;
    }

    public HashMap<Integer, User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(HashMap<Integer, User> allUsers) {
        this.allUsers = allUsers;
    }

    public DataBase() {}

    public DataBase(HashMap<Integer, Streamer> allStreamers, HashMap<Integer, Stream> allStreams, HashMap<Integer, User> allUsers) {
        this.allStreamers = allStreamers;
        this.allStreams = allStreams;
        this.allUsers = allUsers;
    }

    //singleton instance
    public static DataBase Instance() {
        if (uniqeInstance == null) {
            uniqeInstance = new DataBase();
        }
        return uniqeInstance;
    }

    //add stream function from the AddCommand execute
    public void addStream(Stream currentStream) {
        allStreams.put(currentStream.getId(), currentStream);
        Streamer newStreamer = allStreamers.get(currentStream.getStreamerId());
        newStreamer.addStream(currentStream);
    }

    public static void resteDataBase() {
         uniqeInstance = null;
    }

    //get time for list
    public String getTime(Long seconds) {
        long minutes = (seconds / 60) % 60;
        long remainingSeconds = seconds % 60;
        long hours = seconds / 3600;
        String duration = String.format("%02d:%02d", minutes, remainingSeconds);
        if (hours > 0) {
            duration = String.format("%02d:%s", hours, duration);
        }
        return duration;
    }

    //get data from the unix stamp
    public String data(long unixTimestamp) {
        return Instant.ofEpochMilli(unixTimestamp * 1000L)
                .atZone(ZoneId.of("GMT"))
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    //print in json format

    public void printOneStream(Stream currentStream) {
        Map<String, Object> outputMap = new HashMap<>();
        long seconds = currentStream.getLenght();
        String time = getTime(seconds);
        String formattedDate = data(currentStream.getDateAdded());
        outputMap.put("id", currentStream.getId().toString());
        outputMap.put("name", currentStream.getName());
        outputMap.put("streamerName", allStreamers.get(currentStream.getStreamerId()).getName());
        outputMap.put("noOfListenings", currentStream.getNoOfStreams().toString());
        outputMap.put("length", time);
        outputMap.put("dateAdded", formattedDate);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.print(mapper.writeValueAsString(outputMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //iterator where i list all the streams
    public void print(Iterator<Stream> iterator) {
        System.out.print("[");
        while (iterator.hasNext()) {
            Stream currentStream = (Stream) iterator.next();
            printOneStream(currentStream);
            if(iterator.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    // just list the streams
    public void list(Integer id) {
        Streamer currentStreamer = allStreamers.get(id);
        if(currentStreamer != null) {
            Iterator<Stream> streamer = currentStreamer.createIterator();
            print(streamer);

        } else {
            User currentUser = allUsers.get(id);
            Iterator<Stream> user = currentUser.createIterator();
            print(user);
        }
    }

    //delete the streams and use the observer

    public void deleteStream(Integer idStreamer, Integer idStream) {
        allStreams.get(idStream).delete();
        allStreams.remove(idStream);
    }

    //listen command that adds the new stream to user

    public void listen(Integer userId , Integer streamId) {
        User currentUser = allUsers.get(userId);
        Stream currentListenedStream = allStreams.get(streamId);
        currentUser.addStream(currentListenedStream);
    }

    //recommand command that calls the function with template
    public void recommend(Integer idUser, Integer songType) {
        Recommand newRecommend = new Recommand(idUser, songType);
        newRecommend.printRecommendation();
    }
    //same as above for surprise
    public void surprise(Integer userId, Integer type) {
        Surprise newSurprise = new Surprise(userId, type);
        newSurprise.printRecommendation();
    }
}