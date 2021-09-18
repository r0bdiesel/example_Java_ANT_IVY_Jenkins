//variable
def ANT_VERSION = 'Ant1.10.9'

pipeline {
    agent any
    environment {
                VAR_A = "variable_a"
            }
    stages {
        stage('Log Ant, Git, and Java version info') {
            steps {
                antEchoVersions()
            }
        }
        stage('Build') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "${env.STAGE_NAME}ing.."
                doAnt(ANT_VERSION, "initIvy resolveIvyDependencies main")
            }
        }
        stage('Test') {
            steps {
                echo "${env.STAGE_NAME}ing.."
                doAnt(ANT_VERSION, "run")
                
                doAnt(ANT_VERSION, "runTests")
            }
        }
        stage('Deploy') {
            steps {
                echo "${env.STAGE_NAME}ing.."
            }
        }
    }
}

void antTargetsUnix(String antVersion, String Targets){
    echo "Ant -v ${antVersion} Targets ${Targets}"
    withEnv( ["ANT_HOME=${tool antVersion}","TARGETS=${Targets}"] ) {
            sh '"$ANT_HOME/bin/ant" $TARGETS'
        }
}

void antTargetsWindows(String antVersion, String Targets){
   echo "Ant -v ${antVersion} Targets ${Targets}"
   withEnv( ["ANT_HOME=${tool antVersion}","TARGETS=${Targets}"] ) {
            bat '"%ANT_HOME%/bin/ant.bat" %TARGETS%'
        }
}

void antEchoVersions() {
    echo "antEchoVersions"
    if (isUnix()) {
        echo "UNIX"
        sh 'ant -version'
	    sh 'java -version'
	    sh 'git --version'
    }
    else {
        echo "WINDOWS"
    }
}

void doAnt(String antVersion, String Targets) {
     if (isUnix()) {
        echo "UNIX"
        antTargetsUnix(antVersion, Targets)
    }
    else {
        echo "WINDOWS"
        antTargetsWindows(antVersion, Targets)
    }
}



