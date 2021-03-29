#Guess gender detection algorithm

##Endpoints

###GET: /gender/guess/{names}/{algo}
####1st variant require algo = 1 - only first token from param names is checked
####2nd variant without algo parameter - all tokens are checked

###GET: /gender/all
####List of all female and male from flat files

