package saturday.rxandroid.app.api;

import android.util.Log;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import rx.Observable;
import saturday.rxandroid.app.model.GitHubUser;

/**
 * Created by edsonmenegatti on 5/23/15.
 */
public class GitHubApi {
    private static final String TAG = "GitHubApi";
    private final GitHubService gitHubService;

    public GitHubApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i(TAG, msg);
                    }
                })
                .build();
        gitHubService = restAdapter.create(GitHubService.class);
    }

    public Observable<List<GitHubUser>> getUsers() {
        return gitHubService.getUsers();
    }

    public GitHubUser getUser(String login) {
        return gitHubService.getUser(login);
    }
}
