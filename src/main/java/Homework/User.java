package Homework;

import Commands.StreamIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class User implements StreamIterator,StreamObserver {
    private Integer id;
    private String name;
    private ArrayList<Stream> listenedStreams;

    public User() {}

    public User(Integer id, String name, ArrayList<Stream> listenedStreams) {
        this.id = id;
        this.name = name;
        this.listenedStreams = listenedStreams;
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

    public ArrayList<Stream> getListenedStreams() {
        return listenedStreams;
    }

    public void setListenedStreams(ArrayList<Stream> listenedStreams) {
        this.listenedStreams = listenedStreams;
    }



    @Override
    public Iterator<Stream> createIterator() {
        return listenedStreams.iterator();
    }


    public void addStream(Stream stream) {
        Long nr = stream.getNoOfStreams();
        nr ++;
        stream.setNoOfStreams(nr);
        listenedStreams.add(stream);
        stream.addObserver(this);
    }

    public void removeStream(Stream stream) {
        listenedStreams.remove(stream);
        stream.removeObserver(this);
    }

    @Override
    public void update(Stream stream) {
        removeStream(stream);
    }
}
