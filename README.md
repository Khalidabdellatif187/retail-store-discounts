<h1>Objective</h1>
<p>- The application aims to provide users with discounts for products inside retail-store according to type of users and categories</p><br>
<p>- Please note that i am using <strong>java17</strong></p>
<br><br>
<h2>Running The Code</h2>
<p>1- Clone the project from the remote repository using this command <strong>git clone https://github.com/Khalidabdellatif187/retail-store-discounts.git </strong> </p><br>
<p>2- After cloning,checkout branch <strong>development</strong> using  this command <strong>git checkout development</strong>.<br>After that build the maven project using this <strong>mvn clean install</strong></p>
<p>3- I'm using postgressql database so you should create database called <strong>retail_store</strong></p> <br>
<p>4- After creating database,make sue that database have <strong>public</strong> schema</p>
<p>5- Run the code and make sure all sql scripts running successfully and migration happens successfully</p>
<p>6- Please note that i am using <strong>liquibase</strong> migration tool to run sql scripts</p><br><br>
<h2>Test The Code</h2>
<p>1- After Running the application you can use a tool for API testing like <strong>postman</strong></p> <br>
<p>2- Open postman and send this http url <strong>http://localhost:8070/bill</strong> with <strong>POST</strong> request</p> <br>
<p>3- Send the request this Json Body <strong>
{
    "user": {
        "id": 2
    },
    "products": [
        {
            "id": 4
        },
         {
            "id": 1
        }
    ]
}
</strong></p><br>
<p>4- After send the request it will return two fields.<strong>totalAmount</strong> which indicates totalAmount before any discounts <br> and <strong>netAmount</strong>
indicates netAmount after discounts
</p><br>
<h2>Uml-Diagram</h2>
<p>Please note that uml-diagram inside the project with file name called <strong>UmlDiagram.PNG</strong></p>
<br>
<h2>Generate Test Coverage Report</h2>
<p>1- To extract test coverage report,you should run this <strong>mvn clean test</strong></p><br>
<p>2- After this you will see a generation folder called <strong>site</strong> in the <strong>target</strong> folder</p><br>
<p>3- Inside the <strong>target</strong> folder,you will see a folder called <strong>jacoco</strong></p><br>
<p>4- Inside <strong>Jacoco</strong> folder,there is a file called <strong>index.html</strong> which contain coverage report</p>
<p>5- You can see coverage report by openeing your web browser and write this URL <strong>http://localhost:63342/retail-store-discounts/target/site/jacoco/index.html</strong></p>

<h2>Build Script</h2>
<p>There is a file called <strong>build</strong> in the root directory has commands of build the project,run unit testing and run code analysis</p>
