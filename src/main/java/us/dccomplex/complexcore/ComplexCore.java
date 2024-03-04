package us.dccomplex.complexcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.dccomplex.complexcore.apis.CoreAPI;
import us.dccomplex.complexcore.apis.ManagerAPI;
import us.dccomplex.complexcore.files.CustomFile;
import us.dccomplex.complexcore.listeners.DependListener;
import us.dccomplex.complexcore.updates.CheckUpdate;
import us.dccomplex.complexcore.versions.Core_1_16_5;

public final class ComplexCore extends JavaPlugin {
    public static ComplexCore plugin;
    public static DependListener listener;

    public static DependListener getListener() {
        return listener;
    }

    public ManagerAPI managerAPI;

    @Override
    public void onEnable() {
        setEnable();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setEnable(){
        plugin = this;
        CoreAPI.checkSetup();
        CheckUpdate.CheckGitUpdate();
        setupCustomConfig();
        successEnableMSG();
    }
    public void successEnableMSG(){
        getLogger().info("Plugin Successfully Enabled");
    }

    public void reloadPlugin() {
        getServer().getPluginManager().disablePlugin(this);
        getServer().getPluginManager().enablePlugin(this);
    }

    public static ComplexCore getPlugin() {
        return plugin;
    }

    public void setupCustomConfig(){
        CustomFile.setup();
        CustomFile.get().addDefault("update",true);
        CustomFile.get().addDefault("prefix","§7[§5§lCore§r§7]§r");
        CustomFile.get().addDefault("cooldown",10);
        CustomFile.get().options().copyDefaults(true);
        CustomFile.save();
    }

    public boolean setupManager(String serverVersion) {
        if (serverVersion.contains("1.16.5")) {
            managerAPI = new Core_1_16_5();
        } else if (serverVersion.contains("1.20")) {
            managerAPI = new Core_1_16_5();
        } else {
            return false;
        }
        return true;
    }

    public String getServerVersion(){
        return Bukkit.getVersion();
    }



}
