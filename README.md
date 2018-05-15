##  HvA ASV :: Assignment 3
This is an introductory exercise about Continuous Integration. You will work with the Jenkins tool, and will create jobs to compile, package and deploy applications in a staging environment.

## Prerequisites
You must have an IDE to work on this project. We recommend using the IntelliJ IDEA Community Edition. This IDE has good integration with Maven and JUnit.

[IntelliJ IDEA download page](https://www.jetbrains.com/idea/download/#section=mac)


### Preparation

During this phase you will install the required software to do the exercise:

#### 1. Maven installation

Maven will be used to manage a Java application (compilation, testing, deployment). Make sure that you have Maven installed on your local machine. If not:

* Download and extract Maven from [the Maven website](https://maven.apache.org/download.cgi)
* Add your *maven_folder/bin* to your *PATH*


#### 2. Git installation

Git will be used as our repository. Make sure that you have git installed on your local machine. If not:

* Download and extract git from [the Git WebSite](https://git-scm.com/downloads)
* Add your *git_folder/bin* to your *PATH* environment variable

#### 3. Jenkins installation

Jenkins is a CI/CI tool. Make sure that you have Jenkins installed on your local machine. If not:

* Download Jenkins from [the Jenkins WebSite](https://jenkins.io/). Select **Generic Java Package (war)** for this exercise.
* In Mac computers, Jenkins is installed in the */Applications/Jenkins* folder
* Start the Jenkins Server from the command line: 
	* *java -jar jenkins.war*
* The command will start a Jetty container. You can access Jenkins from [http://localhost:8080](localhost:8080)
* Follow the instructions to unlock Jenkins
* You don't need to install any plugin right now
* Note that the war file can also be deployed into any JEE container. But for now, let's use this simplified startup process.

#### 4. Jenkins configuration

1. Open Jenkins startup page [http://localhost:8080](http://localhost:8080)
2. Change the admin password. Go to the admin user menu *(top-right of the screen) > Configure* and change your password
2. Go to *Manage Jenkins > Manage Plugins* option
3. Install the *GitHub plugin*. This plugin integrates Jenkins with Github projects
4. Install the *Deploy to container Plugin*. This plugin allows deploying your app into a JEE server. We will use it during the remaining steps.
5. Go to *Manage Jenkins > Global Tool Configuration* and configure the location of your JDK and Maven. Do not ask Jenkins to install automatically these software units, provide your own location.
	* In Mac computers, the JDK installation resides in */Library/Java/JavaVirtualMachines/jdk[version]/Contents/Home/*

	
### Exercise Part 1 of 3

In this part, you will build a "Hello World" Jenkins Job.

1. Open the Jenkins startup page at [http://localhost:8080](http://localhost:8080)
2. Create a new job (*new item* option)
3. Specify a name and choose the **freestyle project**
4. Create a build step to execute a shell command (Unix/Linux/Mac) or batch command (Windows). 
5. Add some simple command like ```echo "Hello World"```
6. Go to the main page and schedule a build
7. See if your command executed properly in the console output

### Exercise Part 2 of 3

In this part, you create your Maven-based Jenkins project. A sample JEE project on GitHub is provided. 

1. Fork the sample project [https://github.com/marciofk/hva-asv-assignment-3.git](https://github.com/marciofk/hva-asv-assignment-3.git). Please do not use the original project, fork it and you will have your own copy to play during the exercise!
2. Open the Jenkins startup page [http://localhost:8080](http://localhost:8080)
3. Create a new job (*new item* option)
4. Specify a name and choose the **freestyle project**
5. In *Source Code management*, inform your git repository URL (no credentials needed for this GitHub project), keep using the master branch.
6. In *Build*, select to invoke a *top-level Maven target*
	* Select your maven installation
	* Specify the goal ```clean package```
7. Go to the main page and schedule a build
8. See if your command executed properly 
	* Check the console output
	* Check if your target outcomes are presented in the job workspace

Now, configure the job to start automatically if the repository changes:

1. Go to the job configuration
2. In *Build Triggers*, use the *Poll SCM strategy*
	* Use the crontab syntax (check the syntax on Google)
3. Make any change in your project and push it to the repository
4. Check if the task started automatically

### Exercise Part 3 of 3

In this step, you will deploy your application into a Tomcat Staging Server.

1. Download and install [Tomcat 8](http://tomcat.apache.org/download-80.cgi)
2. Change the configuration of Tomcat to run in a different port (e.g. 8090). Note that Jenkins is already running on your local machine at port 8080.
	* Go to the *tomcat_installation/conf/server.xml* and change the port 8080 to 8090
3. Change the configuration of *tomcat-users.xml* and add the following roles and users. For the sake of simplicity let's use the standard tomcat user and password. We are using the role manager-script to allow the deployment using HTTP.

```
  <role rolename="manager-script"/>
  <role rolename="tomcat"/>
  <user username="tomcat" password="tomcat" roles="tomcat,manager-script"/>
```
4. Start Tomcat using (In Unix/Linux/Mac computers you will need to grant execution permissions to the bin shell scripts)

1. Open the Jenkins startup page [http://localhost:8080](http://localhost:8080)
2. Modify your existing job 
	* In *Post-build actions*, create a *deploy war/ear to a container*
	* You can use "target/*.war" to specify the file
	* Leave the context path empty
	* Select "Tomcat 8 container"
	* Inform the Tomcat URL (http://localhost:8080) and credentials (use the user tomcat, password tomcat)
6. Go to the main page and schedule a build
7. Check if the build executed properly 
8. Test if the application is running

#### Optional 1

The actual solution is mixing compilation and deploy in the same job, which is not recommended. Create a pipeline composed of two jobs (compilation + deployment). 

Hints:

* The easiest way is to configure the deployment job to start right after the compilation job. See the "Build Trigger" called "Build after other projects are built". 
* You will need a plugin to copy files from one project to another.
* If you have time take a look at the "Pipeline plugin" for Jenkins

#### Optional 2

This project is using JaCoCo to generate the coverage report. The report is generated in the target folder, as could be seen in assignment 1. Your task is executing scripts to copy the HTML files to a web server. 

Hints:

* For the sake of simplicity, you can publish the HTML in your tomcat running at port 8090 in the folder webapps/root. Static files are visible from the root folder of your tomcat installation.
* You will need a plugin to run "shell scripts"


