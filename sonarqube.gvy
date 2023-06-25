pipeline {
    agent any
    parameters {
        choice(name: 'Branch', choices: ['dev','test','test_sonar'], descripton: 'choose specif branch')
    }
    stages {
        stage('code checkout') {
            steps {
                sh git 'https://github.com/sonudevops/java-hello-world-with-maven.git'
            }

        }
        stage('compile') {
            steps {
                dir('java-hello-world-with-maven') {
                      sh 'mvn clean compile'
                }
                
            }
        }
        stage( 'sonarcheck') {
            environment {
                SONAR_URL = "http://192.168.56.103:9000/"
            }
            steps {
                withSonarQubeEnv(credentialsId: 'sonartoken') {
                    sh 'mvn sonar:sonar  -Dsonar.host.url=${SONAR_URL}'
                }
            }
        }
    

    }
}