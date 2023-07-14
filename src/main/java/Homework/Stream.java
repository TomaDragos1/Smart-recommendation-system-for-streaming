package Homework;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import Commands.StreamIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Stream {
    private Integer streamType;
    private Integer id;
    private Integer streamGenre;
    private Long noOfStreams;
    private Integer streamerId;
    private Long lenght;
    private Long dateAdded;
    private String name;

    ArrayList<StreamObserver> observers;
    public Stream() {};

    public Stream(Integer streamType, Integer id, Integer streamGenre, Long noOfStreams, Integer streamerId, Long lenght, Long dateAdded, String name) {
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.lenght = lenght;
        this.dateAdded = dateAdded;
        this.name = name;
        observers = new ArrayList<>();
    }

    public void addObserver(StreamObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StreamObserver observer) {
        observers.remove(observer);
    }

    public void delete() {
        ArrayList<StreamObserver> clone = new ArrayList<>(observers);
        for (StreamObserver observer : clone) {
            observer.update(this);
        }
        this.setObservers(clone);
    }

    public Integer getStreamType() {
        return streamType;
    }

    public void setStreamType(Integer streamType) {
        this.streamType = streamType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStreamGenre() {
        return streamGenre;
    }

    public void setStreamGenre(Integer streamGenre) {
        this.streamGenre = streamGenre;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(Integer streamerId) {
        this.streamerId = streamerId;
    }

    public Long getLenght() {
        return lenght;
    }

    public void setLenght(Long lenght) {
        this.lenght = lenght;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<StreamObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<StreamObserver> observers) {
        this.observers = observers;
    }

}
