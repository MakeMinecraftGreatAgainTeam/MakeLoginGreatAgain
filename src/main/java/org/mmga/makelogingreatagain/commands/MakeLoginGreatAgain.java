package org.mmga.makelogingreatagain.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mmga.makelogingreatagain.utils.InventoryUtils;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.utils.PluginUtils.*;
import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class MakeLoginGreatAgain implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            String arg1 = args[0];
            if(commandReload.equals(arg1)){
                if(sender.hasPermission(permissionsReload)) {
                    getPlugin().reloadConfig();
                    if (!testDatasourceConfig()) {
                        sender.sendMessage(ChatColor.RED + "你的数据库配置有误，报错已发向后台！");
                    }else{
                        getDefaultTable();
                    }
                    sender.sendMessage(ChatColor.GREEN + "插件重载成功！");
                }else{
                    sender.sendMessage(ChatColor.RED + "你没有权限使用此指令");
                }
            }else if("login".equals(arg1)){
                Player player = (Player) sender;
                player.openInventory(InventoryUtils.getLoginInventory(getPlugin()));
            }else if("rereg".equals(arg1)){
                Player player = (Player) sender;
                player.openInventory(InventoryUtils.getReRegisterInventory(getPlugin()));
            }else if("reg".equals(arg1)){
                Player player = (Player) sender;
                player.openInventory(InventoryUtils.getRegisterInventory(getPlugin()));
            }
        }
        return false;
    }
}
