pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'set "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot" && set "PATH=%JAVA_HOME%\\bin;%PATH%" && mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'set "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot" && set "PATH=%JAVA_HOME%\\bin;%PATH%" && mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'set "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot" && set "PATH=%JAVA_HOME%\\bin;%PATH%" && mvn package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying DocApp...'
                bat 'if not exist deploy mkdir deploy'
                bat 'copy /Y target\\DocApp-0.0.1-SNAPSHOT.jar deploy\\DocApp.jar'
                echo 'DocApp deployment completed successfully.'
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'CI/CD Pipeline failed!'
        }
    }
}