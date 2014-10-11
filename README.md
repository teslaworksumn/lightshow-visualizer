VixenLights Visualizer 2
========================

Overview
--------

This is a Java-based configurable visualizer for use with [VixenLights 2](http://www.vixenlights.com/downloads/vixen-2-downloads/). To get real-time channel data, it reads a file from the Event Data Logger plugin that comes with Vixen. The event data logger writes the signal output to a file instead of a serial port or network interface (Like Renard, DMX, or E.131).
It is designed to behave similarly to the Light-o-Rama visualizer.

Usage
-----

WARNING: This program is is beta/alpha stage.  Use with care.

10/11/2014:
Currently, the program is hard-coded to open the file `./config/samplecfg.xml`.

~~When the program opens, you will be presented with a file dialogue to select a configuration file~~ (called config files). Config files tell the visualizer how to handle the input data from Vixen, and where to look for the event data log. 

Vixen Setup
-----------

1. Add the Event Data Logger plugin to your profile
2. Manually edit the `.pro` file in a text editor to change the location of the log to someplace more useful than a temp directory.
  - [Notepad++](http://notepad-plus-plus.org/) is very good at this
3. Set up the config file to point to the log file that you selected earlier
4. Play your sequences! :)

Developer's Notes:
------------------

This software was written using Eclipse Standard IDE version 4.4.0 (Luna).  You will probably need this to compile.

### Hardware

We used a couple of machines for testing and debugging.  This has run successfully on all of the following machines, and takes up limited system resources even on the least powerful of these machines

|------------------------|--------------------------|------|------------------------------------|-----------------------|--------------------|
| Model                  | Processor                | RAM  | Graphics                           | OS                    | Java Version       |
|------------------------|--------------------------|------|------------------------------------|-----------------------|--------------------|
| Dell Latitude E6420    | Core i7-2720QM @ 2.20GHz | 8GB  | Intel HD 3000 + NVIDIA NVS 4200    | Windows 7 Enterprise  | Java 1.7 Update 67 |
|------------------------|--------------------------|------|------------------------------------|-----------------------|--------------------|
| 13" Macbook Pro        | Intel Core2Duo @ 2.26GHz | 8GB  | NVIDIA GeForce 9400M               | Mac OS X 10.10 beta 5 | Java 1.8 Update 20 |
| (Mid-2009: 5,5)        |                          |      |                                    |                       |                    |
|------------------------|--------------------------|------|------------------------------------|-----------------------|--------------------|
| 13" Macbook Pro Retina | Intel Core i-series      |      | Intel HD Graphics                  | Mac OS X 10.9         |                    |
|------------------------|--------------------------|------|------------------------------------|-----------------------|--------------------|