package com.example.nikolab.localisationfun;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivityList extends ActionBarActivity {

    private HashMap<Integer, String> namePckgPosMap;
    private Drawable[] d;
    private static final String TAG = "PkgFunc";
    private String[] installedApps;
    private HashMap<Integer, String> namePosMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_list);

        final ListView mList = (ListView) findViewById(R.id.list);

        namePckgPosMap = new HashMap<>();
        namePosMap = new HashMap<>();

        ArrayList<String> outfitPckgsList = new ArrayList<>();

        // Get all installed outfit7 apps
        outfitPckgsList = getOutfitPckgs(outfitPckgsList);
        d = new Drawable[outfitPckgsList.size()];
        Log.e("Size img", String.valueOf(outfitPckgsList.size()));
        installedApps = new String[outfitPckgsList.size()];

        // Get list for ListView
        populateAppNamesList(outfitPckgsList);

        CostumList adapter = new CostumList(MainActivityList.this, installedApps, d);
        mList.setAdapter(adapter);

        // List click listener
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickManager(position);
            }
        });
    }

    // Append names
    private void populateAppNamesList(ArrayList<String> listGet) {
        int j = 0;
        for (String temp : listGet) {
            PackageManager packageManager = getApplicationContext().getPackageManager();
            try {
                d[j] = getPackageManager().getApplicationIcon(temp);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return;
            }
            try {
                installedApps[j] = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(temp, PackageManager.GET_META_DATA));
                Log.d("Installed app", installedApps[j]);
                namePckgPosMap.put(j, temp);
                namePosMap.put(j, installedApps[j]);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            j++;
        }
    }

    // Handle list click event
    private void clickManager(int position) {
        String pckgName = namePckgPosMap.get(position);
        String appName = namePosMap.get(position);
        Log.d("Map name pos:", String.valueOf(namePckgPosMap));
        Intent intent = new Intent(MainActivityList.this, MainActivity.class);
        intent.putExtra("message", pckgName);
        intent.putExtra("name", appName);
        startActivity(intent);
    }

    // Get array list of installed outfit7 packages
    public ArrayList<String> getOutfitPckgs(ArrayList<String> list) {
        String mPackageName;
        int i = 0;
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            // Enter your package name prefix here
            if (packageInfo.packageName.contains("com.example")) {
                mPackageName = packageInfo.packageName;
                list.add(i, mPackageName);
                i++;
                Log.d(TAG, mPackageName);
            }
        }
        return list;
    }
}

