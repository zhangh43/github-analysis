# Github Analysis

This repo is focus on analysis 

## Download
We use two scripts to download github history data from [Github Archive](https://www.gharchive.org/)

```shell
# Take year 2016 as example
mkdir 2016
python generate_url.py 2016
python download_url.py url2016 2016 120
```

## Preprocessing 
We preprocess github history data by the following steps:

1. extracting [event, user, repo] pairs from original data.

```shell
python preprocess.py
```

2. filter important users and repos.

3. find the strong relationship between user and repos.


