

# Build the project
mvn clean install

# Run static code analysis
mvn spotbugs:check

# Run unit tests and generate code coverage report
mvn clean test
