/*
pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "huntersleague"
        SONAR_TOKEN = "sqp_4435a3e8e888de0c6ca5eef8a0369ed2008c1c0e"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
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
                    // Appel API pour récupérer le statut Quality Gate
                    def qualityGateStatus = sh(
                        script: """
                        curl -s -u "$SONAR_TOKEN:" \
                        "$SONAR_HOST_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
                        | grep -oP '(?<="status":")[^"]*' | head -n 1
                        """,
                        returnStdout: true
                    ).trim()

                    echo "SonarQube Quality Gate Status: ${qualityGateStatus}"

                    // Condition pour passer ou échouer le pipeline
                    if (qualityGateStatus == "OK") {
                        echo "Quality Gate passed successfully! ✅"
                    } else {
                        error "Quality Gate failed with status: ${qualityGateStatus}. ❌ Stopping the build."
                    }
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
 */



pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "huntersleague"
        SONAR_TOKEN = "sqp_4435a3e8e888de0c6ca5eef8a0369ed2008c1c0e"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        DOCKER_IMAGE = "huntersleague/springboot-app"
        DOCKER_TAG = "latest"
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

                    if (qualityGateStatus == "OK") {
                        echo "Quality Gate passed successfully! ✅ Proceeding to Dockerize the application..."
                    } else {
                        error "Quality Gate failed with status: ${qualityGateStatus}. ❌ Stopping the build."
                    }
                }
            }
        }

        stage('Dockerize Application') {
            when {
                expression { true } // This stage only runs if Quality Gate passed
            }
            steps {
                echo "Building Docker Image for the application..."
                sh """
                docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
                """
            }
        }

        stage('Run Docker Container') {
            steps {
                echo "Deploying Docker container..."
                sh """
                docker stop springboot-app-container || true
                docker rm springboot-app-container || true
                docker run -d -p 8080:8080 --name springboot-app-container $DOCKER_IMAGE:$DOCKER_TAG
                """
            }
        }
    }
    post {
        success {
            echo "🎉 Pipeline executed successfully! Application Dockerized and Running! 🎉"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs for more details. ❌"
        }
        always {
            echo "Pipeline execution complete. 🕒"
        }
    }
}
