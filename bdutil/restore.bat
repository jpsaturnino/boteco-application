set PGPASSWORD=postgres123
cd bdutil
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "botecodbteste" --verbose --no-password "bkp.sql"