kiva miner
==========


build instructions
---------

1. in root dir, run `mvn install`

2. if you want to open a scala console, go into `kiva_explore`, and then run
   `mvn scala:console`.  You also need to configure the scala plugin in your
   global plugin config in `~/.m2/settings.xml` by adding
   `<pluginGroup>org.scala-tools</pluginGroup>`





exploring in scala shel
-------------

```
import java.io.File
import com.imranrashid.kiva_json_beans._
import scala.collection.JavaConverters._
val f = new File("/Users/imran/datasets/kiva_ds_json/loans")
val dataItr = LoanFile.loadDir(f)
val data = dataItr.toIndexedSeq
val data = dataItr.asScala.toIndexedSeq
                                                                                                                                                                                                      
val countryGroups = data.groupBy{_.location.country_code}
val countryCount = countryGroups.map{case(k,v) => k -> v.size}
countryCount.toIndexedSeq.sortBy{_._2}.foreach{println}


val langs = data.flatMap{_.description.languages}
langs.foldLeft(Map[String,Int]()){case (acc, l) => acc + (l -> (acc.getOrElse(l, 0) + 1))}.foreach{println}
```
