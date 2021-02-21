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
                antBuild()
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



void antBuild() {
     if (isUnix()) {
        def uname = sh script: 'uname', returnStdout: true
        if (uname.startsWith("Darwin")) {
            return "Macos"
        }
        // Optionally add 'else if' for other Unix OS  
        else {
            withEnv( ["ANT_HOME=${tool antVersion}"] ) {
                sh '$ANT_HOME/bin/ant target1 target2'
            } 
            return "Linux"
        }
    }
    else {
        withEnv( ["ANT_HOME=${tool antVersion}"] ) {
            bat '%ANT_HOME%/bin/ant.bat target1 target2'
        }
        return "Windows"
    }
}
