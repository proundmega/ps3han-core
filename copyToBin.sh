#!/bin/bash

set -e

root=$(pwd)

cd $root/src/cpp/PS3xploit-resigner-2.0.0/
make clean && make

cp $root/src/cpp/PS3xploit-resigner-2.0.0/ps3xploit_rifgen_edatresign  $root/src/main/resources/bin