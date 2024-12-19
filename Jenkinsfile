pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "Hunters-league"
        SONAR_TOKEN = "sqp_413cbcf4049a324d5a8814a6a893391de6b3d486"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        DOCKER_IMAGE = "nabilhakimi/hunters-league"
        DOCKER_CREDENTIALS_ID = "docker-hub-credentials" // Set this in Jenkins credentials
    }
    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository in Jenkins workspace..."
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
                echo "Building Docker image for the application..."
                sh """
                docker build -t ${DOCKER_IMAGE}:latest .
                """
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                echo "Pushing Docker image to Docker Hub..."
                withDockerRegistry([credentialsId: DOCKER_CREDENTIALS_ID, url: '']) {
                    sh """
                    docker push ${DOCKER_IMAGE}:latest
                    """
                }
            }
        }
    }
    post {
        success {
            echo "🎉 Pipeline executed successfully! 🎉"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs for more details. ❌"
        }
        always {
            echo "Pipeline execution complete. 🕒"
        }
    }
}
