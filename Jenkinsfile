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
                script {
                    echo "Checking out code from repository in Jenkins workspace..."
                    checkout scm
                }
            }
        }
        stage('Build and SonarQube Analysis') {
            steps {
                script {
                    echo "Running Maven build and SonarQube analysis..."
                    sh """
                    mvn clean package sonar:sonar \
                      -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                      -Dsonar.host.url=$SONAR_HOST_URL \
                      -Dsonar.login=$SONAR_TOKEN
                    """
                }
            }
        }
        stage('Quality Gate Check') {
            steps {
                script {
                    echo "Checking SonarQube Quality Gate..."
                    def qualityGate = sh(
                        script: """
                        curl -s -u "$SONAR_TOKEN:" \
                        "$SONAR_HOST_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
                        | tee response.json | jq -r '.projectStatus.status'
                        """,
                        returnStdout: true
                    ).trim()
                    if (qualityGate != "OK") {
                        error "Quality Gate failed! Stopping the build."
                    }
                    echo "Quality Gate passed! Proceeding..."
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker Image..."
                    sh 'docker build -t springboot-app .'
                }
            }
        }
        stage('Deploy Docker Container') {
            steps {
                script {
                    echo "Deploying Docker container..."
                    sh """
                    docker stop springboot-app-container || true
                    docker rm springboot-app-container || true
                    docker run -d -p 8080:8080 --name springboot-app-container springboot-app
                    """
                }
            }
        }
    }
    post {
        success {
            echo "Pipeline executed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
        always {
            echo "Pipeline execution complete."
        }
    }
}
