DROP TABLE IF EXISTS route;
CREATE TABLE route
(
	id bigserial NOT NULL,
	short_name character varying(20),
	long_name character varying(250),
	agency_id bigint,
	route_type smallint,
	descr character varying(1000),
	url character varying(1000),
	active boolean,
	CONSTRAINT route_pk PRIMARY KEY (id),
	CONSTRAINT route_long_name_unique UNIQUE (long_name)
)
WITH (
OIDS=FALSE
);
ALTER TABLE route
OWNER TO postgres;

DROP TABLE IF EXISTS agency;
CREATE TABLE agency
(
	id bigserial NOT NULL,
	agency_name character varying(250),
	url character varying(1000),
	agency_timezone character varying(2),
	CONSTRAINT agency_pk PRIMARY KEY (id),
	CONSTRAINT agency_name_unique UNIQUE (agency_name)
)
WITH (
OIDS=FALSE
);
ALTER TABLE agency
OWNER TO postgres;

DROP TABLE IF EXISTS stop;
CREATE TABLE stop
(
	id bigserial NOT NULL,
	osm_id bigint,
	code character varying(15),
	stop_name character varying(500) NOT NULL,
	descr character varying(1000),
	latitude real,
	longitude real,
	type smallint,
	CONSTRAINT stop_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE stop
OWNER TO postgres;