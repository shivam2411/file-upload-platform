## Features

- This is a file upload platform which stores user files on AWS S3 buckets and provide following functionalities:
- User authentication
- File upload
- File removal
- Meta data update for file uploaded
- Get public URL for the file uploaded to share with world


## Steps to start the server:

- Start Mongo DB server on your machine
- Add your AWS S3 credentials or ask the owner of this service for credentials (TODO - encryption of credentials)
- Run the Spring boot app (server starts on default port 8080)

## Steps to use the service:

Go to local host URL - http://localhost:8080/welcome

## Endpoints explanations:

- http://localhost:8080/users (Create new user)

- http://localhost:8080/upload (Upload file to AWS. It returns a file id )

- http://localhost:8080/update (Update meta data for the file uploaded based on file id)

- http://localhost:8080/remove (Remove the uplaoded file)

- http://localhost:8080/uploaded (View the list of uploaded files)

- (TODO)http://localhost:8080/public-url (Gives a public URL for the file uploaded to share with world)