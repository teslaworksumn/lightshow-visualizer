VixenLights Visualizer 2
========================

Overview
--------

This is a Java-based configurable visualizer for use with
[VixenLights 2](http://www.vixenlights.com/downloads/vixen-2-downloads/).
To get real-time channel data, it reads a file from the Event Data Logger plugin that
comes with Vixen. The event data logger writes the signal output to a file instead of a
serial port or network interface (Like Renard, DMX, or E.131).
It is designed to behave similarly to the Light-o-Rama visualizer.

Usage
-----

**WARNING**: This program is is beta/alpha stage.  Use with care.

The program reads the name of the target log file from the command line.
It reads the layout data from an xml file in /config (see /config/layout1.xml).
The layout data tells it what kind of shapes to draw and what channels to map them to.

Vixen Setup
-----------

1. Add the Event Data Logger plugin to your profile
2. Manually edit the `.pro` file in a text editor to change the location of the log to
someplace more useful than a temp directory.
  - [Notepad++](http://notepad-plus-plus.org/) is very good at this
3. Pass the name of the log file that you selected earlier as the first command line
argument to the visualizer.
4. Play your sequences! :)

Developer's Notes:
------------------

This software was written using Eclipse Standard IDE version 4.4.0 (Luna).  You will
probably need this to compile.

### Hardware

We used a couple of machines for testing and debugging. This has run successfully on all
of the following machines, and takes up limited system resources even on the least
powerful of these machines


| Model                     | Processor               | RAM | OS                    | Java Version        |
| ------------------------- | ----------------------- | --- | --------------------- | ------------------  |
| Dell Latitude E6420       | Core i7-2720QM @ 2.20GHz| 8GB | Windows 7 Enterprise  | Java 1.7 Update 67  |
| 13" Macbook Pro Mid-2009  | Intel Core2Duo @ 2.26GHz| 8GB | Mac OS X 10.10 beta 5 | Java 1.8 Update 20  |
| 13" Macbook Pro Retina    | Intel Core i5 @ 2.40GHz | 8GB | Mac OS X 10.9.5       | Java 1.7 Update 67  |