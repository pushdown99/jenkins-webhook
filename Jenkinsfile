
pipeline {
    agent any
    parameters {
        choice(
            choices: ['greeting' , 'silence'],
            description: '',
            name: 'REQUESTED_ACTION')
    }

    stages {
        stage ('Speak') {
            steps {
                echo  params.REQUESTED_ACTION 
            }
            when {
                // Only say hello if a "greeting" is requested
                expression { params.REQUESTED_ACTION == 'greeting' }
            }
            steps {
                echo "Hello, bitwiseman!"
            }
        }
    }
}