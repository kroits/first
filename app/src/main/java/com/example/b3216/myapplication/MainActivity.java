package com.example.b3216.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String TAG = "saltfactory.net";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGet = (Button) findViewById(R.id.Button);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        HttpClient httpClient = new DefaultHttpClient();


                        String urlString = "http://192.168.1.101/login";
                        try {
                            URI url = new URI(urlString);

                            HttpPost httpPost = new HttpPost();
                            httpPost.setURI(url);

                            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                            nameValuePairs.add(new BasicNameValuePair("userId", "saltfactory"));
                            

                            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                            HttpResponse response = httpClient.execute(httpPost);
                            String responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                            Log.d(TAG, responseString);

                        } catch (URISyntaxException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (ClientProtocolException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        }

                    }
                };


                thread.start();
            }
        });

    }

}
