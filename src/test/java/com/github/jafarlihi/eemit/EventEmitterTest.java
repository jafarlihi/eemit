package com.github.jafarlihi.eemit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventEmitterTest {

    String starChannel = "*";
    String firstChannel = "channel1";
    String secondChannel = "channel2";
    String firstObject = "SomeString";
    String secondObject = "SomeString2";

    @Test public void testStarSubscription() {
        EventEmitter<String> eventEmitter = new EventEmitter<>();
        List<String> results = new ArrayList<>();
        eventEmitter.on("*", (channel, object) -> {
            results.add(channel);
            results.add(object);
        });
        eventEmitter.emit(firstChannel, firstObject);
        eventEmitter.emit(starChannel, secondObject);
        assertEquals(4, results.size());
        assertEquals(firstChannel, results.get(0));
        assertEquals(firstObject, results.get(1));
        assertEquals(starChannel, results.get(2));
        assertEquals(secondObject, results.get(3));
    }

    @Test public void testUnsubscribe() {
        EventEmitter<String> eventEmitter = new EventEmitter<>();
        List<String> results = new ArrayList<>();
        eventEmitter.on(firstChannel, (channel, object) -> {
            results.add(channel);
            results.add(object);
        });
        String uuid = eventEmitter.on(secondChannel, (channel, object) -> {
            results.add(channel);
            results.add(object);
        });
        eventEmitter.off(uuid);
        eventEmitter.emit(firstChannel, firstObject);
        eventEmitter.emit(secondChannel, secondObject);
        assertEquals(2, results.size());
        assertEquals(firstChannel, results.get(0));
        assertEquals(firstObject, results.get(1));
    }

    @Test public void testEmittingToStar() {
        EventEmitter<String> eventEmitter = new EventEmitter<>();
        List<String> results = new ArrayList<>();
        eventEmitter.on(firstChannel, (channel, object) -> {
            results.add(channel);
            results.add(object);
        });
        eventEmitter.emit(starChannel, firstObject);
        assertEquals(2, results.size());
        assertEquals(starChannel, results.get(0));
        assertEquals(firstObject, results.get(1));
    }
}
