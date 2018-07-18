import sys
if len(sys.argv) > 1:
        year = int(sys.argv[1])
else:
        year = 2015
hour = 24
day = 28
month = 12
print year

datafile = open("url"+str(year),"w")
for i in range(hour):
  for j in range(1, day + 1):
    if j < 10:
      daystr = "0" + str(j)
    else:
      daystr = str(j)
    for k in range(1, month + 1):
      if k < 10:
        monthstr = "0" + str(k)
      else:
        monthstr = str(k)
      for l in range(year, year+1):
        file = str(l) + "-" + monthstr + "-" + daystr + "-" + str(i) + ".json.gz"
        url = "http://data.gharchive.org/" + file + '\n'
        datafile.writelines(url)
datafile.close()
