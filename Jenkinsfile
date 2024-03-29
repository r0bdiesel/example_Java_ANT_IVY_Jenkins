pipeline {
    environment {
                VAR_A = "variable_a"
	        ANT_VERSION = 'Ant1.10.9'
		IN_DOCKER_ENV = fileExists('/.dockerenv')
	        REGISTRY = "r0bdiesel/example_java_ant_ivy_jenkins"
      		DOCKERHUB_CREDENTIALS = credentials('dockerhub')
	        dockerImage = ''
            }
    agent any
    options {
    	buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
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
		sh 'docker build -t r0bdiesel/example_java_ant_ivy_jenkins:latest .'
            }
        }
	stage('DockerHub Login') {
            steps {
                echo "${env.STAGE_NAME} Stage"
		sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
	stage('Push Image to DockerHub') {
      	    steps{
	       echo "${REGISTRY}"
	       sh 'docker push r0bdiesel/example_java_ant_ivy_jenkins:latest'
             }
         }
	 stage('DockerHub Logout') {
  	     steps{
		sh 'docker logout'
		sh 'docker image prune -a'
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



