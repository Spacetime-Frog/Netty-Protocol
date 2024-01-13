package com.cahrypt.protocol.netty;

import com.cahrypt.protocol.Protocol;
import com.cahrypt.protocol.packets.Packet;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class PacketDuplexHandler extends ChannelDuplexHandler {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof Packet packet)) {
            super.write(ctx, msg, promise);
            return;
        }

        super.write(ctx, Protocol.getProtocolGson().toJson(packet, packet.getClass()), promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof String json)) {
            super.channelRead(ctx, msg);
            return;
        }

        Packet packet = Protocol.getProtocolGson().fromJson(json, Packet.class);
        super.channelRead(ctx, packet == null ? msg : packet);
    }
}
