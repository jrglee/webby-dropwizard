package com.revinate.webby.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationResourceTest {

    @Mock
    HttpServletRequest request;

    @Test
    public void shouldGetIpFromRequest() {
        LocationResource resource = new LocationResource();

        when(request.getHeader("X-Forwarded-For")).thenReturn("127.0.0.1, 1.2.3.4");

        String ip = resource.getIp(request);

        assertThat(ip, is("127.0.0.1"));
    }

    @Test
    public void shouldGetRemoteAddressWhenThereIsNotXForwardedFor() {
        LocationResource resource = new LocationResource();

        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("8.8.8.8");

        String ip = resource.getIp(request);

        assertThat(ip, is("8.8.8.8"));
    }

}