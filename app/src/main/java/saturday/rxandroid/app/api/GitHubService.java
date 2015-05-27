package saturday.rxandroid.app.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;
import saturday.rxandroid.app.BuildConfig;
import saturday.rxandroid.app.model.GitHubUser;

/**
 * Created by edsonmenegatti on 5/23/15.
 */
public interface GitHubService {

    @GET("/users")
    @Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
    Observable<List<GitHubUser>> getUsers();

    @GET("/users/{login}")
    @Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
    GitHubUser getUser(@Path("login") String login);

    @GET("/users?since={page}")
    Observable<GitHubUser> getUsers(@Path("page") int page);
}
