pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "Hunters-league"
        SONAR_TOKEN = "sqp_413cbcf4049a324d5a8814a6a893391de6b3d486"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        IMAGE_NAME = "springboot-app"
        CONTAINER_NAME = "springboot-app-container"
        HOST_PORT = "8443"
        APP_PORT = "8443"
    }
    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository..."
                checkout scm
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
                echo "Building Docker image..."
                sh """
                docker build -t ${IMAGE_NAME}:latest .
                """
            }
        }

        stage('Deploy Docker Container') {
            steps {
                echo "Stopping and removing any existing container using container ID..."
                sh """
                # Get the container ID by name (if it exists)
                CONTAINER_ID=\$(docker ps -aqf "name=${CONTAINER_NAME}")

                # Stop the container if it exists
                if [ ! -z "\$CONTAINER_ID" ]; then
                    echo "Stopping container ID: \$CONTAINER_ID"
                    docker stop \$CONTAINER_ID || true

                    echo "Removing container ID: \$CONTAINER_ID"
                    docker rm \$CONTAINER_ID || true
                else
                    echo "No container found with the name ${CONTAINER_NAME}."
                fi

                # Run a new container
                echo "Running a new container..."
                docker run -d -p ${HOST_PORT}:${APP_PORT} --name ${CONTAINER_NAME} ${IMAGE_NAME}:latest
                """
            }
        }
    }
    post {
        success {
            echo "🎉 Pipeline executed successfully! Application deployed on port ${HOST_PORT}. 🎉"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs for more details. ❌"
        }
        always {
            echo "Pipeline execution complete. 🕒"
        }
    }
}
