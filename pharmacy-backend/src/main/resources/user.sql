-- Role: "userGrp3"
-- DROP ROLE "userGrp3";

CREATE ROLE "userGrp3" WITH
  LOGIN
  SUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'password';