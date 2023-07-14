package RecommandationTemplate;

import Homework.DataBase;
import Homework.Stream;
import Homework.Streamer;

import java.util.*;
import java.util.stream.Collectors;


/**
 *  Surprise command class with template
 */
public class Surprise extends RecommandTemplate {

    public Surprise(Integer userId, Integer type) {
        this.userId = userId;
        this.type = type;
    }

    //condition with lambda to see if the user
    //knows about the streamer of the current stream
    //if it dosent it will go into the recommandation list
    public boolean verifIfUserNotListens(Stream currentStream, HashMap<Integer, Streamer> allStreamersListened) {
        return allStreamersListened.values().stream().noneMatch(streamer -> streamer.getId().equals(currentStream.getStreamerId()));
    }
    @Override
    List<Stream> createRecommendedStreams(Integer userId, Integer type, HashMap<Integer, Streamer> allStreamersListened) {
        //lambda that filtrers afte the conditions and sorts with lambda
        return DataBase.Instance().getAllStreams().values()
                .stream()
                .filter(s-> s.getStreamType().equals(type))
                .filter(s -> verifIfUserNotListens(s , allStreamersListened))
                .sorted(new DateComapartor())
                .collect(Collectors.toList());
    }

    @Override
    Integer returnNumberOfStreams(List<Stream> recommendedStreams) {
        return Math.min(recommendedStreams.size(), 3);
    }
}

//comparator function for sort
class DateComapartor implements Comparator<Stream> {
    @Override
    public int compare(Stream o1, Stream o2) {
        long unixTimestamp = o1.getDateAdded();
        Date date1 = new Date(unixTimestamp * 1000L);
        unixTimestamp = o2.getDateAdded();
        Date date2 = new Date(unixTimestamp * 1000L);
        if(date1.before(date2)) {
            return 1;
        } else
        if(date1.after(date2))
            return -1;
        else {
            return (int) (o2.getNoOfStreams() - o1.getNoOfStreams());
        }
    }
}