package com.newdatasoftware.firstapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//import android.net.Uri;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class FirstActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;
    TextView timer;
    TextView output;
    long start_time = 0L;
    long paused_time = 0L;
    long pause_total = 0L;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    int nextChange = 0;
    int numberOfChanges = 36;
    Calendar tempCal;
    Handler handler = new Handler();
    SimpleDateFormat df = new SimpleDateFormat("EEE dd MMM HH:mm", Locale.UK);
    String formattedDate;

    MyCal[] myCal_array;

    Intent intent;
    PendingIntent pendingIntent;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    public Runnable updateTimer = new Runnable() {

        public void run() {

            Long timenow = SystemClock.elapsedRealtime() - pause_total - start_time;
            seconds = (int) (timenow / 1000);
            hours = seconds / 3600;
            minutes = (seconds / 60) - (hours * 60);
            seconds = seconds % 60;
            String time_text = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
            timer.setText(time_text);
            builder.setContentText(time_text);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            //int secs = c.get(Calendar.SECOND);
            handler.postDelayed(this, 1000);
        }

    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timer = (TextView) findViewById(R.id.timer);
        output = (TextView) findViewById(R.id.textView);

        Init_myCal();

        intent = new Intent(this, FirstActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder = new NotificationCompat.Builder(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //    fab.setOnClickListener(new View.OnClickListener() {
        //        @Override
        //        public void onClick(View view) {
        //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                    .setAction("Action", null).show();
        //        }
        //    });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void Init_myCal() {
        myCal_array = new MyCal[numberOfChanges];
        for (int i = 0; i < numberOfChanges; i++) {
            myCal_array[i] = new MyCal();
        }
        myCal_array[0].day = 2;
        myCal_array[0].hour = 8;
        myCal_array[0].minute = 30;
        myCal_array[0].reason = getString(R.string.reason_started_work);
        myCal_array[1].day = 2;
        myCal_array[1].hour = 11;
        myCal_array[1].minute = 0;
        myCal_array[1].reason = getString(R.string.reason_start_of_morning_break);
        myCal_array[2].day = 2;
        myCal_array[2].hour = 11;
        myCal_array[2].minute = 10;
        myCal_array[2].reason = getString(R.string.reason_end_of_morning_break);
        myCal_array[3].day = 2;
        myCal_array[3].hour = 13;
        myCal_array[3].minute = 0;
        myCal_array[3].reason = getString(R.string.reason_start_of_dinner);
        myCal_array[4].day = 2;
        myCal_array[4].hour = 13;
        myCal_array[4].minute = 30;
        myCal_array[4].reason = getString(R.string.reason_end_of_dinner);
        myCal_array[5].day = 2;
        myCal_array[5].hour = 15;
        myCal_array[5].minute = 20;
        myCal_array[5].reason = getString(R.string.reason_start_of_afternoon_break);
        myCal_array[6].day = 2;
        myCal_array[6].hour = 15;
        myCal_array[6].minute = 30;
        myCal_array[6].reason = getString(R.string.reason_end_of_afternoon_break);
        myCal_array[7].day = 2;
        myCal_array[7].hour = 17;
        myCal_array[7].minute = 0;
        myCal_array[7].reason = getString(R.string.reason_finished_work);
        myCal_array[8].day = 3;
        myCal_array[8].hour = 8;
        myCal_array[8].minute = 30;
        myCal_array[8].reason = getString(R.string.reason_started_work);
        myCal_array[9].day = 3;
        myCal_array[9].hour = 11;
        myCal_array[9].minute = 0;
        myCal_array[9].reason = getString(R.string.reason_start_of_morning_break);
        myCal_array[10].day = 3;
        myCal_array[10].hour = 11;
        myCal_array[10].minute = 10;
        myCal_array[10].reason = getString(R.string.reason_end_of_morning_break);
        myCal_array[11].day = 3;
        myCal_array[11].hour = 13;
        myCal_array[11].minute = 0;
        myCal_array[11].reason = getString(R.string.reason_start_of_dinner);
        myCal_array[12].day = 3;
        myCal_array[12].hour = 13;
        myCal_array[12].minute = 30;
        myCal_array[12].reason = getString(R.string.reason_end_of_dinner);
        myCal_array[13].day = 3;
        myCal_array[13].hour = 15;
        myCal_array[13].minute = 20;
        myCal_array[13].reason = getString(R.string.reason_start_of_afternoon_break);
        myCal_array[14].day = 3;
        myCal_array[14].hour = 15;
        myCal_array[14].minute = 30;
        myCal_array[14].reason = getString(R.string.reason_end_of_afternoon_break);
        myCal_array[15].day = 3;
        myCal_array[15].hour = 17;
        myCal_array[15].minute = 0;
        myCal_array[15].reason = getString(R.string.reason_finished_work);
        myCal_array[16].day = 4;
        myCal_array[16].hour = 8;
        myCal_array[16].minute = 30;
        myCal_array[16].reason = getString(R.string.reason_started_work);
        myCal_array[17].day = 4;
        myCal_array[17].hour = 11;
        myCal_array[17].minute = 0;
        myCal_array[17].reason = getString(R.string.reason_start_of_morning_break);
        myCal_array[18].day = 4;
        myCal_array[18].hour = 11;
        myCal_array[18].minute = 10;
        myCal_array[18].reason = getString(R.string.reason_end_of_morning_break);
        myCal_array[19].day = 4;
        myCal_array[19].hour = 13;
        myCal_array[19].minute = 0;
        myCal_array[19].reason = getString(R.string.reason_start_of_dinner);
        myCal_array[20].day = 4;
        myCal_array[20].hour = 13;
        myCal_array[20].minute = 30;
        myCal_array[20].reason = getString(R.string.reason_end_of_dinner);
        myCal_array[21].day = 4;
        myCal_array[21].hour = 15;
        myCal_array[21].minute = 20;
        myCal_array[21].reason = getString(R.string.reason_start_of_afternoon_break);
        myCal_array[22].day = 4;
        myCal_array[22].hour = 15;
        myCal_array[22].minute = 30;
        myCal_array[22].reason = getString(R.string.reason_end_of_afternoon_break);
        myCal_array[23].day = 4;
        myCal_array[23].hour = 17;
        myCal_array[23].minute = 0;
        myCal_array[23].reason = getString(R.string.reason_finished_work);
        myCal_array[24].day = 5;
        myCal_array[24].hour = 8;
        myCal_array[24].minute = 30;
        myCal_array[24].reason = getString(R.string.reason_started_work);
        myCal_array[25].day = 5;
        myCal_array[25].hour = 11;
        myCal_array[25].minute = 0;
        myCal_array[25].reason = getString(R.string.reason_start_of_morning_break);
        myCal_array[26].day = 5;
        myCal_array[26].hour = 11;
        myCal_array[26].minute = 10;
        myCal_array[26].reason = getString(R.string.reason_end_of_morning_break);
        myCal_array[27].day = 5;
        myCal_array[27].hour = 13;
        myCal_array[27].minute = 0;
        myCal_array[27].reason = getString(R.string.reason_start_of_dinner);
        myCal_array[28].day = 5;
        myCal_array[28].hour = 13;
        myCal_array[28].minute = 30;
        myCal_array[28].reason = getString(R.string.reason_end_of_dinner);
        myCal_array[29].day = 5;
        myCal_array[29].hour = 15;
        myCal_array[29].minute = 20;
        myCal_array[29].reason = getString(R.string.reason_start_of_afternoon_break);
        myCal_array[30].day = 5;
        myCal_array[30].hour = 15;
        myCal_array[30].minute = 30;
        myCal_array[22].reason = getString(R.string.reason_end_of_afternoon_break);
        myCal_array[31].day = 5;
        myCal_array[31].hour = 17;
        myCal_array[31].minute = 0;
        myCal_array[31].reason = getString(R.string.reason_finished_work);
        myCal_array[32].day = 6;
        myCal_array[32].hour = 8;
        myCal_array[32].minute = 30;
        myCal_array[32].reason = getString(R.string.reason_started_work);
        myCal_array[33].day = 6;
        myCal_array[33].hour = 11;
        myCal_array[33].minute = 0;
        myCal_array[33].reason = getString(R.string.reason_start_of_morning_break);
        myCal_array[34].day = 6;
        myCal_array[34].hour = 11;
        myCal_array[34].minute = 10;
        myCal_array[34].reason = getString(R.string.reason_end_of_morning_break);
        myCal_array[35].day = 6;
        myCal_array[35].hour = 13;
        myCal_array[35].minute = 30;
        myCal_array[35].reason = getString(R.string.reason_weekend);
        for (int i = 0; i < numberOfChanges; i++) {
            myCal_array[i].milliseconds = ((myCal_array[i].day - 1) * 86400000) + (myCal_array[i].hour * 3600000) + (myCal_array[i].minute * 60000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start_timer(View view) {
        pause_total = 0;
        start_time = SystemClock.elapsedRealtime();// - 3590000;
        handler.postDelayed(updateTimer, 0);
        long then = GetFirstChange();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MILLISECOND, (int) then);
        tempCal = now;
        formattedDate = df.format(now.getTime());
        output.setText(formattedDate);
        startNotification();
    }

    private long GetNextChange() {
        nextChange++;
        if (nextChange == numberOfChanges) {
            nextChange = 0;
            Calendar now = tempCal;//.getInstance();
            int dayNow = now.get(Calendar.DAY_OF_WEEK) - 1;
            Calendar then = (Calendar) now.clone();
            then.set(Calendar.HOUR_OF_DAY, 0);
            then.set(Calendar.MINUTE, 0);
            then.add(Calendar.DAY_OF_WEEK, 7 - dayNow);
            return then.getTimeInMillis() - now.getTimeInMillis() + myCal_array[0].milliseconds;
        }
        return myCal_array[nextChange].milliseconds - myCal_array[nextChange - 1].milliseconds;
    }

    private long GetFirstChange() {
        Calendar now = Calendar.getInstance();
        int dayNow = now.get(Calendar.DAY_OF_WEEK) - 1;
        Calendar then = (Calendar) now.clone();
        then.set(Calendar.HOUR_OF_DAY, 0);
        then.set(Calendar.MINUTE, 0);
        then.add(Calendar.DAY_OF_WEEK, -dayNow);
        long nowMilliseconds = now.getTimeInMillis() - then.getTimeInMillis();
        nextChange = 0;
        for (int i = 0; i < numberOfChanges; i++) {
            if (myCal_array[i].milliseconds > nowMilliseconds) break;
            nextChange++;
        }
        if (nextChange == numberOfChanges) {
            ShowToast("past last change");
            nextChange = 0;
            then.add(Calendar.DAY_OF_WEEK, dayNow);
            then.add(Calendar.DAY_OF_WEEK, 7 - dayNow);
            nowMilliseconds = then.getTimeInMillis() - now.getTimeInMillis();
            return nowMilliseconds + myCal_array[0].milliseconds;
        }
        return myCal_array[nextChange].milliseconds - nowMilliseconds;
    }

    private void ShowToast(String toastText) {
        Toast toast = Toast.makeText(getApplicationContext(),
                toastText,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void startNotification() {

        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        builder.setContentTitle("Job Timer");
        builder.setContentText("00:00:00");
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    public void pause_timer(View view) {
        paused_time = SystemClock.elapsedRealtime();
        handler.removeCallbacks(updateTimer);
    }

    public void restart_timer(View view) {
        long then = GetNextChange();
        tempCal.add(Calendar.MILLISECOND,(int)then);
        // now.set(Calendar.HOUR_OF_DAY, 20);
        formattedDate = df.format(tempCal.getTime());
        output.setText(formattedDate);
 //       pause_total = pause_total + SystemClock.elapsedRealtime() - paused_time;
 //       handler.postDelayed(updateTimer, 0);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
/*        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "First Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse(null),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse(null)
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
/*        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "First Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse(null),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse(null)
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
    }

    public enum JobCategory {
        ORDERING, KITTING, PRODUCTION, TESTING,
        LABELLING, PACKING, TRAINING, REWORK
    }

    public class MyCal {
        int day = 0;
        int hour = 0;
        int minute = 0;
        long milliseconds = 0;
        String reason = "No reason!";
    }

    public class MyTimer {
        boolean isActive = false;
        JobCategory jobCategory;
        String startTime = "Not started";
    }
}
