drop table if exists vertex2015;
create table vertex2015(index int, id int, name text, type text);
copy vertex2015 from '/home/gpadmin/mshhhnode2015.txt' delimiter ',';
drop table if exists edge2015;
create table edge2015(src int, dest int);
copy edge2015 from '/home/gpadmin/mshhhedge2015.txt' delimiter ',';

drop table if exists vertex2016;
create table vertex2016(index int, id int, name text, type text);
copy vertex2016 from '/home/gpadmin/mshhhnode2016.txt' delimiter ',';
drop table if exists edge2016;
create table edge2016(src int, dest int);
copy edge2016 from '/home/gpadmin/mshhhedge2016.txt' delimiter ',';

drop table if exists vertex2017;
create table vertex2017(index int, id int, name text, type text);
copy vertex2017 from '/home/gpadmin/mshhhnode2017.txt' delimiter ',';
drop table if exists edge2017;
create table edge2017(src int, dest int);
copy edge2017 from '/home/gpadmin/mshhhedge2017.txt' delimiter ',';

drop table if exists vertex2018;
create table vertex2018(index int, id int, name text, type text);
copy vertex2018 from '/home/gpadmin/mshhhnode2018.txt' delimiter ',';
drop table if exists edge2018;
create table edge2018(src int, dest int);
copy edge2018 from '/home/gpadmin/mshhhedge2018.txt' delimiter ',';


select 2015.1;
drop table if exists pagerank_out;
drop table if exists pagerank_out_summary;
select madlib.pagerank('vertex2015','id','edge2015','src=src,dest=dest','pagerank_out', 0.85, NULL,NULL,NULL, '{"17165658","2183193","843222"}');
select * from pagerank_out, vertex2015 where pagerank_out.id = vertex2015.id  order by pagerank DESC limit 100;

select 2015.2;
drop table if exists pagerank_out2;
drop table if exists pagerank_out2_summary;
select madlib.pagerank('vertex2015','id','edge2015','src=src,dest=dest','pagerank_out2', 0.85, NULL,NULL,NULL, '{"16563587","11225014"}');
select * from pagerank_out2, vertex2015 where pagerank_out2.id = vertex2015.id  order by pagerank DESC limit 100;

select 2015.3;
drop table if exists pagerank_out3;
drop table if exists pagerank_out3_summary;
select madlib.pagerank('vertex2015','id','edge2015','src=src,dest=dest','pagerank_out3', 0.85, NULL,NULL,NULL, '{"3631697","22442668","3910314"}');
select * from pagerank_out3, vertex2015 where pagerank_out3.id = vertex2015.id  order by pagerank DESC limit 100;

select 2016.1;
drop table if exists pagerank_out4;
drop table if exists pagerank_out4_summary;
select madlib.pagerank('vertex2016','id','edge2016','src=src,dest=dest','pagerank_out4', 0.85, NULL,NULL,NULL, '{"17165658","2183193","843222"}');
select * from pagerank_out4, vertex2016 where pagerank_out4.id = vertex2016.id  order by pagerank DESC limit 100;

select 2016.2;
drop table if exists pagerank_out5;
drop table if exists pagerank_out5_summary;
select madlib.pagerank('vertex2016,'id','edge2016','src=src,dest=dest','pagerank_out5', 0.85, NULL,NULL,NULL,NULL,'{"16563587","11225014"}');
select * from pagerank_out5, vertex2016 where pagerank_out5.id = vertex2016.id  order by pagerank DESC limit 100;


select 2016.3;
drop table if exists pagerank_out6;
drop table if exists pagerank_out6_summary;
select madlib.pagerank('vertex2016','id','edge2016','src=src,dest=dest','pagerank_out6', 0.85, NULL,NULL,NULL,NULL,'{"3631697","22442668","3910314"}');
select * from pagerank_out6, vertex2016 where pagerank_out6.id = vertex2016.id  order by pagerank DESC limit 100;

select 2017.1;
drop table if exists pagerank_out7;
drop table if exists pagerank_out7_summary;
select madlib.pagerank('vertex2017','id','edge2017','src=src,dest=dest','pagerank_out7', 0.85, NULL,NULL,NULL, '{"17165658","2183193","843222"}');
select * from pagerank_out7, vertex2017 where pagerank_out7.id = vertex2017.id  order by pagerank DESC limit 100;

select 2017.2;
drop table if exists pagerank_out8;
drop table if exists pagerank_out8_summary;
select madlib.pagerank('vertex2017','id','edge2017','src=src,dest=dest','pagerank_out8', 0.85, NULL,NULL,NULL,NULL,'{"16563587","11225014"}');
select * from pagerank_out8, vertex2017 where pagerank_out8.id = vertex2017.id  order by pagerank DESC limit 100;

select 2017.3;
drop table if exists pagerank_out9;
drop table if exists pagerank_out9_summary;
select madlib.pagerank('vertex2017','id','edge2017','src=src,dest=dest','pagerank_out9', 0.85, NULL,NULL,NULL,NULL,'{"3631697","22442668","3910314"}');
select * from pagerank_out9, vertex2017 where pagerank_out9.id = vertex2017.id  order by pagerank DESC limit 100;


select 2018.1;
drop table if exists pagerank_out10;
drop table if exists pagerank_out10_summary;
select madlib.pagerank('vertex2018','id','edge2018','src=src,dest=dest','pagerank_out10', 0.85, NULL,NULL,NULL, '{"17165658","2183193","843222"}');
select * from pagerank_out10, vertex2018 where pagerank_out10.id = vertex2018.id  order by pagerank DESC limit 100;

select 2018.2;
drop table if exists pagerank_out11;
drop table if exists pagerank_out11_summary;
select madlib.pagerank('vertex2018,'id','edge2018','src=src,dest=dest','pagerank_out11', 0.85, NULL,NULL,NULL,NULL,'{"16563587","11225014"}');
select * from pagerank_out11, vertex2018 where pagerank_out11.id = vertex2018.id  order by pagerank DESC limit 100;

select 2018.3;
drop table if exists pagerank_out12;
drop table if exists pagerank_out12_summary;
select madlib.pagerank('vertex2018','id','edge2018','src=src,dest=dest','pagerank_out12', 0.85, NULL,NULL,NULL,NULL,'{"3631697","22442668","3910314"}');
select * from pagerank_out12, vertex2018 where pagerank_out12.id = vertex2018.id  order by pagerank DESC limit 100;











