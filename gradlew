#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"
APP_HOME="$DIR"
APP_BASE_NAME=${0##*/}
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Discover java
if [ -n "$JAVA_HOME" ] ; then
  JAVA_EXE="$JAVA_HOME/bin/java"
else
  JAVA_EXE="$(command -v java)"
fi

if [ ! -x "$JAVA_EXE" ] ; then
  echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
  exit 1
fi

exec "$JAVA_EXE" -Dorg.gradle.appname="$APP_BASE_NAME" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
