package RecommandationTemplate;

import Homework.*;
import Homework.Stream;

import java.util.function.Function;
import java.util.stream.*;
import java.util.*;

/**
 * Recommand class with template
 */

public class Recommand extends RecommandTemplate{

    public Recommand(Integer userId, Integer type) {
        //this.allData = allData;
        this.userId = userId;
        this.type = type;
    }

    //verify if the use is not listening the current stream
    //but has the streamer int his list

    public boolean verifIfUserListens(HashMap<Integer, Streamer> allListenedStreamers, ArrayList<Stream> currentUserListens, Stream currentStream) {
        return allListenedStreamers.containsKey(currentStream.getStreamerId()) && !currentUserListens.contains(currentStream);
    }

    @Override
    List<Stream> createRecommendedStreams(Integer userId, Integer type, HashMap<Integer, Streamer> allStreamersListened) {
        ArrayList<Stream> userListened = DataBase.Instance().getAllUsers().get(userId).getListenedStreams();

        userListened = (ArrayList<Stream>) userListened.stream()
                .filter(stream -> stream.getStreamType().equals(type))
                .collect(Collectors.toList());
        ArrayList<Stream> finalUserListened = userListened;

        //lambda where i filtrer after the condition above and\
        //after the type and sort after my condition

        return DataBase.Instance().getAllStreams().values()
                .stream()
                .filter(s -> s.getStreamType().equals(type))
                .filter(s -> verifIfUserListens(allStreamersListened, finalUserListened, s))
                .sorted(new SongsComparator())
                .collect(Collectors.toList());
    }
    @Override
    Integer returnNumberOfStreams(List<Stream> recommendedStreams) {
        return Math.min(recommendedStreams.size(), 5);
    }
}
class SongsComparator implements Comparator<Stream> {

    @Override
    public int compare(Stream o1, Stream o2) {
        return (int) (o2.getNoOfStreams() - o1.getNoOfStreams());
    }
}