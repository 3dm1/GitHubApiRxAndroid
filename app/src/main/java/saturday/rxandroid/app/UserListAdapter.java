package saturday.rxandroid.app;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saturday.rxandroid.app.model.GitHubUser;

/**
 * Created by edsonmenegatti on 5/23/15.
 */
public class UserListAdapter extends BaseAdapter {
    private static final String TAG = "UserListAdapter";

    private final HashMap<Integer, GitHubUser> usersHashMap;
    private final Object[] objects;

    public UserListAdapter(List<GitHubUser> gitHubUsers) {
        usersHashMap = new HashMap<>();

        for (GitHubUser user : gitHubUsers) {
            usersHashMap.put(user.id, user);
        }
        objects = usersHashMap.keySet().toArray();
        Log.e(TAG, "UserListAdapter keyset " + Arrays.toString(objects));
    }

    @Override
    public int getCount() {
        return usersHashMap.size();
    }

    @Override
    public GitHubUser getItem(int position) {
        return usersHashMap.get((int)objects[position]);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listItemViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            listItemViewHolder = new ViewHolder();
            listItemViewHolder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(listItemViewHolder);
        } else {
            listItemViewHolder = (ViewHolder) convertView.getTag();
        }

        final GitHubUser gitHubUser = getItem(position);
        listItemViewHolder.iconUrl = gitHubUser.avatar_url;
        listItemViewHolder.title.setText(gitHubUser.login + (gitHubUser.name != null ? " / " + gitHubUser.name : ""));

        return convertView;
    }

    public Boolean updateUser(GitHubUser gitHubUser) {
        Log.e(TAG, "updateUser " + gitHubUser.id);

        if (usersHashMap.containsKey(gitHubUser.id)) {
            Log.e(TAG, "updateUser ");

            usersHashMap.put(gitHubUser.id, gitHubUser);
            return true;
        }

        return false;
    }

    static class ViewHolder {
        String iconUrl;
        TextView title;
    }

}
