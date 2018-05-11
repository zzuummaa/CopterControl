#!/bin/bash

exec > out.txt
exec 2>&1

java -jar coptercontrol.jar -classpath lib
