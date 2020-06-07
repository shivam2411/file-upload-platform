## Features

- This is a file upload platform which stores user files on AWS S3 buckets and provide following functionalities:
- User authentication
- File upload
- File removal
- Meta data update for file uploaded
- Get public URL for the file uploaded to share with world
- Only backend is developed


## Steps to start the server:

- Start Mongo DB server on your machine
- Add your AWS S3 credentials or ask the owner of this service for credentials (TODO - encryption of credentials)
- Run the Spring boot app (server starts on default port 8080)

## Steps to use the service:

- Login in browser and get your JSESSIONID (cookie) to hit via postman
- Set cookie in postman
- Hit endpoints for file upload, removal, metadata update etc

## Endpoints explanations:

- http://localhost:8080/users (Create new user)

- http://localhost:8080/aws/upload (Upload file to AWS. It returns a file id )

- http://localhost:8080/update/{fileId} (Update meta data for the file uploaded based on file id)

- http://localhost:8080/aws/remove/{fileId} (Remove the uplaoded file)

- http://localhost:8080/uploaded (View the list of uploaded files)

- (TODO)http://localhost:8080/public-url/{fileId} (Gives a public URL for the file uploaded to share with world)