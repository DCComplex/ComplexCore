package us.dccomplex.complexcore.apis;

import org.bukkit.plugin.Plugin;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GitHub;
import us.dccomplex.complexcore.ComplexCore;

import java.io.IOException;

public class UpdateCheckAPI {
    public static void checkForPluginUpdateAPI(String gitHubToken, String username, String repository, String currentVersion, Plugin plugin) {
        String latestVersion = fetchLatestVersionFromGitHubAPI(gitHubToken, username, repository,plugin);
        if (latestVersion != null && !latestVersion.equals(currentVersion)) {
            plugin.getLogger().info("A new version (" + latestVersion + ") of your plugin is available!");
        }
    }

    private static String fetchLatestVersionFromGitHubAPI(String gitHubToken, String username, String repository,Plugin plugin) {
        try {
            GitHub github = GitHub.connectUsingOAuth(gitHubToken);
            GHRelease latestRelease = github.getRepository(username + "/" + repository).getLatestRelease();
            if (latestRelease == null) {
                plugin.getLogger().warning("No releases found for the specified repository.");
                return null;
            }
            return latestRelease.getTagName();
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().warning("Failed to fetch latest version from GitHub: " + e.getMessage());
            return null;
        }
    }
}
