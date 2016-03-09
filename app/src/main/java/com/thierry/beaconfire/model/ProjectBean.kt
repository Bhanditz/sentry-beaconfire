package com.thierry.beaconfire.model

/**
 * Created by Thierry on 16/3/9.
 */
data class ProjectBean(val slug: String,
                       val features: Array<String>,
                       val isPublic: Boolean,
                       val isBookmarked: Boolean,
                       val firstEvent: String,
                       val team: TeamBean,
                       val dateCreated: String,
                       val id: String,
                       val name: String) {

}