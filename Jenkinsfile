pipeline {
  agent any
  
  environment{
  		DOCKER_IMG_NAME= 'user-service'
  		DOCKER_TMP_CONTAINER_NAME = 'tmp-user-service-container'
  		DOCKER_REPO = 'manishacc'
  }
  stages {
   stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        
        stage('compile') {
            steps {
            	echo 'compiling source files...'
                sh 'mvn compile'
            }
        }
        
        
        
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('package') {
            steps {
                sh 'mvn package -DskipTests=true'
            }
        }
        stage('dockersize') {
            steps {
            	echo 'building the docker image for user-service...'
                sh "docker build -t ${DOCKER_IMG_NAME}:latest -t ${DOCKER_IMG_NAME}:${env.BUILD_ID} ."
            }
        }
        
        
        /*stage('integration tests'){
        steps{
        	echo 'running the temp-user-service for integration test'
        	sh "docker run -dp 7070:8080 --rm --name ${DOCKER_TMP_CONTAINER_NAME} ${DOCKER_IMG_NAME}:latest"
        	sleep 30
        	sh 'curl -i http://localhost:7070/api/users'
          }
        }*/
        
        stage('docker publish'){
        steps{
        	withDockerRegistry([credentialsId: 'docker_creds', url:'']){
        	sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
        	sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest"
        	}
   	 	  }
   	 	}
   }
    post{
    always{
    	echo 'stopped and removing user-service-container'
    	sh "docker stop ${DOCKER_TMP_CONTAINER_NAME}"
    	sh "docker rmi ${DOCKER_IMG_NAME}:latest ${DOCKER_IMG_NAME}:${env.BUILD_ID}"
    	
    	}
    }
}