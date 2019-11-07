<header>
	<h1>COMIT</h1>
	<p>
		COMIT is a web application university project. The project name stands for Competitive IT Events and the application's main purpose is to create, promote and manage IT competitions among programmers ll over the world. 
		COMIT is a web service-oriented application, able to explore, filter, visualize, and update the information regarding the participants to various IT competitive events (e.g., programming or algorithm-related competitions, Web challenges, hackathons, etc.) and their submitted solutions. 
		Furthermore, the system provides  useful recommendations about alternative solutions, additional tips & tricks, similar problems and events, tutorials, on-line courses, public code repositories, interesting persons (mentors, winners, organizing entities, sponsors, and others).
		The web application is built with Java on the server-side, using Spring Boot as a framework and PostgreSql with pgAdmin4; as for the client-side, the application is build with Angular 8. 
		Moreover, the application is built upon MVC architecture.
</p>
<div>
	<h2>MVC Architecture</h2>
	<p>
	MVC stands for Model, View and Controller. MVC separates application into three components - Model, View and Controller.
	</p>
	<ul>
		<li><b>Model:</b> Model represents shape of the data and business logic. It maintains the data of the application. Model objects retrieve and store model state in a database.></li>
		<li><b>View:</b> View is a user interface. View display data using model to the user and also enables them to modify the data.</li>
		<li><b>Controller:</b> Controller handles the user request. Typically, user interact with View, which in-turn raises appropriate URL request, this request will be handled by a controller. The controller renders the appropriate view with the model data as a response.</li>
	</ul>
	<img src="https://www.tutorialsteacher.com/Content/images/mvc/request-handling-in-mvc.png" alt="MVC Architecture">
</div>
</header>

<body>
<div>
	<h2>Getting Started</h2>
	<p>
		These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 		See deployment for notes on how to deploy the project on a live system.
	</p>
</div>	
<div>
	<h3>Prerequisites</h3>
	<p>
		Before proceeding with the application, we need Angular CLI, Spring Tool Suite and pgAdmin to be installed.
	</p>
	<h4>
		A. Installing Angular CLI
	</h4>
	<p>
		Angular Command Line Interface can be installed through npm. Angular CLI is required to generate new angular project, angular components, service and so on.
Download and install Node from their official <a href="https://nodejs.org/en/download/">website</a> and run the below npm command to install angular CLI.
	</p>
	<p>
	To publish and install packages to and from the public npm registry or your company’s npm Enterprise registry, you must install Node.js and the npm command line interface using either a Node version manager or a Node installer. We strongly recommend using a Node version manager to install Node.js and npm. We do not recommend using a Node installer, since the Node installation process installs npm in a directory with local permissions and can cause permissions errors when you run npm packages globally.
	</p>
	<pre>
		<code>
		npm install npm -g
		</code>
	</pre>
	<p>To see if you already have Node.js and npm installed and check the installed version, run the following commands:</p>
	<pre>
		<code>
		node -v
		npm -v
		</code>
	</pre>
	<p>Now install Angular CLI</p>
	<pre>
		<code>
		npm install -g @angular/cli
		</code>
	</pre>
	<h4>
		B. Installing Spring Tool Suite
	</h4>
	<p>
		Spring Tool Suite can be download from Spring <a href="https://spring.io/tools">website</a>. Extract the zip and the run the .exe file to start with STS
	</p>
	<h4>
		C. Installing pgAdmin
	</h4>
	<p>
		The latest version of pgAdmin can be downloaded at on their official <a href="https://www.pgadmin.org">website</a>.
	</p>
	<h3>Installing</h3>
	<p>
	In order to run this application on your local machine, you must download a copy of this project and import it in your IDE (either Eclipse/ VS Code / IntelliJ).
	Using the source code provided in this repository, you can import the project by cloning it with the project's URL, or you can download the ZIP folder and import the unzipped project.
	</p>
	<p>
	Once you have imported the project, before running it, you must first create an empty database in pgAdmin. After creating the database, you must got to ApplicationProperties.java fiel and change the properties to match your local database, including database name, database URL and password.
	</p>
</div>	
<div>
	<h2>Built With</h2>
	<ul>
		<li><a href="https://spring.io/">Spring</a> - The web framework used</li>
		<li><a href="https://maven.apache.org/">Maven</a> - Build Tool and Dependency Management Tool</li>
	</ul>
</div>
<div>
	<h2>Authors</h2>
	<ul>
		<li><a href="https://github.com/claudiaflorea">Florea Claudia</a></li>
		<li><a href="https://github.com/andrei-h9">Humulescu Andrei</a></li>
		<li><a href="https://github.com/Solonaru">Solonaru Viorel</a></li>
	</ul>
</div>
<div>
	<h2>Acknowledgments</h2>
	<ul>
		<li>Hat tip to anyone whose code was used</li>
		<li>Inspiration</li>
		<li>etc</li>
	</ul>
</div>
</body>



