package com.example.noteapp.extensions.connectInternet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.noteapp.NoteActivity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class NetworkObserver: ConnectObserver {

    private var weakReference: WeakReference<Context>? = WeakReference(NoteActivity.instance)

    private val connectivityManager = weakReference?.get()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observer(): Flow<ConnectObserver.Status> {
        return callbackFlow {

            val networkCall = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectObserver.Status.Available) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectObserver.Status.Unavailable) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectObserver.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectObserver.Status.Lost) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCall)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCall)
            }
        }.distinctUntilChanged()
    }

        fun isNetworkConnect(): Boolean {
            return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }
//        Hàm isNetworkConnect() kiểm tra xem thiết bị có kết nối với internet không.
//        Nó sử dụng connectivityManager để lấy các khả năng mạng của mạng hoạt động.
//        getNetworkCapabilities(connectivityManager.activeNetwork) lấy các khả năng mạng của mạng hoạt động.
//        hasCapability(NET_CAPABILITY_INTERNET) kiểm tra xem mạng có khả năng truy cập internet không.

}