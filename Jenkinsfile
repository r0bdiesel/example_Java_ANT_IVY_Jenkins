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
                antBuild(antVersion)
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



void antBuild(String antVersion) {
     if (isUnix()) {
        echo "Building UNIX"
        withEnv( ["ANT_HOME=${tool antVersion}"] ) {
            sh '"$ANT_HOME/bin/ant" main'
        }
    }
    else {
        echo "Building Windows"
        withEnv( ["ANT_HOME=${tool antVersion}"] ) {
            bat '"%ANT_HOME%/bin/ant.bat" main'
        }
    }
}
