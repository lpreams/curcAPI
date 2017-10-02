# curcAPI

How to get started:

1. Install Eclipse (recommended to use latest Eclipse Oxygen release)
    - Also check for updates, Help > Check for Updates

2. Help > Install New Software > http://download.eclipse.org/releases/oxygen (oxygen=release)
  - Under *Web, XML, Java EE Developmentand OSGi Enterprise Development*, select 
    - Eclipse Java EE Developer Tools
    - Eclipse Java Web Developer Tools
    - Eclipse Web Developer Tools
    - JST Server Adapters
    - JST Server Adapters Extensions
  
3. Help > Eclipse Marketplace
    - Search for buildship
    - Install/Update Buildship Gradle Integration 2.0

4. File > Import > Git > Projects from Git > Next
    - Clone URI > Next
    - Set URI: to https://github.com/lpreams/curcAPI
    - (Optional) add your github user/password
    - Next > Next
    - Change destination directory if desired > Next > Next > Finish
  
5. Wait for gradle to sync/build/etc

6. Window > Show View > Other > Gradle > Gradle Tasks

7. Gradle Tasks > curcAPI > ide > eclipseWtp (double click)

8. Download Apache Tomcat 8.5(.23) from http://supergsego.com/apache/tomcat/tomcat-8/v8.5.23/bin/apache-tomcat-8.5.23.zip
    - Unzip this somewhere (I usually use the Eclipse workspace (NOT THE PROJECT FOLDER)). 

9. Window (Eclipse on macOS) > Preferences > Server > Runtime Environments > Add
    - Apache > Apache Tomcat v8.5 > Create a new local server > Next
    - Browse > browse to wherever you unzipped Tomcat from step 8 > Open
    - Finish > Apply and Close

10. Right click on project in Package Explorer > Run As > Run on Server
    - (Optional) Check Always use this server when running this project
    - Finish

The API is now running. It can be accessed at http://localhost:8080/curcAPI/api/*. Note that most api calls will fail without a db running (which is not currently covered by this document). 
