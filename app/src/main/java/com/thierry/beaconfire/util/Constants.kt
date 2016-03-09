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
        val Root = "/"
        val Login = "/auth/login/sentry/"
        val Stats = "/organizations/#{CurrentOrganization}/stats/"
    }

    object API {
        val Organizations = "/api/0/organizations/"
        val Projects = "/api/0/organizations/${Constants.CurrentOrganization}/projects/"
        val New = "/api/0/organizations/${Constants.CurrentOrganization}/issues/new/"
        val Assigned = "/api/0/organizations/${Constants.CurrentOrganization}/members/me/issues/assigned/"
        val Events = "/api/0/projects/${Constants.CurrentOrganization}/%s/issues/"
    }

    object Notification {
        val LoginExpired = "LoginExpired"
    }
}