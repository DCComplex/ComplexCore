package us.dccomplex.complexcore.updates;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import java.io.IOException;
import org.kohsuke.github.GHRelease;
import us.dccomplex.complexcore.ComplexCore;

public final class CheckUpdate {
    private final static String currentVersion = ComplexCore.getPlugin().getDescription().getVersion();

    private final static String GitK = "api-key";

    private static String checkupdate;
    private static String checkupdateString;

    private static void checkForUpdates() {
        String latestVersion = fetchLatestVersionFromGitHub();
        if (latestVersion != null && !latestVersion.equals(currentVersion)) {
            ComplexCore.getPlugin().getLogger().warning("A new version (" + latestVersion + ") of "+ComplexCore.getPlugin().getDescription().getName()+" is available!");
        }
    }
    private static String fetchLatestVersionFromGitHub() {
        try {
            GitHub github = GitHub.connectUsingOAuth(GitK);
            GHRepository repository = github.getRepository("DCComplex/ComplexCore");
            GHRelease latestRelease = repository.getLatestRelease();
            if (latestRelease == null) {
                ComplexCore.getPlugin().getLogger().warning("No releases found on GitHub.");
                return null;
            }
            String latestVersion = latestRelease.getTagName();
            return latestVersion;
        } catch (IOException e) {
            e.printStackTrace();
            ComplexCore.getPlugin().getLogger().warning("Failed to fetch latest version from GitHub: " + e.getMessage());
            return null;
        }
    }
    public static void CheckVersionUpdate(){
        checkupdateString = ComplexCore.getPlugin().getConfig().getString("General.checkupdate");
        boolean checkupdate = Boolean.parseBoolean(checkupdateString);
        if(checkupdate){
            checkForUpdates();
        }
    }

    public static void CheckGitUpdate() {
        CheckVersionUpdate();
    }
}
