package com.thierry.beaconfire

import android.app.Application
import com.thierry.beaconfire.service.GMNetService
import com.thierry.beaconfire.util.Constants
import kotlin.properties.Delegates

/**
 * Created by Thierry on 16/2/29.
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initNet()
    }


    fun initNet() {
        val service: GMNetService = GMNetService.instance
        service.apiHost = Constants.Host;
    }

}
