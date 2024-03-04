package us.dccomplex.complexcore.dependencies;

import org.bukkit.Bukkit;
import us.dccomplex.complexcore.ComplexCore;
import us.dccomplex.complexcore.listeners.DependListener;

public class CheckDependency {

    private static DependListener listener = new DependListener(); // Instantiate the listener

    public static void checkCore(String pluginText) {
        try {
            if (!staticCore(pluginText)) {
                ComplexCore.getPlugin().getLogger().warning(pluginRequiredMSG(pluginText));
                Bukkit.getPluginManager().disablePlugin(ComplexCore.getPlugin());
                return;
            }
            registerEvent();
        } catch (Exception e) {
            ComplexCore.getPlugin().getLogger().warning(noPluginMSG(pluginText, e));
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(ComplexCore.getPlugin());
        }
    }

    public static boolean staticCore(String pluginText) {
        return Bukkit.getPluginManager().getPlugin(pluginText) != null;
    }

    public static String pluginRequiredMSG(String pluginText){
        return "Could not find " + pluginText + "! This plugin is required.";
    }

    public static String noPluginMSG(String pluginText, Exception e){
        return "An error occurred while checking for " + pluginText + ": " + e.getMessage();
    }

    public static void registerEvent(){
        Bukkit.getPluginManager().registerEvents(listener, ComplexCore.getPlugin());
    }
}