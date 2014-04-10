package com.octo.android.robospice.sample.retrofit;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.sample.retrofit.model.CallResponse;
import com.octo.android.robospice.sample.retrofit.model.Gist;
import com.octo.android.robospice.sample.retrofit.model.GistDetail;
import com.octo.android.robospice.sample.retrofit.network.requests.CallRequest;
import com.octo.android.robospice.sample.retrofit.network.requests.GistRequest;
import com.octo.android.robospice.sample.retrofit.network.requests.GistsRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * This sample demonstrates how to use RoboSpice to perform simple network requests.
 * @author sni
 */
public class SampleSpiceActivity extends BaseSampleSpiceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Button callButton = (Button) findViewById(R.id.startCall);
        if (callButton!=null) {
            callButton.setOnClickListener(twilioCallOnClickListener);
        }

        Button githubButton = (Button) findViewById(R.id.startGithubCall);
        if (githubButton!=null) {
            githubButton.setOnClickListener(gistTwilioCallOnClickListener);
        }
    }

    private final View.OnClickListener twilioCallOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText1 = (EditText) SampleSpiceActivity.this.findViewById(R.id.editText1);
            if (editText1!=null && editText1.getText()!=null) {

                CallRequest callRequest = new CallRequest(RealConfig.twilio_accountsid, RealConfig.twilio_from_number, editText1.getText().toString(), "http://twimlets.com/holdmusic?Bucket=twimletholdmusic");
                getTwilioSpiceManager().execute(callRequest, "twilioCall", DurationInMillis.ONE_SECOND * 5, new CallRequestListener());
            }
        }
    };

    private final View.OnClickListener gistTwilioCallOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getGithubSpiceManager().execute(new GistsRequest(), "gistsRequest", DurationInMillis.ALWAYS_EXPIRED, new GithubRequestListener());
        }
    };

    private final class CallRequestListener implements RequestListener<CallResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(SampleSpiceActivity.this, "Failure", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(CallResponse callResponse) {
            Toast.makeText(SampleSpiceActivity.this, "Call placed successfully.", Toast.LENGTH_LONG).show();
        }
    }

    private final class GithubRequestListener implements RequestListener<Gist.AsList> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(SampleSpiceActivity.this, "Failure", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(Gist.AsList githubGistResponse) {
            Gist g = githubGistResponse.get(0);
            getGithubSpiceManager().execute(new GistRequest(g.getId()), "gistRequest", DurationInMillis.ALWAYS_EXPIRED, new GistRequestListener());
        }
    }

    private final class GistRequestListener implements RequestListener<GistDetail> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(SampleSpiceActivity.this, "Failure", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(GistDetail gistDetail) {

            List<String> keysAsArray = new ArrayList<String>(gistDetail.getFiles().keySet());
            String content = gistDetail.getFiles().get(keysAsArray.get(0)).getContent();

            TextView fileContent = (TextView) SampleSpiceActivity.this.findViewById(R.id.fileContent);
            if (fileContent!=null) {
                fileContent.setText(content);
            }

            EditText editText1 = (EditText) SampleSpiceActivity.this.findViewById(R.id.editText1);
            if (editText1!=null && editText1.getText()!=null) {

                CallRequest callRequest = null;
                try {
                    callRequest = new CallRequest(RealConfig.twilio_accountsid, RealConfig.twilio_from_number, editText1.getText().toString(), "http://twimlets.com/menu?Message=" + URLEncoder.encode(content, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                getTwilioSpiceManager().execute(callRequest, "textCall", DurationInMillis.ONE_SECOND * 5, new CallRequestListener());
            }
        }
    }
}
