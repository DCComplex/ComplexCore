package us.dccomplex.complexcore.apis;

import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GitHub;
import us.dccomplex.complexcore.ComplexCore;

import java.io.IOException;

public class UpdateCheckAPI {
    public static void checkForPluginUpdateAPI(String gitHubToken, String username, String repository, String currentVersion) {
        String latestVersion = fetchLatestVersionFromGitHubAPI(gitHubToken, username, repository);
        if (latestVersion != null && !latestVersion.equals(currentVersion)) {
            ComplexCore.getPlugin().getLogger().info("A new version (" + latestVersion + ") of your plugin is available!");
        }
    }

    private static String fetchLatestVersionFromGitHubAPI(String gitHubToken, String username, String repository) {
        try {
            GitHub github = GitHub.connectUsingOAuth(gitHubToken);
            GHRelease latestRelease = github.getRepository(username + "/" + repository).getLatestRelease();
            if (latestRelease == null) {
                ComplexCore.getPlugin().getLogger().warning("No releases found for the specified repository.");
                return null;
            }
            return latestRelease.getTagName();
        } catch (IOException e) {
            e.printStackTrace();
            ComplexCore.getPlugin().getLogger().warning("Failed to fetch latest version from GitHub: " + e.getMessage());
            return null;
        }
    }
}
