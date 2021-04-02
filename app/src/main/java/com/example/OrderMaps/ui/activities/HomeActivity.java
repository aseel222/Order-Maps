package com.example.OrderMaps.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OrderMaps.model.apimodel.FcmTokenResponse;
import com.example.OrderMaps.model.apimodel.LogOutResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.model.notificationmodel.MyFirebaseInstanceIdService;
import com.example.OrderMaps.rebository.Repository;
import com.example.OrderMaps.ui.fragement.SupportFragment;
import com.example.OrderMaps.ui.fragement.GalleryFragment;
import com.example.OrderMaps.ui.fragement.HomeFragment;
import com.example.OrderMaps.ui.fragement.SlideshowFragment;
import com.example.OrderMaps.viewmodel.LogoutViewModel;
import com.example.splashactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.os.HandlerCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    Toolbar toolbar;
    TextView nav_headername;
    LogoutViewModel logoutViewModel;
    PreferencesManager preferencesManager;


    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("الصفحه الرئيسيه");

        logoutViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        preferencesManager = new PreferencesManager(this);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        observedata2();

        updateToken();
        nav_headername = headerview.findViewById(R.id.profiletxt);
        nav_headername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BranchFileActivity.class);
                startActivity(intent);
            }
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_support, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_setting);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        displaySelectedScreen(R.id.nav_home);
    }

    private void updateToken() {

        PreferencesManager preferencesManager = new PreferencesManager(this);
        Task<InstanceIdResult> instanceId = FirebaseInstanceId.getInstance().getInstanceId();
        if (preferencesManager.islogin()) {

            instanceId.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isComplete())
                        return;

                    Log.d("vfvfvfvfv", "onNext: "+task.getResult().getToken());
                    Log.d("vfvfvfvfv", "onNext: "+preferencesManager.fetchtoken());
                    Repository repository = Repository.getInstance();
                    repository.storetoken(task.getResult().getToken(), "Bearer " + preferencesManager.fetchtoken()).subscribe(new Observer<Response<FcmTokenResponse>>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull Response<FcmTokenResponse> fcmTokenResponseResponse) {

                           // Log.d("vfvfvfvfv", "onNext: "+fcmTokenResponseResponse.);
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            });


        }

    }

    @Override
    public void onBackPressed() {


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.notification:
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;



        }


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());


        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                toolbar.setTitle("الصفحه الرئيسيه");

                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                toolbar.setTitle("المدفوعات");

                break;
            case R.id.nav_slideshow:
                fragment = new SlideshowFragment();
                toolbar.setTitle("الدردشه");

                break;
            case R.id.nav_support:
                fragment = new SupportFragment();
                toolbar.setTitle("الدعم");

                break;
            case R.id.nav_logout:
                logoutViewModel.logout("Bearer " + preferencesManager.fetchtoken());

                preferencesManager.signout();
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        drawer.closeDrawer(GravityCompat.START);

    }

    public void observedata2() {
        logoutViewModel.logoutmutablelivedata.observe(this, new androidx.lifecycle.Observer<LogOutResponse>() {
            @Override
            public void onChanged(LogOutResponse logOutResponse) {
                Toast.makeText(HomeActivity.this, "تم تسجيل الخروج", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}