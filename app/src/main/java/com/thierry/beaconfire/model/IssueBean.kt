package com.thierry.beaconfire.model


/**
 * Created by Thierry on 16/3/9.
 */
data class IssueBean(var id: String,
                     var project: ProjectBean,
                     var status: String,
                     var title: String,
                     var count: Int,
                     var culprit: String,
                     var level: String,
                     var isBookmarked: Boolean,
                     var isPublic: Boolean,
                     var userCount: Int,
                     var firstSeen: String,
                     var lastSeen: String,
                     var permalink: String) : BaseBean