package RecommandationTemplate;

import Homework.DataBase;
import Homework.Stream;
import Homework.Streamer;
import Homework.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Template for recommandations
 */

public abstract class RecommandTemplate {
    Integer userId;
    Integer type;

    public void printRecommendation() {

        //here i make a list of streamers where i save all the streamers that the user listenes
        List<Stream> recommendedStreams;
        User currentUser = DataBase.Instance().getAllUsers().get(userId);
        Map<Integer, Streamer> allStreamersListened = currentUser.getListenedStreams().stream()
                .map(i -> DataBase.Instance().getAllStreamers().get(i.getStreamerId()))
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toMap(Streamer::getId, Function.identity()));
        //here i do the recommandations for each case
        recommendedStreams = createRecommendedStreams(userId, type, (HashMap<Integer, Streamer>) allStreamersListened);
        int i = returnNumberOfStreams(recommendedStreams);

        //just print in json format
        System.out.print("[");
        for(int j = 0 ; j < i ; j++) {
            Stream currentStream = recommendedStreams.get(j);
            DataBase.Instance().printOneStream(currentStream);
            if(j != i - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    abstract List<Stream> createRecommendedStreams(Integer userId, Integer type, HashMap<Integer, Streamer> allStreamersListened);
    abstract Integer returnNumberOfStreams(List<Stream> recommendedStreams);


}
