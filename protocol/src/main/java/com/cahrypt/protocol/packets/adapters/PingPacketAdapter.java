package com.cahrypt.protocol.packets.adapters;

import com.cahrypt.protocol.packets.PacketAdapter;
import com.cahrypt.protocol.packets.types.PingPacket;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class PingPacketAdapter implements PacketAdapter<PingPacket> {

    @Override
    public PingPacket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new PingPacket(json.getAsJsonObject().get("timestamp").getAsLong());
    }

    @Override
    public JsonElement serialize(PingPacket src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("timestamp", src.timestamp());

        return jsonObject;
    }
}
