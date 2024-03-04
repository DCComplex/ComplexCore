package us.dccomplex.complexcore.apis;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import us.dccomplex.complexcore.ComplexCore;

public class CoreAPI {

    public static void CoreInfo(){
        Bukkit.getScheduler().runTaskLater(ComplexCore.getPlugin(), () -> {
            Bukkit.getConsoleSender().sendMessage("---------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("§e"+ComplexCore.getPlugin().getDescription().getName()+" - §eThanks for using Our Plugins");
            Bukkit.getConsoleSender().sendMessage("---------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("§eSocial Media:");
            Bukkit.getConsoleSender().sendMessage("§eTwitter: https://twitter.com/dccomplex");
            Bukkit.getConsoleSender().sendMessage("§eYouTube: https://youtube.com/c/dccomplex");
            Bukkit.getConsoleSender().sendMessage("§eWebsite: https://dccomplex.us");
            Bukkit.getConsoleSender().sendMessage("§eGithub: https://github.com/DCComplex");
            Bukkit.getConsoleSender().sendMessage("---------------------------------------------");
        }, 1L);
    }

    public static void CoreHelp(CommandSender sender){
        sender.sendMessage("§8---------------------------------------");
        sender.sendMessage("§6List of Commands §7| §6" +ComplexCore.getPlugin().getDescription().getName());
        sender.sendMessage("");
        sender.sendMessage("§7» §f/ccore help §7» §eShow this message.");
        sender.sendMessage("§7» §f/ccore social §7» §eShow this social.");
        sender.sendMessage("§7» §f/ccore info §7» §eShows the Plugin Information");
        sender.sendMessage("§7» §f/ccore reload §7» §eReload Plugin Config");
        sender.sendMessage("§8---------------------------------------");
    }

    public static void CoreAuthor(CommandSender sender){
        sender.sendMessage("§8---------------------------------------");
        sender.sendMessage("§6" + ComplexCore.getPlugin().getDescription().getName() + " Information");
        sender.sendMessage("");
        sender.sendMessage("§7» §fPlugin Version: §e" + ComplexCore.getPlugin().getDescription().getVersion());
        sender.sendMessage("§7» §fAuthor: §e" + ComplexCore.getPlugin().getDescription().getAuthors().get(0));
        sender.sendMessage("§7» §fDescription: " + ComplexCore.getPlugin().getDescription().getDescription());
        sender.sendMessage("§8---------------------------------------");
    }

    public static void checkSetup(){
        if (!ComplexCore.getPlugin().setupManager(ComplexCore.getPlugin().getServerVersion())) {
            ComplexCore.getPlugin().getLogger().severe("Failed to setup "+ComplexCore.getPlugin().getDescription().getName()+"!. Running non-compatible version! " + ComplexCore.getPlugin().getServerVersion());
            Bukkit.getPluginManager().disablePlugin(ComplexCore.getPlugin());
        }
    }

}
