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
	CONSTRAINT route_pk PRIMARY KEY (id)
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

DROP TABLE IF EXISTS trip;
CREATE TABLE trip
(
	id bigserial NOT NULL,
	route_id bigint references route(id),
	service_id bigint references calendar(id),
	head_sign character varying(50),
	short_name character varying(50),
	shape_id bigint,
	trip_time integer,
	start_time character varying(8),
	CONSTRAINT trip_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE trip
OWNER TO postgres;

DROP TABLE IF EXISTS calendar;
CREATE TABLE calendar
(
	id bigserial NOT NULL,
	monday boolean NOT NULL,
	tuesday boolean NOT NULL,
	wednesday boolean NOT NULL,
	thursday boolean NOT NULL,
	friday boolean NOT NULL,
	saturday boolean NOT NULL,
	sunday boolean NOT NULL,
	calendar_name character varying (20),
	start_date character varying(8) NOT NULL,
  	end_date character varying(8) NOT NULL,
	CONSTRAINT calendar_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE calendar
OWNER TO postgres;