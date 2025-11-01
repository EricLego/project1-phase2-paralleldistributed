#!/usr/bin/env sh
set -e
cd out
java edu.ksu.election.ElectionClient "${1:-localhost}"
