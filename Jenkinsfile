pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = "Hunters-league"
        SONAR_TOKEN = "sqp_413cbcf4049a324d5a8814a6a893391de6b3d486"
        SONAR_HOST_URL = "http://host.docker.internal:9000"
        NEW_VERSION = "tagname"
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
                docker build -t nabilhakimi/hunters-league:${env.NEW_VERSION} .
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    echo "Pushing Docker image to Docker Hub..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_CREDENTIALS_USR', passwordVariable: 'DOCKERHUB_CREDENTIALS_PSW')]) {
                        sh '''
                        echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                        docker push nabilhakimi/hunters-league:${env.NEW_VERSION}
                        '''
                    }
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    echo "Deploying Docker container on port 8443..."
                    sh '''
                    docker stop springboot-app-container || true
                    docker rm springboot-app-container || true
                    docker run -d -p 8443:8080 --name springboot-app-container springboot-app
                    '''
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
