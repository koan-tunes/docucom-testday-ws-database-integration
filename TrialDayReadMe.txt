Tasks:
1. Connect to use proeprties
1. Read person data from webservice and database
2. Merge data via id
3. Export as csv file

Environment
- Start database/webservice docker:
C:\Docker
docker-compose -f .\test-day.yml up

- Test database access over pgAdmin:
http://localhost/browser/#
- Test webservice access:
http://localhost:8080/persons