package com.thierry.beaconfire.util

/**
 * Created by Thierry on 16/3/9.
 */
object Constants {

    // Default Organization
    val CurrentOrganization = "sentry"
    //    val CurrentOrganization = "sentry-sc"

    // Default Host
    val Host = "http://sentry.gengmei.cc"
    //    val Host = "https://app.getsentry.com"

    object Web {
        val Root = Host + "/"
        val Login = Host + "/auth/login/${Constants.CurrentOrganization}/"
        val Stats = Host + "/organizations/${Constants.CurrentOrganization}/stats/"
    }

    object API {
        val Organizations = Host + "/api/0/organizations/"
        val Projects = Host + "/api/0/organizations/${Constants.CurrentOrganization}/projects/"
        val New = Host + "/api/0/organizations/${Constants.CurrentOrganization}/issues/new/"
        val Assigned = Host + "/api/0/organizations/${Constants.CurrentOrganization}/members/me/issues/assigned/"
        val Events = Host + "/api/0/projects/${Constants.CurrentOrganization}/%s/issues/"
        val Bookmarks = Host + "/api/0/organizations/${Constants.CurrentOrganization}/members/me/issues/bookmarked/"
        val History = Host + "/api/0/organizations/${Constants.CurrentOrganization}/members/me/issues/viewed/"
    }

    object Broadcast {
        val LoginExpired = "LoginExpired"
    }
}