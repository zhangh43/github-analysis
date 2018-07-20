import collections
from sets import Set

pmap = {}
rmap= {}
ppmap={}
rrmap = {}
i = 0
pnamemap={}
rnamemap={}

prefilterpmap={}
prefilterrmap={}
with open('/Users/pivotal/github2015.txt','r') as datafile:
  for line in datafile:
		i = i+1
		if i % 1000000 == 0:
			print i;
		words = line.split(',')
		if words[1] not in prefilterpmap:
			prefilterpmap[words[1]]=1
		else:
			prefilterpmap[words[1]]=prefilterpmap[words[1]]+1
		if words[3] not in prefilterrmap:
			prefilterrmap[words[3]]=1
		else:
			prefilterrmap[words[3]]=prefilterrmap[words[3]]+1

print len(prefilterrmap)
print len(prefilterpmap)


with open('/Users/pivotal/github2015.txt','r') as datafile:
	for line in datafile:
		i = i+1
		if i % 1000000 == 0: 
			print i;
		words = line.split(',')
		if prefilterrmap[words[3]]<100 or prefilterpmap[words[1]] < 20:
			continue
		pnamemap[words[1]]=words[2].strip()
		rnamemap[words[3]]= words[4].strip()
		if words[0] == 'WatchEvent':
			if words[1] in pmap:
				if words[3] not in pmap[words[1]]:
					pmap[words[1]].add(words[3])
			else :
				pmap[words[1]]=set([words[3]])
			if words[3] in rrmap:
				if words[1] not in rrmap[words[3]]:
					rrmap[words[3]].add(words[1])
			else:
				rrmap[words[3]]=set([words[1]])
		if words[0] == 'PushEvent':
			if words[3] in rmap:
				if words[1] in rmap[words[3]]:
					rmap[words[3]][words[1]] = rmap[words[3]][words[1]] + 10
				else:
					rmap[words[3]][words[1]] = 10
			else:
				rmap[words[3]]={words[1]:10}
			# ppmap
			if words[1] in ppmap:
				if words[3] in ppmap[words[1]]:
					ppmap[words[1]][words[3]] = ppmap[words[1]][words[3]] + 10 
				else:
					ppmap[words[1]][words[3]] = 10 
			else:
				ppmap[words[1]]={words[3]:10}
		if words[0] == 'PullRequestEvent':
			if words[3] in rmap:
				if words[1] in rmap[words[3]]:
					rmap[words[3]][words[1]] = rmap[words[3]][words[1]] + 3
				else:
					rmap[words[3]][words[1]] = 3
			else:
				rmap[words[3]]={words[1]:3}
			if words[1] in ppmap:
				if words[3] in ppmap[words[1]]:
					ppmap[words[1]][words[3]] = ppmap[words[1]][words[3]] + 3
				else:
					ppmap[words[1]][words[3]] = 3
			else:
					ppmap[words[1]]={words[3]:3}
#print ppmap
      
#filter
rlist=[]
plist=[]
k = 0
nodefile = open("mshhhnode2015.txt","w")
pfilteredset=Set()
id=1
for key, value in ppmap.items():
  if sum(value.values()) > 800:
		nodefile.write(str(id)+","+str(key)+","+str(pnamemap[key])+",people\n")
		id = id +1
		pfilteredset.add(key)
		k = k+1
print k

rfilteredset=Set()
for key, value in rrmap.items():
	if len(value) > 100:
		rfilteredset.add(key)

for key, value in rmap.items():
	if sum(value.values()) > 800:
		rfilteredset.add(key)

for key in rfilteredset:
	nodefile.write(str(id)+","+str(key)+","+str(rnamemap[key])+",repo\n")
	id =id +1
print len(rfilteredset)

edgefile = open("mshhhedge2015.txt","w")
# print repo->people
for rkey, value in rmap.items():
	if rkey in rfilteredset:
		for peoplekey, scoreval in value.items():
			if scoreval > 20:
				edgefile.write(str(rkey)+","+str(peoplekey)+ "\n")

edgemap={}
# print people->repo
for pkey in pfilteredset:
	if pkey in pmap:
		for rkey in pmap[pkey]:
			edgefile.write(str(pkey)+","+str(rkey)+ "\n")
			if pkey not in edgemap:
				edgemap[pkey]=set(rkey)
			else:
				edgemap[pkey].add(rkey)
	for rkey, scoreval in ppmap[pkey].items():
		if scoreval > 20 and rkey not in edgemap:
			edgefile.write(str(pkey)+","+str(rkey)+ "\n")

'''
mylist=[]
for key, value in pmap.items():
	for element in value:
		mylist.append(element)
counter=collections.Counter(mylist)


nodefile.close()
edgefile.close()
j = 0
for key, value in counter.items():
	for key, value2 in rmap.items():
		if sum(value2.values()) > 800 or value > 800:
			j = j + 1
			#print(key)
print j
'''	 

