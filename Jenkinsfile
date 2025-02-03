
pipeline {
    agent any
    parameters {
        choice (
            choices: [ 'build', 'deploy' ],
            description: '',
            name: 'REQUESTED_ACTION'
        )
    }

    stages {
        stage ('Build') {
            when {
                expression { params.REQUESTED_ACTION == 'build' }
            }
            steps {
                echo "Build, process!"
            }
        }
        stage ('Deploy') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                echo "Deploy, process!"
            }
        }
    }
}