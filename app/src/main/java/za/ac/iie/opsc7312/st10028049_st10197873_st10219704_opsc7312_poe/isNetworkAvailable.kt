
package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

// This code was adapted using ChatGPT
// Link: https://chatgpt.com/

// Used to see if the device is connected to a network
object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
