Tasks:
1. Connect to use properties
2. Read person data from webservice and database
3. Merge data via id
4. Export as csv file

Environment
- Start database/webservice docker:
C:\Docker
docker-compose -f .\test-day.yml up

- Test database access over pgAdmin:
http://localhost/browser/#
- Test webservice access:
http://localhost:8080/persons