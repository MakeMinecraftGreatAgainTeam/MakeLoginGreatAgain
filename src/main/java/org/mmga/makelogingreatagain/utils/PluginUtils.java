package org.mmga.makelogingreatagain.utils;

import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class PluginUtils {
    public static MakeLoginGreatAgainMain getPlugin(){
        return MakeLoginGreatAgainMain.getPlugin(MakeLoginGreatAgainMain.class);
    }
}
