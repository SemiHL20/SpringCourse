In order to test the functionality, you should create a free account at https://mailtrap.io/es/ 
then in your inbox, go to "integrations" where you should see your username and pass, 
copy both into the "application.properties" file.

In addition to that, if the app crashes due to certificate failure, 
you could add the following lines in the "application.properties" file:
spring.mail.properties.mail.smtp.ssl.checkserveridentity=false
spring.mail.properties.mail.smtp.ssl.trust=sandbox.smtp.mailtrap.io
