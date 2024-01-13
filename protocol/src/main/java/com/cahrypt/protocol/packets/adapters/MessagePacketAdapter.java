package com.cahrypt.protocol.packets.adapters;

import com.cahrypt.protocol.packets.PacketAdapter;
import com.cahrypt.protocol.packets.types.MessagePacket;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class MessagePacketAdapter implements PacketAdapter<MessagePacket> {

    @Override
    public MessagePacket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String message = jsonObject.get("message").getAsString();

        return new MessagePacket(message);
    }

    @Override
    public JsonElement serialize(MessagePacket src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("message", src.message());

        return jsonObject;
    }
}
