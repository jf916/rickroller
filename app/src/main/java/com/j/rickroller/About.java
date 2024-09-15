package com.j.rickroller;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import jOS.Core.LIBTestActivity;
import jOS.Core.jAboutActivity;

public class About extends jAboutActivity {

    @NonNull
    @Override
    public Intent versionIntent(@NonNull Context context) {
        return new Intent(context, LIBTestActivity.class);
    }

    @NonNull
    @Override
    public List<Link> links() {
        return new ArrayList<>(Arrays.asList(
                new Link(
                        jOS.Core.R.drawable.ic_github,
                        jOS.Core.R.string.github,
                        "https://github.com/dot166/rickroller"
                )));
    }

    @NonNull
    @Override
    public List<jAboutActivity.Contributor> product() {
        return new ArrayList() {{
            add(new Contributor("._______166", Role.LeadDev, "https://avatars.githubusercontent.com/u/62702353", "https://github.com/dot166"));
        }};
    }

    public enum Role implements Roles {
        LeadDev(jOS.Core.R.string.leaddev);

        private final int descriptionResId;

        Role(int descriptionResId) {
            this.descriptionResId = descriptionResId;
        }

        public int getDescriptionResId() {
            return descriptionResId;
        }

        @Override
        public int descriptionResId() {
            return this.descriptionResId;
        }
    }

}
