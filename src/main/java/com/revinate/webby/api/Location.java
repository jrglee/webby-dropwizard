package com.revinate.webby.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Location {
    @Length(max = 15)
    private String ip;

    private String hostname;

    public Location() {
        // Jackson deserialization
    }

    public Location(String ip, String hostname) {
        this.ip = ip;
        this.hostname = hostname;
    }

    @JsonProperty
    public String getIp() {
        return ip;
    }

    @JsonProperty
    public String getHostname() {
        return hostname;
    }

}
