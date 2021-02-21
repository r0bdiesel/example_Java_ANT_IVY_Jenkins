//variable
def antVersion = 'Ant1.10.9'

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
                
               
                withEnv( ["ANT_HOME=${tool antVersion}"] ) {
                    bat '$ANT_HOME/bin/ant.bat main'
                }
              
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
