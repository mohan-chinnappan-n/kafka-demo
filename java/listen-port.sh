#“lsof” is a contraction for “list open files”. Without any options specifications, lsof lists all open files belonging to all active processes.
#
#“-nP” is a combination of “n” for no resolution of IPs to hostnames using DNS and “P” for no resolution of Port names from numbers.
#
#This is because the command already takes several seconds to run.
#
#“+c 15” specifies command width of 15.

lsof -nP +c 15  | grep LISTEN
