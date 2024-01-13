package com.cahrypt.protocol.packets;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public interface PacketAdapter<T extends Packet> extends JsonSerializer<T>, JsonDeserializer<T> {
}
