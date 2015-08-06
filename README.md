# PSN-API
The apis for retrieving user profiles, games and trophies information from PSN.
A RESTful service is also provided as a distribution, all functions can be used from the service.

##How does it work?
- It uses http client to retrieves the user profiles, game and trophies information from PSN JSON service.

##What the API provides?
- The api provides interfaces to retrieve the user profiles, user's game lists and user's game trophies. (Please check the detail in interface com.elminster.retrieve.service.IPsnApi)

##User Guide
- ####For Java Develpers:####
    - How to build:
        - The system is created by Java 1.7 and built by maven. If you don't have any experience about maven, please reference to <http://maven.apache.org/guides/>.
        - The system depends on following 3 artifacts, please make sure you also got them.
            - [javaPlatform](https://github.com/elminsterjimmy/javaPlatform) which provides the parent poms
            - [javacommons](https://github.com/elminsterjimmy/javacommons) which provides commons
            - [webRetriever](https://github.com/elminsterjimmy/WebRetriever) which is a web retriever implementation based on Apache httpclient.

    - How to use
        - The system requires the login inforamtion for PSN, you may put the information into a properties file named "system.properties" and set it with keys: PSN_USERNAME and PSN_PASSWORD. Here's a sample System.properties file.  
PSN_USERNAME=\<PSN username\>  
PSN_PASSWORD=\<PSN password\>

- ####For End-Users
    - Get yourself a JRE 1.7 envrionment ([here](http://www.oracle.com/technetwork/cn/java/javase/downloads/jre7-downloads-1880261.html))
    - Download the RESUful service [distribution] (https://github.com/elminsterjimmy/PSN-API/tree/master/distribution)
    - Unpack it in your computer
    - Set the login information for PSN
        - Open "system.properties" file and fill the PSN username and password and save the file
    - Start the RESTful service via "start.bat" (Win) or "start.sh" (*nux)
    - The RESTful service will be available at http://localhost:8090
    - Call the service to get the inforamtion
        - service list

            | Service endpoint         | Second Header |    
            | ------------------------ | ------------- |    
            | /userProfile/{username}  | get user profile for specified user  |    
            | /userGameList/{username} | get user's game list for specified user  |    
            | /userGameTrophyList/{username}/{gameId} | get user's game trophies for specified user and game  |    
    - Have fun!
