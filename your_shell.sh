#!/bin/sh
#
# DON'T EDIT THIS!
#
#
# DON'T EDIT THIS!
set -e
mvn -B --quiet package -Ddir=/tmp/codecrafters-shell-target
exec java -jar /tmp/codecrafters-shell-target/java_shell.jar "$@"