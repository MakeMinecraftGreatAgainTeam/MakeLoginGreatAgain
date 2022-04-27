package org.mmga.makelogingreatagain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
                        sender.sendMessage(datasourceWrong);
                    }else{
                        getDefaultTable();
                    }
                    sender.sendMessage(reloadDone);
                }else{
                    sender.sendMessage(permissionsMissing);
                }
            }else{
                sender.sendMessage(wrongArguments);
            }
        }
        return false;
    }
}
