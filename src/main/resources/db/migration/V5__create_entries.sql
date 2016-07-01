drop table if exists digitalyst_participants;
create table digitalyst_participants
(
	id SERIAL,
	participant_name character varying(64) not null,
	participant_email character varying(64) not null,
	participant_org character varying(128) not null,
	primary key(id),
	unique (participant_email)
)