package com.cahrypt.protocol;

import com.cahrypt.protocol.packets.PacketAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class Protocol {
    private static Gson PROTOCOL_GSON;

    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Reflections reflections = new Reflections("com.cahrypt.protocol.packets.adapters");
        reflections.getConstructorsWithSignature()
                .stream()
                .map(constructor -> {
                    try {
                        return (PacketAdapter<?>) constructor.newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(packetAdapter -> gsonBuilder.registerTypeAdapter(packetAdapter.getClass(), packetAdapter));

        PROTOCOL_GSON = gsonBuilder.create();
    }

    public static Gson getProtocolGson() {
        return PROTOCOL_GSON;
    }
}
