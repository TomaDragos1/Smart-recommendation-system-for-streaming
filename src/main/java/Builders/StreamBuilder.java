package Builders;

import Homework.Stream;

/**
 * Builder class for different classes
 */

public class StreamBuilder {
    private Stream stream = new Stream();

    public StreamBuilder withStreamType(Integer streamType) {
        stream.setStreamType(streamType);
        return this;
    }

    public StreamBuilder withId(Integer id) {
        stream.setId(id);
        return this;
    }

    public StreamBuilder withStreamGenre(Integer streamGenre) {
        stream.setStreamGenre(streamGenre);
        return this;
    }

    public StreamBuilder withNoOfStream(Long noOfStream) {
        stream.setNoOfStreams(noOfStream);
        return this;
    }

    public StreamBuilder withStreamerId(Integer streamerId) {
        stream.setStreamerId(streamerId);
        return this;
    }

    public StreamBuilder withLenght(Long lenght) {
        stream.setLenght(lenght);
        return this;
    }

    public StreamBuilder withDateAdded(Long dateAdded) {
        stream.setDateAdded(dateAdded);
        return this;
    }

    public StreamBuilder withName(String name) {
        stream.setName(name);
        return this;
    }

    public Stream build() {
        return stream;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }
}
