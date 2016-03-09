package com.thierry.beaconfire

import android.app.Application
import com.thierry.beaconfire.service.GMNetService
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/2/29.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        this.initNet()
    }

    fun initNet() {
        val service: GMNetService = GMNetService.instance
        service.apiHost = Constants.Host;
    }

}
