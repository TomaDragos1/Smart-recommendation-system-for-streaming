package Builders;

import Homework.*;

public class StreamerBuilder {
    private Streamer streamer = new Streamer();

    public StreamerBuilder withId(Integer id) {
        streamer.setId(id);
        return this;
    }

    public StreamerBuilder withName(String name) {
        streamer.setName(name);
        return this;
    }

    public StreamerBuilder withType(Integer type){
        streamer.setType(type);
        return this;
    }
    public Streamer build() {
        return streamer;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public void setStreamer(Streamer streamer) {
        this.streamer = streamer;
    }
}
