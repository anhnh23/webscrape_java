create table wikipedia_link(
	title varchar(100),
	path varchar(100)
);

drop table wikipedia_link;

use scraperdb;
select * from wikipedia_link;
delete from wikipedia_link;