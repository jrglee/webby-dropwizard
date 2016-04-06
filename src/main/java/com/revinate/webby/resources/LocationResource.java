package com.revinate.webby.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.annotations.VisibleForTesting;
import com.revinate.webby.api.Location;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {

    @GET
    @Timed
    public Location getLocation(@Context HttpServletRequest request) {
        final String ip = getIp(request);
        final String hostname = getHostname(ip);
        return new Location(ip, hostname);
    }

    /**
     * get client's ip, using the X-Forwarded-For header if present
     *
     * @param request  request context
     *
     * @return String  client's ip
     */
    @VisibleForTesting
    String getIp(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("X-Forwarded-For"))
                .map(xff -> xff.split("\\s*,\\s*")[0])
                .orElse(request.getRemoteAddr());
    }

    /**
     * map ip address to hostname
     *
     * @param ip  ip address
     *
     * @return String  hostname
     */
    private String getHostname(String ip) {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(ip);
            return addr.getHostName();
        } catch (UnknownHostException e) {
            // no such hostname
            return ip;
        }
    }

}
