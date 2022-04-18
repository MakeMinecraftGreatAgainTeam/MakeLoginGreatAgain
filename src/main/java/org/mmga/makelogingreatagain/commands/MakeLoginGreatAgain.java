package org.mmga.makelogingreatagain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;

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
                MakeLoginGreatAgainMain plugin = MakeLoginGreatAgainMain.getPlugin(MakeLoginGreatAgainMain.class);

            }
        }
        return false;
    }
}
