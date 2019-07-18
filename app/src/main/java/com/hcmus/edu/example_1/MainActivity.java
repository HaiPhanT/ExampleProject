package com.hcmus.edu.example_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView leftView;
    private View headerView;
    private ImageView ivMenu;
    private ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.layoutDrawer);
        leftView = findViewById(R.id.leftView);
        ivMenu = toolbar.findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(this);
        headerView = leftView.getHeaderView(0);
        ivClose = headerView.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(this);
        setupDrawerContent(leftView);

//        Gender gender = (Gender) getIntent().getSerializableExtra("GENDER");
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }
        );
    }

    public void selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.menu_feed:
                fragmentClass = FeedFragment.class;
                break;
//            case R.id.menu_event:
//                fragmentClass = EventFragment.class;
//                break;
//            case R.id.menu_post:
//                fragmentClass = PostFragment.class;
//                break;
//            case R.id.menu_notification:
//                fragmentClass = NotificationFragment.class;
//                break;
//            case R.id.menu_account:
//                fragmentClass = AccountFragment.class;
//                break;
//            case R.id.menu_logout:
//                fragmentClass = LogoutFragment.class;
//                break;
            default:
                fragmentClass = FeedFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layoutContainer, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ivMenu) {
            drawer.openDrawer(Gravity.START);
        } else if (id == R.id.ivClose) {
            drawer.closeDrawers();
        }
    }
}
