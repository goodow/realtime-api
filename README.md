realtime-store [![Build Status](https://travis-ci.org/goodow/realtime-store.svg?branch=master)](https://travis-ci.org/goodow/realtime-store)
==============

Google Docs–style collaboration via the use of operational transforms

**See [Wiki](https://github.com/goodow/realtime-store/wiki) for more information.**

**Visit [Google groups](https://groups.google.com/forum/#!forum/goodow-realtime) for discussions and announcements**

**Check out the [playground live instance](http://realtimeplayground.goodow.com/).**

Credits:

[LIVE DB](https://github.com/share/livedb) for implementation and documents of database api.

## Build from source and launch server

### Pre-requisites
- [JDK 8+](https://jdk8.java.net/download.html)
- [Apache Maven](http://maven.apache.org/download.html)
- [Git](https://help.github.com/articles/set-up-git)

### Check out sources and run the server with Maven
```bash
git clone https://github.com/goodow/realtime-store.git
cd realtime-store
mvn clean package vertx:runMod
```

### Configuration
https://github.com/goodow/realtime-store/blob/master/src/main/resources/store.conf


## Clients

### For java and android: realtime-android
See: https://github.com/goodow/realtime-android

Try out Goodow Realtime Store API Playground on the Android Market:

[![Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.goodow.realtime.android.playground)

### For iOS and Mac OS X: GDStore
See: https://github.com/goodow/GDStore

### For javascript: realtime.store
See: https://github.com/goodow/bower-realtime-store

You can try out the Goodow Realtime Store API Playground on its [live instance](http://realtimeplayground.goodow.com).
