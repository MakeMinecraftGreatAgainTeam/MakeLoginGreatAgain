package org.mmga.makelogingreatagain.bungee.events;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created On 2022/8/12 13:56
 *
 * @author wzp
 * @version 1.0.0
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(ServerSwitchEvent event){
        ServerInfo from = event.getFrom();
        if (from == null){
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeBoolean(true);
            event.getPlayer().getServer().sendData("mlga:login",buffer.array());
        }
    }
}
