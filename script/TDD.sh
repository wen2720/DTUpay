#!/bin/sh
cd $WORKSPACE
# e.g. in jenkins sh ""
# sh "java -cp \".:./lib/*\" org.junit.runner.JUnitCore model.TestStringCalculator" to cast " in a string require escape
java -cp ".:./lib/*" org.junit.runner.JUnitCore model.TestStringCalculator