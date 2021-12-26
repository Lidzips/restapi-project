CREATE TABLE "symbol"(
	"id" SERIAL PRIMARY KEY,
	"code" TEXT DEFAULT '' NOT NULL
);

CREATE TABLE "alphabet"(
	"id" SERIAL PRIMARY KEY,
	"name" TEXT DEFAULT '' NOT NULL,
	"symbol_id" INT REFERENCES "symbol"("id")
);

CREATE TABLE "symbol_set"(
	"id" SERIAL PRIMARY KEY,
	"symbol_id_1" INT REFERENCES "symbol"("id"),
	"symbol_id_2" INT REFERENCES "symbol"("id"),
	"symbol_id_3" INT REFERENCES "symbol"("id")
);

CREATE TABLE "rule"(
	"id" SERIAL PRIMARY KEY,
	"in_ssid" INT REFERENCES "symbol_set"("id"),
	"out_ssid" INT REFERENCES "symbol_set"("id")
);

CREATE TABLE "rule_set"(
	"id" SERIAL PRIMARY KEY,
	"alphabet_from_id" INT REFERENCES "alphabet"("id"),
	"alphabet_to_id" INT REFERENCES "alphabet"("id"),
	"rule_id" INT REFERENCES "rule"("id")
);