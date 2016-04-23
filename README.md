# VisualTimer_java #

It's like an egg timer, but it doesn't say `beep` is just flashed the screen.

## Why ? ##
Well, when you need a timer, but don't want an annoying beep. Originally I wrote this app as an OS X application (see the [github project](https://github.com/borrob/VisualTimer_OSX) here). The app was intended for the [Off<>zz](http://keyboardsunite.com/offzz/) piano and live coding duo to synchronise their musical actions. Later I ported the application to java.

## How to ##
Run the executable jar in `/build` directory. It starts straight away with a 5 second count down. The app restarts the timer automatically. You can control the timer with the buttons on the bottom. When you enter a new count down time, it will use that new time immediately (but it won't reset the timer). Enter a list of new times and the app cycles through this list. The list should be comma separated (e.g. `60, 10, 90, 3`).

## Java ##
Make sure you have a JRE installed on you computer (1.8 or higher). You can download it from [the Oracle website](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) or you can use [openJDK](http://openjdk.java.net) (not tested, but it should work).
