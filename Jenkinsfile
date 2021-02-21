pipeline {
    agent any
    environment {
                VAR_A = "variable_a"
            }
    stages {
        stage('Build') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "${env.STAGE_NAME}ing.."
            }
        }
        stage('Test') {
            steps {
                echo "${env.STAGE_NAME}ing.."
            }
        }
        stage('Deploy') {
            steps {
                echo "${env.STAGE_NAME}ing.."
            }
        }
    }
}
