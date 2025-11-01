#!/usr/bin/env sh
set -e
mkdir -p out
javac -d out src/main/java/edu/ksu/election/*.java
