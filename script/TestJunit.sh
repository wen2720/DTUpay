#!/bin/sh
cd $WORKSPACE
java -cp ".:./lib/*" org.junit.runner.JUnitCore model.TestStringCalculator