package com.example.sck.circles_survival.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sck.circles_survival.adapters.ViewPagerAdapter;
import com.example.sck.circles_survival.fragments.GameSettingsFragment;
import com.example.sck.circles_survival.R;
import com.example.sck.circles_survival.fragments.AboutGameFragment;
import com.example.sck.circles_survival.views.GameCanvasView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToolbarWithTabs();
    }

    /**
     * adding Toolbar with two tabs to Main screen
     *
     */
    private void addToolbarWithTabs() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    /**
     * add Fragments to Tabs
     *
     * @param viewPager our viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new GameSettingsFragment(), getString(R.string.tab_game_settings_name));
        viewPagerAdapter.addFragment(new AboutGameFragment(), getString(R.string.tab_game_about_name));
        viewPager.setAdapter(viewPagerAdapter);
    }

    /**
     * starts GameActivity if sGameBGcolor was selected
     *
     * @param view FloatingActionButton onClick
     */
    public void startGameClickListener(View view) {
        if( GameCanvasView.getGameBGcolor() != null ) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.please_set_bg_color, Toast.LENGTH_LONG).show();
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_email:
                createSendMailIntent();
                break;
            case R.id.action_about:
                createAboutDialog();
                break;
            case R.id.action_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSendMailIntent() {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts(getString(R.string.mailto), getString(R.string.my_mail), null));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        mailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));
        startActivity(Intent.createChooser(mailIntent, getString(R.string.email_dialog_header)));
    }

    private void createAboutDialog() {
        // add all data to textView
        final TextView message = new TextView(this);
        final SpannableString s = new SpannableString(this.getText(R.string.dialogMsgAbout));
        Linkify.addLinks(s, Linkify.WEB_URLS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());

        // build dialog_game_end
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogTitleAbout);
        builder.setView(message);
        AlertDialog dialog = builder.create();
        dialog.show();

        // make the textview clickable (link to original game). Must be called after show()
        ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
