pipeline {
    environment {
                VAR_A = "variable_a"
	        ANT_VERSION = 'Ant1.10.9'
		IN_DOCKER_ENV = fileExists('/.dockerenv')
	        REGISTRY = "r0bdiesel/example_java_ant_ivy_jenkins"
      		REGISTRY_CREDENTIAL = credentials('dockerhub')
	        dockerImage = ''
            }
    agent any
    stages {
        stage('Log Ant, Git, and Java version info') {
            steps {
		echo "Starting Pipeline Steps"
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
                doAnt(ANT_VERSION, "runTests")
            }
        }
        stage('Deploy') {
            steps {
                echo "${env.STAGE_NAME}ing.."
		doAnt(ANT_VERSION, "run")
            }
        }
	stage('Docker Build') {
            steps {
                echo "${env.STAGE_NAME} Stage"
		script {
          		dockerImage = docker.build REGISTRY + ":$BUILD_NUMBER"
        	}
            }
        }
	stage('Deploy Image') {
      	    steps{
	       echo "${REGISTRY}"
               script {
		     docker.withRegistry( '', REGISTRY_CREDENTIAL) {
                     dockerImage.push()
                     }
                }
             }
         }
	 stage('Remove Unused docker image') {
  	     steps{
    		sh "docker rmi $registry:$BUILD_NUMBER"
  	     }
	}
    }
}

void antTargetsUnixDocker(String antVersion, String Targets){
    echo "executing ant on Targets:${Targets}"
    withEnv( ["TARGETS=${Targets}"] ) {
            sh '"ant" $TARGETS'
        }
}

void antTargetsUnix(String antVersion, String Targets){
    echo "executing ant version:${antVersion} on Targets ${Targets}"
    withEnv( ["ANT_HOME=${tool antVersion}","TARGETS=${Targets}"] ) {
            sh '"$ANT_HOME/bin/ant" $TARGETS'
        }
}

void antTargetsWindows(String antVersion, String Targets){
   echo "executing ant version:${antVersion} on Targets ${Targets}"
   withEnv( ["ANT_HOME=${tool antVersion}","TARGETS=${Targets}"] ) {
            bat '"%ANT_HOME%/bin/ant.bat" %TARGETS%'
        }
}

void antEchoVersions() {
    if (isUnix()) {
        echo "UNIX"
        sh 'ant -version'
	sh 'java -version'
	sh 'git --version'
	echo "inDockerEnv:${IN_DOCKER_ENV}"
    }
    else {
        echo "WINDOWS"
    }
}

void doAnt(String antVersion, String Targets) {
     if (isUnix()) {
        echo "UNIX"
	if(IN_DOCKER_ENV) {
            antTargetsUnixDocker(antVersion, Targets)
	} else {
	    antTargetsUnix(antVersion, Targets)
	}
     } else {
        echo "WINDOWS"
        antTargetsWindows(antVersion, Targets)
    }
}



