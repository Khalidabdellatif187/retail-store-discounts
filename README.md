<h1>Objective</h1>
<p>- The application aims to provide users with discounts for products inside retail-store according to type of users and categories</p><br>
<p>- Please note that i am using <strong>java17</strong></p>
<br><br>
<h2>Running The Code</h2>
<p>1- Clone the project from the remote repository using this command <strong>git clone https://github.com/Khalidabdellatif187/retail-store-discounts.git </strong> </p>
<p>2- I'm using postgressql database so you should create database called <strong>retail_store</strong></p> <br>
<p>3- After creating database,make sue that database have <strong>public</strong> schema</p>
<p>4- Run the code and make sure all sql scripts running successfully and migration happens successfully</p>
<p>5- Please note that i am using <strong>liquibase</strong> migration tool to run sql scripts</p><br><br>
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
<p>4- After send the request it will return two fields.<strong>totalAmount</strong> whic indicates totalAmount before any discounts <br> and <strong>netAmount</strong>
indicates netAmount after discounts
</p>
