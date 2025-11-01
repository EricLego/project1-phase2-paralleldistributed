#!/usr/bin/env sh
set -e
cd out
java edu.ksu.election.ElectionServer "${1:-localhost}"
