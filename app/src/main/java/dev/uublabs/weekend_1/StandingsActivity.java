package dev.uublabs.weekend_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class StandingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private ToggleButton nfcBtn;
    private ToggleButton afcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("NFL Standings 2017-18");

        afcBtn = findViewById(R.id.afcToggle);
        nfcBtn = findViewById(R.id.nfcToggle);

        final ImageView standings = findViewById(R.id.stands);

        CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener()
        {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    if (buttonView == afcBtn)
                    {
                        nfcBtn.setChecked(false);
                        standings.setImageResource(R.drawable.afc);
                    }
                    else if(buttonView == nfcBtn)
                    {
                        afcBtn.setChecked(false);
                        standings.setImageResource(R.drawable.nfc);
                    }
                }
            }
        };
        afcBtn.setOnCheckedChangeListener(changeListener);
        nfcBtn.setOnCheckedChangeListener(changeListener);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.actionGronk)
        {
            startActivity(new Intent(this, GronkActivity.class));
            return true;
        }
        else if(id == R.id.actionHome)
        {
            startActivity(new Intent(this, TeamHome.class));
            return true;
        }
        else if (id == R.id.actionTix)
        {
            startActivity(new Intent(this, TicketsActivity.class));
            return true;
        }
        else if (id == android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.navHome)
        {
            startActivity(new Intent(this, HomeActivity.class));
        }
        else if (id == R.id.navRoster)
        {
            startActivity(new Intent(this, RosterActivity.class));
        }
        else if (id == R.id.navSchedule)
        {
            startActivity(new Intent(this, ScheduleActivity.class));
        }
        else if (id == R.id.navStandings)
        {
//                nothing this is the standings screen
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
