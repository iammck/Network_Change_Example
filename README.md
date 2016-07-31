A quick example of network state change listeners and broadcast receivers which log output.

Log of network changes. notice that when wifi is active and cellular is turned on there is update.  Also, when wifi is disconnected, its disconnections are notified before broadcasting cellular availability when it is available.


    Has cellular but no wifi

    .......... onResume()
    Targeting Lollipop or newer, register network callbacks.
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 0
    key extraInfo with value <unknown ssid>
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.
    CellularNetworkCallback.onAvailable() with network 386
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: MOBILE[LTE], state: CONNECTED/CONNECTED, reason: (unspecified), extra: VZWINTERNET, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 0
    key inetCondition with value 0
    key extraInfo with value VZWINTERNET
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is connected.
    CellularNetworkCallback.onAvailable() with network 386

    turn off wifi

    WifiNetworkCallback.onAvailable() with network 387
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: MOBILE[LTE], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: VZWINTERNET, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 0
    key inetCondition with value 100
    key extraInfo with value VZWINTERNET
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: "Allegro", roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 100
    key extraInfo with value "Allegro"
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is connected.
    CellularNetworkCallback.onLost() with network 386

    turn off cellular

    onPause()...............
    .......... onResume()
    The network is connected in onResume()
    Targeting Lollipop or newer, register network callbacks.
    WifiNetworkCallback.onAvailable() with network 389
    CellularNetworkCallback.onAvailable() with network 388
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: "Allegro", roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 100
    key extraInfo with value "Allegro"
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is connected.


    turn off wifi

    CellularNetworkCallback.onLost() with network 388
    WifiNetworkCallback.onLost() with network 389
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key otherNetwork with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key inetCondition with value 0
    key extraInfo with value <unknown ssid>
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 0
    key extraInfo with value <unknown ssid>
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.


    turn on wifi

    WifiNetworkCallback.onAvailable() with network 390
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: "Allegro", roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 0
    key extraInfo with value "Allegro"
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is connected.
    WifiNetworkCallback.onAvailable() with network 390


    turn cellular on

    - no resulting output

    turn off wifi

    WifiNetworkCallback.onLost() with network 390
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key otherNetwork with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key inetCondition with value 0
    key extraInfo with value <unknown ssid>
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: <unknown ssid>, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 1
    key inetCondition with value 0
    key extraInfo with value <unknown ssid>
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is not connected.
    CellularNetworkCallback.onAvailable() with network 391
    NetworkStatusBroadcastReceiver.onReceive() with extras:
    key networkInfo with value [type: MOBILE[LTE], state: CONNECTED/CONNECTED, reason: (unspecified), extra: VZWINTERNET, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]
    key networkType with value 0
    key inetCondition with value 0
    key extraInfo with value VZWINTERNET
    key isDefault with value true
    NetworkStatusBroadcastReceiver.onReceive() the network is connected.
    CellularNetworkCallback.onAvailable() with network 391
