import json
import gzip
from pprint import pprint
import os
from os.path import isfile, join
from datetime import datetime
import sys

if len(sys.argv) > 1:
        year = sys.argv[1]
else:
        year = "2015"

targetfile = open("github"+year+".txt",'w');
result = []
i =0
for filename in os.listdir(year):
  filename = join(year+"/", filename)
  i = i+1
  print filename, i
  ff = gzip.open(filename, 'r')
  for line in ff:
      if line[len(line)-2] == '}':
          #rec = json.loads(line, strict=False);
          ii = line.find("\"type\":\"") +8
	  line = line[ii:]
          ii = line.find("\"")
          if line[0:ii] != "PullRequestEvent" and line[0:ii] != "PushEvent" and line[0:ii] != "WatchEvent":
		continue
          typename = line[0:ii]
          line = line[ii+1:]
          ii = line.find("\"id\":") +5
          line = line[ii:]
          ii = line.find(",")
          actorid=line[0:ii]
          line = line[ii+1:]
          ii = line.find("\"login\":\"") +9
          line = line[ii:]
          ii = line.find("\"")
          actorname = line[0:ii]
          line = line[ii+1:]
          ii = line.find("\"id\":") +5
          line = line[ii:]
          ii = line.find(",")
          repoid=line[0:ii]
          line = line[ii+1:]
          ii = line.find("\"name\":\"") +8
          line = line[ii:]
          ii = line.find("\"")
          reponame = line[0:ii]
          if reponame.startswith(actorname):
              continue
          result.append(typename)
          result.append(",")
          result.append(actorid)
          result.append(",")
          result.append(actorname)
          result.append(",")
          result.append(repoid)
          result.append(",")
          result.append(reponame)
          result.append("\n")
  ff.close()
  targetfile.write(''.join(result));
  result = []
targetfile.close()
