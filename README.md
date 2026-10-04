# garmin-tools-server

A port of https://github.com/jpickup/GarminTools that runs as a web app. It shares the bulk of the Java code from GarminTools and that runs as a REST server back-end. A VueJS front-end allows files to be uploaded and converted to Garmin FIT.

This primary reason I created this was because creating Windows, MacOS and Linux builds for GarminTools was a little tedious and difficult to properly test, especially after I moved to an Apple silicon Mac.

There is an instance of this service running on my home server at https://garmin-tools.pickup-dev.uk/
