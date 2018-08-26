#!/bin/bash

set -e

root=$(pwd)

cd $root/src/cpp/PS3xploit-resigner-2.0.0/
make clean && make

cp $root/src/cpp/PS3xploit-resigner-2.0.0/ps3xploit_rifgen_edatresign  $root/src/test/resources/
cd $root/src/test/resources/
./ps3xploit_rifgen_edatresign UP0102-NPUB30033_00-AGEOFBOOTYGAME00.rap > output.txt

mv output.txt $root/
cd $root
subl output.txt
