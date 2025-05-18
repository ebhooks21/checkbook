#!/bin/bash

# This shell script is used to compile the checkbook program in Linux.
# Written by: Eric Hooks
# Last edited: 06/26/2010

# Compile all files in the current directory
# Display message about compiling all files in directory
echo "Compiling all files in directory: `pwd`"

# Copmpile the files
javac *.java

# End of script
