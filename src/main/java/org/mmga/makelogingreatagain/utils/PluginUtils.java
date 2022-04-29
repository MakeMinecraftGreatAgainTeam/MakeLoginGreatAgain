package org.mmga.makelogingreatagain.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.constants.StringConstants;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class PluginUtils {
    public static MakeLoginGreatAgainMain getPlugin(){
        return MakeLoginGreatAgainMain.getPlugin(MakeLoginGreatAgainMain.class);
    }
    public static boolean reloadLanguageConfig(){
        //检查语言配置完整性
        FileConfiguration config = getPlugin().getConfig();
        String inventoryLoginTitle = config.getString("language.inventory.LoginTitle");
        String inventoryRegisterTitle = config.getString("language.inventory.RegisterTitle");
        String inventoryReRegisterTitle = config.getString("language.inventory.ReRegisterTitle");
        String messageWrongPassword = config.getString("language.message.wrongPassword");
        String messageRightPassword = config.getString("language.message.rightPassword");
        String messageTooLongPassword = config.getString("language.message.tooLongPassword");
        String messageClickFrame = config.getString("language.message.clickFrame");
        String messageExitInventory = config.getString("language.message.exitInventory");
        String messageExitServer = config.getString("language.message.exitServer");
        String messageTwoPasswordNotEquals = config.getString("language.message.twoPasswordNotEquals");
        String messageSuccessfulRegister = config.getString("language.message.successfulRegister");
        String messageDatasourceWrong = config.getString("language.message.datasourceWrong");
        String messageReloadSuccessful = config.getString("language.message.reloadSuccessful");
        String messagePermissionsMissing = config.getString("language.message.permissionsMissing");
        String messageWrongArguments = config.getString("language.message.wrongArguments");
        String itemFrame = config.getString("language.item.frame");
        String itemCapsLock = config.getString("language.item.capsLock");
        String itemQuitServer = config.getString("language.item.quitServer");
        String itemDone = config.getString("language.item.done");
        String itemClearInput = config.getString("language.item.clearInput");
        //检查以上变量是否有为空的，如果有则返回false
        if(inventoryLoginTitle == null || inventoryRegisterTitle == null || inventoryReRegisterTitle == null ||
                messageWrongPassword == null || messageRightPassword == null || messageTooLongPassword == null ||
                messageClickFrame == null || messageExitInventory == null || messageExitServer == null ||
                messageTwoPasswordNotEquals == null || messageSuccessfulRegister == null || messageDatasourceWrong == null ||
                messageReloadSuccessful == null || messagePermissionsMissing == null || messageWrongArguments == null ||
                itemFrame == null || itemCapsLock == null || itemQuitServer == null || itemDone == null || itemClearInput == null){
            return false;
        }
        StringConstants.inventoryLoginTitle = inventoryLoginTitle;
        StringConstants.inventoryRegisterTitle = inventoryRegisterTitle;
        StringConstants.inventoryReRegisterTitle = inventoryReRegisterTitle;
        StringConstants.wrongPassword = messageWrongPassword;
        StringConstants.rightPassword = messageRightPassword;
        StringConstants.tooLongPassword = messageTooLongPassword;
        StringConstants.inventoryFrameClick = messageClickFrame;
        StringConstants.tryExitInventory = messageExitInventory;
        StringConstants.exitServer = messageExitServer;
        StringConstants.twoPasswordNotEquals = messageTwoPasswordNotEquals;
        StringConstants.successfulRegister = messageSuccessfulRegister;
        StringConstants.datasourceWrong = messageDatasourceWrong;
        StringConstants.reloadDone = messageReloadSuccessful;
        StringConstants.permissionsMissing = messagePermissionsMissing;
        StringConstants.wrongArguments = messageWrongArguments;
        StringConstants.FrameName = itemFrame;
        StringConstants.CapsLockName = itemCapsLock;
        StringConstants.quitServerName = itemQuitServer;
        StringConstants.clearInputName = itemClearInput;
        return true;
    }
}
