package saturday.rxandroid.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import saturday.rxandroid.app.api.GitHubApi;
import saturday.rxandroid.app.model.GitHubUser;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";

    @InjectView(R.id.list_view_user_list)
    ListView userList;

//    @InjectView(R.id.refresh_layout)
//    SwipeRefreshLayout refreshLayout;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GitHubApi gitHubApi = new GitHubApi();

        final Observable<List<GitHubUser>> users = gitHubApi.getUsers();
        users.observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubUsers -> userList.setAdapter(new UserListAdapter(gitHubUsers)));

        users.flatMap(Observable::from)
                .map(gitHubUser -> ((UserListAdapter) userList.getAdapter()).updateUser(gitHubApi.getUser(gitHubUser.login)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> ((BaseAdapter) userList.getAdapter()).notifyDataSetChanged());
    }
}
