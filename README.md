# devfestnineteen
Example app I have used during my talk at DevFest Veneto 2019.

**The CLEAN cut - https://devfestvenice.com/schedule/2019-11-16?sessionId=216**

>Nowadays having a proper architecture in any software is crucial to have a healthy codebase that is manageable throughout the entire lifetime of a product. Some Android specific architectural concepts have been around for quite a while and some of them are very popular. While evolving the platform-specific architectures has had a lot of focus from Google and gained developers' interest, less attention has been put on how to properly build architectured software from the ground up. It is now very common to have projects which rely on legacy untested pieces of software, built android-ages ago which represent a productivity bottleneck and pose a threat to code quality, software reliability, and ultimately to revenue. CLEAN has been around in the software industry for a long period already, but just recently it has gained traction as a means to develop fully testable, scalable and reliable mobile apps. In this talk, we'll run through a strategy about how to transition between a real-world legacy codebase towards a modularized, CLEAN architectured, unit-testable implementation of a mobile app.

Speakerdeck https://speakerdeck.com/beraldofilippo/the-clean-cut.

## Description
The app showcases a hybrid modularization approach, CLEAN-architectured feature modules.

It's a news reading app, hitting a network endpoint and the using Room to store data locally, it has Unit Tests for all major components inside the _feed_ module as well as instrumented tests for the local data source (located in the app package).

The project dependency graph can be found at root level _dependency_graph.png_.

## Run
To make it work you need to put a proper API key obtained from newsapi.org.
