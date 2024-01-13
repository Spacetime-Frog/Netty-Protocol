package com.cahrypt.protocol.packets.types;

import com.cahrypt.protocol.packets.Packet;

public record PingPacket(long timestamp) implements Packet {
    public long getLatency() {
        return System.currentTimeMillis() - timestamp;
    }
}
