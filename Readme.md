Lab How to Create a Table:

1. Start the postgres service to the application locally.
   In a Terminal run following commands

   sudo /etc/init.d/postgresql-9.3 start

2. Go to predix/predix-workshop/Lab3
   cd predix-workshop
   cd Lab3

3. Build application:
   mvn install

   This would create a jar file: in the target folder target/Lab3-0.0.5-SNAPSHOT.jar

4. Go To Eclipse-STS icon on desktop :
   File->Import->Maven->Existing Maven Projects

5. Click ->Browse
   Choose file /predix/predix-workshop/Lab3 -> press OK :: This would create a project called Lab3, should be visible in explorer

6. Select pom.xml checkbox -> click next -> click finish

7. Open the /Lab3/src/manifest.yml and change the name of application to digitalyst-participants-{sso id}
   change the host name also to digitalyst-participants-{sso id}
   In the services section, edit the name of the postgres service which should be same as it was created during the Lab 1
   Caution: Please don't change structure or formatting of yml file. 

8. Add a JPA entity 
   In STS Go to Lab3/src/com/com/ge/predix/digitalyst/entity directory
   Look at the DigitalystModel.java << edit the table digitalyst_participants_{ssoid} >>
   caution: Please don't use "-" in table name 
   

9. Add a JPA Repository
    In STS Go to Lab3/src/com/com/ge/predix/digitalyst/repository
    Look at DigitalystModelRep.java  << No changes required >>

10. Check the Lab3/src/main/resources/application.properties, set the folllowing data base address: This would be used for testing with 
localhost database:
    
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    spring.datasource.driver-class-name=org.postgresql.Driver



11. In STS open the following file: Lab3\src\com\ge\predix\digitalyst\controller\DigitalystController.java
   Look at the  method saveDigitalystModel(), note the method parameters
   Look at the class com\ge\predix\digitalystservice\DigitalystService.java
   Understand the flow: browser->Rest Controller->Service-> Save Entity


12. Build it localy with localhost: In STS click,  Lab3->Run As-> Maven Install


12. Run it localy with localhost: In STS click,  Lab3->Run As-> Spring Boot App

13. Test it localy

Save Participants
   Open firefox: Click on REST client brown button on right corner of the browser.
   Chose Method : POST and URL: http://localhost:8080/saveparticipants
   Add following in headers:
   Name: participantName  Value:xxxx
   Name: participantEmail Value:xxxx
   Name: participantOrg   Value:xxxx  

   Click on send. You should get a 200 response

14. Get Participants

.Open firefox: Click on REST client brown button on right corner of the browser.
   Chose Method : GET and URL: http://localhost:8080/getparticipants
   You should get the participant you added in the previous step.

  
15: Test it on cloud:

  Go to Terminal window:
  Go to predix/predix-workshop/Lab3
  cf push 


14. Test on Cloud:
   cf apps
   Look for your application digitalyst-participant-{ssoid} that you  pushed in previous lab:   
   copy urls: digitalyst-participant-2.run.aws-usw02-pr.ice.predix.io

15. Open firefox: Click on REST client brown button on right corner of the browser.
   Chose Method : GET and URL: https://digitalyst-participant-302018112.run.aws-usw02-pr.ice.predix.io/getparticipants
   if there is no participant added you would get an empty list.

16.Save Participants
   Open firefox: Click on REST client brown button on right corner of the browser.
   Chose Method : POST and URL: https://digitalyst-participant-xxx.run.aws-usw02-pr.ice.predix.io/saveparticipants
   Add following in headers:
   Name: participantName  Value:xxxx
   Name: participantEmail Value:xxxx
   Name: participantOrg   Value:xxxx  

   Click on send. You should get a 200 response



17.Open firefox: Click on REST client brown button on right corner of the browser.
   Chose Method : GET and URL: https://digitalyst-participant-xxxx.run.aws-usw02-pr.ice.predix.io/getparticipant
   You should get the participant you added in the previous step.





