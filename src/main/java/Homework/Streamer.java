package Homework;

import Commands.StreamIterator;
import org.apache.commons.collections.list.AbstractLinkedList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Streamer implements StreamIterator, StreamObserver {
    private Integer id;
    private String name;
    private Integer type;

    private HashMap<Integer, Stream> allStreams;

    public Streamer() {}

    public Streamer(Integer id, String name, Integer type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.allStreams = new LinkedHashMap<>();
    }

    @Override
    public void update(Stream stream) {
        removeStream(stream);
    }

    public void addStream(Stream stream) {
        allStreams.put(stream.getId(), stream);
        stream.addObserver(this);
    }

    public void removeStream(Stream stream) {
        allStreams.remove(stream.getId());
        stream.removeObserver(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public HashMap<Integer, Stream> getAllStreams() {
        return allStreams;
    }

    public void setAllStreams(HashMap<Integer, Stream> allStreams) {
        this.allStreams = allStreams;
    }

    @Override
    public Iterator<Stream> createIterator() {
        return allStreams.values().iterator();
    }
}
