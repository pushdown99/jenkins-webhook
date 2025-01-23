pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }
    environment {
        SOURCECODE_JENKINS_CREDENTIAL_ID = 'github-jenkis'
        SOURCE_CODE_URL = 'https://github.com/pushdown99/jenkins-webhook.git'
        RELEASE_BRANCH = 'main'
    }
    stages {
        stage('Init') {
            steps {
                echo 'clear'
                sh 'docker stop $(docker ps -aq)'
                sh 'docker rm $(docker ps -aq)'
                deleteDir()
            }
        }

        stage('clone') {
            steps {
                git url: "$SOURCE_CODE_URL",
                    branch: "$RELEASE_BRANCH",
                    credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
                sh "ls -al"
            }
        }

        stage('frontend dockerizing') {
            steps {
                sh "docker build -t todo/frontend ./frontend"
            }
        }

        stage('backend dockerizing') {
            steps {
                sh "pwd"
                dir("./backend"){
                    sh "pwd"

                    sh "gradle clean"
                    sh "gradle bootJar"

                    sh "docker build -t todo/backend ."
                }
            }
        }

        stage('deploy') {
            steps {
                sh '''
                  docker run -d -p 5000:5000 todo/frontend

                  docker run -d -p 8080:8080 todo/backend
                '''
            }
        }
    }
}