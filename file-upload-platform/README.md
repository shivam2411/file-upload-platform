- Features

This is a file upload platform which stores user files on AWS S3 buckets

User authentication
File upload
File removal
Meta data update for file uploaded
Get public URL for the file uploaded to share with world
Only backend is developed


Steps to start the server:

Start Mongo DB server on your machine
Add your AWS S3 credentials or ask the owner of this service for credentials (TODO - encryption of credentials)
Run the Spring boot app (server starts on default port 8080)

Steps to use the service:

Login in browser and get your JSESSIONID (cookie) to hit via postman
Set cookie in postman
Hit endpoints for file upload, removal, metadata update etc

Endpoints explanations:

1.http://localhost:8080/users (Create new user)

2. http://localhost:8080/aws/upload (Upload file to AWS. It returns a file id )

3. http://localhost:8080/update/{fileId} (Update meta data for the file uploaded based on file id)

4. http://localhost:8080/aws/remove/{fileId} (Remove the uplaoded file)

5. http://localhost:8080/uploaded (View the list of uploaded files)

6. (TODO)http://localhost:8080/public-url/{fileId} (Gives a public URL for the file uploaded to share with world)