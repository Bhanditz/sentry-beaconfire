# Sentry Beaconfire
Kotlin based getsentry/sentry android client, now you can care about your server's error logging and aggregation over the android phone.

<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-4.png-h" width="140" />
<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-0.png-h" width="140" />
<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-1.png-h" width="140" />
<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-5.png-h" width="140" />
<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-2.png-h" width="140" />
<img src="http://7xjlg5.com1.z0.glb.clouddn.com/QQ20160326-3.png-h" width="140" />


## About Sentry
[Sentry](https://github.com/getsentry/sentry) is a modern error logging and aggregation platform.

## About Project
* Using Kotlin with MVVM pattern.
* Using Android Databinding framework.

## Feature List
* Configure your own sentry server host and organization.
* Login to your own sentry server.
* Never miss any Assigned and New issues with Dashboard.
* All projects and teams listed.
* Track issues in a project and see more detail.

## How to use
Simply replace the defaults with your own sentry server host and organization in Constants.kt
```kotlin
// Default Organization
val CurrentOrganization = "YOUR_ORGANIZATION"

// Default Host
val Host = "YOUR_SENTRY_HOST"
```
## Sentry Version
As far as I know it worked well with Sentry 8.0+, but it may compatible with lower versions, so if anyone find that please let me know, thank you! 

## Contribute
It is just the beginning of Beaconfire, so anyone interested, feel free to fork it and pull requests to me. Let's make it more fun.

## Author
Thierry Xing thierry.xing@gmail.com

