pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "huntersleague"
        SONAR_TOKEN = "sqp_4435a3e8e888de0c6ca5eef8a0369ed2008c1c0e"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        DOCKER_IMAGE = "huntersleague/springboot-app"
        DOCKER_TAG = "latest"
        CONTAINER_NAME = "hunters-league"
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
                mvn clean package sonar:sonar -X \
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

        stage('Rebuild Docker Image') {
            steps {
                echo "Rebuilding Docker Image for the application..."
                sh """
                docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
                """
            }
        }

        stage('Redeploy Application') {
            steps {
                echo "Stopping and removing the existing container..."
                sh """
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                """

                echo "Running a new container with the latest image..."
                sh """
                docker run -d -p 7000:7000 --name $CONTAINER_NAME $DOCKER_IMAGE:$DOCKER_TAG
                """
            }
        }
    }
    post {
        success {
            echo "🎉 Pipeline executed successfully! The application has been rebuilt and redeployed. 🎉"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs for more details. ❌"
        }
        always {
            echo "Pipeline execution complete. 🕒"
        }
    }
}
