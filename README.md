# eemit
eemit is a Java event emitter library. It lets you emit objects (from multiple places) of the specified type in a given channel and handle them elsewhere in code by subscribing to that channel.

### Install
Maven:
```xml
<dependency>
    <groupId>com.github.jafarlihi</groupId>
    <artifactId>eemit</artifactId>
    <version>0.1.0</version>
</dependency>
```

Gradle:
```groovy
implementation 'com.github.jafarlihi:eemit:0.1.0'
```

### Usage
```java
import io.github.jafarlihi.eemit.EventEmitter;

...

// Create EventEmitter with any type as an event type, here just String
EventEmitter<String> emitter = new EventEmitter<>();

// Listen to events on channel "channel666", pass in a lambda that'll receive channel name and the event object
emitter.on("channel666", (channel, object) -> {
  // Do something with object (which is String because we parameterized EventEmitter with String)
  // "channel" argument will either be "channel666" or "*" here
});

// Listen to events on all channels
emitter.on("*", (channel, object) -> {
  // Do something with object
});

// Emit an event on "channel666", second parameter is of type that you parameterized EventEmitter with
emitter.emit("channel666", "Some string");

// Emit to all channels
emitter.emit("*", "Some string");

// How to unlisten:
String uuid = emitter.on("channel666", (channel, object) -> {
  // Do something with object
});
// Unregister callback
emitter.off(uuid);
```
