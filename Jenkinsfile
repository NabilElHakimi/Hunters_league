pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "Hunters-league"
        SONAR_TOKEN = "sqp_413cbcf4049a324d5a8814a6a893391de6b3d486"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        IMAGE_NAME = "hunters-league"
        DOCKERHUB_REPO = "nabilhakimi/hunters-league"
        TAG_NAME = "tagname"
        CONTAINER_NAME = "springboot-app-container"
        HOST_PORT = "8443"
        APP_PORT = "8443"
    }
    stages {

        stage('Test Docker Access') {
            steps {
                echo "Testing Docker access from Jenkins..."
                sh '''
                docker --version
                docker ps
                '''
            }
        }

        stage('Build and SonarQube Analysis') {
            steps {
                echo "Running Maven build and SonarQube analysis..."
                sh """
                mvn clean package sonar:sonar \
                  -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                  -Dsonar.host.url=$SONAR_HOST_URL \
                  -Dsonar.login=$SONAR_TOKEN
                """
            }
        }

        stage('Quality Gate Check') {
            steps {
                echo "Checking SonarQube Quality Gate..."
                script {
                    def qualityGateStatus = sh(
                        script: """
                        curl -s -u "$SONAR_TOKEN:" \
                        "$SONAR_HOST_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
                        | grep -oP '(?<="status":")[^"]*' | head -n 1
                        """,
                        returnStdout: true
                    ).trim()

                    echo "SonarQube Quality Gate Status: ${qualityGateStatus}"

                    if (qualityGateStatus != "OK") {
                        error "Quality Gate failed with status: ${qualityGateStatus}. ❌ Stopping the build."
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image for the application..."
                sh '''
                docker build -t hunters-league .
                '''
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                echo "Pushing Docker image to Docker Hub..."
                sh '''
                echo Hakimi6714@ | docker login -u nabilhakimi --password-stdin
                docker tag hunters-league nabilhakimi/hunters-league:tagname
                docker push nabilhakimi/hunters-league:tagname
                '''
            }
        }

        stage('Deploy Docker Container') {
            steps {
                echo "Stopping and removing any existing container..."
                sh '''
                # Get the container ID by exact name (if it exists)
                CONTAINER_ID=$(docker ps -aqf "name=^springboot-app-container$")

                # Stop the container if it exists
                if [ ! -z "$CONTAINER_ID" ]; then
                    echo "Stopping container ID: $CONTAINER_ID"
                    docker stop $CONTAINER_ID || true

                    echo "Removing container ID: $CONTAINER_ID"
                    docker rm $CONTAINER_ID || true
                else
                    echo "No container found with the name springboot-app-container."
                fi

                # Run a new container
                echo "Running a new container..."
                docker run -d -p 8443:8443 --name springboot-app-container hunters-league
                '''
            }
        }
    }
    post {
        success {
            echo "🎉 Pipeline executed successfully! Application deployed on port 8443. 🎉"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs for more details. ❌"
        }
        always {
            echo "Pipeline execution complete. 🕒"
        }
    }
}