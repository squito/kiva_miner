kiva miner
==========


build instructions
---------

1. in root dir, run `mvn install`

2. if you want to open a scala console, go into `kiva_explore`, and then run
   `mvn scala:console`.  You also need to configure the scala plugin in your
   global plugin config in `~/.m2/settings.xml` by adding
   `<pluginGroup>org.scala-tools</pluginGroup>`
