pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        stage('Build Microservices') {
            steps {
                bat 'mvn clean package -DskipTests'  // Use bat instead of sh for Windows
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Login to Docker (if pushing to Docker Hub)
                    bat 'docker login -u YOUR_DOCKER_USER -p YOUR_DOCKER_PASSWORD'
                    
                    // Build images
                    bat 'docker-compose build'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop and remove existing containers
                    bat 'docker-compose down || exit 0'  // Continue even if no containers exist
                    
                    // Start new containers
                    bat 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed! Check logs.'
        }
    }
}