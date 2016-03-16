package com.thierry.beaconfire.util

/**
 * Created by Thierry on 16/3/9.
 */
object Constants {

    // Default Organization
    val CurrentOrganization = "sentry"

    // Default Host
    val Host = "http://sentry.gengmei.cc"

    object Web {
        val Root = Host + "/"
        val Login = Host + "/auth/login/sentry/"
        val Stats = Host + "/organizations/#{CurrentOrganization}/stats/"
    }

    object API {
        val Organizations = Host + "/api/0/organizations/"
        val Projects = Host + "/api/0/organizations/${Constants.CurrentOrganization}/projects/"
        val New = Host + "/api/0/organizations/${Constants.CurrentOrganization}/issues/new/"
        val Assigned = Host + "/api/0/organizations/${Constants.CurrentOrganization}/members/me/issues/assigned/"
        val Events = Host + "/api/0/projects/${Constants.CurrentOrganization}/%s/issues/"
    }

    object Broadcast {
        val LoginExpired = "LoginExpired"
    }
}