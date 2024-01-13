package com.cahrypt.protocol.packets.types;

import com.cahrypt.protocol.packets.Packet;

public record MessagePacket(String message) implements Packet {
}
