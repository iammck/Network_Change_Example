package com.mck.networkexample;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    // used to listen for network status changes via BroadcastReceiver or
    // connectivityManager.NetworkCallback subclass.
    private NetworkStatusBroadcastReceiver networkReceiver;
    private WifiNetworkCallback wifiNetworkCallback;
    private CellularNetworkCallback cellularNetworkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, ".......... onResume()");
        if (isNetworkAvailable()){
            Log.v(TAG, "The network is connected in onResume()");
        }

        // if targeting a lollipop, then can register network callbacks for both
        // WIFI nd cellular.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.v(TAG, "Targeting Lollipop or newer, register network callbacks.");
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkRequest.Builder wifiRequestBuilder = new NetworkRequest.Builder();
            wifiRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            wifiRequestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            wifiNetworkCallback = new WifiNetworkCallback();
            connectivityManager.registerNetworkCallback(wifiRequestBuilder.build(),
                    wifiNetworkCallback);

            NetworkRequest.Builder cellularRequestBuilder = new NetworkRequest.Builder();
            cellularRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
            cellularRequestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            cellularNetworkCallback = new CellularNetworkCallback();
            connectivityManager.registerNetworkCallback(cellularRequestBuilder.build(),
                    cellularNetworkCallback);
        }

        // register a broadcast receiver to listen for connectivity updates.
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkReceiver = new NetworkStatusBroadcastReceiver();
        registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()...............");
        // always unregister any listeners.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            connectivityManager.unregisterNetworkCallback(wifiNetworkCallback);
            connectivityManager.unregisterNetworkCallback(cellularNetworkCallback);
        }
        // receivers must be unregistered.
        unregisterReceiver(networkReceiver);
    }

    /**
     * Use the ConnectivityManager to check that there is a network and it is
     * connected. This requires access to network state, thus the manifest must
     * include ACCESS_NETWORK_STATE permission.
     * @return true if the network is available and connected.
     */
    public boolean isNetworkAvailable(){
        // the class that answers queries about the state of network connectivity.
        // Can also notify applications when network connectivity changes.
        // Get an instance of this class through Context.getSystemService()
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // returns details about the currently active default data network. When connected,
        // this network is the default route for outgoing connections.
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // Always check isConnected() and be assured not null
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * BroadcastReceiver to receive network status changes. Requires uses
     * Access_network_state permission in manifest.
     */
    private class NetworkStatusBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // get the extras for the broadcast and check to see what they are.
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Log.v(TAG, "NetworkStatusBroadcastReceiver.onReceive() with extras:");
                for (String key : extras.keySet()) {
                    Log.v(TAG, "key " + key + " with value " + extras.get(key));
                }
            } else {
                Log.v(TAG, "NetworkStatusBroadcastReceiver.onReceive() with no extras.");
            }
            // can also use ConnectivityManagerCompat to get network info and check for connectivity.
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = ConnectivityManagerCompat.getNetworkInfoFromBroadcast(cm, intent);
            if (info == null){
                Log.v(TAG, "NetworkStatusBroadcastReceiver.onReceive() network info is not available.");
            } else if (info.isConnected()){
                Log.v(TAG, "NetworkStatusBroadcastReceiver.onReceive() the network is connected.");
            } else {
                Log.v(TAG, "NetworkStatusBroadcastReceiver.onReceive() the network is not connected.");
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private class WifiNetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            Log.v(TAG, "WifiNetworkCallback.onAvailable() with network " + network.toString());
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            Log.v(TAG, "WifiNetworkCallback.onLost() with network " + network.toString());
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private class CellularNetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            Log.v(TAG, "CellularNetworkCallback.onAvailable() with network " + network.toString());
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            Log.v(TAG, "CellularNetworkCallback.onLost() with network " + network.toString());
        }
    }
}

