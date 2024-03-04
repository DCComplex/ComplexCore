package us.dccomplex.complexcore.dependencies;

import org.bukkit.Bukkit;
import us.dccomplex.complexcore.ComplexCore;
import us.dccomplex.complexcore.listeners.DependListener;

public class CheckDependency {

    public static void checkCore(String plugintext) {
        try {
            if (!staticCore(plugintext)) {
                ComplexCore.getPlugin().getLogger().warning(pluginRequiredMSG(plugintext));
                Bukkit.getPluginManager().disablePlugin(ComplexCore.getPlugin());
                return;
            }
            Bukkit.getPluginManager().registerEvents(ComplexCore.getListener(), ComplexCore.getPlugin());
        } catch (Exception e) {
            ComplexCore.getPlugin().getLogger().warning(noPluginMSG(plugintext,e));
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(ComplexCore.getPlugin());
        }
    }

    public static boolean staticCore(String plugintext) {
        return Bukkit.getPluginManager().getPlugin(plugintext) != null;
    }

    public static String pluginRequiredMSG(String plugintext){
        return "Could not find "+plugintext+"! This plugin is required.";
    }

    public static String noPluginMSG(String plugintext,Exception e){
        return "An error occurred while checking for "+plugintext+": "+e.getMessage();
    }


}
