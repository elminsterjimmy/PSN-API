# PSN-API
The apis for retrieving user profiles, games and trophies information from PSN.

###How to build:
- The system is created by Java 1.7 and built by maven. If you don't have any experience about maven, please reference to <http://maven.apache.org/guides/>.
- The system depends on both [javaPlatform](https://github.com/elminsterjimmy/javaPlatform) which provides the parent poms and [javacommons](https://github.com/elminsterjimmy/javacommons) which provides common functions. Please make sure you also got these 2 projects.

###How does it work?
- It uses http client to retrieves the user profiles, game and trophies information from PSN JSON service.

###What the API provides?
- The api provides interfaces to retrieve the user profiles, user's game lists and user's game trophies. (Please check the detail in interface com.elminster.retrieve.service.IPsnApi)

###How to use?
- The system requires the login inforamtion for MS live, you may put the information into a properties file named "System.properties" and set it with keys: PSN_USERNAME and PSN_PASSWORD. Here's a sample System.properties file.  
PSN_USERNAME=\<PSN username\>  
PSN_PASSWORD=\<PSN password\>
