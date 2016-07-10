package com.bryonnicoson.singletondatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bryon on 7/10/16.
 */

public class GameFragment extends Fragment {

    TextView name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {

        Bundle arguments = this.getArguments();
        View gameFragment = inflater.inflate(R.layout.gamefragment, container, false);
        name = (TextView) gameFragment.findViewById(R.id.game_name);
        name.setText(arguments.getString("NAME"));
        return gameFragment;

    }
}
