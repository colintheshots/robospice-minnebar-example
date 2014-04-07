package com.octo.android.robospice.sample.retrofit;

import android.app.Activity;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.sample.retrofit.network.GithubSpiceService;
import com.octo.android.robospice.sample.retrofit.network.TwilioSpiceService;

/**
 * This class is the base class of all activities of the sample project. This class offers all
 * subclasses an easy access to a {@link SpiceManager} that is linked to the {@link Activity}
 * lifecycle. Typically, in a new project, you will have to create a base class like this one and
 * copy the content of the {@link BaseSampleSpiceActivity} into your own class.
 * @author sni
 */
public abstract class BaseSampleSpiceActivity extends Activity {
    private SpiceManager twilioSpiceManager = new SpiceManager(TwilioSpiceService.class);
    private SpiceManager githubSpiceManager = new SpiceManager(GithubSpiceService.class);

    @Override
    protected void onStart() {
        twilioSpiceManager.start(this);
        githubSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        twilioSpiceManager.shouldStop();
        githubSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getTwilioSpiceManager() {
        return twilioSpiceManager;
    }
    protected SpiceManager getGithubSpiceManager() { return githubSpiceManager; }

}
