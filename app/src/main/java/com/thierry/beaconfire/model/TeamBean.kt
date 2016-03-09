package com.thierry.beaconfire.model

/**
 * Created by Thierry on 16/3/9.
 */
data class TeamBean(var slug: String,
                      var name: String,
                      var hasAccess: Boolean,
                      var isPending: Boolean,
                      var dataCreated: String,
                      var isMember: Boolean,
                      var id: String)